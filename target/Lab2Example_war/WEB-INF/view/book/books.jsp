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
        .btn {
            display: inline-block;       
            padding: 6px 12px;           
            font-size: 13px;             
            font-weight: 600;
            text-decoration: none;
            border-radius: 4px;
            color: white;
            text-align: center;
            vertical-align: middle;
            margin: 2px;                 
            cursor: pointer;
        }
        .btn-edit {
            background-color: #3498db;
        }
        .btn-delete {
            background-color: #e74c3c; /* Гарний червоний колір для кошика */
        }
        .btn-details {
            background-color: #34495e; /* Акуратний темний колір для кнопки Деталі */
        }
        .btn-add { background-color: #4CAF50; margin-bottom: 20px; display: inline-block; }
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
                    
                    <td>
                        <a href="${pageContext.request.contextPath}/books/details?id=${book.id}" style="font-weight: bold; color: #2c3e50; text-decoration: none;">
                            <c:out value="${book.title}"/>
                        </a>
                    </td>
                    
                    <td><c:out value="${book.author}"/></td>
                    
                    <td>
                        <c:out value="${not empty book.description ? book.description : 'Немає опису'}"/>
                    </td>
                    
                    <td>
                        <c:choose>
                            <c:when test="${not empty book.reader}">
                                <span style="color: #2196F3; font-weight: bold;">👤 <c:out value="${book.reader.fullName}"/></span>
                            </c:when>
                            <c:otherwise>
                                <span style="color: #9e9e9e; font-style: italic;">Вільна</span>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    
                    <td>
                        <a href="${pageContext.request.contextPath}/books/details?id=${book.id}" 
                           class="btn btn-details">Деталі</a>

                        <a href="${pageContext.request.contextPath}/books/addBook?id=${book.id}" 
                           class="btn btn-edit">Редагувати</a>

                        <a href="${pageContext.request.contextPath}/books/delete?id=${book.id}" 
                           class="btn btn-delete" 
                           onclick="return confirm('Ви впевнені, що хочете видалити цю книгу?')">🗑</a>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty books}">
                <tr>
                    <td colspan="6" style="text-align: center; color: #999;">У бібліотеці поки немає жодної книги.</td>
                </tr>
            </c:if>
        </tbody>
    </table>

</body>
</html>