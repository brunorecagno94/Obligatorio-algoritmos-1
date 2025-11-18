package sistemaAutogestion;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class Test2_11DeshacerUltimosRetiros {

    private Retorno retorno;
    private final IObligatorio s = new Sistema();

    @Before
    public void setUp() {
        s.crearSistemaDeGestion();
    }

    @Test
    public void deshacerUltimosRetirosOk() {

        s.registrarUsuario("12345678", "Ana");
        s.registrarUsuario("23456789", "Luis");

        s.registrarEstacion("Estación Centro", "Centro", 10);
        s.registrarEstacion("Estación Buceo", "Buceo", 10);

        s.registrarBicicleta("111111", "MOUNTAIN");
        s.registrarBicicleta("222222", "URBANA");

        s.asignarBicicletaAEstacion("111111", "Estación Centro");
        s.asignarBicicletaAEstacion("222222", "Estación Buceo");

        s.alquilarBicicleta("12345678", "Estación Centro");
        s.alquilarBicicleta("23456789", "Estación Buceo");

        retorno = s.deshacerUltimosRetiros(2);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    }

    @Test
    public void deshacerUltimosRetirosOk_NoHayAlquileres() {

        retorno = s.deshacerUltimosRetiros(3);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    }

        @Test
    public void deshacerUltimosRetirosError01() {

        retorno = s.deshacerUltimosRetiros(0);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.deshacerUltimosRetiros(-5);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }
}
