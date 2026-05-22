package ua.kpi.model.dao;

import ua.kpi.model.entity.Reader;
import java.util.List;

public interface ReaderDao extends GenericDao<Reader> {
    List<Reader> findByFullName(String fullName);
}