package es.unex.cum.bd.practicapareja.model.dao;

import java.sql.SQLException;
import java.util.List;

public interface Dao <T, K>{

    T get(K k) throws SQLException;
    
    List<T> getAll() throws SQLException;
    
    void insert(T t) throws SQLException;
    
    void update(T t) throws SQLException;
    
    void delete(K k) throws SQLException;
}   