package sistemaAutogestion;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class Test2_08AsignarBicicletaAEstacion {

    private Retorno retorno;
    private final IObligatorio s = new Sistema();

    @Before
    public void setUp() {
        s.crearSistemaDeGestion();
    }

    @Test
    public void asignarBicicletaAEstacionOk() {
        s.registrarBicicleta("123456", "MOUNTAIN");
        ((Sistema) s).registrarEstacionConAnclajes("Estacion Central", "Centro", 5, 0);
        
        retorno = s.asignarBicicletaAEstacion("123456", "Estacion Central");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    }

    @Test
    public void asignarBicicletaAEstacion01() {
        s.registrarBicicleta("123456", "MOUNTAIN");
        ((Sistema) s).registrarEstacionConAnclajes("Estacion Central", "Centro", 5, 0);
        
        retorno = s.asignarBicicletaAEstacion("", "Estacion Central");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.asignarBicicletaAEstacion("123456", "");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.asignarBicicletaAEstacion("   ", "Estacion Central");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.asignarBicicletaAEstacion("123456", "   ");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.asignarBicicletaAEstacion(null, "Estacion Central");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.asignarBicicletaAEstacion("123456", null);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    public void asignarBicicletaAEstacionError02() {
        s.registrarBicicleta("123456", "MOUNTAIN");
        ((Sistema) s).registrarEstacionConAnclajes("Estacion Central", "Centro", 5, 0);
        
        retorno = s.asignarBicicletaAEstacion("000000", "Estacion Central");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    @Test
    public void asignarBicicletaAEstacionError03() {
        s.registrarBicicleta("123456", "MOUNTAIN");
        ((Sistema) s).registrarEstacionConAnclajes("Estacion Central", "Centro", 5, 0);
        
        retorno = s.asignarBicicletaAEstacion("123456", "Estacion Ciudad Vieja");
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());
    }

    @Test
    public void asignarBicicletaAEstacionError04() {
        s.registrarBicicleta("123456", "MOUNTAIN");
        ((Sistema) s).registrarEstacionConAnclajes("Estacion Central", "Centro", 5, 5);
        
        retorno = s.asignarBicicletaAEstacion("123456", "Estacion Central");
        assertEquals(Retorno.Resultado.ERROR_4, retorno.getResultado());
    }

}
