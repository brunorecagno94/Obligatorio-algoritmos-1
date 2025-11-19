package sistemaAutogestion;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class Test3_07OcupacionPromedioPorBarrio {
    private Retorno retorno;
    private final IObligatorio s = new Sistema();

    @Before
    public void setUp() {
        s.crearSistemaDeGestion();
    }

    @Test
    public void ocupacionPromedioPorBarrioOk() {
        ((Sistema)s).registrarEstacionConAnclajes("Estacion1", "Cordon", 20, 6);
        ((Sistema)s).registrarEstacionConAnclajes("Estacion2", "Centro", 20, 17);
        ((Sistema)s).registrarEstacionConAnclajes("Estacion3", "Carrasco", 20, 3);

        retorno = s.ocupacionPromedioXBarrio();

        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(30, ((Sistema)s).encontrarBarrio("Cordon").getPorcentajeOcupacion());
        assertEquals(85, ((Sistema)s).encontrarBarrio("Centro").getPorcentajeOcupacion());
        assertEquals(15, ((Sistema)s).encontrarBarrio("Carrasco").getPorcentajeOcupacion());
    }
}
