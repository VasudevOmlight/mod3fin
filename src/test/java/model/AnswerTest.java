import model.entities.Answer;
import org.junit.Test;

import static org.junit.Assert.*;


// Тестовый класс для проверки функциональности класса Answer.

public class AnswerTest {

    // Тест конструктора с одним параметром (text).
    @Test
    public void testConstructorWithText() {
        // Создаем объект с текстом ответа
        Answer answer = new Answer("Правильный ответ");

        // Проверяем, что текст установлен правильно
        assertEquals("Правильный ответ", answer.getText());

        // Убеждаемся, что сообщение об ошибке равно null
        assertNull(answer.getFailureMessage());
    }

    //Тест конструктора с двумя параметрами (text и failureMessage).
    @Test
    public void testConstructorWithTextAndFailureMessage() {
        // Создаем объект с текстом ответа и сообщением об ошибке
        Answer answer = new Answer("Неправильный ответ", "Ошибка: ответ неверный");

        // Проверяем, что текст установлен правильно
        assertEquals("Неправильный ответ", answer.getText());

        // Проверяем, что сообщение об ошибке установлено правильно
        assertEquals("Ошибка: ответ неверный", answer.getFailureMessage());
    }

    // Тест методов-геттеров и сеттеров.
    @Test
    public void testGettersAndSetters() {
        // Создаем пустой объект
        Answer answer = new Answer();

        // Устанавливаем текст ответа
        answer.setText("Новый текст");

        // Устанавливаем сообщение об ошибке
        answer.setFailureMessage("Сообщение об ошибке");

        // Проверяем, что текст установлен правильно
        assertEquals("Новый текст", answer.getText());

        // Проверяем, что сообщение об ошибке установлено правильно
        assertEquals("Сообщение об ошибке", answer.getFailureMessage());
    }

    // Тест метода isWrongAnswer для проверки правильности определения неправильного ответа.
    @Test
    public void testIsWrongAnswer() {
        // Создаем объект с сообщением об ошибке
        Answer wrongAnswer = new Answer("Неправильный ответ", "Ошибка");

        // Проверяем, что ответ считается неправильным
        assertTrue(wrongAnswer.isWrongAnswer());

        // Создаем объект без сообщения об ошибке
        Answer correctAnswer = new Answer("Правильный ответ");

        // Проверяем, что ответ считается правильным
        assertFalse(correctAnswer.isWrongAnswer());
    }

    // Тест метода getWrongAnswerEndText для проверки возвращаемого текста сообщения об ошибке.
    @Test
    public void testGetWrongAnswerEndText() {
        // Создаем объект с сообщением об ошибке
        Answer answer = new Answer("Неправильный ответ", "Ошибка: ответ неверный");

        // Проверяем, что метод возвращает правильное сообщение
        assertEquals("Ошибка: ответ неверный", answer.getWrongAnswerEndText());

        // Создаем объект без сообщения об ошибке
        Answer correctAnswer = new Answer("Правильный ответ");

        // Проверяем, что метод возвращает null
        assertNull(correctAnswer.getWrongAnswerEndText());
    }
}
