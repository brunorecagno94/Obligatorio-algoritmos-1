package dominio;

import java.util.Objects;
import tads.ListaNodos;

public class Usuario implements Comparable {

    private String cedula;
    private String nombre;
    //private ListaNodos<Alquiler> alquileres;

    // Getters
    public String getCedula() {
        return cedula;
    }

    public String getNombre() {
        return nombre;
    }

//    public ListaNodos getAlquileres() {
//        return alquileres;
//    }
    // Setters
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

//    public void setAlquileres(ListaNodos alquileres) {
//        this.alquileres = alquileres;
//    }
    public Usuario(String cedula, String nombre) {
        this.cedula = cedula;
        this.nombre = nombre;
        //this.alquileres = new ListaNodos<Alquiler>();
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + ", Cedula: " + cedula;
    }

    
    @Override
    public int compareTo(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        return Objects.equals(this.cedula, other.cedula);
    }

}
