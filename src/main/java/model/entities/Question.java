package model.entities;
import java.util.List;

 //Класс для представления вопроса.
public class Question {
    private int id;
    private String questionText;
    private List<Answer> answers;

    // Конструктор без аргументов для Jackson
    public Question() {}

    public Question(int id, String questionText, List<Answer> answers) {
        this.id = id;
        this.questionText = questionText;
        this.answers = answers;
    }

    // Геттеры и сеттеры
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    // Возвращает список ответов - список объектов Answer.
    public List<Answer> getAnswerList() {
        return answers;
    }


    // Возвращает текст вопроса
    public String getText() {
        return questionText;
    }
}
