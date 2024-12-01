package utils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class QuestScanner {

     // Сканирует директорию и возвращает список доступных квестов. Ключ — идентификатор квеста, значение — название квеста.
     //  return Список квестов.

    private static final String QUESTS_DIRECTORY = "src/main/resources/quests";


    public static Path getQuestFolder(String questName) {
        return Paths.get(QUESTS_DIRECTORY, questName);
    }
    public static Map<String, String> getAvailableQuests() {
        Map<String, String> quests = new LinkedHashMap<>();
        String resourcePath;

        try {
            // Получаем путь к ресурсам
            resourcePath = QuestScanner.class.getClassLoader().getResource("").getFile();
            File resourceDir = new File(resourcePath);

            System.out.println("Поиск квестов в директории: " + resourcePath);

            // Ищем папки quest#N
            File[] subDirectories = resourceDir.listFiles(file -> file.isDirectory() && file.getName().startsWith("quest#"));
            if (subDirectories != null && subDirectories.length > 0) {
                for (File subDir : subDirectories) {
                    String questId = subDir.getName();
                    File questTitleFile = new File(subDir, "questTitle.json");

                    // Читаем название квеста
                    if (questTitleFile.exists()) {
                        try (FileReader reader = new FileReader(questTitleFile)) {
                            JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();
                            String questTitle = jsonObject.get("title").getAsString();
                            quests.put(questId, questTitle);
                            System.out.println("Найден квест: ID = " + questId + ", Название = " + questTitle);
                        } catch (IOException | IllegalStateException e) {
                            System.err.println("Ошибка чтения файла " + questTitleFile.getAbsolutePath() + ": " + e.getMessage());
                        }
                    } else {
                        System.err.println("Файл questTitle.json отсутствует в директории: " + subDir.getAbsolutePath());
                    }
                }
            } else {
                System.out.println("Папки с квестами не найдены в директории: " + resourcePath);
            }
        } catch (Exception e) {
            System.err.println("Ошибка при сканировании квестов: " + e.getMessage());
        }







        return quests;
    }
}
