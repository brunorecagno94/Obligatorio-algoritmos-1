package sistemaAutogestion;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class Test2_06RepararBicicleta {

    private Retorno retorno;
    private final IObligatorio s = new Sistema();

    @Before
    public void setUp() {
        s.crearSistemaDeGestion();
    }

    @Test
    public void RepararBicicletaOk() {
        s.registrarBicicleta("123456", "MOUNTAIN");
        s.marcarEnMantenimiento("123456", "Pinchada");

        retorno = s.repararBicicleta("123456");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    }

    @Test
    public void RepararBicicletaError01() {
        s.registrarBicicleta("123456", "MOUNTAIN");
        s.marcarEnMantenimiento("123456", "Pinchada");

        retorno = s.repararBicicleta("");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.repararBicicleta(null);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    public void RepararBicicletaError02() {
        s.registrarBicicleta("123456", "MOUNTAIN");
        retorno = s.repararBicicleta("123453");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    @Test
    public void RepararBicicletaError03() {
        s.registrarBicicleta("345678", "MOUNTAIN");
        s.registrarBicicleta("123456", "MOUNTAIN");
        ((Sistema) s).cambiarEstadoBicicleta("123456", "Alquilada");

        retorno = s.repararBicicleta("123456");
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());

        retorno = s.repararBicicleta("345678");
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());
    }
}
