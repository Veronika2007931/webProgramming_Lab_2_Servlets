package ua.kpi.controller.command;

import ua.kpi.model.entity.Book;
import ua.kpi.model.entity.Reader;
import ua.kpi.model.service.BookService;
import ua.kpi.model.service.ReaderService;
import ua.kpi.utils.AttributesHolder;
import ua.kpi.utils.PagesHolder;
import ua.kpi.utils.PathsHolder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddBookCommand implements Command {
    private final BookService bookService = BookService.getInstance();
    private final ReaderService readerService = ReaderService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getMethod().equals("GET")) {
            request.setAttribute(AttributesHolder.READERS, readerService.getAll());
            return PagesHolder.BOOK;
        }

        // Логіка для POST (Збереження)
        String title = request.getParameter(AttributesHolder.TITLE);
        String author = request.getParameter(AttributesHolder.AUTHOR);
        String description = request.getParameter(AttributesHolder.DESCRIPTION);
        String readerIdParam = request.getParameter(AttributesHolder.READER_ID);

        Reader reader = null;
        if (readerIdParam != null && !readerIdParam.isEmpty()) {
            reader = new Reader.Builder()
                    .setId(Integer.parseInt(readerIdParam))
                    .build();
        }

        Book book = new Book.Builder()
                .setTitle(title)
                .setAuthor(author)
                .setDescription(description)
                .setReader(reader)
                .build();

        bookService.create(book);

        // Перенаправляємо назад на список книг (Редирект, як у Северина)
        return "redirect:" + PathsHolder.BOOKS;
    }
}
