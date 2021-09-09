

package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion implements Properties{
    public static Connection getConnection() {
        // conectar
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(
                    Properties.dbURL,Properties.username,Properties.password);
            if (conn != null) {
                System.out.println("***CONECTADO A LA BASE DE DATOS***");
                return conn;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return conn;
    }
}
