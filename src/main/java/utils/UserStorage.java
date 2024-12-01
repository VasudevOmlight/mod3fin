package utils;
import model.entities.User;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//Хранилище пользователей и их статистики.
public class UserStorage {
    private static final Map<String, User> userList = new ConcurrentHashMap<>();

    // Получает пользователя по имени. Если пользователь отсутствует, создаёт нового.
    public static synchronized User getUser(String userName) {
        return userList.computeIfAbsent(userName, User::new);
    }

    // Увеличивает количество побед пользователя.
    public static void incrementWins(String userName) {
        User user = getUser(userName);
        user.incrementWins();
        user.incrementAttempts(); // Победа также считается попыткой
        updateUser(user); // Обновление пользователя в репозитории
    }

    // Увеличивает количество поражений пользователя.
    public static void incrementLosses(String userName) {
        User user = getUser(userName);
        user.incrementLosses();
        user.incrementAttempts(); // Поражение также считается попыткой
        updateUser(user); // Обновление пользователя в репозитории
    }

    // Обновляет информацию о пользователе в хранилище.
    public static synchronized void updateUser(User user) {
        userList.put(user.getUserName(), user);
    }

    // Получает статистику всех пользователей.
    public static Map<String, User> getAllUsers() {
        return userList;
    }
}
