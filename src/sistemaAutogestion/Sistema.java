package sistemaAutogestion;

import tads.ListaNodos;
import tads.NodoLista;
import dominio.*;
import java.util.HashSet;
import java.util.Set;

//Bruno Recagno-333245, Victoria Calvo-339977
public class Sistema implements IObligatorio {

    ListaNodos<Estacion> estaciones;
    ListaNodos<Usuario> usuarios;
    ListaNodos<Bicicleta> bicicletasEnEstaciones;
    ListaNodos<Bicicleta> bicicletasEnDeposito;
    MapaEstaciones mapaEstaciones;

    public Sistema() {
        this.estaciones = new ListaNodos<Estacion>();
        this.usuarios = new ListaNodos<Usuario>();
        this.bicicletasEnEstaciones = new ListaNodos<Bicicleta>();
        this.bicicletasEnDeposito = new ListaNodos<Bicicleta>();
        this.mapaEstaciones = new MapaEstaciones();
    }

    public static void main(String[] args) {
        Sistema s = new Sistema();
        s.registrarUsuario("22223333", "Carlos");
        s.registrarUsuario("33334444", "Ana");
        s.registrarUsuario("44445555", "Beatriz");
        
        System.out.println(s.listarUsuarios());
    }
    @Override
    public Retorno crearSistemaDeGestion() {
        Sistema sistema = new Sistema();
        return Retorno.ok();
    }

    @Override
    public Retorno registrarEstacion(String nombre, String barrio, int capacidad) {
        if (nombre == null
                || nombre.isBlank()
                || barrio == null
                || barrio.isBlank()) {
            return Retorno.error1();
        }
        if (capacidad <= 0) {
            return Retorno.error2();
        }

        Estacion e = new Estacion(nombre, barrio, capacidad);

        if (estaciones.obtenerElemento(e) != null) {
            return Retorno.error3();
        }

        estaciones.agregarOrd(e);
        return Retorno.ok();
    }

    @Override
    public Retorno registrarUsuario(String cedula, String nombre) {
        if (cedula == null || nombre == null || cedula.isBlank() || nombre.isBlank()) {
            return Retorno.error1();
        }
        if (cedula.length() != 8 || !cedula.matches("\\d+")) {
            return Retorno.error2();
        }

        Usuario usuario = new Usuario(cedula, nombre);

        if (usuarios.obtenerElemento(usuario) != null) {
            return Retorno.error3();
        }

        usuarios.agregarOrd(usuario);
        return Retorno.ok();
    }

    @Override
    public Retorno registrarBicicleta(String codigo, String tipo) {
        if (codigo == null
                || codigo.isBlank()
                || tipo == null
                || tipo.isBlank()) {
            return Retorno.error1();
        }
        if (codigo.length() != 6) {
            return Retorno.error2();
        }
        if (!tipo.equals("URBANA")
                && !tipo.equals("MOUNTAIN")
                && !tipo.equals("ELECTRICA")) {
            return Retorno.error3();
        }

        Bicicleta b = new Bicicleta(codigo, tipo);

        if (bicicletasEnEstaciones.obtenerElemento(b) != null || bicicletasEnDeposito.obtenerElemento(b) != null) {
            return Retorno.error4();
        }

        bicicletasEnDeposito.agregarOrd(b);
        return Retorno.ok();
    }

    @Override
    public Retorno marcarEnMantenimiento(String codigo, String motivo) {
        if (codigo == null
                || codigo.isBlank()
                || motivo == null
                || motivo.isBlank()) {
            return Retorno.error1();
        }

        NodoLista nodo = encontrarBicicleta(codigo);

        if (nodo == null) {
            return Retorno.error2();
        }

        Bicicleta b = (Bicicleta) nodo.getDato();

        if (b.getEstado().contains("Alquilada")) {
            return Retorno.error3();
        }
        if (b.getEstado().contains("Mantenimiento")) {
            return Retorno.error4();
        }

        b.setEstado("Mantenimiento");
        b.setEstacionAsignada(null);
        bicicletasEnEstaciones.borrarElemento(b);
        bicicletasEnDeposito.agregarOrd(b);

        return Retorno.ok();
    }

    @Override
    public Retorno repararBicicleta(String codigo) {
        if (codigo == null
                || codigo.isBlank()) {
            return Retorno.error1();
        }

        NodoLista nodo = encontrarBicicleta(codigo);
        if (nodo == null) {
            return Retorno.error2();
        }

        Bicicleta b = (Bicicleta) nodo.getDato();

        if (!b.getEstado().contains("Mantenimiento")) {
            return Retorno.error3();
        }

        b.setEstado("Disponible");
        b.setMotivoDeMantenimiento(null);
        bicicletasEnDeposito.agregarOrd(b);

        return Retorno.ok();
    }

    @Override
    public Retorno eliminarEstacion(String nombre) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno asignarBicicletaAEstacion(String codigo, String nombreEstacion) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno alquilarBicicleta(String cedula, String nombreEstacion) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno devolverBicicleta(String cedula, String nombreEstacionDestino) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno deshacerUltimosRetiros(int n) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno obtenerUsuario(String cedula) {
        if (cedula == null || cedula.isBlank()) {
            System.out.println("error 1");
            return Retorno.error1();
        }
        if (cedula.length() != 8) {
            System.out.println("error 2");
            return Retorno.error2();
        }

        Usuario usuarioAux = new Usuario(cedula, "aux");
        NodoLista usuarioBuscado = usuarios.obtenerElemento(usuarioAux);

        if (usuarioBuscado == null) {
            System.out.println("error 3");
            return Retorno.error3();
        }

        System.out.println(usuarioBuscado.getDato());
        return Retorno.ok(usuarioBuscado.getDato().toString());
    }

    @Override
    public Retorno listarUsuarios() {
        return Retorno.ok(usuarios.devolverListaString());
    }

    @Override
    public Retorno listarBicisEnDeposito() {
        return Retorno.ok(bicicletasEnDeposito.devolverListaString());
    }

    @Override
    public Retorno informaciónMapa(String[][] mapa) {
        return Retorno.ok(mapaEstaciones.devolverInformacionMapa(mapa));
    }

    @Override
    public Retorno listarBicicletasDeEstacion(String nombreEstacion) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno estacionesConDisponibilidad(int n) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno ocupacionPromedioXBarrio() {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno rankingTiposPorUso() {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno usuariosEnEspera(String nombreEstacion) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno usuarioMayor() {
        return Retorno.noImplementada();
    }

    // Métodos custom 
    public NodoLista<Bicicleta> encontrarBicicleta(String codigo) {

        NodoLista nodo = bicicletasEnEstaciones.obtenerElemento(new Bicicleta(codigo));

        if (nodo == null) {
            nodo = bicicletasEnDeposito.obtenerElemento(new Bicicleta(codigo));
        }

        return nodo;
    }

    // Para Tests
    public void cambiarEstadoBicicleta(String codigo, String estado) {

        NodoLista nodo = encontrarBicicleta(codigo);

        if (nodo == null) {
            System.out.println("No se encontró la bicicleta");
        }

        Bicicleta b = (Bicicleta) nodo.getDato();

        if (b != null && b.getEstado() == "Disponible") {
            if (estado.contains("Alquilada")) {
                b.setEstado(estado);
                bicicletasEnEstaciones.agregarOrd(b);
            } else if (estado.contains("Mantenimiento")) {
                b.setEstado(estado);
                bicicletasEnDeposito.agregarOrd(b);
            } else {
                System.out.println("El estado no es válido");
            }
        }
    }
}
