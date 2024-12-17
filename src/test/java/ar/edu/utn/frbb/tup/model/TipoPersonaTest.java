package ar.edu.utn.frbb.tup.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class TipoPersonaTest {

    // Test para el método getDescripcion()
    @Test
    public void testGetDescripcion() {
        assertEquals("F", TipoPersona.PERSONA_FISICA.getDescripcion());
        assertEquals("J", TipoPersona.PERSONA_JURIDICA.getDescripcion());
    }

    //salta la excepcion con un tipo de persona no valido
    @Test
    public void testFromString() {
        assertEquals(TipoPersona.PERSONA_FISICA, TipoPersona.fromString("F"));
        assertEquals(TipoPersona.PERSONA_JURIDICA, TipoPersona.fromString("J"));

        assertThrows(IllegalArgumentException.class, () -> {
            TipoPersona.fromString("INVALIDO");
        });
    }
}
