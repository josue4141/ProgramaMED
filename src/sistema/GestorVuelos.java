package sistema;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import javax.swing.JComboBox;

public class GestorVuelos {

    private static GestorVuelos instance;

    private final List<Vuelo> vuelos; // lista principal de vuelos
    private NodoArbol raiz; // raíz del árbol para búsqueda por destino

    // Constructor privado para Singleton
    private GestorVuelos() {
        vuelos = new ArrayList<>();
        raiz = null;
    }

    // Inizializacion de lista
    public static synchronized GestorVuelos getInstancia() {
        if (instance == null) {
            instance = new GestorVuelos();
        }
        return instance;
    }

    // Inserta un vuelo a la lista y al árbol por destino
    public void agregarVuelo(Vuelo vuelo) {
        if (vuelo == null) {
            throw new IllegalArgumentException("El vuelo no puede ser nulo");
        }
        vuelos.add(vuelo);
        raiz = insertarEnArbol(raiz, vuelo);
    }

    // Busca un vuelo específico
    public Optional<Vuelo> buscarVuelo(String origen, String destino, LocalDateTime fecha, String codigo) {
        return vuelos.stream()
                .filter(v -> v.getOrigen().equalsIgnoreCase(origen)
                && v.getDestino().equalsIgnoreCase(destino)
                && v.getFechaHora().toLocalDate().equals(fecha.toLocalDate())
                && String.valueOf(v.getId()).equals(codigo))
                .findFirst();
    }

    // Carga los vuelos en un JComboBox
    public void cargarVuelosEnComboBox(JComboBox<String> comboBox) {
        comboBox.removeAllItems();
        vuelos.forEach(v -> comboBox.addItem(String.valueOf(v.getId())));
    }

    // Nodo para el árbol binario
    private static class NodoArbol {

        String destino;
        List<Vuelo> vuelosDestino;
        NodoArbol izquierda;
        NodoArbol derecha;

        NodoArbol(String destino) {
            this.destino = destino;
            this.vuelosDestino = new ArrayList<>();
        }
    }

    // Inserta vuelo en el árbol por destino
    private NodoArbol insertarEnArbol(NodoArbol nodo, Vuelo vuelo) {
        if (nodo == null) {
            NodoArbol nuevoNodo = new NodoArbol(vuelo.getDestino().toLowerCase());
            nuevoNodo.vuelosDestino.add(vuelo);
            return nuevoNodo;
        }

        int compare = vuelo.getDestino().toLowerCase().compareTo(nodo.destino);
        if (compare == 0) {
            nodo.vuelosDestino.add(vuelo);
        } else if (compare < 0) {
            nodo.izquierda = insertarEnArbol(nodo.izquierda, vuelo);
        } else {
            nodo.derecha = insertarEnArbol(nodo.derecha, vuelo);
        }
        return nodo;
    }

    // Busca vuelos por destino
    public Vuelo buscarVuelosPorDestino(String destino) {
        if (destino == null || destino.trim().isEmpty()) {
            return null;
        }

        NodoArbol nodo = buscarNodoDestino(raiz, destino.toLowerCase());
        if (nodo != null && !nodo.vuelosDestino.isEmpty()) {
            return nodo.vuelosDestino.get(0); // Devuelve el primer vuelo
        }

        return null;
    }

    private NodoArbol buscarNodoDestino(NodoArbol nodo, String destino) {
        if (nodo == null) {
            return null;
        }

        int compare = destino.compareTo(nodo.destino);
        if (compare == 0) {
            return nodo;
        } else if (compare < 0) {
            return buscarNodoDestino(nodo.izquierda, destino);
        } else {
            return buscarNodoDestino(nodo.derecha, destino);
        }
    }

    // Ordena la lista de vuelos por precio (más eficiente usando Collections.sort)
    public void ordenarPorPrecio() {
        vuelos.sort(Comparator.comparingDouble(Vuelo::getPrecio));
    }

    // Ordena la lista de vuelos por fecha/hora
    public void ordenarPorFechaHora() {
        vuelos.sort(Comparator.comparing(Vuelo::getFechaHora));
    }

    // Búsqueda binaria para buscar vuelo por precio
    public Optional<Vuelo> busquedaBinariaPorPrecio(double precio) {
        ordenarPorPrecio();
        int izquierda = 0;
        int derecha = vuelos.size() - 1;

        while (izquierda <= derecha) {
            int medio = izquierda + (derecha - izquierda) / 2;
            Vuelo vueloMedio = vuelos.get(medio);

            if (vueloMedio.getPrecio() == precio) {
                return Optional.of(vueloMedio);
            } else if (vueloMedio.getPrecio() < precio) {
                izquierda = medio + 1;
            } else {
                derecha = medio - 1;
            }
        }
        return Optional.empty();
    }

    // Obtener todos los vuelos (devuelve copia para evitar modificaciones externas)
    public List<Vuelo> getVuelos() {
        return new ArrayList<>(vuelos);
    }

    // Método para limpiar todos los vuelos (útil para testing)
    public void limpiarVuelos() {
        vuelos.clear();
        raiz = null;
    }

    // Método para obtener cantidad de vuelos
    public int cantidadVuelos() {
        return vuelos.size();
    }
}
