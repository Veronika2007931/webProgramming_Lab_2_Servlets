package ua.kpi.controller.command;

import ua.kpi.utils.PathsHolder;
import java.util.HashMap;
import java.util.Map;

public class CommandHolder {
    private static final Map<String, Command> commands = new HashMap<>();

    static {
        // Мапимо адреси на наші класи команд
        commands.put(PathsHolder.BOOKS, new GetBooksCommand());
        commands.put(PathsHolder.ADD_BOOK, new AddBookCommand());
        commands.put(PathsHolder.DELETE_BOOK, new DeleteBookCommand());
        commands.put(PathsHolder.BOOKS_DETAILS, new BookDetailsCommand());
    }

    public static Command getCommand(String path) {
        // Якщо точного збігу немає, повертаємо команду показу книг за замовчуванням
        return commands.getOrDefault(path, new GetBooksCommand());
    }
}