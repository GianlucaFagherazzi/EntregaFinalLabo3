package ar.edu.utn.frbb.tup.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PrestamoDtoTest {

    @BeforeAll
    public void setUp() {
    }

    //verifico que el prestamoDto guarde los valores correctamente
    @Test
    void testCrearPrestamoDto() {
        PrestamoDto prestamoDto = new PrestamoDto();

        prestamoDto.setNumeroCliente(12345);
        prestamoDto.setPlazoMeses(12);
        prestamoDto.setMontoPrestamo(50000.0);
        prestamoDto.setMoneda("PESOS");

        assertEquals(12345, prestamoDto.getNumeroCliente());
        assertEquals(12, prestamoDto.getPlazoMeses());
        assertEquals(50000.0, prestamoDto.getMontoPrestamo());
        assertEquals("PESOS", prestamoDto.getMoneda());
    }
}
