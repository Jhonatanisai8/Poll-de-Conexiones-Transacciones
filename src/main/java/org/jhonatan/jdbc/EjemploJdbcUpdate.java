package org.jhonatan.jdbc;

import org.jhonatan.jdbc.modelo.Categoria;
import org.jhonatan.jdbc.modelo.Producto;
import org.jhonatan.jdbc.modelo.repositorio.ProductoRepositorioImpl;
import org.jhonatan.jdbc.modelo.repositorio.Repositorio;

public class EjemploJdbcUpdate {

    public static void main(String[] args) {
        System.out.println("JAVA Y JBDC");

        System.out.println("\t========LISTA DE PRODUCTOS========");
        Repositorio<Producto> repositorio = new ProductoRepositorioImpl();
        repositorio.listar().forEach(System.out::println);

        System.out.println("\t========POR ID========");
        System.out.println(repositorio.porId(3l));
        System.out.println("\t========MODIFICACION DE PRODUCTO========");
        Producto p = new Producto();
        p.setId(13l);
        p.setNombre("Laptop HP A232");
        p.setPrecio(5600);

        //creamos la categoria y establecemos al producto
        Categoria ca = new Categoria();
        ca.setIdCategoria(2l);
        p.setCategoria(ca);

        //guardamos
        repositorio.guardar(p);
        System.out.println("Producto modificado con exito");
        System.out.println("\t========LISTA DE PRODUCTOS========");
        repositorio.listar().forEach(System.out::println);
    }
}
