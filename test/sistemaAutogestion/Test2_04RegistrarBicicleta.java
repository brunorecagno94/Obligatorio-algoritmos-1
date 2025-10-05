package sistemaAutogestion;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class Test2_04RegistrarBicicleta {

    private Retorno retorno;
    private final IObligatorio s = new Sistema();

    @Before
    public void setUp() {
        s.crearSistemaDeGestion();
    }

    @Test
    public void registrarBicicletaOk() {
        retorno = s.registrarBicicleta("123456", "MOUNTAIN");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        
        retorno = s.registrarBicicleta("234567", "URBANA");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        
        retorno = s.registrarBicicleta("345678", "ELECTRICA");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());      
    }

    @Test
    public void registrarBicicletaError01() {
        retorno = s.registrarBicicleta(null, "MOUNTAIN");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.registrarBicicleta("", "MOUNTAIN");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.registrarBicicleta("123456", null);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.registrarBicicleta("123456", "");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    public void registrarBicicletaError02() {
         retorno = s.registrarBicicleta("1234", "URBANA");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
        
        retorno = s.registrarBicicleta("123456789", "URBANA");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    @Test
    public void registrarBicicletaError03() {
        retorno = s.registrarBicicleta("123456", "URNA");
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());
    }
    
    @Test
    public void registrarBicicletaError04() {
        s.registrarBicicleta("456789", "ELECTRICA");
        retorno = s.registrarBicicleta("456789", "ELECTRICA");
        assertEquals(Retorno.Resultado.ERROR_4, retorno.getResultado());
    }

}
