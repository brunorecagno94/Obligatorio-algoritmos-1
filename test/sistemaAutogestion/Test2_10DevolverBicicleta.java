package sistemaAutogestion;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class Test2_10DevolverBicicleta {

    private Retorno retorno;
    private final IObligatorio s = new Sistema();

    @Before
    public void setUp() {
        s.crearSistemaDeGestion();
    }

    @Test
    public void devolverBicicletaOk() {
        s.registrarUsuario("12345678", "Ana");
        s.registrarEstacion("Estación Centro", "Centro", 10);
        s.registrarEstacion("Estación Buceo", "Buceo", 10);
        s.registrarBicicleta("123456", "MOUNTAIN");

        s.asignarBicicletaAEstacion("123456", "Estación Centro");
        s.alquilarBicicleta("12345678", "Estación Centro");

        retorno = s.devolverBicicleta("12345678", "Estación Buceo");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    }
    
    @Test
    public void devolverBicicletaOk_EstacionLlena() {
        s.registrarUsuario("12345678", "Ana");
        s.registrarEstacion("Estación Centro", "Centro", 10);
        s.registrarEstacion("Estación Buceo", "Buceo", 1);
        s.registrarBicicleta("123456", "MOUNTAIN");
        s.registrarBicicleta("234567", "MOUNTAIN");
        
        s.asignarBicicletaAEstacion("234567", "Estación Buceo");
        s.asignarBicicletaAEstacion("123456", "Estación Centro");
        s.alquilarBicicleta("12345678", "Estación Centro");

        retorno = s.devolverBicicleta("12345678", "Estación Buceo");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    }
    
    @Test
    public void devolverBicicletaOk_SeAlquila() {
        s.registrarUsuario("12345678", "Ana");
        s.registrarUsuario("23456789", "Luis");
        s.registrarEstacion("Estación Centro", "Centro", 10);
        s.registrarEstacion("Estación Buceo", "Buceo", 1);
        s.registrarBicicleta("123456", "MOUNTAIN");
        
        s.asignarBicicletaAEstacion("123456", "Estación Centro");
        s.alquilarBicicleta("12345678", "Estación Centro");
        s.alquilarBicicleta("23456789", "Estación Centro");

        retorno = s.devolverBicicleta("12345678", "Estación Buceo");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    }

    @Test
    public void devolverBicicletaError01() {        
        retorno = s.devolverBicicleta("", "Estación Buceo");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.devolverBicicleta("12345678", "");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.devolverBicicleta(null, "Estación Buceo");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.devolverBicicleta("12345678", null);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
        
        retorno = s.devolverBicicleta("  ", "Estación Buceo");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.devolverBicicleta("12345678", "  ");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    public void devolverBicicletaError02_UsuarioNoExiste() {
        s.registrarEstacion("Estación Centro", "Centro", 10);
        s.registrarEstacion("Estación Buceo", "Buceo", 10);
        s.registrarBicicleta("123456", "MOUNTAIN");

        s.asignarBicicletaAEstacion("123456", "Estación Centro");
        s.alquilarBicicleta("12345678", "Estación Centro");
        
        retorno = s.devolverBicicleta("12345678", "Estación Buceo");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }
    
    @Test
    public void devolverBicicletaError02_UsuarioSinBicicletaAlquilada() {
        s.registrarUsuario("12345678", "Ana");
        s.registrarEstacion("Estación Centro", "Centro", 10);
        s.registrarEstacion("Estación Buceo", "Buceo", 10);
        s.registrarBicicleta("123456", "MOUNTAIN");

        s.asignarBicicletaAEstacion("123456", "Estación Centro");
        
        retorno = s.devolverBicicleta("12345678", "Estación Buceo");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    @Test
    public void devolverBicicletaError03() {
        s.registrarUsuario("12345678", "Ana");
        s.registrarEstacion("Estación Centro", "Centro", 10);
        s.registrarBicicleta("123456", "MOUNTAIN");

        s.asignarBicicletaAEstacion("123456", "Estación Centro");
        s.alquilarBicicleta("12345678", "Estación Centro");

        retorno = s.devolverBicicleta("12345678", "Estación Buceo");
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());
    }
}