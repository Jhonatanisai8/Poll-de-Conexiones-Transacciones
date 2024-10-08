package org.jhonatan.jdbc.util;

import java.sql.Connection;
import java.sql.SQLException;
import org.apache.commons.dbcp2.BasicDataSource;

public class ConexionBaseDatos {
    //indicamos la zona horario para evitar el error de la zona horaria

    private static final String ruta = "jdbc:mysql://localhost:3306/java_curso?serverTimezone=UTC";
    private static final String userName = "root";
    private static final String password = "1234";
    private static BasicDataSource poll;

    //patron singleton
    public static BasicDataSource getInstance() throws SQLException {
        if (poll == null) {
            poll = new BasicDataSource();

            //asiganamos los datos
            poll.setUrl(ruta);
            poll.setUsername(userName);
            poll.setPassword(password);

            //pol inicial 
            poll.setInitialSize(3);
            poll.setMinIdle(3);
            poll.setMaxIdle(8);
            poll.setMaxTotal(8); //cantidad maxima

        }
        return poll;
    }
    
    //metodo para obtener una nueva a la base de datos
    public static Connection getConnection() throws SQLException{
        return getInstance().getConnection();
    }
    
}
