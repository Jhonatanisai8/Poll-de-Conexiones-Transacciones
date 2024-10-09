package org.jhonatan.jdbc.modelo.repositorio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.jhonatan.jdbc.modelo.Categoria;

public class CategoriaRepositorioImpl
        implements Repositorio<Categoria> {

    //atributo de la conexion a la base de datos
    private Connection conn;

    public CategoriaRepositorioImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Categoria> listar() throws SQLException {
        List<Categoria> categorias = new ArrayList<>();
        try ( Statement stmt = conn.createStatement();  ResultSet rs = stmt.executeQuery("SELECT * FROM  categoria")) {
            //poblamos
            while (rs.next()) {
                //agregamos  a la lista 
                categorias.add(crearCategoria(rs));
            }
        }
        return categorias;
    }

    @Override
    public Categoria porId(Long id) throws SQLException {
        Categoria cat = null;
        try ( PreparedStatement stmt
                = conn.prepareStatement("SELECT * FROM categoria AS c WHERE c.id_categoria = ?")) {
            //establecmos el parametro a la consulta
            stmt.setLong(1, id);
            try ( ResultSet rs = stmt.executeQuery()) {

                //si encuentra la categoria por el id
                if (rs.next()) {
                    cat = crearCategoria(rs);
                }
            }
        }
        return cat;
    }

    @Override
    public Categoria guardar(Categoria t) throws SQLException {
        String sql = null;
        //preguntamos
        if (t.getId() != null && t.getId() > 0) {
            sql = "UPDATE categoria SET nombre = ? WHERE id_categoria = ?";
        } else {
            sql = "INSERT INTO categoria (nombre) VALUES (?)";
        }
        //que devuelva el id generado del insert 
        try ( PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, t.getNombre());
            if (t.getId() != null && t.getId() > 0) {
                stmt.setLong(2, t.getId());
            }
            stmt.executeUpdate();
            if (t.getId() == null) {
                try ( ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        t.setId(rs.getLong(1));
                    }
                }
            }
        }
        return t;
    }

    @Override
    public void eliminar(Long id) throws SQLException {
        try ( PreparedStatement stmt = conn.prepareStatement("DELETE FROM categoria WHERE id_categoria  = ?")) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    private Categoria crearCategoria(final ResultSet rs) throws SQLException {
        Categoria categoria = new Categoria();
        //establemos los satributos
        categoria.setId(rs.getLong("id_categoria"));
        categoria.setNombre(rs.getString("categoria"));
        return categoria;
    }
}
