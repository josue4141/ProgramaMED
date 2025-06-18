package sistema;

import java.util.ArrayList;
import java.util.List;

public class GestorReservas { //Permite agregar nuevas reservas y consultar la lista existente
    
    private static GestorReservas instance;

    // Lista donde se almacenan todas las reservas
    private List<Reserva> reservas;

    public GestorReservas() {
        reservas = new ArrayList<>();
    }
    
    // Inizializacion de lista
    public static synchronized GestorReservas getInstancia() {
        if (instance == null) {
            instance = new GestorReservas();
        }
        return instance;
    }
    
    
    //Agrega una nueva reserva al sistema, siempre que no exista ya una igual. r La reserva que se desea agregar

    public void agregarReserva(Reserva r) {
        for (Reserva res : reservas) {
            // Verificamos si el pasajero ya tiene una reserva en ese vuelo
            if (res.getVuelo().getId() == r.getVuelo().getId() &&
                res.getPasajero().equals(r.getPasajero())) {
                throw new IllegalArgumentException("Este pasajero ya tiene una reserva para este vuelo.");
            }
        }
        reservas.add(r);// Si no hay duplicado, se agrega la nueva reserva
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

}
