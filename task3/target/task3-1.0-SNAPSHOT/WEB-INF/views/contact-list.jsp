<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Contact Directory</title>
    <style>
        body { font-family: 'Segoe UI', Arial, sans-serif; background-color: #f4f6f9; margin: 0; padding: 40px; position: relative; min-height: 90vh; }
        .container { max-width: 900px; margin: 0 auto; background: white; padding: 30px; border-radius: 8px; box-shadow: 0 4px 15px rgba(0,0,0,0.05); }
        .header-section { display: flex; justify-content: space-between; align-items: center; margin-bottom: 20px; }
        h2 { margin: 0; color: #333; }
        
        /* Search Box styling */
        .search-box { width: 100%; max-width: 300px; padding: 8px 12px; border: 1px solid #ccc; border-radius: 4px; font-size: 14px; }
        
        /* Table Styles */
        table { width: 100%; border-collapse: collapse; margin-top: 15px; text-align: left; }
        th, td { padding: 12px 15px; border-bottom: 1px solid #ddd; }
        th { background-color: #f8f9fa; color: #555; }
        tr:hover { background-color: #fdfdfd; }
        
        /* Toast Notification styling */
        .toast { background-color: #28a745; color: white; padding: 12px 25px; position: fixed; top: 20px; right: 20px; border-radius: 4px; box-shadow: 0 4px 12px rgba(0,0,0,0.15); z-index: 1000; animation: fadeOut 4s forwards; }
        @keyframes fadeOut { 0% { opacity: 1; } 80% { opacity: 1; } 100% { opacity: 0; visibility: hidden; } }
        
        .empty-placeholder { text-align: center; padding: 40px; color: #777; font-style: italic; }
        
        /* Floating Add Button */
        .floating-add-btn { position: fixed; bottom: 40px; right: 40px; width: 60px; height: 60px; background-color: #007bff; color: white; border-radius: 50%; text-align: center; font-size: 32px; line-height: 56px; text-decoration: none; box-shadow: 0 5px 15px rgba(0,123,255,0.4); transition: transform 0.2s; }
        .floating-add-btn:hover { transform: scale(1.1); background-color: #0056b3; }
    </style>
</head>
<body>

<c:if test="${not empty sessionScope.successMessage}">
    <div class="toast" id="successToast">
        <c:out value="${sessionScope.successMessage}"/>
    </div>
    <c:remove var="successMessage" scope="session" />
</c:if>

<div class="container">
    <div class="header-section">
        <h2>Contact Directory</h2>
        <input type="text" id="searchInput" class="search-box" onkeyup="filterContacts()" placeholder="Search contacts by name...">
    </div>

    <table id="contactTable">
        <thead>
            <tr>
                <th>Name</th>
                <th>Email</th>
                <th>Phone</th>
            </tr>
        </thead>
        <tbody>
            <c:choose>
                <c:when test="${not empty sessionScope.contacts}">
                    <c:forEach var="contact" items="${sessionScope.contacts}">
                        <tr class="contact-row">
                            <td><c:out value="${contact.name}"/></td>
                            <td><c:out value="${contact.email}"/></td>
                            <td>
                                <c:choose>
                                    <c:when test="${not empty contact.phone}">
                                        <c:out value="${contact.phone}"/>
                                    </c:when>
                                    <c:otherwise><span style="color:#ccc;">—</span></c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <tr id="emptyRow">
                        <td colspan="3" class="empty-placeholder">No contacts available. Click the '+' button to add one.</td>
                    </tr>
                </c:otherwise>
            </c:choose>
        </tbody>
    </table>
</div>

<a href="${pageContext.request.contextPath}/contacts/add" class="floating-add-btn" title="Add New Contact">+</a>

<script>
    // Search-as-you-type client functionality logic
    function filterContacts() {
        const input = document.getElementById('searchInput');
        const filter = input.value.toLowerCase();
        const table = document.getElementById('contactTable');
        const rows = table.getElementsByClassName('contact-row');
        let visibleCount = 0;

        for (let i = 0; i < rows.length; i++) {
            let nameCol = rows[i].getElementsByTagName('td')[0];
            if (nameCol) {
                let txtValue = nameCol.textContent || nameCol.innerText;
                if (txtValue.toLowerCase().indexOf(filter) > -1) {
                    rows[i].style.display = "";
                    visibleCount++;
                } else {
                    rows[i].style.display = "none";
                }
            }
        }
    }
</script>
</body>
</html>