package ua.kpi.model.dao.jdbc;

import ua.kpi.model.dao.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class JdbcDaoFactory extends DaoFactory {

    // Змінюємо mem на шлях до домашньої папки (~/). База створить файл
    // librarydb.mv.db
    private static final String DB_URL = "jdbc:h2:~/librarydb;AUTO_SERVER=TRUE";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "";

    private static Connection keepAliveConnection;

    public JdbcDaoFactory() {
        try {
            Class.forName("org.h2.Driver");

            if (keepAliveConnection == null || keepAliveConnection.isClosed()) {
                keepAliveConnection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            }

            try (Statement stmt = keepAliveConnection.createStatement()) {
                // Створюємо таблиці (вони створяться лише один раз при першому запуску)
                stmt.execute("CREATE TABLE IF NOT EXISTS readers (" +
                        "id INT AUTO_INCREMENT PRIMARY KEY, " +
                        "full_name VARCHAR(255) NOT NULL)");

                stmt.execute("CREATE TABLE IF NOT EXISTS books (" +
                        "id INT AUTO_INCREMENT PRIMARY KEY, " +
                        "title VARCHAR(255) NOT NULL, " +
                        "author VARCHAR(255) NOT NULL, " +
                        "description VARCHAR(255), " +
                        "reader_id INT, " +
                        "FOREIGN KEY (reader_id) REFERENCES readers(id) ON DELETE SET NULL)");

                // 🛑 ВАЖЛИВО: Оскільки база тепер постійна, ми маємо перевіряти,
                // чи таблиця readers взагалі порожня, перед тим як додавати початкових
                // користувачів.
                // Інакше щоразу при запуску сервера у тебе дублюватимуться ті самі читачі!
                var rs = stmt.executeQuery("SELECT COUNT(*) FROM readers");
                if (rs.next() && rs.getInt(1) == 0) {
                    stmt.execute("INSERT INTO readers (full_name) VALUES ('Вероніка Нєма')");
                    stmt.execute("INSERT INTO readers (full_name) VALUES ('Олександр Іванов')");
                    stmt.execute("INSERT INTO readers (full_name) VALUES ('Марія Петренко')");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public DaoConnection getConnection() {
        try {
            Class.forName("org.h2.Driver");
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            return new JdbcDaoConnection(connection);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ReaderDao createReaderDao(DaoConnection daoConnection) {
        JdbcDaoConnection jdbcConnection = (JdbcDaoConnection) daoConnection;
        return new JdbcReaderDao(jdbcConnection.getConnection());
    }

    @Override
    public BookDao createBookDao(DaoConnection daoConnection) {
        JdbcDaoConnection jdbcConnection = (JdbcDaoConnection) daoConnection;
        return new JdbcBookDao(jdbcConnection.getConnection());
    }
}