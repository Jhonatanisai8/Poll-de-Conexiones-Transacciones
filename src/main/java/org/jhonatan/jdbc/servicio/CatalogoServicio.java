package org.jhonatan.jdbc.servicio;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import org.jhonatan.jdbc.modelo.Categoria;
import org.jhonatan.jdbc.modelo.Producto;
import org.jhonatan.jdbc.repositorio.CategoriaRepositorioImpl;
import org.jhonatan.jdbc.repositorio.ProductoRepositorioImpl;
import org.jhonatan.jdbc.repositorio.Repositorio;
import org.jhonatan.jdbc.util.ConexionBaseDatos;

public class CatalogoServicio
        implements Servicio {

    //atributos 
    private Repositorio<Producto> productoRepositorio;
    private Repositorio<Categoria> categoriaRepositorio;

    public CatalogoServicio() {
        this.productoRepositorio = new ProductoRepositorioImpl();
        this.categoriaRepositorio = new CategoriaRepositorioImpl();
    }

    @Override
    public List<Producto> listar() throws SQLException {
        try ( Connection conn = ConexionBaseDatos.getConnection()) {
            //asignamos la conexion
            productoRepositorio.setConn(conn);
            return productoRepositorio.listar();
        }
    }

    @Override
    public Producto porId(Long id) throws SQLException {
        try ( Connection conn = ConexionBaseDatos.getConnection()) {
            //asignamos la conexion
            productoRepositorio.setConn(conn);
            return productoRepositorio.porId(id);
        }
    }

    @Override
    public Producto guardar(Producto producto) throws SQLException {
        try ( Connection conn = ConexionBaseDatos.getConnection()) {
            //asignamos la conexion
            productoRepositorio.setConn(conn);
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
            Producto nuevoProducto = null;
            try {
                nuevoProducto = productoRepositorio.guardar(producto);
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                System.out.println("Error en guardar: " + e.getMessage());
            }
            return nuevoProducto;
        }
    }

    @Override
    public void eliminar(Long id) throws SQLException {
        try ( Connection conn = ConexionBaseDatos.getConnection()) {
            //asignamos la conexion
            productoRepositorio.setConn(conn);
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
            try {
                productoRepositorio.eliminar(id);
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                System.out.println("Error en guardar: " + e.getMessage());
            }
        }
    }

    @Override
    public List<Categoria> listarCategorias() throws SQLException {
        try ( Connection conn = ConexionBaseDatos.getConnection()) {
            //asignamos la conexion
            categoriaRepositorio.setConn(conn);
            return categoriaRepositorio.listar();
        }
    }

    @Override
    public Categoria porIdCategoria(Long id) throws SQLException {
        try ( Connection conn = ConexionBaseDatos.getConnection()) {
            //asignamos la conexion
            categoriaRepositorio.setConn(conn);
            return categoriaRepositorio.porId(id);
        }
    }

    @Override
    public Categoria guardarCategoria(Categoria c) throws SQLException {
        try ( Connection conn = ConexionBaseDatos.getConnection()) {
            //asignamos la conexion
            categoriaRepositorio.setConn(conn);
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
            Categoria nuevaCategoria = null;
            try {
                nuevaCategoria = categoriaRepositorio.guardar(c);
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                System.out.println("Error en guardar: " + e.getMessage());
            }
            return nuevaCategoria;
        }
    }

    @Override
    public void eliminarCategoria(Long id) throws SQLException {
        try ( Connection conn = ConexionBaseDatos.getConnection()) {
            //asignamos la conexion
            categoriaRepositorio.setConn(conn);
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
            try {
                categoriaRepositorio.eliminar(id);
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                System.out.println("Error en guardar: " + e.getMessage());
            }
        }
    }

    @Override
    public void guardarProductoConCategoria(Producto p, Categoria c) throws SQLException {
        try ( Connection conn = ConexionBaseDatos.getConnection()) {
            //asignamos la conexion
            productoRepositorio.setConn(conn);
            categoriaRepositorio.setConn(conn);
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
            try {

                //nuevas variables
                Categoria nueCategoria = categoriaRepositorio.guardar(c);
                //asignamos la categoria 
                p.setCategoria(nueCategoria);

                productoRepositorio.guardar(p);
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                System.out.println("Error en guardar: " + e.getMessage());
            }
        }
    }

}
