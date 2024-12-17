package ar.edu.utn.frbb.tup.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CuotaTest {

    private Cuota cuota;

    @BeforeEach
    public void setUp() {
        cuota = new Cuota(1, 1000.50);
    }

    //pruebo el constructor
    @Test
    public void testConstructor() {
        assertEquals(1, cuota.getNroCuota());
        assertEquals(1000.50, cuota.getMonto());
    }
}