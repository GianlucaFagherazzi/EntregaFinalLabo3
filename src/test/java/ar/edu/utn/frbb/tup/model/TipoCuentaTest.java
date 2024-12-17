package ar.edu.utn.frbb.tup.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class TipoCuentaTest {

    //veo las descripciones de las cuentas
    @Test
    public void testGetDescripcion() {
        assertEquals("CUENTA_CORRIENTE", TipoCuenta.CUENTA_CORRIENTE.getDescripcion());
        assertEquals("CAJA_AHORRO", TipoCuenta.CAJA_AHORRO.getDescripcion());
    }

    //hago que salte la excepcion
    @Test
    public void testFromString() {
        assertEquals(TipoCuenta.CUENTA_CORRIENTE, TipoCuenta.fromString("CUENTA_CORRIENTE"));
        assertEquals(TipoCuenta.CAJA_AHORRO, TipoCuenta.fromString("CAJA_AHORRO"));

        assertThrows(IllegalArgumentException.class, () -> {
            TipoCuenta.fromString("TipoCuentaNoValida");
        });
    }
}
