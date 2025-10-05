package sistemaAutogestion;

import tads.ListaNodos;
import tads.NodoLista;
import dominio.*;

//Bruno Recagno-333245, Victoria Calvo-339977
public class Sistema implements IObligatorio {

    ListaNodos<Estacion> estaciones;
    ListaNodos<Usuario> usuarios;
    ListaNodos<Bicicleta> bicicletas;
     
    public Sistema() {
        this.estaciones = new ListaNodos<Estacion>();
        this.usuarios = new ListaNodos<Usuario>();
        this.bicicletas = new ListaNodos<Bicicleta>();
    }

    @Override
    public Retorno crearSistemaDeGestion() {
        Sistema sistema = new Sistema();
        return Retorno.ok();
    }

    @Override
    public Retorno registrarEstacion(String nombre, String barrio, int capacidad) {
        if(nombre == null || 
                nombre.isBlank() || 
                barrio == null || 
                barrio.isBlank()){
            return Retorno.error1();
        }
        if(capacidad <= 0){
            return Retorno.error2();
        }
        
        Estacion e = new Estacion(nombre, barrio, capacidad);
        
        if(estaciones.obtenerElemento(e) != null){
            return Retorno.error3();
        }    
        
        estaciones.agregarOrd(e);
        return Retorno.ok();
    }

    @Override
    public Retorno registrarUsuario(String cedula, String nombre) {
        Usuario usuario = new Usuario(cedula, nombre);

        if (cedula.isBlank() || nombre.isBlank()) {
            return Retorno.error1();
        }
        if (cedula.length() < 8) {
            return Retorno.error2();
        }
        if (usuarios.obtenerElemento(usuario) != null) {
            return Retorno.error3();
        }

        usuarios.agregarFinal(usuario);
        return Retorno.ok();
    }

    @Override
    public Retorno registrarBicicleta(String codigo, String tipo) {
        if(codigo == null ||
           codigo.isBlank() ||
           tipo == null ||
           tipo.isBlank()){
            return Retorno.error1();
        }       
        if(codigo.length() != 6){
            return Retorno.error2();
        }
        if(!tipo.equals("URBANA") && 
           !tipo.equals("MOUNTAIN") &&
           !tipo.equals("ELECTRICA")){
            return Retorno.error3();
        }
        
        Bicicleta b = new Bicicleta(codigo, tipo);
        
        if(bicicletas.obtenerElemento(b) != null){
            return Retorno.error4();
        }
        
        bicicletas.agregarOrd(b);
        return Retorno.ok();
    }

    @Override
    public Retorno marcarEnMantenimiento(String codigo, String motivo) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno repararBicicleta(String codigo) {
        return Retorno.noImplementada();
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
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarUsuarios() {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarBicisEnDeposito() {
        return Retorno.noImplementada();
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
}
