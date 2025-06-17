package sistema;

import java.util.Arrays;
import java.util.List;

public class PaisesDisponibles {

    // Lista de países de Latinoamérica disponibles en el sistema
    private static final List<String> paises = Arrays.asList(
            "Argentina", "Bolivia", "Brasil", "Chile", "Colombia",
            "Costa Rica", "Cuba", "Ecuador", "El Salvador", "Guatemala",
            "Honduras", "México", "Nicaragua", "Panamá", "Paraguay",
            "Perú", "República Dominicana", "Uruguay", "Venezuela"
    );

    // Devuelve la lista de países disponibles
    public static List<String> obtenerPaises() {
        return paises;
    }

    // Verifica si un país está disponible
    public static boolean estaDisponible(String pais) {
        return paises.contains(pais);
    }

}
