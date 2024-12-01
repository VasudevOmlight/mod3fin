package model.entities;

// Класс для представления текстовых строк страницы index.jsp.

public class IndexText {
    /*
    private String headerText;
    private String mainText;

    // Конструктор для Jackson
    public IndexText() {}

    public IndexText(String headerText, String mainText) {
        this.headerText = headerText;
        this.mainText = mainText;
    }

    // Геттеры и сеттеры
    public String getHeaderText() {
        return headerText;
    }

    public void setHeaderText(String headerText) {
        this.headerText = headerText;
    }

    public String getMainText() {
        return mainText;
    }

    public void setMainText(String mainText) {
        this.mainText = mainText;
    }  */

    private String headerText;
    private String mainText;

    public IndexText(String headerText, String mainText) {
        this.headerText = headerText;
        this.mainText = mainText;
    }

    public String getHeaderText() {
        return headerText;
    }

    public String getMainText() {
        return mainText;
    }
}
