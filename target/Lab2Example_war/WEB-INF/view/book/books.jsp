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
            display: inline-block;       /* 🌟 Рятує від злипання та наповзання! */
            padding: 6px 12px;           /* Зменшуємо внутрішні відступи, щоб кнопки стали меншими */
            font-size: 13px;             /* Робимо шрифт акуратнішим */
            font-weight: 600;
            text-decoration: none;
            border-radius: 4px;
            color: white;
            text-align: center;
            vertical-align: middle;
            margin: 2px;                 /* Додаємо невеликий відступ між кнопками з усіх боків */
            cursor: pointer;
        }
        .btn-edit {
            background-color: #3498db;
        }

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
                    <a href="${pageContext.request.contextPath}/books/addBook?id=${book.id}" 
                    class="btn btn-edit" style="background-color: #3498db; margin-right: 5px;">Редагувати</a>

                    <a href="${pageContext.request.contextPath}/books/delete?id=${book.id}" 
                    class="btn btn-delete" 
                    onclick="return confirm('Ви впевнені, що хочете видалити цю книгу?')">🗑</a>
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