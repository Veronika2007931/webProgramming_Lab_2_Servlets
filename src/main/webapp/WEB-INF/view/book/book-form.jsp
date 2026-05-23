<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Додати книгу</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; background-color: #f9f9f9; }
        .form-container { background: white; padding: 30px; border-radius: 8px; max-width: 500px; box-shadow: 0 0 10px rgba(0,0,0,0.1); }
        .form-group { margin-bottom: 15px; }
        label { display: block; margin-bottom: 5px; font-weight: bold; }
        input[type="text"], textarea, select { width: 100%; padding: 10px; border: 1px solid #ccc; border-radius: 4px; box-sizing: border-box; }
        .btn-submit { background-color: #4CAF50; color: white; border: none; padding: 12px 20px; border-radius: 4px; cursor: pointer; font-size: 16px; }
        .btn-cancel { background-color: #9e9e9e; color: white; padding: 12px 20px; border-radius: 4px; text-decoration: none; margin-left: 10px; font-size: 16px; display: inline-block; }
    </style>
</head>
<body>

    <div class="form-container">
        <h2>Додати нову книгу до системи</h2>
        
        <form action="${pageContext.request.contextPath}/books/addBook" method="POST">
            
            <div class="form-group">
                <label for="title">Назва книги:</label>
                <input type="text" id="title" name="title" required placeholder="Наприклад, Гіперіон">
            </div>

            <div class="form-group">
                <label for="author">Автор:</label>
                <input type="text" id="author" name="author" required placeholder="Ден Сіммонс">
            </div>

            <div class="form-group">
                <label for="description">Короткий опис:</label>
                <textarea id="description" name="description" rows="4" placeholder="Опис сюжету..."></textarea>
            </div>

            <div class="form-group">
                <label for="readerId">Прив'язати до читача:</label>
                <select id="readerId" name="readerId">
                    <option value="">-- Залишити вільну книгу --</option>
                    <c:forEach var="reader" items="${readers}">
                        <option value="${reader.id}">${reader.fullName}</option>
                    </c:forEach>
                </select>
            </div>

            <button type="submit" class="btn-submit">Зберегти книгу</button>
            <a href="${pageContext.request.contextPath}/books" class="btn btn-cancel">Скасувати</a>
        </form>
    </div>

</body>
</html>