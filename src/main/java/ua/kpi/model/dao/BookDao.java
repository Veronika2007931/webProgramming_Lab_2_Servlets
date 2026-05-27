package ua.kpi.model.dao;

import ua.kpi.model.entity.Book;
import java.util.List;

public interface BookDao extends GenericDao<Book> {
    List<Book> findByTitle(String title);
}