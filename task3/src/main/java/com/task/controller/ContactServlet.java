package com.task.controller;

import com.task.dao.ContactDAO;
import com.task.dao.ContactDAOImpl;
import com.task.model.Contact;
import com.task.util.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@WebServlet(urlPatterns = {"/contacts", "/contacts/add", "/contacts/submit"})
public class ContactServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ContactDAO contactDAO;

    @Override
    public void init() {
        this.contactDAO = new ContactDAOImpl(DBUtil.getDataSource());
    }

    // Validation Regular Expressions
    private static final Pattern EMAIL_REGEX = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
    private static final Pattern PHONE_REGEX = Pattern.compile("^\\d{10}$");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String path = request.getServletPath();
        
        if ("/contacts/add".equals(path)) {
            request.getRequestDispatcher("/WEB-INF/views/contact-form.jsp").forward(request, response);
        } else {
            try {
                String searchTerm = request.getParameter("search");
                List<Contact> contacts;
                
                if (searchTerm != null && !searchTerm.trim().isEmpty()) {
                    contacts = contactDAO.searchContacts(searchTerm);
                } else {
                    contacts = contactDAO.getAllContacts();
                }
                
                request.setAttribute("contacts", contacts);
                request.getRequestDispatcher("/WEB-INF/views/contact-list.jsp").forward(request, response);
            } catch (SQLException e) {
                throw new ServletException("Error retrieving contacts from database", e);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // 1. Input Sanitization
        String name = request.getParameter("name") != null ? request.getParameter("name").trim() : "";
        String email = request.getParameter("email") != null ? request.getParameter("email").trim() : "";
        String phone = request.getParameter("phone") != null ? request.getParameter("phone").trim() : "";

        // 2. Server-side Validation
        Map<String, String> errors = new HashMap<>();
        if (name.isEmpty() || name.length() < 2 || name.length() > 50) {
            errors.put("name", "Please enter a valid name (2-50 characters)");
        }
        if (email.isEmpty() || !EMAIL_REGEX.matcher(email).matches()) {
            errors.put("email", "Invalid email address");
        }
        if (!phone.isEmpty() && !PHONE_REGEX.matcher(phone).matches()) {
            errors.put("phone", "Use 10-digit format");
        }

        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
            request.setAttribute("presetName", name);
            request.getRequestDispatcher("/WEB-INF/views/contact-form.jsp").forward(request, response);
            return;
        }

        Contact newContact = new Contact(null, name, email, phone);

        try {
            contactDAO.addContact(newContact);
            // Use Post-Redirect-Get pattern to prevent duplicate submissions
            response.sendRedirect(request.getContextPath() + "/contacts");
        } catch (SQLException e) {
            if ("23000".equals(e.getSQLState())) {
                errors.put("email", "Email already exists");
                request.setAttribute("errors", errors);
                request.getRequestDispatcher("/WEB-INF/views/contact-form.jsp").forward(request, response);
                return;
            }
            request.setAttribute("errorMessage", "Error saving contact: " + e.getMessage());
            request.getRequestDispatcher("/WEB-INF/views/contact-form.jsp").forward(request, response);
        }
    }
}