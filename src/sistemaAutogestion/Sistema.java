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
    ListaNodos<Bicicleta> bicicletas;
    ListaNodos<Bicicleta> bicicletasEnDeposito;

    public Sistema() {
        this.estaciones = new ListaNodos<Estacion>();
        this.usuarios = new ListaNodos<Usuario>();
        this.bicicletas = new ListaNodos<Bicicleta>();
    }

    public static void main(String[] args) {
        Sistema sistema = new Sistema();
        sistema.usuarios = new ListaNodos<Usuario>();

        sistema.registrarBicicleta("ABC123", "MOUNTAIN");
        sistema.registrarBicicleta("BCD243", "URBANA");
        sistema.registrarBicicleta("ABG347", "ELECTRICA");

        Usuario usu1 = new Usuario("48027123", "Bruno");
        Usuario usu2 = new Usuario("23123242", "Juan");

        sistema.usuarios.agregarFinal(usu1);
        sistema.usuarios.agregarFinal(usu2);

        sistema.listarUsuarios();
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
        if (cedula.length() != 8) {
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

        if (bicicletas.obtenerElemento(b) != null) {
            return Retorno.error4();
        }

        bicicletas.agregarOrd(b);
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

        NodoLista nodo = bicicletas.obtenerElemento(new Bicicleta(codigo));
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
        b.setEnDeposito(true);

        nodo.setDato(b);

        return Retorno.ok();
    }

    @Override
    public Retorno repararBicicleta(String codigo) {
        if (codigo == null
                || codigo.isBlank()) {
            return Retorno.error1();
        }

        NodoLista nodo = bicicletas.obtenerElemento(new Bicicleta(codigo));
        if (nodo == null) {
            return Retorno.error2();
        }

        Bicicleta b = (Bicicleta) nodo.getDato();

        if (!b.getEstado().contains("Mantenimiento")) {
            return Retorno.error3();
        }

        b.setEstado("Disponible");
        b.setMotivoDeMantenimiento(null);
        nodo.setDato(b);

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
        return Retorno.noImplementada();
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

    //para Tests
    public void cambiarEstadoBicicleta(String codigo, String estado) {

        NodoLista nodo = bicicletas.obtenerElemento(new Bicicleta(codigo));
        Bicicleta b = (Bicicleta) nodo.getDato();
        if (b != null) {
            if (estado.contains("Disponible") || estado.contains("Alquilada") || estado.contains("Mantenimiento")) {
                b.setEstado(estado);
                nodo.setDato(b);
            } else {
                System.out.println("El estado no es válido");
            }
        } else {
            System.out.println("No se encontró la bicicleta");
        }

    }
}
