package ua.kpi.controller.command;

import ua.kpi.model.service.BookService;
import ua.kpi.utils.PathsHolder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteBookCommand implements Command {
    private final BookService bookService = BookService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idParam = request.getParameter("id");
        if (idParam != null && !idParam.isEmpty()) {
            bookService.delete(Integer.parseInt(idParam));
        }

        return "redirect:" + PathsHolder.BOOKS;
    }
}