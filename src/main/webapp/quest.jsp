<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Text Quest</title>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <style>
        .blinking-text {
            color: green;
            animation: blink 1s step-end infinite;
        }

        @keyframes blink {
            50% {
                opacity: 0;
            }
        }
    </style>
    <script>
        document.addEventListener('DOMContentLoaded', () => {
            const victoryText = document.querySelector('.blinking-text');
            if (victoryText) {
                setTimeout(() => {
                    victoryText.style.animation = 'none'; // Stop blinking
                }, 3000); // Stop after 3 seconds
            }
        });
    </script>
</head>
<body>
<div class="background">
    <!-- Frame encapsulates all content -->
    <div class="frame">
        <!-- Include header inside the frame -->
        <div class="header bg-info text-white">
            <h1>ФІНАЛЬНЫЙ ПРОЕКТ ТРЕТЬОГО МОДУЛЯ</h1>
        </div>
        <!-- Main quest content -->
        <div class="content">
            <c:choose>
                <c:when test="${question != null}">
                    <h1 class="text-center">${question.getText()}</h1>
                    <div class="d-flex flex-column align-items-center">
                        <c:forEach var="i" begin="0" end="${question.getAnswerList().size() - 1}">
                            <button class="btn btn-info btn-lg my-2"
                                    onclick="window.location='/gameServlet?answerId=${i}'">
                                <c:out value="${question.getAnswerList().get(i).getText()}"/>
                            </button>
                        </c:forEach>
                    </div>
                </c:when>
                <c:otherwise>
                    <c:if test="${wrongAnswer != null}">
                        <h1>${wrongAnswer.getWrongAnswerEndText()}</h1>
                    </c:if>
                    <c:if test="${wrongAnswer == null}">
                        <h1>Успішне рішення. <span class="blinking-text">VICTORY!</span></h1>
                    </c:if>
                    <button onclick="window.location='/restart'" class="btn btn-danger btn-lg mt-4">Розпочати заново</button>
                </c:otherwise>
            </c:choose>
        </div>
        <!-- Include footer inside the frame -->
        <div class="footer bg-info text-white">
            <p class="fs-6">ІМ'Я : ${sessionScope.userName}</p>
            <p class="fs-6">КІЛЬКІСТЬ СПРОБ : ${sessionScope.winCounter + sessionScope.loseCounter}</p>
            <p class="fs-6">ПЕРЕМОГ : ${sessionScope.winCounter}</p>
            <p class="fs-6">ПОРАЗОК : ${sessionScope.loseCounter}</p>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

