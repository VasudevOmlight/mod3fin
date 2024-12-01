<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Text Quest</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div class="background">
    <div class="frame">
        <div class="header bg-info text-white">
            <h1>ВИБІР КВЕСТУ</h1>
        </div>
        <div class="content">
            <h1 class="text-center text-primary fw-bold">Здійсни пошук та обери квест із меню:</h1>
            <!-- Кнопка "ШУКАТИ КВЕСТИ" теперь зеленая -->
            <form action="choiceServlet" method="post" class="d-flex flex-column align-items-center">
                <input type="hidden" name="action" value="searchQuests">
                <button class="btn btn-info btn-lg" type="submit">ШУКАТИ КВЕСТИ</button>
            </form>
            <!-- Кнопка "ВИБРАТИ КВЕСТ" теперь синяя -->
            <form action="choiceServlet" method="post" class="d-flex flex-column align-items-center">
                <input type="hidden" name="action" value="selectQuest">
                <select name="selectedQuest" class="form-select my-3" size="5" required>
                    <c:forEach var="entry" items="${quests}">
                        <option value="${entry.key}">${entry.value}</option>
                    </c:forEach>
                </select>
                <button class="btn btn-info btn-lg" type="submit">ВИБРАТИ КВЕСТ</button>
            </form>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

