package sistema;

import java.time.LocalDateTime; //Se importa la libreria LocalDateTime para obtener fecha y hora exacta
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Vuelo {

    private int id; //Todos los atributos privados del vuelo
    private String origen;  
    private String destino;
    private LocalDateTime fechaHora;
    private int duracionMinutos;
    private double precio;
    private int capacidadEconomica;
    private int capacidadAlta;
    private List<Asiento> asientosEconomica = new ArrayList<>(); // Lista de asientos en clase economica
    private List<Asiento> asientosAlta = new ArrayList<>(); // Lista de asientos en clase alta

    //Se inicializa un constructor para todos los campos del vuelo 
    public Vuelo(int id, String origen, String destino, LocalDateTime fechaHora, int duracionMinutos, double precio, int capacidadEconomica, int capacidadAlta) {

        // Validaciones para que los datos del vuelo sean correctos
        if (origen == null) {
            throw new IllegalArgumentException("El origen no puede ser null.");
        }
        if (origen.isEmpty()) {
            throw new IllegalArgumentException("El origen no puede estar vacío.");
        }
        if (destino == null) {
            throw new IllegalArgumentException("El destino no puede ser null.");
        }
        if (destino.isEmpty()) {
            throw new IllegalArgumentException("El destino no puede estar vacío.");
        }
        if (fechaHora == null) {
            throw new IllegalArgumentException("Elegir una fecha y hora para el vuelo");
        }
        if (fechaHora.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("La fecha y hora deben ser futuras.");
        }
        if (duracionMinutos <= 0) {
            throw new IllegalArgumentException("La duración del vuelo debe ser positiva.");
        }
        if (precio < 0) {
            throw new IllegalArgumentException("El precio no puede ser menor a cero");
        }
        if (capacidadEconomica < 0) {
            throw new IllegalArgumentException("La capacidad económica no puede ser negativa.");
        }
        if (capacidadAlta < 0) {
            throw new IllegalArgumentException("La capacidad alta no puede ser negativa.");
        }
 
        // Asignacion de los valores validados a los atributos del objeto
        this.id = id;
        this.origen = origen;
        this.destino = destino;
        this.fechaHora = fechaHora;
        this.duracionMinutos = duracionMinutos;
        this.precio = precio;
        this.capacidadEconomica = capacidadEconomica;
        this.capacidadAlta = capacidadAlta;

        // Inicializa los asientos de clase economica con un codigos como "E1", "E2"
        for (int i = 1; i <= capacidadEconomica; i++) {
            asientosEconomica.add(new Asiento("E" + i));
        }
        //Se inicializa los asientos de clase alta con un codigo como "A1", "A2"
        for (int i = 1; i <= capacidadAlta; i++) {
            asientosAlta.add(new Asiento("A" + i));
        }
    }

  
    // Verifica si hay asiento disponible en una clase
    public boolean tieneAsientoDisponible(Reserva.ClaseViaje clase) {
        List<Asiento> lista = (clase == Reserva.ClaseViaje.ECONOMICA) ? asientosEconomica : asientosAlta;
        return lista.stream().anyMatch(a -> !a.estaOcupado());
    }

    // Ocupar un asiento disponible
    public String ocuparAsiento(Reserva.ClaseViaje clase, Pasajero p) {
        List<Asiento> lista = (clase == Reserva.ClaseViaje.ECONOMICA) ? asientosEconomica : asientosAlta;
        for (Asiento a : lista) {
            if (!a.estaOcupado()) {
                a.reservar(p);
                return a.getCodigo();
            }
        }
        return null;
    }

    // Retorna prioridad de clase para ordenamiento: 0 (alta), 1 (económica), 2 (ninguna)
    public int prioridadClase() {
        if (tieneAsientoDisponible(Reserva.ClaseViaje.ALTA)) return 0;
        if (tieneAsientoDisponible(Reserva.ClaseViaje.ECONOMICA)) return 1;
        return 2;
    }

    // Método para ordenar lista de vuelos por destino, fecha y clase
    public static void ordenarPorPaisFechaClase(List<Vuelo> vuelos) {
        if (vuelos == null) {
            System.out.println("La lista de vuelos es nula. Asegúrate de inicializarla antes de ordenar.");
            return;
        }

        if (vuelos.isEmpty()) {
            System.out.println(" No hay vuelos registrados en la lista. Agrega vuelos antes de ordenarlos.");
            return;
        }

        for (Vuelo vuelo : vuelos) {
            if (vuelo.getDestino() == null) {
                System.out.println("Uno de los vuelos no tiene destino definido.");
                return;
            }
            if (vuelo.getDestino().trim().isEmpty()) {
                System.out.println("Uno de los vuelos tiene destino vacío.");
                return;
            }
            if (vuelo.getFechaHora() == null) {
                System.out.println("Uno de los vuelos no tiene fecha asignada.");
                return;
            }
        }

        vuelos.sort(
            Comparator.comparing(Vuelo::getDestino, String.CASE_INSENSITIVE_ORDER)
                      .thenComparing(Vuelo::getFechaHora)
                      .thenComparingInt(Vuelo::prioridadClase)
        );

        System.out.println("Los vuelos han sido ordenados correctamente por país, fecha y clase.");
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getOrigen() {
        return origen;
    }

    public String getDestino() {
        return destino;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public double getPrecio() {
        return precio;
    }

    public int getDuracionMinutos() {
        return duracionMinutos;
    }

    public int getCapacidadEconomica() {
        return capacidadEconomica;
    }

    public int getCapacidadAlta() {
        return capacidadAlta;
    }

    public List<Asiento> getAsientosEconomica() {
        return asientosEconomica;
    }

    public List<Asiento> getAsientosAlta() {
        return asientosAlta;
    }
}
