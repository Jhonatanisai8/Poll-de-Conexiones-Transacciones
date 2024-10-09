package org.jhonatan.jdbc;

import java.sql.SQLException;
import java.util.Date;
import org.jhonatan.jdbc.modelo.Categoria;
import org.jhonatan.jdbc.modelo.Producto;
import org.jhonatan.jdbc.modelo.repositorio.ProductoRepositorioImpl;
import org.jhonatan.jdbc.modelo.repositorio.Repositorio;

public class EjemploJdbc {

    public static void main(String[] args) throws SQLException {
        System.out.println("JAVA Y JBDC TRANSACCIONES CON UN POLL DE  CONEXIONES");

        System.out.println("\t========LISTA DE PRODUCTOS========");
        Repositorio<Producto> repositorio = new ProductoRepositorioImpl();
        repositorio.listar().forEach(System.out::println);

        System.out.println("\t========POR ID========");
        System.out.println(repositorio.porId(1l));

        System.out.println("\t========CREAR UN OBJETO========");
        Producto p = new Producto();
        p.setNombre("Notebook Omen HP");
        p.setPrecio(4500);
        p.setFechaRegistro(new Date());

        //creamos una categoria
        Categoria c = new Categoria();
        c.setIdCategoria(3L);

        //establecemos la categoria al producto
        p.setCategoria(c);

        //guardamos
        repositorio.guardar(p);
        System.out.println("Producto guardado con exito");

        System.out.println("\t========LISTA DE PRODUCTOS========");
        repositorio.listar().forEach(System.out::println);

    }
}
