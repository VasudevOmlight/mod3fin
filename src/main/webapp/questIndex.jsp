<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="utils.IndexTextManager" %>
<%@ page import="model.entities.IndexText" %>

<%
    // Отримуємо текстові рядки
    IndexText indexText = IndexTextManager.getIndexText();
%>

<html>
<head>
    <title>Text Quest</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <script>
        document.addEventListener('DOMContentLoaded', () => {
            const nameInput = document.querySelector('input[name="firstName"]');
            const startButton = document.querySelector('button[type="submit"]');

            // Функція перевірки імені
            const validateName = () => {
                const name = nameInput.value.trim();
                startButton.disabled = name.length === 0; // Блокируем кнопку, если имя некорректно
            };

            // Перевірки при кожному зміненні поля
            nameInput.addEventListener('input', validateName);

            // Початкове блокування кнопки
            validateName();
        });
    </script>
</head>
<body>
<div class="background">
    <!-- Рамка, що об'єднує весь контент -->
    <div class="frame">
        <!-- Включення хедера у рамці -->
        <div class="header bg-info text-white">
            <h1><%= indexText.getHeaderText() %></h1>
        </div>
        <!-- Головний контент сторінки -->
        <div class="content">
            <h1 class="text-center text-primary fw-bold"><%= indexText.getMainText() %></h1>
            <form action="gameServlet" method="post" class="d-flex flex-column align-items-center">
                <input type="text" name="firstName" class="form-control my-3" placeholder="Ваше ім'я">
                <button class="btn btn-info btn-lg" type="submit" disabled>Почати квест</button>
            </form>
        </div>
        <!-- Включення футера всередині рамки -->
        <div class="footer bg-info text-white">
            <p class="fs-6">ІМ'Я: ${sessionScope.userName}</p>
            <p class="fs-6">КІЛЬКІСТЬ СПРОБ: ${sessionScope.userId}</p>
            <p class="fs-6">ПЕРЕМОГ: ${sessionScope.winCounter}</p>
            <p class="fs-6">ПОРАЗОК: ${sessionScope.loseCounter}</p>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

