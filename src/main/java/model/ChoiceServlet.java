package model;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.entities.PrefaceFilePath;
import utils.FileUtils;
import utils.QuestScanner;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import utils.IndexTextStorage;
import utils.QuestionsStorage;
import model.entities.QuestionsFilePath;
import model.entities.SelectedQuest;

// Сервлет для обработки выбора и загрузки квестов.
@WebServlet("/choiceServlet")
public class ChoiceServlet extends HttpServlet {

     // Метод для обработки GET-запросов. param request  запрос клиента, param response ответ сервера
     // throws ServletException если возникает ошибка сервлета, throws IOException,если возникает ошибка ввода/вывода
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("[ChoiceServlet] Метод doGet запущен.");

        // Получаем список доступных квестов.
        Map<String, String> quests = QuestScanner.getAvailableQuests();

        // Проверяем, найдены ли квесты, и выводим лог.
        if (quests.isEmpty()) {
            System.out.println("[ChoiceServlet] Квесты не найдены.");
        } else {
            quests.forEach((key, value) -> System.out.println("[ChoiceServlet] Найден квест: " + key + " - " + value));
        }

        // Передаём список квестов в JSP-страницу.
        request.setAttribute("quests", quests);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

     // Метод для обработки POST-запросов. param request - запрос клиента, param response ответ сервера
     // throws ServletException если возникает ошибка сервлета, throws IOException - если возникает ошибка ввода/вывода

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Получаем параметр действия из запроса.
        String action = request.getParameter("action");

        // Если действие — поиск квестов, перенаправляем на doGet.
        if ("searchQuests".equals(action)) {
            doGet(request, response);
        }
        // Если действие — выбор квеста.
        else if ("selectQuest".equals(action)) {
            // Получаем выбранный квест.
            String selectedQuest = request.getParameter("selectedQuest");

            // Если квест не выбран, возвращаем клиента на страницу выбора.
            if (selectedQuest == null || selectedQuest.isEmpty()) {
                System.out.println("[ChoiceServlet] Ошибка: квест не выбран.");
                response.sendRedirect("choiceServlet");
                return;
            }

            System.out.println("[ChoiceServlet] Выбран квест: " + selectedQuest);

            // Формируем пути к JSON-файлам квеста.
            String questionsFilePath = "quests\\" + selectedQuest + "\\questions.json";
            String indexTextFilePath = "quests\\" + selectedQuest + "\\indexText.json";

            // Сохраняем имя выбранного квеста.
            SelectedQuest.SelectedFilePath = selectedQuest;

            // Логируем сформированные пути.
            System.out.println("[ChoiceServlet] Путь к файлу вопросов: " + questionsFilePath);
            System.out.println("[ChoiceServlet] Путь к файлу описания: " + indexTextFilePath);

            // Сохраняем пути в соответствующих сущностях.
            QuestionsFilePath.setFilePath(questionsFilePath);
            PrefaceFilePath.setFilePath(indexTextFilePath);

            try {
                // Загружаем данные для квеста.
                IndexTextStorage indexTextStorage = new IndexTextStorage(selectedQuest);
                QuestionsStorage questionsStorage = new QuestionsStorage(selectedQuest);

                // Передаём данные в JSP-страницу.
                request.setAttribute("indexText", indexTextStorage.getIndexText());
                request.setAttribute("questions", questionsStorage.getQuestions());

                // Перенаправляем на страницу с описанием квеста.
                request.getRequestDispatcher("questIndex.jsp").forward(request, response);
            } catch (Exception e) {
                // Обрабатываем ошибку загрузки файлов.
                System.err.println("[ChoiceServlet] Ошибка загрузки файлов квеста: " + e.getMessage());
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Ошибка загрузки файлов квеста.");
            }
        }
    }
}
