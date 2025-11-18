package sistemaAutogestion;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class Test3_10UsuarioConMasAlquileres {

    private Retorno retorno;
    private final IObligatorio s = new Sistema();

    @Before
    public void setUp() {
        s.crearSistemaDeGestion();
        s.registrarEstacion("Estacion Cordon", "Cordon", 30);
        
        s.registrarBicicleta("ABC124", "MOUNTAIN");
        s.registrarBicicleta("BBC234", "MOUNTAIN");
        s.registrarBicicleta("FBC154", "MOUNTAIN");
        s.registrarBicicleta("GHC454", "MOUNTAIN");
        s.registrarBicicleta("DKC164", "MOUNTAIN");
        s.registrarBicicleta("NDC644", "MOUNTAIN");
        
        s.asignarBicicletaAEstacion("ABC124", "Estacion Cordon");
        s.asignarBicicletaAEstacion("BBC234", "Estacion Cordon");
        s.asignarBicicletaAEstacion("FBC154", "Estacion Cordon");
        s.asignarBicicletaAEstacion("GHC454", "Estacion Cordon");
        s.asignarBicicletaAEstacion("DKC164", "Estacion Cordon");
        s.asignarBicicletaAEstacion("NDC644", "Estacion Cordon");
        
        s.registrarUsuario("41234567", "Ana Lopez");
        s.registrarUsuario("44834537", "Martin Lopez");
        s.registrarUsuario("23567027", "Anibal Lopez");
    }

    @Test
    public void usuarioConMasAlquileresOk() {
        s.alquilarBicicleta("41234567", "Estacion Cordon");
        s.alquilarBicicleta("41234567", "Estacion Cordon");
        s.alquilarBicicleta("41234567", "Estacion Cordon");
        s.alquilarBicicleta("44834537", "Estacion Cordon");
        s.alquilarBicicleta("44834537", "Estacion Cordon");
        retorno = s.usuarioMayor();

        assertEquals("41234567", retorno.getValorString());
    }
}
