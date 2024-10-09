package org.jhonatan.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import org.jhonatan.jdbc.modelo.Categoria;
import org.jhonatan.jdbc.modelo.Producto;
import org.jhonatan.jdbc.modelo.repositorio.CategoriaRepositorioImpl;
import org.jhonatan.jdbc.modelo.repositorio.ProductoRepositorioImpl;
import org.jhonatan.jdbc.modelo.repositorio.Repositorio;
import org.jhonatan.jdbc.util.ConexionBaseDatos;

public class EjemploJbdcTrx {

    public static void main(String[] args) throws SQLException {
        System.out.println("JAVA Y JBDC TRANSACCIONES CON UN POLL DE  CONEXIONES");
        try ( Connection con = ConexionBaseDatos.getConnection()) {

            //cambiamos el commit
            if (con.getAutoCommit()) {
                con.setAutoCommit(false);
            }

            try {
                Repositorio<Categoria> repositorioCat = new CategoriaRepositorioImpl(con);
                System.out.println("\t===INSERTAR NUEVA CATEGORIA===");
                //para la categoria 
                Categoria categoria = new Categoria();
                categoria.setNombre("ElectroHogar");

                //guardamos 
                Categoria nuevaCategoria = repositorioCat.guardar(categoria);
                System.out.println("=CATEGORIA GUARDADO CON EXITO: " + nuevaCategoria.getId());
                System.out.println("");

                Repositorio<Producto> repositorio = new ProductoRepositorioImpl(con);
                System.out.println("\t===LISTA DE PRODUCTOS===");
                repositorio.listar().forEach(System.out::println);
                System.out.println("");

                System.out.println("\t===OBETENER POR ID====");
                System.out.println(repositorio.porId(1L));;
                System.out.println("");

                System.out.println("\t===INSERTAR NUEVO PRODUCTO===");
                Producto producto = new Producto();
                producto.setNombre("Refrigerador SanSumng");
                producto.setPrecio(4500);
                producto.setFechaRegistro(new Date());
                producto.setSku("alod12312");
                //le establecemos la categoria 
                producto.setCategoria(nuevaCategoria);

                //guardamos 
                repositorio.guardar(producto);
                System.out.println("=PRODUCTO GUARDADO CON EXITO: " + producto.getId());
                System.out.println("");

                System.out.println("\t===LISTA ACTUALIZADA DE PRODUCTOS===");
                repositorio.listar().forEach(System.out::println);

                System.out.println("\t===LISTA ACTUALIZADA DE CATEGORIAS===");
                repositorioCat.listar().forEach(System.out::println);

                //hacemos el commit
                con.commit();
            } catch (SQLException ex) {

                //si algo pasa mal revertimos los cambios
                con.rollback();
                System.out.println("Error el la clase main => " + ex.getMessage());
            }
        }
    }
}
