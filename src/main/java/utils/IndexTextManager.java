package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.entities.IndexText;

import java.io.InputStream;

// Класс для загрузки текстовых строк из файла indexText.json.
public class IndexTextManager {

     // Возвращает объект IndexText с текстами для страницы. return IndexText объект.
    public static IndexText getIndexText() {
        return new IndexText("ФІНАЛЬНЫЙ ПРОЕКТ III МОДУЛЯ JAVARUSH", "Авторизуйся перед початком квесту:");
    }
}
