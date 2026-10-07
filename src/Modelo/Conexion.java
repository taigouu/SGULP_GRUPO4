
package Modelo;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexion {
    
    private static final String URL = "jdbc:mariadb://localhost:3307/sgulp";
    private static final String USUARIO = "root";
    private static final String CLAVE = "";
    
public static Connection getConexion()
        throws SQLException, ClassNotFoundException {

    Class.forName("org.mariadb.jdbc.Driver");

    return DriverManager.getConnection(URL, USUARIO, CLAVE);
}
    
    
    
}
