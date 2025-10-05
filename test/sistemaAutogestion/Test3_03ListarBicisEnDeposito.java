package sistemaAutogestion;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class Test3_03ListarBicisEnDeposito {

    private Retorno retorno;
    private final IObligatorio s = new Sistema();

    @Before
    public void setUp() {
        s.crearSistemaDeGestion();
    }

    @Test
    public void listarBicisEnDepositoVacio() {
        retorno = s.listarBicisEnDeposito();
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("", retorno.getValorString());
    }

    @Test
    public void listarBicisEnDepositoSoloUnaBici() {
        s.registrarBicicleta("ABC123", "MOUNTAIN");
        retorno = s.listarBicisEnDeposito();
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("ABC123#MOUNTAIN", retorno.getValorString());
    }

    @Test
    public void listarBicisEnDepositoIngresoOrdenado() {
        s.registrarBicicleta("ABC123", "MOUNTAIN");
        s.registrarBicicleta("BCD243", "URBANA");
        s.registrarBicicleta("ABG347", "ELECTRICA");
        retorno = s.listarBicisEnDeposito();
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("ABC123#MOUNTAIN|BCD243#URBANA|ABG347#ELECTRICA", retorno.getValorString());
    }

    @Test
    public void listarBicisEnDepositoIngresoDesordenado() {
        s.registrarBicicleta("ABC123", "MOUNTAIN");
        s.registrarBicicleta("BCD243", "URBANA");
        s.registrarBicicleta("ABG347", "ELECTRICA");
        retorno = s.listarBicisEnDeposito();
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("ABC123#MOUNTAIN|BCD243#URBANA|ABG347#ELECTRICA", retorno.getValorString());
    }

}
