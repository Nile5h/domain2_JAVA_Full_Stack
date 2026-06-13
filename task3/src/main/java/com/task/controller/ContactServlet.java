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
        contactDAO = new ContactDAOImpl(DBUtil.getDataSource());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {        
        String path = request.getServletPath();
        if (path == null) path = "/contacts";

        if ("/contacts/add".equals(path)) {
            request.getRequestDispatcher("/WEB-INF/views/contact-form.jsp").forward(request, response);
        } else if ("/contacts".equals(path) || "/".equals(path)) {
            try {
                String search = request.getParameter("search");
                List<Contact> contacts = contactDAO.searchContacts(search);
                request.setAttribute("contacts", contacts);
                request.getRequestDispatcher("/WEB-INF/views/contact-list.jsp").forward(request, response);
            } catch (SQLException e) {
                throw new ServletException("Database error", e);
            }
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
