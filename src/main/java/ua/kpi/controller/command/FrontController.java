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
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String path = request.getRequestURI();

        Command command = CommandHolder.getCommand(path);
        String resultPage = command.execute(request, response);

        if (resultPage.startsWith("redirect:")) {
            String redirectUrl = resultPage.substring("redirect:".length());
            response.sendRedirect(redirectUrl);
        } else {

            request.getRequestDispatcher(resultPage).forward(request, response);
        }
    }
}