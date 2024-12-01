package model.entities;

 // Класс для хранения пути к файлу indexText.json.

public class PrefaceFilePath {
    private static String filePath;


    // Устанавливает путь к файлу indexText.json. path путь к файлу.
    public static void setFilePath(String path) {
        filePath = path;
    }

  // Возвращает путь к файлу indexText.json.
    public static String getFilePath() {
        return filePath;
    }
}
