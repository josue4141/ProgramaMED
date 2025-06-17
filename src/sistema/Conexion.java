package sistema;

public class Conexion {

    //Atributos privados asociados a la conexion 
    private String origen;
    private String destino;
    private double precio;
    private double distancia;

    public Conexion(String origen, String destino, double precio, double distancia) {
        this.origen = origen;
        this.destino = destino;
        this.precio = precio;
        this.distancia = distancia;
    }

    //getters para obtener los datos 
    public String getOrigen() {
        return origen;
    }

    public String getDestino() {
        return destino;
    }

    public double getPrecio() {
        return precio;
    }

    public double getDistancia() {
        return distancia;
    }

    @Override
    public String toString() {
        return origen + " → " + destino + " | $" + precio + " | " + distancia + " km";
        //Muestra la informacion por ejemplo   Guatemala → Antigua Guatemala | $250.0 | 120.0 km
    }

}
