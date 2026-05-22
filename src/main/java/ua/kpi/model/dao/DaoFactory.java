package ua.kpi.model.dao;

import ua.kpi.model.dao.jdbc.JdbcDaoFactory;

public abstract class DaoFactory {
    public abstract DaoConnection getConnection();

    public abstract ReaderDao createReaderDao(DaoConnection daoConnection);

    public abstract BookDao createBookDao(DaoConnection daoConnection);

    private static DaoFactory instance;

    public static DaoFactory getInstance() {
        if (instance == null) {
            // Спрощуємо виклик фабрики напряму для лаби, щоб не мучитися з файлами
            // конфігурацій .properties
            instance = new JdbcDaoFactory();
        }
        return instance;
    }
}