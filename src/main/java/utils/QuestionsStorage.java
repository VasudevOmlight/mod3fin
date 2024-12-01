package utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.entities.Question;

import java.io.InputStream;
import java.util.List;

// Класс для загрузки и хранения вопросов конкретного квеста.
public class QuestionsStorage {
    // Список вопросов, загруженных из JSON-файла.
    private List<Question> questions;

     //Конструктор QuestionsStorage. Выполняет загрузку вопросов из файла, связанного с указанным именем квеста.
     //param questName имя квеста, для которого загружаются вопросы.
    public QuestionsStorage(String questName) {
        loadQuestions(questName);
    }

     // Метод загрузки вопросов из JSON-файла. Файл ищется в директории ресурсов, путь строится на основе имени квеста.
     // param questName имя квеста, для которого загружается файл с вопросами
    private void loadQuestions(String questName) {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("quests/" + questName + "/questions.json")) {
            // Логируем путь загружаемого файла.
            System.out.println(" [QuestionsStorage] Reading new " + "quests/" + questName + "/questions.json");

            // Если файл не найден, выбрасываем исключение.
            if (inputStream == null) {
                throw new RuntimeException("Файл questions.json не найден для квеста: " + questName);
            }

            // Используем Jackson для чтения JSON и преобразования его в список объектов Question.
            ObjectMapper objectMapper = new ObjectMapper();
            this.questions = objectMapper.readValue(inputStream, new TypeReference<List<Question>>() {});
        } catch (Exception e) {
            // В случае ошибки выбрасываем исключение с описанием проблемы.
            throw new RuntimeException("Ошибка загрузки questions.json: " + e.getMessage(), e);
        }
    }

     // Метод для получения списка вопросов. return список объектов Question, загруженных из JSON
    public List<Question> getQuestions() {
        return questions;
    }
}
