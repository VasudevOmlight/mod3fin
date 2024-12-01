package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

// Сервлет для инициализации сессии и перенаправления пользователя на стартовую страницу.
@WebServlet(name = "controller.InitServlet", value = "/start")
public class InitServlet extends HttpServlet {

     // Метод обработки GET-запросов. param req  запрос клиента. param resp ответ сервера.
    // throws ServletException если возникает ошибка при перенаправлении на JSP.
     // throws IOException , если возникает ошибка ввода/вывода.

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Создание новой сессии, если она еще не существует.
        req.getSession(true);

        // Перенаправление пользователя на стартовую страницу "index.jsp".
        getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
