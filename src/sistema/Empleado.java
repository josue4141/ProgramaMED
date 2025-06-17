
package sistema;

/**
 *
 * @author paola
 */
public class Empleado {
    
     private NodoEmpleado raiz;

    public Empleado() {
        raiz = new NodoEmpleado("Empleados");

        NodoEmpleado agentes = new NodoEmpleado("Agentes de Reserva");
        agentes.agregarHijo(new NodoEmpleado("Ana"));
        agentes.agregarHijo(new NodoEmpleado("Carlos"));
        agentes.agregarHijo(new NodoEmpleado("Beatriz"));

        NodoEmpleado planificadores = new NodoEmpleado("Planificadores de Ruta");
        planificadores.agregarHijo(new NodoEmpleado("Daniel"));
        planificadores.agregarHijo(new NodoEmpleado("Angel"));
        planificadores.agregarHijo(new NodoEmpleado("Paola"));

        NodoEmpleado pilotos = new NodoEmpleado("Pilotos");
        pilotos.agregarHijo(new NodoEmpleado("Pedro"));
        pilotos.agregarHijo(new NodoEmpleado("Mari­a"));
        pilotos.agregarHijo(new NodoEmpleado("Aaron"));

        NodoEmpleado azafatas = new NodoEmpleado("Azafatas");
        azafatas.agregarHijo(new NodoEmpleado("Laura"));
        azafatas.agregarHijo(new NodoEmpleado("SofÃ­a"));

        raiz.agregarHijo(agentes);
        raiz.agregarHijo(planificadores);
        raiz.agregarHijo(pilotos);
        raiz.agregarHijo(azafatas);
    }

    public void agregarJerarquiaATexto(StringBuilder sb) {
        agregarNombres(raiz, sb, "");
    }

    private void agregarNombres(NodoEmpleado nodo, StringBuilder sb, String indent) {
        if (nodo != null) {
            sb.append(indent).append("- ").append(nodo.getNombre()).append("\n");
            for (NodoEmpleado hijo : nodo.getHijos()) {
                agregarNombres(hijo, sb, indent + "  ");
            }
        }
    }

    
}
