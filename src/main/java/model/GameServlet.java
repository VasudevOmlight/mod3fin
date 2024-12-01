package model;

import model.entities.Answer;
import model.entities.Question;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utils.QuestionManager;
import java.io.IOException;

import utils.UserStorage;
import model.service.UserInit;

// Сервлет для обработки игрового процесса
@WebServlet(name = "GameServlet", value = "/gameServlet")
public class GameServlet extends HttpServlet {

    //Метод для обработки GET-запросов. Выполняет основную логику переходов между вопросами, учёта побед и поражений.

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession(); // Получение текущей сессии
        QuestionManager questionManager = new QuestionManager(); // Создание менеджера вопросов
        Question currentQuestion = getCurrentQuestion(req); // Получение текущего вопроса из сессии
        Question nextQuestion = null; // Следующий вопрос
        String userName = (String) session.getAttribute("userName"); // Имя пользователя

        // Если текущего вопроса нет, начинаем с первого вопроса
        if (currentQuestion == null) {
            nextQuestion = questionManager.getById(1);
        } else {
            // Проверяем ответ пользователя на текущий вопрос
            Answer currentAnswer = getCurrentAnswer(currentQuestion, req);
            if (currentAnswer != null && !currentAnswer.isWrongAnswer()) {
                // Если ответ правильный, загружаем следующий вопрос
                nextQuestion = questionManager.getById(currentQuestion.getId() + 1);
            } else {
                // Если ответ неправильный, фиксируем поражение
                session.setAttribute("wrongAnswer", currentAnswer);
                UserStorage.incrementLosses(userName); // Учёт поражения
            }
        }

        // Если вопросов больше нет, фиксируем победу
        if (nextQuestion == null && currentQuestion != null) {
            Answer currentAnswer = getCurrentAnswer(currentQuestion, req);
            if (currentAnswer != null && !currentAnswer.isWrongAnswer()) {
                UserStorage.incrementWins(userName); // Учёт победы
            }
        }

        // Сохраняем данные в сессии
        session.setAttribute("question", nextQuestion);
        session.setAttribute("winCounter", UserStorage.getUser(userName).getWins());
        session.setAttribute("loseCounter", UserStorage.getUser(userName).getLosses());
        session.setAttribute("attemptCounter", UserStorage.getUser(userName).getAttempts());
        resp.sendRedirect("/quest.jsp"); // Перенаправление на страницу квеста
    }


     // Метод для получения текущего вопроса из сессии. param req HTTP-запрос, return Текущий вопрос или null, если вопрос отсутствует

    private Question getCurrentQuestion(HttpServletRequest req) {
        try {
            return (Question) req.getSession().getAttribute("question");
        } catch (Exception e) {
            return null;
        }
    }

     // Метод для получения выбранного ответа на текущий вопрос. param currentQuestion Текущий вопрос, param req HTTP-запрос
     // return Выбранный ответ или null, если произошла ошибка

    private Answer getCurrentAnswer(Question currentQuestion, HttpServletRequest req) {
        try {
            int answerId = Integer.parseInt(req.getParameter("answerId")); // Получаем ID ответа из параметров запроса
            return currentQuestion.getAnswerList().get(answerId); // Возвращаем ответ по ID
        } catch (Exception e) {
            return null;
        }
    }

     // Метод для обработки POST-запросов.  Инициализирует новую игру для пользователя.

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8"); // Установка кодировки
        String userName = req.getParameter("firstName"); // Получение имени пользователя из запроса
        req.getSession().setAttribute("questId", "quest1"); // Установка идентификатора квеста
        UserInit.addUser(req.getSession(), userName); // Инициализация пользователя
        req.getSession().removeAttribute("question"); // Удаление старых данных квеста
        doGet(req, resp); // Переход к обработке через метод doGet
    }
}
