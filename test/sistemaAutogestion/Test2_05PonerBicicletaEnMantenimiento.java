package sistemaAutogestion;

import dominio.Bicicleta;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import tads.NodoLista;

public class Test2_05PonerBicicletaEnMantenimiento {

    private Retorno retorno;
    private final IObligatorio s = new Sistema();

    @Before
    public void setUp() {
        s.crearSistemaDeGestion();
    }

    @Test
    public void PonerBicicletaEnMantenimientoOk() {
        s.registrarBicicleta("123456", "MOUNTAIN");
        retorno = s.marcarEnMantenimiento("123456", "motivo");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());  
    }

    @Test
    public void PonerBicicletaEnMantenimientoError01(){ 
        s.registrarBicicleta("123456", "MOUNTAIN");
            
        retorno = s.marcarEnMantenimiento(null, "Pinchada");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.marcarEnMantenimiento("123456", null);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.marcarEnMantenimiento("", "Pinchada");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.marcarEnMantenimiento("123456", "");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    public void PonerBicicletaEnMantenimientoError02() {
        retorno = s.marcarEnMantenimiento("123456", "Pinchada");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    @Test
    public void PonerBicicletaEnMantenimientoError03() {
        s.registrarBicicleta("123456", "MOUNTAIN");   
        ((Sistema)s).cambiarEstadoBicicleta("123456", "Alquilada");
        
        retorno = s.marcarEnMantenimiento("123456", "Pinchada");
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());
    }
    
    @Test
    public void PonerBicicletaEnMantenimientoError04() {
        s.registrarBicicleta("123456", "MOUNTAIN");   
        ((Sistema)s).cambiarEstadoBicicleta("123456", "Mantenimiento");
        
        retorno = s.marcarEnMantenimiento("123456", "Pinchada");
        assertEquals(Retorno.Resultado.ERROR_4, retorno.getResultado());
    }

}

