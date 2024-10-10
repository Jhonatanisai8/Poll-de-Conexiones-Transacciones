package org.jhonatan.jdbc.servicio;

import java.sql.SQLException;
import java.util.List;
import org.jhonatan.jdbc.modelo.Categoria;
import org.jhonatan.jdbc.modelo.Producto;

public interface Servicio {

    List<Producto> listar() throws SQLException;

    Producto porId(Long id) throws SQLException;

    Producto guardar(Producto producto) throws SQLException;

    void eliminar(Long id) throws SQLException;

    List<Categoria> listarCategorias() throws SQLException;

    Categoria porIdCategoria(Long id) throws SQLException;

    Categoria guardarCategoria(Categoria c) throws SQLException;

    void eliminarCategoria(Long id) throws SQLException;

    void guardarProductoConCategoria(Producto p, Categoria c) throws SQLException;
}
