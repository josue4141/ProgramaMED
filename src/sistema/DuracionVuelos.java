package sistema;

// Importa las clases necesarias para manejar mapas
import java.util.HashMap;
import java.util.Map;

public class DuracionVuelos {

    // Mapa de distancias aproximadas entre ciudades latinoamericanas (en kilómetros)
    private static final Map<String, Double> distancias = new HashMap<>();

    // Bloque estático de inicialización donde se llenan los datos del mapa de distancias
    static {
        distancias.put("Argentina-Brasil", 2300.0);
        distancias.put("Argentina-Chile", 1400.0);
        distancias.put("México-Colombia", 3100.0);
        distancias.put("Perú-Colombia", 1800.0);
        distancias.put("Chile-Perú", 2500.0);
        distancias.put("Ecuador-Venezuela", 2000.0);
        distancias.put("Uruguay-Paraguay", 1100.0);
        distancias.put("Bolivia-Paraguay", 1500.0);
        distancias.put("Cuba-Panamá", 1600.0);
        distancias.put("Costa Rica-Nicaragua", 400.0);
    }

    // Método público y estático para obtener la distancia entre dos ciudades
    public static double obtenerDistancia(String origen, String destino) {
        // Se forman dos claves: origen-destino y destino-origen
        String clave1 = origen + "-" + destino;
        String clave2 = destino + "-" + origen;

        // Se busca si alguna de las dos claves existe en el mapa
        if (distancias.containsKey(clave1)) {
            return distancias.get(clave1); // Retorna la distancia si se encuentra en orden origen-destino
        } else if (distancias.containsKey(clave2)) {
            return distancias.get(clave2); // Retorna la distancia si está en orden inverso
        } else {
            return -1; // Distancia desconocida
        }
    }

}
