package sistemaAutogestion;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class Test3_08RankingPorTipoDeUso {

    private Retorno retorno;
    private final IObligatorio s = new Sistema();

    @Before
    public void setUp() {
        s.crearSistemaDeGestion();
    }

    @Test
    public void rankingPorTipoDeUsoOk() {

        s.registrarUsuario("12345678", "Juan");
        s.registrarUsuario("22345628", "Juana");
        s.registrarUsuario("32345688", "Juanelo");

        s.registrarBicicleta("123456", "MOUNTAIN");
        s.registrarBicicleta("789123", "URBANA");
        s.registrarBicicleta("345678", "URBANA");

        ((Sistema)s).registrarEstacionConAnclajes("Estacion1", "Cordon", 20, 10);
        ((Sistema)s).registrarEstacionConAnclajes("Estacion2", "Centro", 20, 5);
        ((Sistema)s).registrarEstacionConAnclajes("Estacion3", "Carrasco", 20, 0);

        s.asignarBicicletaAEstacion("123456", "Estacion2");
        s.asignarBicicletaAEstacion("789123", "Estacion1");
        s.asignarBicicletaAEstacion("345678", "Estacion3");

        s.alquilarBicicleta("22345628", "Estacion1");
        s.alquilarBicicleta("32345688", "Estacion2");
        s.alquilarBicicleta("12345678", "Estacion3");

        retorno = s.rankingTiposPorUso();

        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("URBANA#2|MOUNTAIN#1|ELECTRICA#0", retorno.getValorString());
    }
}