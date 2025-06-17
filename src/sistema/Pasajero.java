
package sistema;

import java.time.LocalDate;
import java.util.ArrayList; //Se importa la clase LocalDate para manejar fechas y ArrayList y List para manejar colecciones
import java.util.List;

// La clase Pasajero extiende de la clase Persona, heredando sus atributos y métodos
public class Pasajero  extends Persona {
    
 private String tipoPersona;
    private List<Reserva> reservas;
    private boolean tieneVisa;
    private boolean esMenorDeEdad;
    private String acompanante;
    private String nacionalidad;

    public Pasajero(String nombre, String apellido, String documentoID, LocalDate fechaNacimiento, String email, String telefono) {
        super(nombre, apellido, documentoID, fechaNacimiento, email);
        this.tipoPersona = "Adulto";
        this.tieneVisa = false;
        this.esMenorDeEdad = getEdad() < 18;
        this.acompanante = null;
        this.nacionalidad = "Desconocida";
        this.reservas = new ArrayList<>();
    }

     // Método que indica si el pasajero menor de edad necesita acompañante (si no tiene asignado uno)
    public boolean necesitaAcompanante() {
        return esMenorDeEdad && (acompanante == null || acompanante.isEmpty());
    }

    // Asigna un acompañante solo si el pasajero es menor de edad
    public void asignarAcompanante(String nombreAcompanante) {
        if (esMenorDeEdad) {
            this.acompanante = nombreAcompanante;
        }
    }
    // Agrega una reserva a la lista, si no tiene ya una reserva para ese vuelo
    public void agregarReserva(Reserva reserva) {
        for (Reserva r : reservas) {
            if (r.getVuelo().getId() == reserva.getVuelo().getId()) {
                return; // Ya tiene reserva para este vuelo
            }
        }
        reservas.add(reserva); // Agrega la reserva a la lista
    }
       


    public String getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(String tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public boolean isTieneVisa() {
        return tieneVisa;
    }

    public void setTieneVisa(boolean tieneVisa) {
        this.tieneVisa = tieneVisa;
    }

    public boolean isEsMenorDeEdad() {
        return esMenorDeEdad;
    }
    
    // Si cambia a no menor, elimina el acompañante porque no es necesario
    public void setEsMenorDeEdad(boolean esMenorDeEdad) {
        this.esMenorDeEdad = esMenorDeEdad;
        if (!esMenorDeEdad) {
            this.acompanante = null;
        }
    }

    public String getAcompanante() {
        return acompanante;
    }

    // Solo permite asignar acompañante si es menor de edad
    public void setAcompanante(String acompanante) {
        if (esMenorDeEdad) {
            this.acompanante = acompanante;
        }
    }

     // Devuelve una copia de la lista de reservas para evitar manipulación externa directa
    public List<Reserva> getReservas() {
        return new ArrayList<>(reservas);
    }

   // Permite cancelar una reserva, devuelve true si fue eliminada
    public boolean cancelarReserva(Reserva reserva) {
        return reservas.remove(reserva);
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }
    
    
    
    
    
}