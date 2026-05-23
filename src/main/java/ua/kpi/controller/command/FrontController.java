package ua.kpi.controller.command;

import ua.kpi.controller.command.Command;
import ua.kpi.controller.command.CommandHolder;
import ua.kpi.utils.PathsHolder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// Анотація, яка змушує Tomcat ловити всі запити, що йдуть на наш застосунок
@WebServlet(urlPatterns = { "/books/*" })
public class FrontController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Отримуємо чистий URL, на який клацнув користувач
        String path = request.getRequestURI();

        // Шукаємо потрібну команду через наш Holder
        Command command = CommandHolder.getCommand(path);

        // Виконуємо команду та отримуємо куди йти далі (на JSP сторінку чи на редирект)
        String resultPage = command.execute(request, response);

        // Перевіряємо, чи це редирект (починається з "redirect:") чи звичайний показ
        // сторінки
        if (resultPage.startsWith("redirect:")) {
            String redirectUrl = resultPage.substring("redirect:".length());
            response.sendRedirect(redirectUrl);
        } else {
            // Класичний Forward на JSP сторінку всередині WEB-INF
            request.getRequestDispatcher(resultPage).forward(request, response);
        }
    }
}