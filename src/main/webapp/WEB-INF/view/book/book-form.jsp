<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${not empty book.id ? 'Редагувати книгу' : 'Додати книгу'}</title>
    <style>
        body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background-color: #f9f9f9; color: #333; padding: 40px; }
        .form-container { max-width: 500px; background: white; padding: 30px; border-radius: 10px; box-shadow: 0 4px 15px rgba(0,0,0,0.05); margin: 0 auto; }
        h2 { color: #2c3e50; margin-bottom: 20px; font-size: 24px; }
        .form-group { margin-bottom: 15px; }
        .form-group label { display: block; margin-bottom: 5px; font-weight: 600; color: #444; }
        .form-group input, .form-group textarea, .form-group select { width: 100%; padding: 10px; border: 1px solid #ccc; border-radius: 6px; font-size: 14px; box-sizing: border-box; }
        .error-box { background-color: #fde8e8; color: #e74c3c; border: 1px solid #f8b4b4; padding: 12px; border-radius: 6px; margin-bottom: 20px; font-weight: bold; }
        .btn-submit { background-color: #5cb85c; color: white; border: none; padding: 10px 20px; border-radius: 6px; font-size: 16px; cursor: pointer; font-weight: bold; }
        .btn-submit:hover { background-color: #4cae4c; }
        .btn-cancel { color: #7f8c8d; text-decoration: none; margin-left: 15px; font-size: 15px; }
        .btn-cancel:hover { text-decoration: underline; }
    </style>
</head>
<body>

    <div class="form-container">
        <h2>${not empty book.id ? '📝 Редагувати книгу' : '📚 Додати нову книгу'}</h2>
        
        <c:if test="${not empty errorMessage}">
            <div class="error-box">${errorMessage}</div>
        </c:if>
        
        <form action="${pageContext.request.contextPath}/books/addBook" method="POST">
            <input type="hidden" name="id" value="${book.id}">
            
            <div class="form-group">
                <label for="title">Назва книги:</label>
                <input type="text" id="title" name="title" value="<c:out value='${book.title}'/>">
            </div>

            <div class="form-group">
                <label for="author">Автор:</label>
                <input type="text" id="author" name="author" value="<c:out value='${book.author}'/>">
            </div>

            <div class="form-group">
                <label for="description">Короткий опис:</label>
                <textarea id="description" name="description" rows="4"><c:out value='${book.description}'/></textarea>
            </div>

            <div class="form-group">
                <label for="readerId">Прив'язати до читача:</label>
                <select id="readerId" name="readerId">
                    <option value="">-- Залишити вільну книгу --</option>
                    <c:forEach var="reader" items="${readers}">
                        <option value="${reader.id}" ${book.reader.id == reader.id ? 'selected' : ''}>
                            ${reader.fullName}
                        </option>
                    </c:forEach>
                </select>
            </div>

            <button type="submit" class="btn-submit">Зберегти</button>
            <a href="${pageContext.request.contextPath}/books" class="btn-cancel">Скасувати</a>
        </form>
    </div>

</body>
</html>