package ua.kpi.controller.command;

import ua.kpi.model.service.BookService;
import ua.kpi.utils.AttributesHolder;
import ua.kpi.utils.PagesHolder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetBooksCommand implements Command {
    private final BookService bookService = BookService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute(AttributesHolder.BOOKS, bookService.getAll());
        return PagesHolder.BOOKS;
    }
}
