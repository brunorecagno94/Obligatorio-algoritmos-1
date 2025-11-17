package sistemaAutogestion;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class Test2_09AlquilarBicicleta {

    private Retorno retorno;
    private final IObligatorio s = new Sistema();

    @Before
    public void setUp() {
        s.crearSistemaDeGestion();
    }

    @Test
    public void alquilarBicicletaOk() {
        s.registrarUsuario("12345678", "Florencia");
        s.registrarEstacion("Estación Centro", "Centro", 10);
        s.registrarBicicleta("123456", "MOUNTAIN");
        s.asignarBicicletaAEstacion("123456", "Estación Centro");
        
        retorno = s.alquilarBicicleta("12345678", "Estación Centro");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    }
    
    @Test
    public void alquilarBicicletaOk_NoHayBicicletaDisponible() {
        s.registrarUsuario("12345678", "Florencia");
        s.registrarEstacion("Estación Centro", "Centro", 10);
        
        retorno = s.alquilarBicicleta("12345678", "Estación Centro");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    }

    @Test
    public void alquilarBicicletaError01() {
        s.registrarUsuario("12345678", "Florencia");
        s.registrarEstacion("Estación Centro", "Centro", 10);
        s.registrarBicicleta("123456", "MOUNTAIN");
        s.asignarBicicletaAEstacion("123456", "Estación Centro");
        
        retorno = s.alquilarBicicleta("", "Estación Centro");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.alquilarBicicleta("12345678", "");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.alquilarBicicleta(null, "Estación Centro");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.alquilarBicicleta("12345678", null);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
        
        retorno = s.alquilarBicicleta("  ", "Estación Centro");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.alquilarBicicleta("12345678", " ");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    public void alquilarBicicletaError02() {
        s.registrarEstacion("Estación Centro", "Centro", 10);
        s.registrarBicicleta("123456", "MOUNTAIN");
        s.asignarBicicletaAEstacion("123456", "Estación Centro");
        
        retorno = s.alquilarBicicleta("12345678", "Estación Centro");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    @Test
    public void alquilarBicicletaError03() {
        s.registrarUsuario("12345678", "Florencia");
        s.registrarBicicleta("123456", "MOUNTAIN");
        s.asignarBicicletaAEstacion("123456", "Estación Centro");
        
        retorno = s.alquilarBicicleta("12345678", "Estación Centro");
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());
    }

}

