/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistema;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author paola
 */
public class NodoEmpleado {
    
     private String nombre;
    private List<NodoEmpleado> hijos;

    public NodoEmpleado(String nombre) {
        this.nombre = nombre;
        this.hijos = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public List<NodoEmpleado> getHijos() {
        return hijos;
    }

    public void agregarHijo(NodoEmpleado hijo) {
        hijos.add(hijo);
    }
    
}
