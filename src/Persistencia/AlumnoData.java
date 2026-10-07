package Persistencia;

import Modelo.Conexion;
import java.sql.Connection;
import java.sql.SQLException;
import Modelo.Alumno;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class AlumnoData {

    private Connection con;

    public AlumnoData() throws SQLException, ClassNotFoundException {
        con = Conexion.getConexion();
    }

    public void guardarAlumno(Alumno alumno) throws SQLException {

        String sql = "INSERT INTO alumno"
                + "(dni, apellido, nombre, fechaNacimiento, estado) "
                + "VALUES (?,?,?,?,?)";

        try (PreparedStatement ps = con.prepareStatement(
                
                sql, Statement.RETURN_GENERATED_KEYS)) {
            
            ps.setInt(1, alumno.getDni());
            ps.setString(2, alumno.getApellido());
            ps.setString(3, alumno.getNombre());
            ps.setDate(4, Date.valueOf(alumno.getFechaNacimiento()));
            ps.setBoolean(5, alumno.isEstado());
            
            ps.executeUpdate();
            
            try (ResultSet resultado = ps.getGeneratedKeys()) {
            if (resultado.next()) {
                alumno.setIdAlumno(resultado.getInt(1));
                }

            }

        }

    }
    
    public List<Alumno> listarAlumnos() throws SQLException {

    List<Alumno> alumnos = new ArrayList<>();

    String sql = "SELECT * FROM alumno";

    try (PreparedStatement ps = con.prepareStatement(sql);
         ResultSet resultado = ps.executeQuery()) {

        while (resultado.next()) {

            Alumno alumno = new Alumno();

            alumno.setIdAlumno(resultado.getInt("idAlumno"));
            alumno.setDni(resultado.getInt("dni"));
            alumno.setApellido(resultado.getString("apellido"));
            alumno.setNombre(resultado.getString("nombre"));

            alumno.setFechaNacimiento(
                    resultado.getDate("fechaNacimiento").toLocalDate()
            );

            alumno.setEstado(resultado.getBoolean("estado"));

            alumnos.add(alumno);
        }
    }

    return alumnos;
}
    
    public void cerrarConexion() throws SQLException {
    con.close();
}
    public boolean existeAlumnoPorDni(int dni) throws SQLException {

    String sql = "SELECT idAlumno FROM alumno WHERE dni = ?";

    try (PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setInt(1, dni);

        try (ResultSet resultado = ps.executeQuery()) {
            return resultado.next();
        }
    }
}
    
    
}
