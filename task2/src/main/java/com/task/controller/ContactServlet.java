package com.task.controller;

import com.task.model.Contact;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@WebServlet(urlPatterns = {"/contacts", "/contacts/add"})
public class ContactServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Initialize In-Memory Session Storage if it doesn't exist
        HttpSession session = request.getSession(true);
        if (session.getAttribute("contacts") == null) {
            session.setAttribute("contacts", new CopyOnWriteArrayList<Contact>());
        }

        String path = request.getServletPath();
        if (path == null) path = "/contacts";

        if ("/contacts/add".equals(path)) {
            // Forward to the Contact Creation form
            request.getRequestDispatcher("/WEB-INF/views/contact-form.jsp").forward(request, response);
        } else if ("/contacts".equals(path) || "/".equals(path)) {
            // Default to /contacts list view
            request.getRequestDispatcher("/WEB-INF/views/contact-list.jsp").forward(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}