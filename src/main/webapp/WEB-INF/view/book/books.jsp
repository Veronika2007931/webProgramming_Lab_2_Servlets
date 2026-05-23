<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Бібліотека — Список книг</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; background-color: #f9f9f9; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; background: white; }
        th, td { border: 1px solid #ddd; padding: 12px; text-align: left; }
        th { background-color: #4CAF50; color: white; }
        tr:nth-child(even) { background-color: #f2f2f2; }
        .btn { padding: 8px 12px; text-decoration: none; color: white; border-radius: 4px; font-size: 14px; }
        .btn-add { background-color: #4CAF50; margin-bottom: 20px; display: inline-block; }
        .btn-delete { background-color: #f44336; }
    </style>
</head>
<body>

    <h2>📚 Моя Бібліотека</h2>
    
    <a href="${pageContext.request.contextPath}/books/addBook" class="btn btn-add">+ Додати нову книгу</a>

    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Назва книги</th>
                <th>Автор</th>
                <th>Опис</th>
                <th>Хто читає</th>
                <th>Дії</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="book" items="${books}">
                <tr>
                    <td>${book.id}</td>
                    <td><strong>${book.title}</strong></td>
                    <td>${book.author}</td>
                    <td>${book.description}</td>
                    <td>
                        <c:choose>
                            <c:when test="${not empty book.reader}">
                                <span style="color: #2196F3;">👤 ${book.reader.fullName}</span>
                            </c:when>
                            <c:otherwise>
                                <span style="color: #9e9e9e; font-style: italic;">Вільна</span>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/books/delete?id=${book.id}" 
                           class="btn btn-delete" 
                           onclick="return confirm('Ви впевнені, що хочете видалити цю книгу?')">Видалити</a>
                    </td>
                </tr>
            </c:forEach>
            <c:if var="isEmpty" test="${empty books}">
                <tr>
                    <td colspan="6" style="text-align: center; color: #999;">У бібліотеці поки немає жодної книги.</td>
                </tr>
            </c:if>
        </tbody>
    </table>

</body>
</html>