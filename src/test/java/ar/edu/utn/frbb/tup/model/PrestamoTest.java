package ar.edu.utn.frbb.tup.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PrestamoTest {
    
    @BeforeAll
    public void setUp() {
    }

    //Pruebo que el prestamo se creo correctamente
    @Test
    public void testCrearPrestamo() {
        Cuota cuotaMock = mock(Cuota.class);

        Prestamo prestamo = new Prestamo();
        prestamo.setNumeroCliente(12345);
        prestamo.setIdPrestamo(1);
        prestamo.setPlazoMeses(12);
        prestamo.setMontoPrestamo(10000.0);
        prestamo.setMoneda("ARS");
        prestamo.setEstado("APROBADO");
        prestamo.setMensaje("El préstamo ha sido aprobado");
        prestamo.addCuota(cuotaMock);
        prestamo.setInteresTotal(1200.0);

        // Verificaciones
        assertEquals(12345, prestamo.getNumeroCliente());
        assertEquals(1, prestamo.getIdPrestamo());
        assertEquals(12, prestamo.getPlazoMeses());
        assertEquals(10000.0, prestamo.getMontoPrestamo());
        assertEquals("ARS", prestamo.getMoneda());
        assertEquals("APROBADO", prestamo.getEstado());
        assertEquals("El préstamo ha sido aprobado", prestamo.getMensaje());
        assertEquals(1200.0, prestamo.getInteresTotal());
        assertNotNull(prestamo.getPlanPagos());
        assertEquals(1, prestamo.getPlanPagos().size());
        assertTrue(prestamo.getPlanPagos().contains(cuotaMock));
    }
}
