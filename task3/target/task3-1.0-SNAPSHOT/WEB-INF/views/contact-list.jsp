<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Contact List</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th, td { padding: 10px; text-align: left; border: 1px solid #ddd; }
        th { background-color: #f4f4f4; }
        .search-bar { margin-bottom: 20px; }
    </style>
</head>
<body>
    <h2>Persistent Contact Manager</h2>

    <!-- Search Functionality -->
    <div class="search-bar">
        <form action="${pageContext.request.contextPath}/contacts" method="get">
            <input type="text" name="search" placeholder="Search by name or email..." 
                   value="<c:out value='${param.search}'/>">
            <button type="submit">Search</button>
            <c:if test="${not empty param.search}">
                <a href="${pageContext.request.contextPath}/contacts">Clear Search</a>
            </c:if>
        </form>
    </div>

    <p><a href="${pageContext.request.contextPath}/contacts/add">Add New Contact</a></p>

    <table>
        <thead>
            <tr>
                <th>Name</th>
                <th>Email</th>
                <th>Phone</th>
                <th>Created At</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="contact" items="${contacts}">
                <tr>
                    <td><c:out value="${contact.name}"/></td>
                    <td><c:out value="${contact.email}"/></td>
                    <td><c:out value="${contact.phone}"/></td>
                    <td><c:out value="${contact.createdAt}"/></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>