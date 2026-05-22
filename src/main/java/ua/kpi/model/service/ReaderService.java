package ua.kpi.model.service;

import ua.kpi.model.dao.DaoConnection;
import ua.kpi.model.dao.DaoFactory;
import ua.kpi.model.dao.ReaderDao;
import ua.kpi.model.entity.Reader;
import java.util.List;

public class ReaderService {
    private final DaoFactory daoFactory = DaoFactory.getInstance();

    private static class Holder {
        static final ReaderService INSTANCE = new ReaderService();
    }

    public static ReaderService getInstance() {
        return Holder.INSTANCE;
    }

    public List<Reader> getAll() {
        try (DaoConnection connection = daoFactory.getConnection()) {
            ReaderDao readerDao = daoFactory.createReaderDao(connection);
            return readerDao.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}