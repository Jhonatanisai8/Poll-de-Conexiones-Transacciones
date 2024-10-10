package org.jhonatan.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import org.jhonatan.jdbc.modelo.Categoria;
import org.jhonatan.jdbc.modelo.Producto;
import org.jhonatan.jdbc.repositorio.CategoriaRepositorioImpl;
import org.jhonatan.jdbc.repositorio.ProductoRepositorioImpl;
import org.jhonatan.jdbc.repositorio.Repositorio;
import org.jhonatan.jdbc.servicio.CatalogoServicio;
import org.jhonatan.jdbc.servicio.Servicio;
import org.jhonatan.jdbc.util.ConexionBaseDatos;

public class EjemploJbdcTrxService {

    public static void main(String[] args) throws SQLException {
        System.out.println("\t==REPOSITORIO DE PRODUCTOS==");
        //objeto de la clase Servicio
        Servicio servicio = new CatalogoServicio();

        System.out.println("\t==LISTAR PRODUCTOS==");
        servicio.listar().forEach(System.out::println);
        System.out.println("");

        System.out.println("\t==INSERTAR UNA NUEVA CATEGORIA==");
        Categoria categoria = new Categoria();
        categoria.setNombre("Iluminacion");
        System.out.println("");

        System.out.println("\t==INSERTAR UNA NUEVA PRODUCTO==");
        Producto producto = new Producto();
        producto.setNombre("Lampara Led Escritorio");
        producto.setPrecio(990);
        producto.setFechaRegistro(new Date());
        producto.setSku("abodff2312");
        servicio.guardarProductoConCategoria(producto, categoria);

        System.out.println("Producto Insertado------ " + producto.getId());
        System.out.println("");
        System.out.println("\t==LISTAR PRODUCTOS==");
        servicio.listar().forEach(System.out::println);
        System.out.println("");

    }
}
