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
    }

    @Test
    public void RepararBicicletaOk() {
        s.registrarEstacion("Estacion Centro", "Centro", 15);
        retorno = s.eliminarEstacion("Estacion Centro");

        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    }

    @Test
    public void RepararBicicletaError01() {
        s.registrarEstacion("Estacion Centro", "Centro", 15);
        
        retorno = s.eliminarEstacion("");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.eliminarEstacion(null);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    public void RepararBicicletaError02() {
        s.registrarEstacion("Estacion Centro", "Centro", 15);
        retorno = s.eliminarEstacion("Estacion Union");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    @Test
    public void RepararBicicletaError03() {
        // FALTA IMPLEMENTAR CUANDO TENGAMOS COLA DE ESPERA
    }
}
