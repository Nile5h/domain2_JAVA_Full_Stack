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

@WebServlet(urlPatterns = {"/contacts", "/contacts/add"})
public class ContactServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ContactDAO contactDAO;

    @Override
    public void init() {
        this.contactDAO = new ContactDAOImpl(DBUtil.getDataSource());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath().substring("/contacts".length());
        if ("add".equals(action)) {
            // Handle add contact logic
        } else if ("".equals(action)) {
            // Handle list contacts logic
            List<Contact> contacts = null;
            try{
                contacts = contactDAO.getAllContacts();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            request.setAttribute("contacts", contacts);
            request.getRequestDispatcher("/contact-list.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath().substring("/contacts".length());
        if ("add".equals(action)) {
            // Handle add contact logic
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");

            Contact contact = new Contact(null, name, email, phone);
            try {
                contactDAO.addContact(contact);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            response.sendRedirect("/contacts"); // Redirect to contacts page
        }
    }
}