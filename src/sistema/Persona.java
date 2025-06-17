package sistema;

// Importación de la clase LocalDate para manejar fechas
// Importación de la clase Period para calcular la diferencia de años entre fechas (edad).
import java.time.LocalDate;
import java.time.Period;

/**
 * Aldana
 */
public class Persona {

    // Atributos protegidos para permitir acceso desde subclases
    protected String nombre;
    protected String apellido;
    protected String documentoID;
    protected LocalDate fechaNacimiento;
    protected String email;
    

      //Constructor que inicializa una nueva persona con sus datos personales   
    public Persona(String nombre, String apellido, String documentoID, LocalDate fechaNacimiento, String email) {

        if (nombre == null) {
            throw new IllegalArgumentException("Por favor ingresa tu nombre");
        }
        if (nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacio");
        }

        if (apellido == null) {
            throw new IllegalArgumentException("Por favor ingresa tu apellido");
        }
        if (apellido.trim().isEmpty()) {
            throw new IllegalArgumentException("El apellido no puede estar vacio");
        }

        if (documentoID == null) {
            throw new IllegalArgumentException("Ingresar tu documento de identidad");
        }
        if (documentoID.trim().isEmpty()) {
            throw new IllegalArgumentException("El documento de identidad no puede estar vacío.");
        }

        if (fechaNacimiento == null) {
            throw new IllegalArgumentException("Indicar tu fecha de nacimiento.");
        }
        if (fechaNacimiento.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de nacimiento no puede ser posterior a hoy.");
        }

        // Asignación de los valores a los atributos
        this.nombre = nombre;
        this.apellido = apellido;
        this.documentoID = documentoID;
        this.fechaNacimiento = fechaNacimiento;
        this.email = email;
        
    }

    // Calcula la edad de la persona a partir de su fecha de nacimiento. 
    public int getEdad() {
        return Period.between(fechaNacimiento, LocalDate.now()).getYears();
    }

    // Métodos para obtener los valores de cada campo
    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getDocumentoID() {
        return documentoID;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getEmail() {
        return email;
    }

 

    // Muestra la informacion de la persona.
    public String mostrarInformacion() {
        return nombre + " " + apellido + " - ID: " + documentoID + " - Edad: " + getEdad() + " - Email: " + email;
    }

    @Override
    public String toString() {
        return mostrarInformacion();
    }
}
