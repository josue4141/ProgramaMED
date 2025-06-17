package sistema;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GestorVuelos {
    
    private List<Vuelo> vuelos; // lista principal de vuelos
    private NodoArbol raiz; // raíz del árbol para búsqueda por destino

    public GestorVuelos() {
        vuelos = new ArrayList<>();
        raiz = null;
    }
    public static GestorVuelos getInstancia() {
        if (instancia == null) {
            instancia = new GestorVuelos();
        }
        return instancia;
    }
    
    // Inserta un vuelo a la lista y al árbol por destino
    
    public void agregarVuelo(Vuelo vuelo) {
        vuelos.add(vuelo);
    }
    
    public Optional<Vuelo> buscarVuelo(String origen, String destino, LocalDateTime fecha, String codigo) {
        return vuelos.stream()
                .filter(v -> v.getOrigen().equalsIgnoreCase(origen) &&
                            v.getDestino().equalsIgnoreCase(destino) &&
                            v.getFechaHora().toLocalDate().equals(fecha.toLocalDate()) &&
                            String.valueOf(v.getId()).equals(codigo))
                .findFirst();
    }
    
    public void cargarVuelosEnComboBox(javax.swing.JComboBox<String> comboBox) {
        comboBox.removeAllItems();
        vuelos.forEach(v -> comboBox.addItem(String.valueOf(v.getId())));
    }

   // Nodo para el árbol binario, cada nodo guarda destino y lista de vuelos con ese destino
    private class NodoArbol {
        String destino;
        List<Vuelo> vuelosDestino;
        NodoArbol izquierda;
        NodoArbol derecha;

        NodoArbol(String destino) {
            this.destino = destino;
            vuelosDestino = new ArrayList<>();
            izquierda = null;
            derecha = null;
        }
    }


    // Inserta vuelo en el árbol por destino (alfabético)
    private NodoArbol insertarEnArbol(NodoArbol nodo, Vuelo vuelo) {
        if (nodo == null) {
            NodoArbol nuevoNodo = new NodoArbol(vuelo.getDestino().toLowerCase());
            nuevoNodo.vuelosDestino.add(vuelo);
            return nuevoNodo;
        }

        int compare = vuelo.getDestino().toLowerCase().compareTo(nodo.destino);
        if (compare == 0) {
            // mismo destino, agregamos vuelo a la lista
            nodo.vuelosDestino.add(vuelo);
        } else if (compare < 0) {
            nodo.izquierda = insertarEnArbol(nodo.izquierda, vuelo);
        } else {
            nodo.derecha = insertarEnArbol(nodo.derecha, vuelo);
        }
        return nodo;
    }

    // Busca vuelos por destino usando búsqueda en árbol binario
    public List<Vuelo> buscarVuelosPorDestino(String destino) {
        NodoArbol nodo = buscarNodoDestino(raiz, destino.toLowerCase());
        if (nodo != null) {
            return nodo.vuelosDestino;
        }
        return new ArrayList<>(); // vacío si no encuentra
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

    // Ordena la lista de vuelos por precio usando ordenamiento por inserción
    public void ordenarPorPrecio() {
        for (int i = 1; i < vuelos.size(); i++) {
            Vuelo key = vuelos.get(i);
            int j = i - 1;
            while (j >= 0 && vuelos.get(j).getPrecio() > key.getPrecio()) {
                vuelos.set(j + 1, vuelos.get(j));
                j--;
            }
            vuelos.set(j + 1, key);
        }
    }

    // Ordena la lista de vuelos por fecha/hora usando ordenamiento por inserción
    public void ordenarPorFechaHora() {
        for (int i = 1; i < vuelos.size(); i++) {
            Vuelo key = vuelos.get(i);
            int j = i - 1;
            while (j >= 0 && vuelos.get(j).getFechaHora().isAfter(key.getFechaHora())) {
                vuelos.set(j + 1, vuelos.get(j));
                j--;
            }
            vuelos.set(j + 1, key);
        }
    }

    // Búsqueda binaria para buscar vuelo por precio en lista ordenada (por precio)
    public Vuelo busquedaBinariaPorPrecio(double precio) {
        ordenarPorPrecio();
        int izquierda = 0;
        int derecha = vuelos.size() - 1;

        while (izquierda <= derecha) {
            int medio = (izquierda + derecha) / 2;
            Vuelo vueloMedio = vuelos.get(medio);
            if (vueloMedio.getPrecio() == precio) {
                return vueloMedio;
            } else if (vueloMedio.getPrecio() < precio) {
                izquierda = medio + 1;
            } else {
                derecha = medio - 1;
            }
        }
        return null;
    }

    // Obtener todos los vuelos
    public List<Vuelo> getVuelos() {
        return vuelos;
    }
    
}
