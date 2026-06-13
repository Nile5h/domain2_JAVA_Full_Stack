package com.task.controller;

import com.task.model.Contact;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.regex.Pattern;

@WebServlet("/contacts/submit")
public class AddContactServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Validation Regular Expressions
    private static final Pattern EMAIL_REGEX = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
    private static final Pattern PHONE_REGEX = Pattern.compile("^\\d{10}$");

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Session expired. Please try again.");
            return;
        }

        // 1. Data Capture & Basic Input Sanitization (Trimming whitespace)
        String name = request.getParameter("name") != null ? request.getParameter("name").trim() : "";
        String email = request.getParameter("email") != null ? request.getParameter("email").trim() : "";
        String phone = request.getParameter("phone") != null ? request.getParameter("phone").trim() : "";

        Map<String, String> errors = new HashMap<>();

        // 2. Server-side Rules Validations
        if (name.isEmpty() || name.length() < 2 || name.length() > 50) {
            errors.put("name", "Please enter a valid name (2-50 characters)");
        }
        if (email.isEmpty() || !EMAIL_REGEX.matcher(email).matches()) {
            errors.put("email", "Invalid email address");
        }
        if (!phone.isEmpty() && !PHONE_REGEX.matcher(phone).matches()) {
            errors.put("phone", "Use 10-digit format");
        }

        // 3. Evaluate Validation Results
        if (!errors.isEmpty()) {
            // Preserve entered fields & pass errors back down to the view layer
            request.setAttribute("errors", errors);
            request.setAttribute("presetName", name);
            request.setAttribute("presetEmail", email);
            request.setAttribute("presetPhone", phone);
            
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 400 Bad Request Status
            request.getRequestDispatcher("/WEB-INF/views/contact-form.jsp").forward(request, response);
            return;
        }

        // 4. Persistence Execution (Session-bound List)
        @SuppressWarnings("unchecked")
        List<Contact> contactList = (List<Contact>) session.getAttribute("contacts");
        if (contactList == null) {
            contactList = new CopyOnWriteArrayList<>();
            session.setAttribute("contacts", contactList);
        }

        // Generate unique identifier and save the record
        String uniqueId = UUID.randomUUID().toString();
        Contact newContact = new Contact(uniqueId, name, email, phone);
        contactList.add(newContact);

        // 5. Success Path Redirection (Prevents Duplicate Form Submissions via PRG Pattern)
        session.setAttribute("successMessage", "Contact added successfully!");
        response.sendRedirect(request.getContextPath() + "/contacts");
    }
}
