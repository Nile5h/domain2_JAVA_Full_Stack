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
import java.util.List;

@WebServlet(urlPatterns = {"/contacts", "/contacts/add"})
public class ContactServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ContactDAO contactDAO;

    @Override
    public void init() {
        this.contactDAO = new ContactDAOImpl(DBUtil.getDataSource());
    }

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
        
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");

        Contact newContact = new Contact(null, name, email, phone);

        try {
            contactDAO.addContact(newContact);
            // Use Post-Redirect-Get pattern to prevent duplicate submissions
            response.sendRedirect(request.getContextPath() + "/contacts");
        } catch (SQLException e) {
            request.setAttribute("errorMessage", "Error saving contact: " + e.getMessage());
            request.getRequestDispatcher("/WEB-INF/views/contact-form.jsp").forward(request, response);
        }
    }
}