package sistemaAutogestion;

import tads.ListaNodos;
import tads.NodoLista;
import dominio.*;
import java.util.HashSet;
import java.util.Set;
import tads.PilaNodos;

//Bruno Recagno-333245, Victoria Calvo-339977
public class Sistema implements IObligatorio {

    ListaNodos<Estacion> estaciones;
    ListaNodos<Usuario> usuarios;
    ListaNodos<Bicicleta> bicicletasEnEstaciones;
    ListaNodos<Bicicleta> bicicletasEnDeposito;
    PilaNodos<Alquiler> alquileresAsignados;
    MapaEstaciones mapaEstaciones;

    public Sistema() {
        this.estaciones = new ListaNodos<Estacion>();
        this.usuarios = new ListaNodos<Usuario>();
        this.bicicletasEnEstaciones = new ListaNodos<Bicicleta>();
        this.bicicletasEnDeposito = new ListaNodos<Bicicleta>();
        this.alquileresAsignados = new PilaNodos<Alquiler>();
        this.mapaEstaciones = new MapaEstaciones();
    }

    public static void main(String[] args) {
        Sistema s = new Sistema();
        Estacion e1 = new Estacion("Estacion1", "Cordon", 35);
        Estacion e2 = new Estacion("Estacion2", "Centro", 12);
        Estacion e3 = new Estacion("Estacion3", "Carrasco", 48);

        s.estaciones.agregarOrd(e1);
        s.estaciones.agregarOrd(e2);
        s.estaciones.agregarOrd(e3);

        s.estacionesConDisponibilidad(20);
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

        if (!b.isEnDeposito()) {
            bicicletasEnEstaciones.borrarElemento(b);
            bicicletasEnDeposito.agregarOrd(b);
        }

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

    // =====================================================
    //  TESTEAR CUANDO TERMINEMOS CON COLAS DE ESPERA:
    // =====================================================
    @Override
    public Retorno eliminarEstacion(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            return Retorno.error1();
        }
        NodoLista nodo = encontrarEstacion(nombre);
        if (nodo == null) {
            return Retorno.error2();
        }

        Estacion estacion = (Estacion) nodo.getDato();

        if (estacion.getAnclajesOcupados() > 0
                || estacion.getColaEsperaAnclaje() != null
                || estacion.getColaEsperaAlquiler() != null) {
            return Retorno.error3();
        }

        estaciones.borrarElemento(estacion);
        return Retorno.ok();
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
        if (n <= 1) {
            System.out.println("Ingrese una cantidad mayor a 1");
            return Retorno.error1();
        } 
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

    public NodoLista<Estacion> encontrarEstacion(String nombre) {
        NodoLista nodo = estaciones.obtenerElemento(new Estacion(nombre));

        if (nodo == null) {
            System.out.println("No se encontró la estación");
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
