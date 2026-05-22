
package ua.kpi.model.dao.exception;

public class DaoException extends RuntimeException {
    public DaoException() {
        super("Error in DAO layer");
    }

    public DaoException(Throwable cause) {
        super(cause);
    }
}