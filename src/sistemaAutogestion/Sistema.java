package sistemaAutogestion;

import tads.ILista;
import tads.IPila;
import tads.ICola;
import tads.ListaNodos;
import tads.PilaNodos;
import tads.ColaNodos;
import dominio.*;
import java.util.HashSet;
import java.util.Set;
import tads.PilaNodos;

//Bruno Recagno-333245, Victoria Calvo-339977
public class Sistema implements IObligatorio {

    ILista<Estacion> estaciones;
    ILista<Usuario> usuarios;
    ILista<Bicicleta> bicicletasEnEstaciones;
    ILista<Bicicleta> bicicletasEnDeposito;
    IPila<Alquiler> alquileresAsignados;
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
//        Estacion e1 = new Estacion("Estacion1", "Cordon", 35, 12);
//        Estacion e2 = new Estacion("Estacion2", "Centro", 12, 4);
//        Estacion e3 = new Estacion("Estacion3", "Carrasco", 48, 10);
//
//        s.estaciones.agregarOrd(e1);
//        s.estaciones.agregarOrd(e2);
//        s.estaciones.agregarOrd(e3);
        ((Sistema)s).registrarEstacionConAnclajes("Estacion 1", "Ciudad Vieja", 20, 15);
        ((Sistema)s).registrarEstacionConAnclajes("Estacion 2", "Palermo", 10, 0);
        ((Sistema)s).registrarEstacionConAnclajes("Estacion 3", "Aguada", 30, 22);
        ((Sistema)s).registrarEstacionConAnclajes("Estacion 4", "Capurro", 25, 12);
        ((Sistema)s).registrarEstacionConAnclajes("Estacion 5", "Cordon", 15, 15);
        ((Sistema)s).estacionesConDisponibilidad(14);
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

        Bicicleta bicicleta = new Bicicleta(codigo, tipo);

        if (bicicletasEnEstaciones.obtenerElemento(bicicleta) != null || bicicletasEnDeposito.obtenerElemento(bicicleta) != null) {
            return Retorno.error4();
        }

        bicicletasEnDeposito.agregarOrd(bicicleta);
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

        Bicicleta bicicleta = encontrarBicicleta(codigo);

        if (bicicleta == null) {
            return Retorno.error2();
        }

        if (bicicleta.getEstado().contains("Alquilada")) {
            return Retorno.error3();
        }
        if (bicicleta.getEstado().contains("Mantenimiento")) {
            return Retorno.error4();
        }

        bicicleta.setEstado("Mantenimiento");
        bicicleta.setEstacionAsignada(null);

        if (!bicicleta.isEnDeposito()) {
            bicicletasEnEstaciones.borrarElemento(bicicleta);
            bicicletasEnDeposito.agregarOrd(bicicleta);
        }

        return Retorno.ok();
    }

    @Override
    public Retorno repararBicicleta(String codigo) {
        if (codigo == null
                || codigo.isBlank()) {
            return Retorno.error1();
        }

        Bicicleta bicicleta = encontrarBicicleta(codigo);
        if (bicicleta == null) {
            return Retorno.error2();
        }

        if (!bicicleta.getEstado().contains("Mantenimiento")) {
            return Retorno.error3();
        }

        bicicleta.setEstado("Disponible");
        bicicleta.setMotivoDeMantenimiento(null);
        bicicletasEnDeposito.agregarOrd(bicicleta);

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

        Estacion estacion = (Estacion) estaciones.obtenerElemento(new Estacion(nombre));
        if (estacion == null) {
            return Retorno.error2();
        }

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
            return Retorno.error1();
        }
        if (cedula.length() != 8) {
            return Retorno.error2();
        }

        Usuario usuarioBuscado = usuarios.obtenerElemento(new Usuario(cedula));

        if (usuarioBuscado == null) {
            return Retorno.error3();
        }

        System.out.println(usuarioBuscado);
        return Retorno.ok(usuarioBuscado.toString());
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

        int cantidadEstaciones = 0;

        for (int i = 0; i < estaciones.cantElementos(); i++) {
            Estacion estacion = estaciones.obtenerElementoEnPosicion(i);

            if (estacion.getAnclajesOcupados() > n) {
                System.out.println("+1 estacion");
                cantidadEstaciones++;
            }
        }

        System.out.println(cantidadEstaciones);
        return Retorno.ok(cantidadEstaciones + "");
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

    // =================================================
    // Métodos custom 
    // =================================================
    public Bicicleta encontrarBicicleta(String codigo) {

        Bicicleta bicicleta = bicicletasEnEstaciones.obtenerElemento(new Bicicleta(codigo));

        if (bicicleta == null) {
            bicicleta = bicicletasEnDeposito.obtenerElemento(new Bicicleta(codigo));
        }

        return bicicleta;
    }

    public Estacion encontrarEstacion(String nombre) {
        Estacion estacion = estaciones.obtenerElemento(new Estacion(nombre));

        if (estacion == null) {
            System.out.println("No se encontró la estación");
        }

        return estacion;
    }

    // Para Tests
    public void cambiarEstadoBicicleta(String codigo, String estado) {

        Bicicleta bicicleta = encontrarBicicleta(codigo);

        if (bicicleta == null) {
            System.out.println("No se encontró la bicicleta");
        }

        if (bicicleta != null && bicicleta.getEstado() == "Disponible") {
            if (estado.contains("Alquilada")) {
                bicicleta.setEstado(estado);
                bicicletasEnEstaciones.agregarOrd(bicicleta);
            } else if (estado.contains("Mantenimiento")) {
                bicicleta.setEstado(estado);
                bicicletasEnDeposito.agregarOrd(bicicleta);
            } else {
                System.out.println("El estado no es válido");
            }
        }
    }

    public void registrarEstacionConAnclajes(String nombre, String barrio, int capacidad, int anclajesOcupados) {
        if (nombre == null
                || nombre.isBlank()
                || barrio == null
                || barrio.isBlank()) {
            System.out.println("Ingrese un nombre valido");
        }
        if (capacidad <= 0) {
            System.out.println("La capacidad debe ser mayor que 0");
        }
        if (anclajesOcupados < 0) {
            System.out.println("Los anclajes ocupados no pueden ser menos que 0");
        }
        if (anclajesOcupados > capacidad) {
            System.out.println("La capacidad de la estación no puede ser menor a los anclajes ocupados");
        }

        Estacion e = new Estacion(nombre, barrio, capacidad, anclajesOcupados);

        if (estaciones.obtenerElemento(e) != null) {
            System.out.println("La estacion ya existe");
        }

        estaciones.agregarOrd(e);
    }
}
