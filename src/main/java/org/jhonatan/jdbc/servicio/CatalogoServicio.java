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

        }
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Producto porId(Long id) throws SQLException {
        try ( Connection conn = ConexionBaseDatos.getConnection()) {
            //asignamos la conexion
            productoRepositorio.setConn(conn);

        }
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Producto guardar(Producto producto) throws SQLException {
        try ( Connection conn = ConexionBaseDatos.getConnection()) {
            //asignamos la conexion
            productoRepositorio.setConn(conn);

        }
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminar(Long id) throws SQLException {
        try ( Connection conn = ConexionBaseDatos.getConnection()) {
            //asignamos la conexion
            productoRepositorio.setConn(conn);
        }
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Categoria> listarCategorias() throws SQLException {
        try ( Connection conn = ConexionBaseDatos.getConnection()) {
            //asignamos la conexion
            categoriaRepositorio.setConn(conn);
        }
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Categoria porIdCategoria(Long id) throws SQLException {
        try ( Connection conn = ConexionBaseDatos.getConnection()) {
            //asignamos la conexion
            categoriaRepositorio.setConn(conn);
        }
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Categoria guardarCategoria() throws SQLException {
        try ( Connection conn = ConexionBaseDatos.getConnection()) {
            //asignamos la conexion
            categoriaRepositorio.setConn(conn);
        }
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminarCategoria(Long id) throws SQLException {
        try ( Connection conn = ConexionBaseDatos.getConnection()) {
            //asignamos la conexion
            categoriaRepositorio.setConn(conn);
        }
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void guardarProductoConCategoria(Producto p, Categoria c) throws SQLException {
        try ( Connection conn = ConexionBaseDatos.getConnection()) {
            //asignamos la conexion
            productoRepositorio.setConn(conn);
            categoriaRepositorio.setConn(conn);
        }
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
