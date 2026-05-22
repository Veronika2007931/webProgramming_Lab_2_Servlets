package ua.kpi.model.dao;

import ua.kpi.model.entity.Book;
import java.util.List;
import java.util.Optional;

public interface BookDao extends GenericDao<Book> {
    List<Book> findByTitle(String title);
}