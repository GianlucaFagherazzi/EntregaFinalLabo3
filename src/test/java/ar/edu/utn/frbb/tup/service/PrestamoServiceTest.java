package ar.edu.utn.frbb.tup.service;

import ar.edu.utn.frbb.tup.controller.dto.PrestamoConsultaDto;
import ar.edu.utn.frbb.tup.controller.dto.PrestamoDto;
import ar.edu.utn.frbb.tup.model.Cuenta;
import ar.edu.utn.frbb.tup.model.Cuota;
import ar.edu.utn.frbb.tup.model.Prestamo;
import ar.edu.utn.frbb.tup.persistence.dao.PrestamoDao;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyIterable;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PrestamoServiceTest {

    @Mock
    private PrestamoDao prestamoDao;

    @Mock
    private PrestamoDto prestamoDto;

    @InjectMocks
    private PrestamoService prestamoService;

    @BeforeAll
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    //Calculo de intereses
    @Test
    void calculaInteresesTest() {
        double calculo = 5000.20 * ((double) 8 / 12);
        double calculoMetodo = prestamoService.calculaIntereses(5000.20, 8);

        assertDoesNotThrow(() -> prestamoService.calculaIntereses(5000.20, 8));
        assertEquals(calculo, calculoMetodo);
    }

    //Verifica si 
    @Test
    void pedirConsultaPrestamosExitoTest() {
        Cuota cuota1 = new Cuota(1, 4000.20);
        Cuota cuota2 = new Cuota(2, 4000.20);
        List<Cuota> cuotasLista = new ArrayList<Cuota>(List.of(cuota1, cuota2));

        Prestamo prestamo = new Prestamo();
        prestamo.setNumeroCliente(123456789);
        prestamo.setMontoPrestamo(4000.20);
        prestamo.setInteresTotal(2000.10);
        prestamo.setPlazoMeses(6);
        prestamo.setPlanPagos(cuotasLista);

        List<Prestamo> prestamosCliente = new ArrayList<Prestamo>();
        prestamosCliente.add(prestamo);

        when(prestamoDao.getPrestamosByCliente(anyInt())).thenReturn(prestamosCliente);

        assertNotNull(prestamoDao.getPrestamosByCliente(123456789));
        assertEquals(prestamoDao.getPrestamosByCliente(123456789).size(), prestamosCliente.size());
        assertDoesNotThrow(() -> prestamoService.pedirConsultaPrestamos(123456789));
        assertTrue( (prestamoService.pedirConsultaPrestamos(123456789) instanceof PrestamoConsultaDto));
    }

    @Test
    void pedirConsultaPrestamosFalloTest() {
        List<Prestamo> prestamosCliente = new ArrayList<Prestamo>();

        when(prestamoDao.getPrestamosByCliente(anyInt())).thenReturn(prestamosCliente);
        assertThrows(IllegalArgumentException.class, () -> {
            prestamoService.pedirConsultaPrestamos(123456789);
        });
    }

    //getCuentaPermitida (4)
    //validator (4)
    //verificaScore (4)
    //establecerMensajeScoring (3)
    //pedirPrestamo(2)
    //calcularScoring(2)
    @Test
    void testPedirPrestamo() {
        Prestamo prestamo = new Prestamo(prestamoDto);
        prestamo.setNumeroCliente(123456789);
        prestamo.setMontoPrestamo(4000.20);
        prestamo.setPlazoMeses(6);
        prestamo.setInteresTotal(2000.10);
        prestamo.setEstado("APROBADO");
        prestamo.setMensaje("El monto del prestamo fue acreditado a su cuenta");
        
        


    }
}