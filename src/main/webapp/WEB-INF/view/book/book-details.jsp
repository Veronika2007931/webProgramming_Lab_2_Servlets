<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Деталі книги: ${book.title}</title>
    <style>
        body { font-family: 'Segoe UI', sans-serif; background-color: #f4f7f6; padding: 40px; color: #333; }
        .details-card { max-width: 650px; background: white; margin: 0 auto; border-radius: 12px; box-shadow: 0 4px 20px rgba(0,0,0,0.08); overflow: hidden; }
        .card-header { background-color: #2c3e50; color: white; padding: 25px; }
        .card-header h2 { margin: 0; font-size: 26px; }
        .card-header p { margin: 5px 0 0 0; opacity: 0.8; font-style: italic; }
        .card-body { padding: 30px; }
        .info-row { display: flex; margin-bottom: 15px; border-bottom: 1px solid #f0f0f0; padding-bottom: 10px; }
        .info-label { width: 30%; font-weight: bold; color: #7f8c8d; }
        .info-value { width: 70%; color: #2c3e50; }
        .reader-box { background: #ebf5fb; border-left: 5px solid #3498db; padding: 15px; border-radius: 4px; margin-top: 20px; }
        .btn-back { display: inline-block; background: #7f8c8d; color: white; text-decoration: none; padding: 10px 20px; border-radius: 6px; font-weight: bold; margin-top: 20px; }
        .btn-back:hover { background: #95a5a6; }
    </style>
</head>
<body>

    <div class="details-card">
        <div class="card-header">
            <h2>📖 ${book.title}</h2>
            <p>Автор: ${book.author}</p>
        </div>
        <div class="card-body">
            <div class="info-row">
                <div class="info-label">Жанр:</div>
                <div class="info-value">${not empty book.genre ? book.genre : 'Не вказано'}</div>
            </div>
            <div class="info-row">
                <div class="info-label">Рік видання:</div>
                <div class="info-value">${book.publishingYear != 0 ? book.publishingYear : 'Не вказано'}</div>
            </div>
            <div class="info-row">
                <div class="info-label">Опис:</div>
                <div class="info-value">${not empty book.description ? book.description : 'Опис відсутній.'}</div>
            </div>

            <div class="reader-box">
                <h3>👤 Інформація про читача:</h3>
                <c:choose>
                    <c:when test="${not empty book.reader}">
                        <p><strong>Повне ім'я:</strong> ${book.reader.fullName}</p>
                        <p><strong>Формуляр читача (ID):</strong> #000${book.reader.id}</p>
                        <p style="color: #2980b9; font-size: 13px;">* Книга знаходиться на руках у цього користувача.</p>
                    </c:when>
                    <c:otherwise>
                        <p style="color: #27ae60; font-weight: bold;">🟢 Книга зараз вільна! Вона знаходиться на полиці бібліотеки.</p>
                    </c:otherwise>
                </c:choose>
            </div>

            <a href="${pageContext.request.contextPath}/books" class="btn-back">← Назад до бібліотеки</a>
        </div>
    </div>

</body>
</html>