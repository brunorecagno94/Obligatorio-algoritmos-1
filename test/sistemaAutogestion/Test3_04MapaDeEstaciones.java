package sistemaAutogestion;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class Test3_04MapaDeEstaciones {

    private Retorno retorno;
    private final IObligatorio s = new Sistema();

    @Before
    public void setUp() {
        s.crearSistemaDeGestion();
    }

    @Test
    public void mapaDeEstacionesOk() {
        String[][] mapa
                = {{"o", "o", "E4", "o", "E3"},
                {"E3", "E3", "E3", "E3", "E3"},
                {"o", "E3", "E3", "E3", "E4"},
                {"o", "o", "o", "o", "o"},
                {"o", "o", "o", "o", "E3"}
                };
        retorno = s.informaciónMapa(mapa);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("5#fila|existe", retorno.getValorString());
    }

    @Test
    public void mapaDeEstacionesVacio() {
        String[][] mapa
                = {{"o", "o", "o", "o", "o"},
                {"o", "o", "o", "o", "o"},
                {"o", "o", "o", "o", "o"},
                {"o", "o", "o", "o", "o"},
                {"o", "o", "o", "o", "o"}
                };
        retorno = s.informaciónMapa(mapa);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("0#ambas|no existe", retorno.getValorString());
    }

}
