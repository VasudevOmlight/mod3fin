package model.service;

import model.entities.User;
import utils.UserStorage;

// Класс для управления статистикой пользователей. Предоставляет методы для обновления статистики и получения данных.
public class StatisticsService {

    // Инкрементирует количество побед у указанного пользователя. userName имя пользователя
    public void incrementWins(String userName) {
        User user = UserStorage.getUser(userName);
        user.incrementWins();
        UserStorage.updateUser(user);
    }

    // Инкрементирует количество поражений у указанного пользователя.
    public void incrementLosses(String userName) {
        User user = UserStorage.getUser(userName);
        user.incrementLosses();
        UserStorage.updateUser(user);
    }

    // Инкрементирует общее количество завершенных квестов у пользователя.
    public void incrementAttempts(String userName) {
        User user = UserStorage.getUser(userName);
        user.incrementAttempts();
        UserStorage.updateUser(user);
    }

    // Возвращает количество завершенных квестов для пользователя. return количество завершенных квестов
    public int getAttempts(String userName) {
        User user = UserStorage.getUser(userName);
        return user.getAttempts();
    }


    // Возвращает количество побед для пользователя. return количество побед
    public int getWins(String userName) {
        User user = UserStorage.getUser(userName);
        return user.getWins();
    }

    // Возвращает количество поражений для пользователя. return количество поражений
    public int getLosses(String userName) {
        User user = UserStorage.getUser(userName);
        return user.getLosses();
    }
}
