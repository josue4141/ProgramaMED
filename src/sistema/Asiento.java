package sistema;

//Cada asiento tiene un código único y puede estar ocupado por un pasajero.
public class Asiento {

    private String codigo;      // Código que identifica al asiento (por ejemplo: "A1", "E2")
    private Pasajero pasajero;  // Pasajero que ha reservado este asiento 

    // Constructor que crea un nuevo asiento con su código correspondiente. 
    public Asiento(String codigo) {
        this.codigo = codigo;
        this.pasajero = null; // El asiento comienza sin ocupar
    }

    //Verifica si el asiento esta ocupado
    public boolean estaOcupado() {
        return pasajero != null;
    }

    //Asigna un pasajero a este asiento, reservándolo.
    //Pasajero que ocupará el asiento
    public void reservar(Pasajero p) {
        this.pasajero = p;
    }

    //Libera el asiento, quitando al pasajero asignado.
    public void liberar() {
        this.pasajero = null;
    }

    // Devuelve el código del asiento.
    public String getCodigo() {
        return codigo;
    }

    // Devuelve el pasajero que ocupa el asiento.
    //Pasajero asignado o null si está libre
    public Pasajero getPasajero() {
        return pasajero;
    }
}
