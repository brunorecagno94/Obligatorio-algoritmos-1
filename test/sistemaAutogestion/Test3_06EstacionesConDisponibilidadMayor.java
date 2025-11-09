package sistemaAutogestion;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class Test3_06EstacionesConDisponibilidadMayor {

    private Retorno retorno;
    private final IObligatorio s = new Sistema();

    @Before
    public void setUp() {
        s.crearSistemaDeGestion();
    }

    @Test
    public void estacionesConDisponibilidadOk() {
        ((Sistema)s).registrarEstacionConAnclajes("Estacion 1", "Ciudad Vieja", 20, 15);
        ((Sistema)s).registrarEstacionConAnclajes("Estacion 2", "Palermo", 10, 0);
        ((Sistema)s).registrarEstacionConAnclajes("Estacion 3", "Aguada", 30, 22);
        ((Sistema)s).registrarEstacionConAnclajes("Estacion 4", "Capurro", 25, 12);
        ((Sistema)s).registrarEstacionConAnclajes("Estacion 5", "Cordon", 15, 15);
        retorno = s.estacionesConDisponibilidad(12);
        
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("3", retorno.getValorString());
    }

    @Test
    public void estacionesConDisponibilidadError01() {
        ((Sistema)s).registrarEstacionConAnclajes("Estacion 1", "Ciudad Vieja", 20, 15);
        retorno = s.estacionesConDisponibilidad(1);
        
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

}
