package sistema;
import java.util.*;

public class RedDeRutas {
    
     private Map<String, List<Conexion>> grafo;

    public RedDeRutas() {
        grafo = new HashMap<>();
    }

    // Agrega una conexión dirigida de origen a destino
    public void agregarConexion(String origen, String destino, double precio, double distancia) {
        grafo.putIfAbsent(origen, new ArrayList<>());
        grafo.putIfAbsent(destino, new ArrayList<>()); // por si luego se agregan conexiones desde ese nodo

        Conexion conexion = new Conexion(origen, destino, precio, distancia);
        grafo.get(origen).add(conexion);
    }

   // Devuelve la lista de conexiones desde una ciudad
    public List<Conexion> obtenerConexionesDesde(String ciudad) {
        return grafo.getOrDefault(ciudad, new ArrayList<>());
    }

    // Verifica si existe una ruta entre dos ciudades (por DFS)
    public boolean existeRuta(String origen, String destino) {
        Set<String> visitados = new HashSet<>();
        return rutaExiste(origen, destino, visitados);
    }

    private boolean rutaExiste(String actual, String destino, Set<String> visitados) {
        if (actual.equals(destino)) return true;// Caso base: encontramos el destino
        visitados.add(actual); // Marca la ciudad actual como visitada
        // Recorre todas las conexiones (ciudades vecinas) desde la ciudad actual
        for (Conexion conexion : grafo.getOrDefault(actual, new ArrayList<>())) {
            String siguiente = conexion.getDestino();
            if (!visitados.contains(siguiente)) {  // Si la ciudad siguiente no ha sido visitada, explórala recursivamente
                if (rutaExiste(siguiente, destino, visitados)) return true;
            }
        }
        return false;// No se encontró ruta al destino desde esta rama
    }

    // Ruta más corta por distancia usando Dijkstra
    public List<String> rutaMasCortaPorDistancia(String origen, String destino) {
        Map<String, Double> distancias = new HashMap<>();
        Map<String, String> anteriores = new HashMap<>();
        PriorityQueue<Conexion> cola = new PriorityQueue<>(Comparator.comparingDouble(Conexion::getDistancia));

        for (String ciudad : grafo.keySet()) {
            distancias.put(ciudad, Double.MAX_VALUE);
        }
        distancias.put(origen, 0.0);
        cola.add(new Conexion(null, origen, 0, 0)); // origen sin costo

        while (!cola.isEmpty()) {
            Conexion actual = cola.poll();
            String ciudadActual = actual.getDestino();

            for (Conexion conexion : grafo.getOrDefault(ciudadActual, new ArrayList<>())) {
                double nuevaDistancia = distancias.get(ciudadActual) + conexion.getDistancia();
                if (nuevaDistancia < distancias.get(conexion.getDestino())) {
                    distancias.put(conexion.getDestino(), nuevaDistancia);
                    anteriores.put(conexion.getDestino(), ciudadActual);
                    cola.add(new Conexion(conexion.getOrigen(), conexion.getDestino(), conexion.getPrecio(), nuevaDistancia));
                }
            }
        }

        return reconstruirRuta(origen, destino, anteriores);
    }

    // Ruta más barata por precio usando Dijkstra
    public List<String> rutaMasBarata(String origen, String destino) {
        Map<String, Double> costos = new HashMap<>();
        Map<String, String> anteriores = new HashMap<>();
        PriorityQueue<Conexion> cola = new PriorityQueue<>(Comparator.comparingDouble(Conexion::getPrecio));

        for (String ciudad : grafo.keySet()) {
            costos.put(ciudad, Double.MAX_VALUE);
        }
        costos.put(origen, 0.0);
        cola.add(new Conexion(null, origen, 0, 0)); // origen sin costo

        while (!cola.isEmpty()) {
            Conexion actual = cola.poll();
            String ciudadActual = actual.getDestino();

            for (Conexion conexion : grafo.getOrDefault(ciudadActual, new ArrayList<>())) {
                double nuevoCosto = costos.get(ciudadActual) + conexion.getPrecio();
                if (nuevoCosto < costos.get(conexion.getDestino())) {
                    costos.put(conexion.getDestino(), nuevoCosto);
                    anteriores.put(conexion.getDestino(), ciudadActual);
                    cola.add(new Conexion(conexion.getOrigen(), conexion.getDestino(), nuevoCosto, conexion.getDistancia()));
                }
            }
        }

        return reconstruirRuta(origen, destino, anteriores);
    }

    // Método auxiliar para reconstruir la ruta
    private List<String> reconstruirRuta(String origen, String destino, Map<String, String> anteriores) {
        LinkedList<String> ruta = new LinkedList<>();
        String actual = destino;
        while (actual != null) {
            ruta.addFirst(actual);
            actual = anteriores.get(actual);
        }
        if (!ruta.isEmpty() && ruta.getFirst().equals(origen)) {
            return ruta;
        } else {
            return new ArrayList<>(); // No se encontró ruta
        }
    }

    public Map<String, List<Conexion>> getGrafo() {
        return grafo;
    }
    
    
    
    
}
