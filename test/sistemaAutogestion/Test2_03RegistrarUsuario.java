package sistemaAutogestion;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class Test2_03RegistrarUsuario {

    private Retorno retorno;
    private final IObligatorio s = new Sistema();

    @Before
    public void setUp() {
        s.crearSistemaDeGestion();
    }

    @Test
    public void registrarUsuarioOk() {
        retorno = s.registrarUsuario("43561234", "Juan Perez");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    }

    @Test
    public void registrarUsuarioError01() {
        retorno = s.registrarUsuario("", "Juan Perez");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.registrarUsuario("43561234", "");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.registrarUsuario("   ", "Juan Perez");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.registrarUsuario("43561234", "   ");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.registrarUsuario(null, "Juan Perez");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.registrarUsuario("43561234", null);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    public void registrarUsuarioError02() {
        retorno = s.registrarUsuario("342", "Juan Perez");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    @Test
    public void registrarUsuarioError03() {
        s.registrarUsuario("43561234", "Juan Perez");
        retorno = s.registrarUsuario("43561234", "Juan Lopez");
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());

    }

}