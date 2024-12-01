package model.entities;
import java.util.concurrent.atomic.AtomicInteger;

 // Класс для хранения данных пользователя.

public class User {
    private static final AtomicInteger idGenerator = new AtomicInteger(1); // Генератор уникальных ID
    private final int userId;
    private final String userName;
    private int wins = 0;
    private int losses = 0;
    private int attempts = 0;

    public User(int userId, String userName) {
        this.userId = userId;
        this.userName = userName;
        this.wins = 0;
        this.losses = 0;
        this.attempts = 0;
        System.out.println("User " + userName + " ID " + userId);
    }

    public User(String userName) {
        this.userId = idGenerator.getAndIncrement(); // Уникальный ID
        this.userName = userName;
        this.wins = 0;
        this.losses = 0;
        this.attempts = 0;
        System.out.println("User " + userName + " ID " + userId);
    }

    public int getUserId() {
        System.out.println("User " + userName + " ID " + userId);
        return userId;
    }

    public String getUserName() {
        System.out.println("User " + userName + " ID " + userId);
        return userName;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public int getAttempts() {
        return attempts;
    }

    public void incrementWins() {
        wins++;
    }

    public void incrementLosses() {
        losses++;
    }

    public void incrementAttempts() {
        attempts++;
    }
}
