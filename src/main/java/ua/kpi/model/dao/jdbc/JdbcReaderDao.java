package ua.kpi.model.dao.jdbc;

import ua.kpi.model.dao.ReaderDao;
import ua.kpi.model.entity.Reader;
import java.sql.*;
import java.util.List;

public class JdbcReaderDao extends AbstractJdbcDao<Reader> implements ReaderDao {
    private static final String DELETE_READER_BY_ID = "DELETE FROM readers WHERE id = ?";
    private static final String INSERT_INTO_READER = "INSERT INTO readers (full_name) VALUES (?)";
    private static final String SELECT_FROM_READER = "SELECT * FROM readers";
    private static final String WHERE_ID = " WHERE id = ?";
    private static final String ID = "id";
    private static final String FULL_NAME = "full_name";

    public JdbcReaderDao(Connection connection) {
        super(connection);
    }

    @Override
    protected String getSelectAllQuery() {
        return SELECT_FROM_READER;
    }

    @Override
    protected String getCreateQuery() {
        return INSERT_INTO_READER;
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE readers SET full_name = ?" + WHERE_ID;
    }

    @Override
    protected String getDeleteQuery() {
        return DELETE_READER_BY_ID;
    }

    @Override
    protected String getSelectByIdQuery() {
        return SELECT_FROM_READER + WHERE_ID;
    }

    @Override
    protected Reader getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        return new Reader.Builder()
                .setId(resultSet.getInt(ID))
                .setFullName(resultSet.getString(FULL_NAME))
                .build();
    }

    @Override
    protected void setIdForEntity(Reader entity, int id) {
        entity.setId(id);
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement query, Reader entity) throws SQLException {
        query.setString(1, entity.getFullName());
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement query, Reader entity) throws SQLException {
        query.setString(1, entity.getFullName());
        query.setInt(2, entity.getId());
    }

    @Override
    public List<Reader> findByFullName(String fullName) {
        throw new UnsupportedOperationException();
    }
}