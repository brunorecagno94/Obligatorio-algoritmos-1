package sistemaAutogestion;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class Test2_07EliminarEstacion {

    private Retorno retorno;
    private final IObligatorio s = new Sistema();

    @Before
    public void setUp() {
        s.crearSistemaDeGestion();
        s.registrarBicicleta("123456", "URBANA");
        s.registrarEstacion("Estacion Centro", "Centro", 15);
    }

    @Test
    public void EliminarEstacionOk() {
        retorno = s.eliminarEstacion("Estacion Centro");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    }

    @Test
    public void EliminarEstacionError01() {        
        retorno = s.eliminarEstacion("");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.eliminarEstacion(null);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    public void EliminarEstacionError02() {
//        s.registrarEstacion("Estacion Centro", "Centro", 15);
        retorno = s.eliminarEstacion("Estacion Union");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    @Test
    public void EliminarEstacionError03() {
        s.asignarBicicletaAEstacion("123456", "Estacion Centro");
        retorno = s.eliminarEstacion("Estacion Centro");
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());
    }
}
