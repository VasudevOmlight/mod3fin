package model.service;
import jakarta.servlet.http.HttpSession;
import utils.UserStorage;
import model.entities.User;

// Класс для инициализации пользователя в текущей сессии.
public class UserInit {

    /**
     * Добавляет пользователя в текущую сессию. Если пользователь не существует, он будет создан.
     *
     * @param session  текущая HTTP-сессия
     * @param userName имя пользователя
     */
    public static void addUser(HttpSession session, String userName) {
        // Получаем или создаём пользователя с указанным именем
        User user = UserStorage.getUser(userName);

        // Устанавливаем данные пользователя в сессии
        session.setAttribute("userId", user.getUserId());
        session.setAttribute("userName", user.getUserName());
        session.setAttribute("winCounter", user.getWins());
        session.setAttribute("loseCounter", user.getLosses());
        session.setAttribute("attemptCounter", user.getAttempts());
    }
}
