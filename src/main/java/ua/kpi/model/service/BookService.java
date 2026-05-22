package ua.kpi.model.service;

import ua.kpi.model.dao.BookDao;
import ua.kpi.model.dao.DaoConnection;
import ua.kpi.model.dao.DaoFactory;
import ua.kpi.model.entity.Book;
import java.util.List;
import java.util.Optional;

public class BookService {
    private final DaoFactory daoFactory = DaoFactory.getInstance();

    private static class Holder {
        static final BookService INSTANCE = new BookService();
    }

    public static BookService getInstance() {
        return Holder.INSTANCE;
    }

    public void create(Book book) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            BookDao dao = daoFactory.createBookDao(connection);
            connection.begin();
            dao.create(book);
            connection.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Book book) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            BookDao dao = daoFactory.createBookDao(connection);
            connection.begin();
            dao.update(book);
            connection.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(int bookId) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            BookDao dao = daoFactory.createBookDao(connection);
            dao.delete(bookId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Book> getById(Integer id) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            BookDao dao = daoFactory.createBookDao(connection);
            return dao.find(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Book> getAll() {
        try (DaoConnection connection = daoFactory.getConnection()) {
            BookDao dao = daoFactory.createBookDao(connection);
            return dao.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}