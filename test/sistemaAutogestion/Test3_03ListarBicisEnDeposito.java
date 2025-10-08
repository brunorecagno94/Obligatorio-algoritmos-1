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
        assertEquals("ABC123#MOUNTAIN#Disponible", retorno.getValorString());
    }

    @Test
    public void listarBicisEnDepositoIngresoOrdenado() {
        s.registrarBicicleta("ABG347", "ELECTRICA");
        s.registrarBicicleta("BCD243", "URBANA");
        s.registrarBicicleta("ABC123", "MOUNTAIN");
        retorno = s.listarBicisEnDeposito();
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("ABC123#MOUNTAIN#Disponible|BCD243#URBANA#Disponible|ABG347#ELECTRICA#Disponible", retorno.getValorString());
    }

    @Test
    public void listarBicisEnDepositoIngresoDesordenado() {
        s.registrarBicicleta("ABC123", "MOUNTAIN");
        s.registrarBicicleta("BCD243", "URBANA");
        s.registrarBicicleta("ABG347", "ELECTRICA");
        retorno = s.listarBicisEnDeposito();
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("ABC123#MOUNTAIN#Disponible|BCD243#URBANA#Disponible|ABG347#ELECTRICA#Disponible", retorno.getValorString());
    }

}
