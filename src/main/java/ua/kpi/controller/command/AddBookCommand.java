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

        request.setAttribute(AttributesHolder.READERS, readerService.getAll());

        if (request.getMethod().equals("GET")) {
            String idParam = request.getParameter("id");
            if (idParam != null && !idParam.isEmpty()) {
                // Якщо передано id, значить ми РЕДАГУЄМО існуючу книгу
                Book bookToEdit = bookService.getById(Integer.parseInt(idParam)).orElse(null);
                request.setAttribute("book", bookToEdit);
            }
            return PagesHolder.BOOK;
        }

        String idParam = request.getParameter("id");
        String title = request.getParameter(AttributesHolder.TITLE);
        String author = request.getParameter(AttributesHolder.AUTHOR);
        String description = request.getParameter(AttributesHolder.DESCRIPTION);
        String genre = request.getParameter("genre");
        String publishingYearParam = request.getParameter("publishingYear"); // Змінили ім'я для ясності
        String readerIdParam = request.getParameter(AttributesHolder.READER_ID);

        Integer py = null;
        if (publishingYearParam != null && !publishingYearParam.trim().isEmpty()) {
            try {
                py = Integer.parseInt(publishingYearParam.trim());
            } catch (NumberFormatException e) {

            }
        }

        int currentYear = java.time.Year.now().getValue();

        if (title == null || title.trim().isEmpty() || author == null || author.trim().isEmpty()) {
            request.setAttribute("errorMessage",
                    "Помилка валідації! Назва книги та Автор є обов'язковими для заповнення.");

            Book textBook = new Book.Builder().setTitle(title).setAuthor(author).setDescription(description)
                    .setGenre(genre).setPublishingYear(py).build();
            request.setAttribute("book", textBook);
            return PagesHolder.BOOK;
        } else if (py != null && (py < 1 || py > currentYear)) {
            request.setAttribute("errorMessage",
                    "Помилка валідації! Рік видання повинен бути в межах від 1 до " + currentYear + ".");

            Book textBook = new Book.Builder()
                    .setTitle(title)
                    .setAuthor(author)
                    .setDescription(description)
                    .setGenre(genre)
                    .setPublishingYear(py)
                    .build();
            request.setAttribute("book", textBook);
            return PagesHolder.BOOK;
        }

        Reader reader = null;
        if (readerIdParam != null && !readerIdParam.isEmpty()) {
            reader = new Reader.Builder()
                    .setId(Integer.parseInt(readerIdParam))
                    .build();
        }

        Book.Builder bookBuilder = new Book.Builder()
                .setTitle(title.trim())
                .setAuthor(author.trim())
                .setDescription(description)
                .setGenre(genre)
                .setPublishingYear(py)
                .setReader(reader);

        if (idParam != null && !idParam.isEmpty()) {
            bookBuilder.setId(Integer.parseInt(idParam));
            bookService.update(bookBuilder.build());
        } else {
            bookService.create(bookBuilder.build());
        }

        return "redirect:" + PathsHolder.BOOKS;
    }
}