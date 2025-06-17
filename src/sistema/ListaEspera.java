package sistema;

import java.util.LinkedList;
import java.util.Queue;

public class ListaEspera { 
    
    //Utiliza una cola para mantener el orden de llegada.
    // Cola que almacena a los pasajeros en espera
    private Queue<Pasajero> colaEspera = new LinkedList<>();

    //Agrega un pasajero a la cola de espera
    public void agregarPasajero(Pasajero p) {
        if (p == null) {
            throw new IllegalArgumentException("No se puede agregar un pasajero vacío a la lista de espera.");
        }
        colaEspera.offer(p); // Agrega al final de la cola
    }
    //Atiende y remueve al primer pasajero en la lista de espera
    public Pasajero atenderPasajero() {
        return colaEspera.poll();
    }

    //Verifica si la lista de espera está vacía.
    public boolean estaVacia() {
        return colaEspera.isEmpty();
    }

    //Devuelve la cantidad de pasajeros que están esperando
    public int cantidadEnEspera() {
        return colaEspera.size();
    }

    //Devuelve la cola completa de pasajeros en espera.
    public Queue<Pasajero> getColaEspera() {
        return colaEspera;
    }

    //Elimina un pasajero por ID de pasaporte (simulando prioridad por código)
    public boolean eliminarPorIDPasaporte(String idPasaporte) {
        return colaEspera.removeIf(p -> p.getDocumentoID().equalsIgnoreCase(idPasaporte));
    }

    //Elimina al pasajero que fue insertado primero con un ID de pasaporte específico
    public Pasajero eliminarPorInsercion(String idPasaporte) {
        for (Pasajero p : colaEspera) {
            if (p.getDocumentoID().equalsIgnoreCase(idPasaporte)) {
                colaEspera.remove(p);
                return p; // Devuelve el pasajero eliminado
            }
        }
        return null; // No se encontró
    }
}
