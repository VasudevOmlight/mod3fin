package utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.entities.Question;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.entities.QuestionsFilePath;

// Класс для управления вопросами из JSON-файлов.
public class QuestionManager {

    // Хранилище вопросов, где ключ — ID вопроса, а значение — объект Question.
    public final static Map<Integer, Question> questionList = new HashMap<>();

    // Статический блок инициализации для загрузки вопросов при загрузке класса.
    static {
        init();
    }

     // Метод инициализации вопросов из JSON-файла. Загружает вопросы из файла, указанного в пути, и сохраняет их в questionList.
    private static void init() {
        try (InputStream inputStream = QuestionManager.class.getClassLoader().getResourceAsStream(QuestionsFilePath.getFilePath())) {
            // Проверяем, существует ли файл.
            if (inputStream == null) {
                throw new RuntimeException("Файл questions.json не найден в ресурсах.");
            }

            // Используем библиотеку Jackson для чтения JSON-файла.
            ObjectMapper objectMapper = new ObjectMapper();
            List<Question> questions = objectMapper.readValue(inputStream, new TypeReference<List<Question>>() {});

            // Переносим вопросы в карту questionList с использованием их ID в качестве ключей.
            for (Question question : questions) {
                questionList.put(question.getId(), question);
            }
        } catch (Exception e) {
            // В случае ошибки выбрасываем исключение с описанием причины.
            throw new RuntimeException("Ошибка загрузки вопросов из JSON: " + e.getMessage(), e);
        }
    }

     //Метод для получения вопроса по его ID. param - id идентификатор вопроса, return объект Question или null, если вопрос с таким ID отсутствует

    public Question getById(int id) {
        return questionList.get(id);
    }
}
