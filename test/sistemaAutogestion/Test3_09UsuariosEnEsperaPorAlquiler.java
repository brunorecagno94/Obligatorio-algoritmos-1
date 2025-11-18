package sistemaAutogestion;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class Test3_09UsuariosEnEsperaPorAlquiler {

    private Retorno retorno;
    private final IObligatorio s = new Sistema();

    @Before
    public void setUp() {
        s.crearSistemaDeGestion();
        s.registrarEstacion("Estacion Cordon", "Cordon", 30);
        s.registrarBicicleta("ABC124", "MOUNTAIN");
        s.registrarUsuario("41234567", "Ana Lopez");
        s.registrarUsuario("44834537", "Martin Lopez");
        s.registrarUsuario("23567027", "Anibal Lopez");
    }

    @Test
    public void usuariosEnEsperaPorAlquilerOk() {
        s.alquilarBicicleta("44834537", "Estacion Cordon");
        s.alquilarBicicleta("41234567", "Estacion Cordon");
        s.alquilarBicicleta("23567027", "Estacion Cordon");
        retorno = s.usuariosEnEspera("Estacion Cordon");
        
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("44834537|41234567|23567027|", retorno.getValorString());
    }

@Test
    public void usuariosEnEsperaPorAlquilerUnaBiciOk() {
        s.asignarBicicletaAEstacion("ABC124", "Estacion Cordon");
        s.alquilarBicicleta("44834537", "Estacion Cordon");
        s.alquilarBicicleta("41234567", "Estacion Cordon");
        s.alquilarBicicleta("23567027", "Estacion Cordon");
        retorno = s.usuariosEnEspera("Estacion Cordon");
        
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("41234567|23567027|", retorno.getValorString());
    }
}
