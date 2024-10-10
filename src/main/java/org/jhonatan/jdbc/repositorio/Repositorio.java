package org.jhonatan.jdbc.repositorio;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

//de tipo generico
public interface Repositorio<T> {

    void setConn(Connection conn);

    List<T> listar() throws SQLException;

    T porId(Long id) throws SQLException;

    T guardar(T t) throws SQLException;

    void eliminar(Long id) throws SQLException;

}
