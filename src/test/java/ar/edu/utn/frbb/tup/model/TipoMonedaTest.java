package ar.edu.utn.frbb.tup.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class TipoMonedaTest {

    //testeo las descripciones de las monedas
    @Test
    public void testGetDescripcion() {
        assertEquals("PESOS", TipoMoneda.PESOS.getDescripcion());
        assertEquals("DOLARES", TipoMoneda.DOLARES.getDescripcion());
    }

    //hago que salte la excepcion con una moneda no valida
    @Test
    public void testFromString() {
        assertEquals(TipoMoneda.PESOS, TipoMoneda.fromString("PESOS"));
        assertEquals(TipoMoneda.DOLARES, TipoMoneda.fromString("DOLARES"));

        assertThrows(IllegalArgumentException.class, () -> {
            TipoMoneda.fromString("EUROS");
        });
    }
}
