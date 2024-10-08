package org.jhonatan.jdbc;
import org.jhonatan.jdbc.modelo.Producto;
import org.jhonatan.jdbc.modelo.repositorio.ProductoRepositorioImpl;
import org.jhonatan.jdbc.modelo.repositorio.Repositorio;

public class EjemploJdbcDelete {

    public static void main(String[] args) {
        System.out.println("JAVA Y JBDC");

        System.out.println("\t========LISTA DE PRODUCTOS========");
        Repositorio<Producto> repositorio = new ProductoRepositorioImpl();
        repositorio.listar().forEach(System.out::println);

        System.out.println("\t========POR ID========");
        System.out.println(repositorio.porId(3l));
        System.out.println("\t========ELIMINACION DE PRODUCTO========");
        Producto p = new Producto();
        p.setId(13l);

        //guardamos
        repositorio.eliminar(p.getId());
        System.out.println("Producto eliminado con exito");
        System.out.println("\t========LISTA DE PRODUCTOS========");
        repositorio.listar().forEach(System.out::println);

    }
}
