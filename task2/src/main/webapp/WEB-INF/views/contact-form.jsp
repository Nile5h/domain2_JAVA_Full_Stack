<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add Contact | Contact Manager</title>
    <style>
        body { font-family: 'Segoe UI', Arial, sans-serif; background-color: #f4f6f9; margin: 0; padding: 40px; }
        .form-container { max-width: 500px; background: white; padding: 30px; border-radius: 8px; box-shadow: 0 4px 15px rgba(0,0,0,0.1); margin: 0 auto; }
        h2 { margin-top: 0; color: #333; }
        .form-group { margin-bottom: 20px; position: relative; }
        label { display: block; margin-bottom: 8px; font-weight: 600; color: #555; }
        input[type="text"], input[type="email"] { width: 100%; padding: 10px; border: 1px solid #ccc; border-radius: 4px; box-sizing: border-box; font-size: 14px; }
        input.invalid { border-color: #dc3545; background-color: #fff8f8; }
        .error-msg { color: #dc3545; font-size: 12px; margin-top: 5px; display: block; }
        .btn-submit { background-color: #007bff; color: white; border: none; padding: 12px 20px; border-radius: 4px; cursor: pointer; font-size: 16px; width: 100%; transition: background 0.2s; }
        .btn-submit:hover { background-color: #0056b3; }
        .back-link { display: inline-block; margin-top: 15px; color: #007bff; text-decoration: none; }
        /* Loading Spinner */
        .spinner { display: none; width: 20px; height: 20px; border: 3px solid #f3f3f3; border-top: 3px solid #007bff; border-radius: 50%; animation: spin 1s linear infinite; margin: 10px auto; }
        @keyframes spin { 0% { transform: rotate(0deg); } 100% { transform: rotate(360deg); } }
    </style>
</head>
<body>

<div class="form-container">
    <h2>Add New Contact</h2>
    
    <form id="contactForm" action="${pageContext.request.contextPath}/contacts/submit" method="POST" onsubmit="showLoading()">
        
        <div class="form-group">
            <label for="name">Name *</label>
            <input type="text" id="name" name="name" value="<c:out value='${presetName}'/>" 
                   class="${not empty errors.name ? 'invalid' : ''}" />
            <c:if test="${not empty errors.name}">
                <span class="error-msg">${errors.name}</span>
            </c:if>
        </div>

        <div class="form-group">
            <label for="email">Email *</label>
            <input type="email" id="email" name="email" value="<c:out value='${presetEmail}'/>" 
                   class="${not empty errors.email ? 'invalid' : ''}" />
            <c:if test="${not empty errors.email}">
                <span class="error-msg">${errors.email}</span>
            </c:if>
        </div>

        <div class="form-group">
            <label for="phone">Phone</label>
            <input type="text" id="phone" name="phone" value="<c:out value='${presetPhone}'/>" 
                   class="${not empty errors.phone ? 'invalid' : ''}" />
            <c:if test="${not empty errors.phone}">
                <span class="error-msg">${errors.phone}</span>
            </c:if>
        </div>

        <button type="submit" class="btn-submit">Save Contact</button>
        <div id="loadingSpinner" class="spinner"></div>
    </form>

    <a href="${pageContext.request.contextPath}/contacts" class="back-link">← Back to Contact List</a>
</div>

<script>
    // UX Quality Rule: Auto-focus first invalid input field
    window.addEventListener('DOMContentLoaded', () => {
        const firstInvalidInput = document.querySelector('input.invalid');
        if (firstInvalidInput) {
            firstInvalidInput.focus();
        } else {
            const firstInput = document.querySelector('input');
            if(firstInput) firstInput.focus();
        }
    });

    function showLoading() {
        document.getElementById('loadingSpinner').style.display = 'block';
    }
</script>
</body>
</html>