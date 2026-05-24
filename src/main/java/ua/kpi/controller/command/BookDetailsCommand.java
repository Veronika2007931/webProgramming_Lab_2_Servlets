package ua.kpi.controller.command;

import ua.kpi.model.entity.Book;
import ua.kpi.model.service.BookService;
import ua.kpi.utils.PagesHolder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BookDetailsCommand implements Command {
    private final BookService bookService = BookService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idParam = request.getParameter("id");
        if (idParam != null && !idParam.isEmpty()) {
            Book book = bookService.getById(Integer.parseInt(idParam)).orElse(null);
            request.setAttribute("book", book);
        }
        return PagesHolder.BOOK_DETAILS; // або через твій PagesHolder
    }
}