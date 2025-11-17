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
        Bicicleta b1 = new Bicicleta("123456", "MOUNTAIN");
        Bicicleta b2 = new Bicicleta("789123", "URBANA");
        Bicicleta b3 = new Bicicleta("231423", "URBANA");

        Estacion e1 = new Estacion("Estacion1", "Cordon", 35, 12);
        Estacion e2 = new Estacion("Estacion2", "Centro", 12, 4);
        Estacion e3 = new Estacion("Estacion3", "Carrasco", 48, 10);

        s.registrarBicicleta("123456", "MOUNTAIN");
        s.registrarBicicleta("789123", "URBANA");
        s.registrarBicicleta("654321", "URBANA");
        s.registrarEstacionConAnclajes("Estacion1", "Cordon", 20, 0);
        s.registrarEstacionConAnclajes("Estacion2", "Centro", 20, 0);
        s.registrarEstacionConAnclajes("Estacion3", "Carrasco", 20, 0);
        s.asignarBicicletaAEstacion("789123", "Estacion2");
        s.asignarBicicletaAEstacion("123456", "Estacion2");
        s.asignarBicicletaAEstacion("654321", "Estacion2");

        s.listarBicicletasDeEstacion("Estacion2");
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

        if (!bicicleta.isEnDeposito()) {
            bicicletasEnEstaciones.borrarElemento(bicicleta);
            bicicletasEnDeposito.agregarOrd(bicicleta);
            bicicleta.getEstacionAsignada().liberarAnclaje();
            bicicleta.setEstacionAsignada(null);
        }

        bicicleta.setEstado("Mantenimiento");

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
                || !estacion.getColaEsperaAnclaje().esVacia()
                || !estacion.getColaEsperaAlquiler().esVacia()) {
            System.out.println("Viejo no podés: " + estacion.getColaEsperaAlquiler());
            return Retorno.error3();
        }

        estaciones.borrarElemento(estacion);
        return Retorno.ok();
    }

    @Override
    public Retorno asignarBicicletaAEstacion(String codigo, String nombreEstacion) {
        if (codigo == null
                || codigo.isBlank()
                || nombreEstacion == null
                || nombreEstacion.isBlank()) {
            return Retorno.error1();
        }

        // Busca la bicicleta en depósito
        Bicicleta bicicleta = bicicletasEnDeposito.obtenerElemento(new Bicicleta(codigo));

        // Si no está en depósito, la busca en estaciones
        if (bicicleta == null) {
            bicicleta = bicicletasEnEstaciones.obtenerElemento(new Bicicleta(codigo));
        }

        Estacion estacion = estaciones.obtenerElemento(new Estacion(nombreEstacion));

        if (bicicleta == null) {
            return Retorno.error2();
        } else if (!bicicleta.getEstado().equals("Disponible")) {
            return Retorno.error2();
        }
        if (estacion == null) {
            return Retorno.error3();
        }
        if (estacion.getAnclajesOcupados() == estacion.getCapacidad()) {
            return Retorno.error4();
        }

        if (bicicleta.getEstacionAsignada() != estacion) {
            if (bicicleta.getEstacionAsignada() == null) {
                bicicletasEnDeposito.borrarElemento(bicicleta);
                bicicleta.setEstacionAsignada(estacion);
                bicicletasEnEstaciones.agregarOrd(bicicleta);
                estacion.ocuparAnclaje();
            } else {
                bicicleta.setEstacionAsignada(estacion);
                estacion.ocuparAnclaje();
            }

            //Si la estación tiene alquileres en cola de espera, 
            //asignar la bicicleta a un alquiler
            if (estacion.getColaEsperaAlquiler().cantElementos() != 0) {
                Alquiler alquiler = pasarDeColaDeEsperaAAlquiler(estacion, bicicleta);
                bicicleta.setEstado("Alquilada");
                bicicleta.setUsuarioAsignado(encontrarUsuario(alquiler.getUsuario()));
                System.out.println("La bicicleta fue alquilada por un usuario en cola de espera");
                estacion.ocuparAnclaje();
            }
        } else {
            System.out.println("La bicicleta ya está asignada a esta estación");
        }

        return Retorno.ok();
    }

    @Override
    public Retorno alquilarBicicleta(String cedula, String nombreEstacion) {
        if (cedula == null
                || cedula.isBlank()
                || nombreEstacion == null
                || nombreEstacion.isBlank()) {
            return Retorno.error1();
        }

        Usuario usuario = encontrarUsuario(cedula);
        if (usuario == null) {
            return Retorno.error2();
        }

        Estacion estacion = encontrarEstacion(nombreEstacion);
        if (estacion == null) {
            return Retorno.error3();
        }

        Bicicleta bicicleta = bicicletaDisponibleEnEstacion(estacion);

        if (bicicleta != null) {
            bicicleta.setEstacionAsignada(null);
            bicicleta.setEstado("Alquilada");
            bicicleta.setUsuarioAsignado(usuario);

            Alquiler alquiler = new Alquiler(usuario.getCedula(), bicicleta.getCodigo(), estacion.getNombre());
            alquileresAsignados.push(alquiler);
            usuario.agregarAlquiler(alquiler);
            return Retorno.ok("Alquiler exitoso");
        } else {
            estacion.ponerAlquilerEnColaDeEspera(new Alquiler(usuario.getCedula(), estacion.getNombre()));
            return Retorno.ok();
        }
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
        String listaEstaciones = "";
        
        for (int i = 0; i < bicicletasEnEstaciones.cantElementos(); i++) {
            Bicicleta bicicleta = bicicletasEnEstaciones.obtenerElementoEnPosicion(i);
            if (bicicleta.getEstacionAsignada().getNombre() == nombreEstacion) {
                listaEstaciones += bicicleta.getCodigo() + "|";
            }
        }
   
        return Retorno.ok(listaEstaciones);
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
        return estaciones.obtenerElemento(new Estacion(nombre));
    }

    public Bicicleta bicicletaDisponibleEnEstacion(Estacion estacion) {
        int i = 0;
        while (i < bicicletasEnEstaciones.cantElementos()) {
            Bicicleta bici = bicicletasEnEstaciones.obtenerElementoEnPosicion(i);
            if (bici.getEstacionAsignada() != null) {
                if (bici.getEstacionAsignada().equals(estacion) && bici.getEstado().equals("Disponible")) {
                    return bici;
                }
            }
            i++;
        }
        return null;
    }

    public Usuario encontrarUsuario(String cedula) {
        return usuarios.obtenerElemento(new Usuario(cedula));
    }

    public Alquiler pasarDeColaDeEsperaAAlquiler(Estacion estacion, Bicicleta bicicleta) {
        Alquiler nuevoAlquiler = estacion.sacarAlquilerDeColaDeEspera();
        nuevoAlquiler.setBicicleta(bicicleta.getCodigo());
        alquileresAsignados.push(nuevoAlquiler);
        return nuevoAlquiler;
    }

    // =================================================
    // Para Tests
    // ==========================S=======================
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
