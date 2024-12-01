package utils;

import model.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class UserStorageTest {

    // Перед каждым тестом очищаем хранилище пользователей, чтобы тесты не зависели друг от друга
    @BeforeEach
    void setUp() {
        UserStorage.getAllUsers().clear();
    }

    // Тестируем метод getUser
    // Проверяем создание нового пользователя и возврат уже существующего
    @Test
    void testGetUser() {
        // Проверяем, что новый пользователь создается корректно
        User user = UserStorage.getUser("Alice");
        assertNotNull(user, "Пользователь не должен быть null");
        assertEquals("Alice", user.getUserName(), "Имя пользователя должно совпадать");
        assertEquals(0, user.getWins(), "Количество побед должно быть 0");
        assertEquals(0, user.getLosses(), "Количество поражений должно быть 0");
        assertEquals(0, user.getAttempts(), "Количество попыток должно быть 0");

        // Проверяем, что повторный вызов возвращает тот же объект пользователя
        User sameUser = UserStorage.getUser("Alice");
        assertSame(user, sameUser, "Должен вернуться тот же объект пользователя");
    }

    // Тестируем метод incrementWins
    // Проверяем увеличение количества побед и попыток
    @Test
    void testIncrementWins() {
        // Увеличиваем количество побед для пользователя
        UserStorage.incrementWins("Bob");
        User user = UserStorage.getUser("Bob");
        assertEquals(1, user.getWins(), "Количество побед должно быть 1");
        assertEquals(1, user.getAttempts(), "Количество попыток должно быть 1");

        // Проверяем повторное увеличение количества побед
        UserStorage.incrementWins("Bob");
        assertEquals(2, user.getWins(), "Количество побед должно быть 2");
        assertEquals(2, user.getAttempts(), "Количество попыток должно быть 2");
    }

    // Тестируем метод incrementLosses
    // Проверяем увеличение количества поражений и попыток
    @Test
    void testIncrementLosses() {
        // Увеличиваем количество поражений для пользователя
        UserStorage.incrementLosses("Charlie");
        User user = UserStorage.getUser("Charlie");
        assertEquals(1, user.getLosses(), "Количество поражений должно быть 1");
        assertEquals(1, user.getAttempts(), "Количество попыток должно быть 1");

        // Проверяем повторное увеличение количества поражений
        UserStorage.incrementLosses("Charlie");
        assertEquals(2, user.getLosses(), "Количество поражений должно быть 2");
        assertEquals(2, user.getAttempts(), "Количество попыток должно быть 2");
    }

    // Тестируем метод updateUser
    // Проверяем ручное обновление пользователя в хранилище
    @Test
    void testUpdateUser() {
        // Создаем нового пользователя и изменяем его статистику
        User user = new User("Dave");
        user.incrementWins();
        user.incrementLosses();
        UserStorage.updateUser(user);

        // Проверяем, что данные пользователя обновились в хранилище
        User retrievedUser = UserStorage.getUser("Dave");
        assertEquals(1, retrievedUser.getWins(), "Количество побед должно быть 1");
        assertEquals(1, retrievedUser.getLosses(), "Количество поражений должно быть 1");
        assertEquals(0, retrievedUser.getAttempts(), "Количество попыток должно быть 0 (не увеличивается вручную)");
    }

    // Тестируем метод getAllUsers
    // Проверяем корректность извлечения всех пользователей из хранилища
    @Test
    void testGetAllUsers() {
        // Добавляем двух пользователей с разной статистикой
        UserStorage.incrementWins("Eve");
        UserStorage.incrementLosses("Frank");

        // Извлекаем всех пользователей и проверяем их количество
        Map<String, User> allUsers = UserStorage.getAllUsers();
        assertEquals(2, allUsers.size(), "Должно быть 2 пользователя в хранилище");

        // Проверяем, что каждый пользователь корректно сохранен
        assertTrue(allUsers.containsKey("Eve"), "Хранилище должно содержать пользователя 'Eve'");
        assertTrue(allUsers.containsKey("Frank"), "Хранилище должно содержать пользователя 'Frank'");
        assertEquals(1, allUsers.get("Eve").getWins(), "Количество побед пользователя 'Eve' должно быть 1");
        assertEquals(1, allUsers.get("Frank").getLosses(), "Количество поражений пользователя 'Frank' должно быть 1");
    }
}
