package ua.kpi.model.dao.jdbc;

import ua.kpi.model.dao.BookDao;
import ua.kpi.model.entity.Book;
import ua.kpi.model.entity.Reader;
import java.sql.*;
import java.util.List;

public class JdbcBookDao extends AbstractJdbcDao<Book> implements BookDao {
    private static final String DELETE_BOOK_BY_ID = "DELETE FROM books WHERE id = ?";
    private static final String INSERT_INTO_BOOK = "INSERT INTO books (title, author, description, genre, publishing_year, reader_id) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_FROM_BOOK = "SELECT b.id, b.title, b.author, b.description, b.genre, b.publishing_year, b.reader_id, r.full_name FROM books b LEFT JOIN readers r ON b.reader_id = r.id";
    private static final String WHERE_ID = " WHERE b.id = ?";

    private static final String ID = "id";
    private static final String TITLE = "title";
    private static final String AUTHOR = "author";
    private static final String DESCRIPTION = "description";
    private static final String GENRE = "genre";
    private static final String PUBLISHING_YEAR = "publishing_year";
    private static final String READER_ID = "reader_id";
    private static final String READER_NAME = "full_name";

    public JdbcBookDao(Connection connection) {
        super(connection);
    }

    @Override
    protected String getSelectAllQuery() {
        return SELECT_FROM_BOOK;
    }

    @Override
    protected String getCreateQuery() {
        return INSERT_INTO_BOOK;
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE books SET title = ?, author = ?, description = ?, genre = ?, publishing_year = ?, reader_id = ? WHERE id = ?";
    }

    @Override
    protected String getDeleteQuery() {
        return DELETE_BOOK_BY_ID;
    }

    @Override
    protected String getSelectByIdQuery() {
        return SELECT_FROM_BOOK + WHERE_ID;
    }

    @Override
    protected Book getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        Reader reader = null;
        int readerId = resultSet.getInt(READER_ID);
        if (!resultSet.wasNull()) {
            reader = new Reader.Builder()
                    .setId(readerId)
                    .setFullName(resultSet.getString(READER_NAME))
                    .build();
        }

        return new Book.Builder()
                .setId(resultSet.getInt(ID))
                .setTitle(resultSet.getString(TITLE))
                .setAuthor(resultSet.getString(AUTHOR))
                .setDescription(resultSet.getString(DESCRIPTION))
                .setGenre(resultSet.getString(GENRE))
                .setPublishingYear(resultSet.getInt(PUBLISHING_YEAR))
                .setReader(reader)
                .build();
    }

    @Override
    protected void setIdForEntity(Book entity, int id) {
        entity.setId(id);
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement query, Book entity) throws SQLException {
        query.setString(1, entity.getTitle());
        query.setString(2, entity.getAuthor());
        query.setString(3, entity.getDescription());
        query.setString(4, entity.getGenre());
        if (entity.getPublishingYear() != null) {
            query.setInt(5, entity.getPublishingYear());
        } else {
            query.setNull(5, Types.INTEGER);
        }
        if (entity.getReader() != null && entity.getReader().getId() != null) {
            query.setInt(6, entity.getReader().getId());
        } else {
            query.setNull(6, Types.INTEGER);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement query, Book entity) throws SQLException {
        query.setString(1, entity.getTitle());
        query.setString(2, entity.getAuthor());
        query.setString(3, entity.getDescription());
        query.setString(4, entity.getGenre());

        if (entity.getPublishingYear() != null && entity.getPublishingYear() != 0) {
            query.setInt(5, entity.getPublishingYear());
        } else {
            query.setNull(5, Types.INTEGER);
        }

        if (entity.getReader() != null && entity.getReader().getId() != null) {
            query.setInt(6, entity.getReader().getId());
        } else {
            query.setNull(6, Types.INTEGER);
        }

        query.setInt(7, entity.getId());

    }

    @Override
    public List<Book> findByTitle(String title) {
        throw new UnsupportedOperationException("Method findByTitle is not implemented yet.");
    }
}