package sistemaAutogestion;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class Test3_05ListarBicicletasDeEstacion {

    private Retorno retorno;
    private final IObligatorio s = new Sistema();

    @Before
    public void setUp() {
        s.crearSistemaDeGestion();
        s.registrarEstacion("Estacion Cordon", "Cordon", 30);
    }

    @Test
    public void listarBicicletasDeEstacionVacio() {
        retorno = s.listarBicicletasDeEstacion("Estacion Cordon");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("", retorno.getValorString());
    }

    @Test
    public void listarBicicletasDeEstacionSoloUnaBici() {
        s.registrarBicicleta("ABC123", "MOUNTAIN");
        s.asignarBicicletaAEstacion("ABC123", "Estacion Cordon");
        retorno = s.listarBicicletasDeEstacion("Estacion Cordon");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("ABC123|", retorno.getValorString());
    }

    @Test
    public void listarBicicletasDeEstacionIngresoOrdenado() {
        s.registrarBicicleta("ABC123", "MOUNTAIN");
        s.registrarBicicleta("ABG347", "ELECTRICA");
        s.registrarBicicleta("BCD243", "URBANA");
        s.asignarBicicletaAEstacion("ABC123", "Estacion Cordon");
        s.asignarBicicletaAEstacion("ABG347", "Estacion Cordon");
        s.asignarBicicletaAEstacion("BCD243", "Estacion Cordon");
        retorno = s.listarBicicletasDeEstacion("Estacion Cordon");
        
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("ABC123|ABG347|BCD243|", retorno.getValorString());
    }

    @Test
    public void listarBicicletasDeEstacionIngresoDesordenado() {
        s.registrarBicicleta("ABG347", "ELECTRICA");
        s.registrarBicicleta("BCD243", "URBANA");
        s.registrarBicicleta("ABC123", "MOUNTAIN");
        s.asignarBicicletaAEstacion("ABG347", "Estacion Cordon");
        s.asignarBicicletaAEstacion("BCD243", "Estacion Cordon");
        s.asignarBicicletaAEstacion("ABC123", "Estacion Cordon");
        retorno = s.listarBicicletasDeEstacion("Estacion Cordon");
        
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("ABC123|ABG347|BCD243|", retorno.getValorString());
    }

}
