package Vista;

import Modelo.Alumno;
import Persistencia.AlumnoData;
import java.sql.SQLException;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        try {
            AlumnoData alumnoData = new AlumnoData();

            try {
                Alumno alumno1 = new Alumno(
                        48674866,
                        "Carnota",
                        "Thiago Hernan",
                        LocalDate.of(2008, 4, 11),
                        false
                );

                if (!alumnoData.existeAlumnoPorDni(alumno1.getDni())) {

                    alumnoData.guardarAlumno(alumno1);

                    System.out.println("Alumno guardado con ID: "
                            + alumno1.getIdAlumno());

                } else {
                    System.out.println("El alumno ya estaba registrado: "
                            + alumno1.getApellido() + ", " + alumno1.getNombre());
                }

                Alumno alumno2 = new Alumno(
                        26784292,
                        "Carnota",
                        "Pablo German",
                        LocalDate.of(1978, 9, 8),
                        false
                );

                if (!alumnoData.existeAlumnoPorDni(alumno2.getDni())) {

                    alumnoData.guardarAlumno(alumno2);

                    System.out.println("Alumno guardado con ID: "
                            + alumno2.getIdAlumno());

                } else {
                    System.out.println("El alumno ya estaba registrado: "
                            + alumno2.getApellido() + ", " + alumno2.getNombre());
                }
                Alumno alumno3 = new Alumno(
                        29545876,
                        "Lucero",
                        "Juan Ignacio",
                        LocalDate.of(1990, 2, 12),
                        false
                );

                if (!alumnoData.existeAlumnoPorDni(alumno3.getDni())) {

                    alumnoData.guardarAlumno(alumno3);

                    System.out.println("Alumno guardado con ID: "
                            + alumno3.getIdAlumno());

                } else {
                    System.out.println("El alumno ya estaba registrado: "
                            + alumno3.getApellido() + ", " + alumno3.getNombre());
                }

                System.out.println("--- ALUMNOS REGISTRADOS ---");

                for (Alumno registrado : alumnoData.listarAlumnos()) {
                    System.out.println(registrado);
                }

            } finally {
                alumnoData.cerrarConexion();
            }

        } catch (ClassNotFoundException ex) {
            System.out.println("No se encontro el controlador.");
            System.out.println(ex.getMessage());

        } catch (SQLException ex) {
            System.out.println("Error de base de datos.");
            System.out.println(ex.getMessage());
        }
    }
}
