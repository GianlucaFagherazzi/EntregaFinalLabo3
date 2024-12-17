package ar.edu.utn.frbb.tup.controller.validator;

import ar.edu.utn.frbb.tup.controller.PrestamoDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PrestamoValidatorTest {

    // Verifica que el monto sea positivo
    @Test
    void validateMontoPositivoTest() {
        PrestamoDto prestamoDto = new PrestamoDto();
        prestamoDto.setMontoPrestamo(1000.0);

        assertDoesNotThrow(() -> PrestamoValidator.validate(prestamoDto));
    }

    // Verifica que el monto sea negativo
    @Test
    void validateMontoNegativoTest() {
        PrestamoDto prestamoDto = new PrestamoDto();
        prestamoDto.setMontoPrestamo(-500.0);

        Exception exception = assertThrows(IllegalArgumentException.class, 
            () -> PrestamoValidator.validate(prestamoDto));
        assertEquals("Debe establecer un monto en positivo", exception.getMessage());
    }

    // Verifica que el monto sea cero
    @Test
    void validateMontoCeroTest() {
        PrestamoDto prestamoDto = new PrestamoDto();
        prestamoDto.setMontoPrestamo(0.0);

        Exception exception = assertThrows(IllegalArgumentException.class, 
            () -> PrestamoValidator.validate(prestamoDto));
        assertEquals("Debe establecer cuanto va a pedir", exception.getMessage());
    }
}
