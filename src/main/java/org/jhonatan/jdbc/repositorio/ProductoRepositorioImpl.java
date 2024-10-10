package org.jhonatan.jdbc.repositorio;

import java.sql.*;
import java.util.*;
import org.jhonatan.jdbc.modelo.*;
import org.jhonatan.jdbc.util.ConexionBaseDatos;

public class ProductoRepositorioImpl
        implements Repositorio<Producto> {

    //atributo de esta clase 
    private Connection con;

    //constructor 
    public ProductoRepositorioImpl(Connection con) {
        this.con = con;
    }

    private Connection getConection() throws SQLException {
        return ConexionBaseDatos.getConnection();
    }

    @Override
    public List<Producto> listar() throws SQLException {
        List<Producto> productos = new ArrayList<>();
        try ( Statement stmt = con.createStatement();  ResultSet rs = stmt.executeQuery("SELECT p.*,c.categoria AS categoria FROM productos AS p  INNER JOIN categoria AS c ON p.id_categoria = c.id_categoria")) {
            while (rs.next()) {
                Producto p = creaProducto(rs);
                //agregamos al arraylist
                productos.add(p);
            }
        }
        //revolvemos la lista
        return productos;
    }

    @Override
    public Producto porId(Long id) throws SQLException {
        Producto p = null;
        try ( PreparedStatement stmt = con.prepareStatement("SELECT p.*,c.categoria AS categoria FROM productos AS p  INNER JOIN categoria AS c ON p.id_categoria = c.id_categoria "
                + " WHERE p.id_categoria = ?")) {
            //parametro de la consulta
            stmt.setLong(1, id);
            try ( //ejecutamos la consulta y se cierra automaticamente
                     ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    p = creaProducto(rs);
                }
            }
        }
        return p;
    }

    @Override
    public Producto guardar(Producto t) throws SQLException {
        String sql;
        if (t.getId() != null && t.getId() > 0) {
            sql = "UPDATE productos  "
                    + "SET nombre = ?, "
                    + " precio = ?, "
                    + "id_categoria = ?, "
                    + "sku = ?  "
                    + " WHERE idproducto = ? ";
        } else {
            sql = "INSERT INTO productos"
                    + " (nombre,precio,id_categoria,sku,fecha) "
                    + "VALUES (?,?,?,?,?)";
        }
        try ( PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            //le pasamos los parametros
            stmt.setString(1, t.getNombre());
            stmt.setLong(2, t.getPrecio());
            stmt.setLong(3, t.getCategoria().getId());
            stmt.setString(4, t.getSku());

            if (t.getId() != null && t.getId() > 0) {
                stmt.setLong(5, t.getId());
            } else {
                stmt.setDate(5, new java.sql.Date(t.getFechaRegistro().getTime()));
            }
            //ejecutamos
            stmt.executeUpdate();
            //si el id el igual a null
            if (t.getId() == null) {
                try ( ResultSet rs = stmt.getGeneratedKeys()) {
                    //movemos el cursor si tiene un id generado o incrementado
                    if (rs.next()) {
                        t.setId(rs.getLong(1));
                    }
                }
            }
        }
        //retornamos el producto
        return t;
    }

    @Override
    public void eliminar(Long id) throws SQLException {
        try ( PreparedStatement stmt = con.prepareStatement("DELETE FROM productos WHERE idproducto = ?")) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    public Producto creaProducto(ResultSet rs) throws SQLException {
        Producto p = new Producto();
        p.setId(rs.getLong("idproducto"));
        p.setNombre(rs.getString("nombre"));
        p.setPrecio(rs.getInt("precio"));
        p.setFechaRegistro(rs.getDate("fecha"));
        p.setSku(rs.getString("sku"));

        Categoria c = new Categoria();
        c.setId(rs.getLong("id_categoria"));
        c.setNombre(rs.getString("categoria"));
        //estableecmos la categoria
        p.setCategoria(c);
        return p;
    }
}
