package model.entities;
import lombok.Getter;


 //Класс для представления ответа.

public class Answer {
    private String text;
    private String failureMessage;

    // Конструктор без аргументов для Jackson
    public Answer() {}

    public Answer(String text) {
        this.text = text;
    }

    public Answer(String text, String failureMessage) {
        this.text = text;
        this.failureMessage = failureMessage;
    }

    // Геттеры и сеттеры
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFailureMessage() {
        return failureMessage;
    }

    public void setFailureMessage(String failureMessage) {
        this.failureMessage = failureMessage;
    }

     // Проверяет, является ли ответ неправильным - true, если ответ ошибочный, иначе false.
    public boolean isWrongAnswer() {
        return failureMessage != null && !failureMessage.isEmpty();
    }

    // Возвращает текст сообщения для неправильного ответа - возвращает текст сообщения или null, если его нет.
    public String getWrongAnswerEndText() {
        return failureMessage;
    }
}
