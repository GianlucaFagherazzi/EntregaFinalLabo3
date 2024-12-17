package ar.edu.utn.frbb.tup.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EstadoPrestamoTest {

    private Prestamo prestamo;

    @BeforeEach
    public void setUp() {
        prestamo = new Prestamo();
    }

    //compruebo el constructor
    @Test
    public void testConstructor() {
        prestamo.setMontoPrestamo(50000.0);
        prestamo.setPlazoMeses(24);
        prestamo.setInteresTotal(12000.0);

        EstadoPrestamo estadoPrestamo = new EstadoPrestamo(prestamo);

        assertEquals(50000.0, estadoPrestamo.getMonto());
        assertEquals(24, estadoPrestamo.getPlazoMeses());
        assertEquals(12000.0, estadoPrestamo.getIntereses());
    }
}
