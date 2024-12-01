package model.entities;

 // Класс для хранения пути к файлу questions.json.

public class QuestionsFilePath {
    private static String filePath;

     // Устанавливает путь к файлу questions.json. param path путь к файлу.

    public static void setFilePath(String path) {
        filePath = path;
    }


    // Возвращает путь к файлу questions.json.  return путь к файлу.

    public static String getFilePath() {
        return filePath;
    }
}
