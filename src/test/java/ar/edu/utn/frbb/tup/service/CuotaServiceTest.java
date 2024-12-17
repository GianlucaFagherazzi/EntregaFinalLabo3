package ar.edu.utn.frbb.tup.service;

import ar.edu.utn.frbb.tup.model.Prestamo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CuotaServiceTest {

    private Prestamo prestamo = new Prestamo();

    @BeforeEach
    public void prestamoConfig(){
        prestamo.setInteresTotal(8000.0);
        prestamo.setMontoPrestamo(16000.0);
        prestamo.setPlazoMeses(12);
    }

    @BeforeAll
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    //Revisar que se generen bien las cuotas
    @Test
    void generarCuotasTest(){
        CuotaService.generarCuotas(prestamo);
        assertDoesNotThrow( () -> CuotaService.generarCuotas(prestamo));
    }

    //Revisar que las cuotas generadas de manera random funcionen
    @Test
    void generarRandomCantCoutasTest(){
        int random = CuotaService.generarRandomCantCuotas(prestamo.getPlazoMeses());

        assertDoesNotThrow( () -> CuotaService.generarRandomCantCuotas(prestamo.getPlazoMeses()) );
        assertNotEquals(prestamo.getPlazoMeses(), random);
    }

    //Calcular correctamente el monto de la cuota
    @Test
    void calcularMontoCuotaTest(){
        double calculoTesteo = (16000.0 + 8000.0) / 12;
        double resultadoCalculo = CuotaService.calcularMontoCuota(prestamo);
        assertDoesNotThrow( () -> CuotaService.calcularMontoCuota(prestamo));
        assertEquals(calculoTesteo, resultadoCalculo);
    }

}