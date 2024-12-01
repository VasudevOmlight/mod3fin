package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.InputStream;
import java.util.Map;

public class IndexTextStorage {
    private Map<String, String> indexText;

    public IndexTextStorage(String questName) {
        loadIndexText(questName);
    }

    private void loadIndexText(String questName) {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("quests/" + questName + "/indexText.json")) {
            if (inputStream == null) {
                throw new RuntimeException("Файл indexText.json не найден для квеста: " + questName);
            }

            ObjectMapper objectMapper = new ObjectMapper();
            this.indexText = objectMapper.readValue(inputStream, Map.class);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка загрузки indexText.json: " + e.getMessage(), e);
        }
    }

    public Map<String, String> getIndexText() {
        return indexText;
    }
}
