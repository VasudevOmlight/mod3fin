package controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

// Сервлет для перезапуска игры, сброса сессии и перенаправления на стартовую страницу.
@WebServlet(name = "controller.RestartServlet", value = "/restart")
public class RestartServlet extends HttpServlet {

     // Метод обработки GET-запросов. param req - запрос клиента.  param resp - ответ сервера.
     // throws IOException если возникает ошибка ввода/вывода.
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // Инвалидируем текущую сессию, чтобы удалить все данные, связанные с пользователем.
        req.getSession().invalidate();

        // Перенаправляем пользователя на стартовую страницу.
        resp.sendRedirect("/start");
    }
}
