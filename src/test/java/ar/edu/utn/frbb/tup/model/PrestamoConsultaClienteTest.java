package ar.edu.utn.frbb.tup.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;

public class PrestamoConsultaClienteTest {

    private PrestamoConsultaCliente consultaCliente;
    private Prestamo prestamo;

    @BeforeEach
    public void setUp() {
        // Crear una instancia de Prestamo con datos de ejemplo
        prestamo = new Prestamo();
        prestamo.setIdPrestamo(1);
        prestamo.setMontoPrestamo(1000.00);
        prestamo.setInteresTotal(200.00);
        prestamo.setPlazoMeses(12);
        // Suponemos que el plan de pagos contiene cuotas de 100
        Cuota cuota1 = new Cuota(1, 100);
        Cuota cuota2 = new Cuota(2, 100);
        prestamo.setPlanPagos(Arrays.asList(cuota1, cuota2));
    }

    //pruebo el constructor
    @Test
    public void testConstructor() {
        consultaCliente = new PrestamoConsultaCliente(prestamo);
        
        assertEquals(1, consultaCliente.getPrestamoId());
        assertEquals(1000, consultaCliente.getMonto());
        assertEquals(200, consultaCliente.getIntereses());
        assertEquals(12, consultaCliente.getPlazoMeses());
        assertEquals(2, consultaCliente.getPagosRealizados());
    }

    //pruebo el calculo del saldo restante a pagar
    @Test
    public void testCalcularSaldoRestante() {
        consultaCliente = new PrestamoConsultaCliente(prestamo);

        double saldoRestanteEsperado = (1000 + 200) - (100 * 2); 
        assertEquals(saldoRestanteEsperado, consultaCliente.getSaldoRestante());
    }
}
