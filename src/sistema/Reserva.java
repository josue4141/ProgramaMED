package sistema;

//Se importa la clase UUID para generar identificadores unicos para cada reserva
import java.util.UUID;

public class Reserva {

    // Identificador unico que se genera automáticamente para cada reserva
    private String idReserva;
    private Vuelo vuelo;
    private Pasajero pasajero;
    private String asiento;
    private ClaseViaje claseViaje;

    // Define las clases de viaje disponibles
    public enum ClaseViaje {
        ECONOMICA, ALTA
    }

    // Define los metodos de pago que se encuentran disponibles
    public enum MetodoPago {
        EFECTIVO, TARJETA
    }

    public Reserva(Vuelo vuelo, Pasajero pasajero, String asiento, ClaseViaje claseViaje, MetodoPago metodoPago) {
        if (vuelo == null) {
            throw new IllegalArgumentException("Seleccionar un vuelo valido");
        }
        if (pasajero == null) {
            throw new IllegalArgumentException("Ingresar los datos correctamente.");
        }
        if (asiento == null || asiento.trim().isEmpty()) {
            throw new IllegalArgumentException("El asiento no puede estar vacío.");
        }
        if (claseViaje == null) {
            throw new IllegalArgumentException("Seleccionar la clase en la que desea viajar");
        }
        if (metodoPago == null) {
            throw new IllegalArgumentException("Elige un metodo de pago para completar la reserva");
        }

        //Genera y asigna un ID unico para la reserva
        this.idReserva = UUID.randomUUID().toString();
        this.vuelo = vuelo;
        this.pasajero = pasajero;
        this.asiento = asiento;
        this.claseViaje = claseViaje;

    }

    public Reserva() {
    }

    // Getters para acceder a los atributos
    public String getIdReserva() {
        return idReserva;
    }

    public Vuelo getVuelo() {
        return vuelo;
    }

    public Pasajero getPasajero() {
        return pasajero;
    }

    public String getAsiento() {
        return asiento;
    }

    public ClaseViaje getClaseViaje() {
        return claseViaje;
    }

}
