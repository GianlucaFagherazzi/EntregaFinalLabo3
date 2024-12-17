package ar.edu.utn.frbb.tup.persistence.entity;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import ar.edu.utn.frbb.tup.model.Cuota;
import ar.edu.utn.frbb.tup.model.Prestamo;

import java.util.ArrayList;
import java.util.List;

public class PrestamoEntityTest {

    @Mock
    private Prestamo prestamo;

    @Mock
    private PrestamoEntity prestamoEntity;

    @BeforeEach
    // antes de cada prueba, creo un prestamo y un prestamoEntity
    public void setUp() {
        Cuota cuota1 = new Cuota(1, 4000.20);
        Cuota cuota2 = new Cuota(2, 4000.20);
        List<Cuota> cuotasLista = new ArrayList<>();
        cuotasLista.add(cuota1);
        cuotasLista.add(cuota2);

        prestamo = new Prestamo();
        prestamo.setNumeroCliente(12345678);
        prestamo.setMontoPrestamo(4000.20);
        prestamo.setInteresTotal(2000.10);
        prestamo.setPlazoMeses(6);
        prestamo.setMoneda("DOLARES");
        prestamo.setEstado("APROBADO");
        prestamo.setMensaje("Prestamo aprobado");
        prestamo.setPlanPagos(cuotasLista);

        prestamoEntity = new PrestamoEntity(prestamo, 1);
    }

    //Pruebo la funcion toPrestamo()
    @Test
    public void testPrestamoEntityToPrestamo() {
        Prestamo prestamoConverted = prestamoEntity.toPrestamo();

        assertEquals(prestamo.getNumeroCliente(), prestamoConverted.getNumeroCliente());
        assertEquals(prestamo.getPlazoMeses(), prestamoConverted.getPlazoMeses());
        assertEquals(prestamo.getMontoPrestamo(), prestamoConverted.getMontoPrestamo());
        assertEquals(prestamo.getMoneda(), prestamoConverted.getMoneda());
        assertEquals(prestamo.getEstado(), prestamoConverted.getEstado());
        assertEquals(prestamo.getMensaje(), prestamoConverted.getMensaje());
        assertEquals(prestamo.getInteresTotal(), prestamoConverted.getInteresTotal());
        assertEquals(prestamo.getPlanPagos().size(), prestamoConverted.getPlanPagos().size());
    }

    //pruebo el constructor de prestamoentity
    @Test
    public void testPrestamoEntityConstructor() {
        Prestamo prestamoTest = new Prestamo();
        prestamoTest.setNumeroCliente(87654321);
        prestamoTest.setMontoPrestamo(10000.0);
        prestamoTest.setInteresTotal(5000.0);
        prestamoTest.setPlazoMeses(12);
        prestamoTest.setMoneda("PESOS");
        prestamoTest.setEstado("APROBADO");
        prestamoTest.setMensaje("Prestamo aprobado");

        Cuota cuotaTest1 = new Cuota(1, 5000.0);
        Cuota cuotaTest2 = new Cuota(2, 5000.0);
        List<Cuota> cuotasTest = new ArrayList<>();
        cuotasTest.add(cuotaTest1);
        cuotasTest.add(cuotaTest2);

        prestamoTest.setPlanPagos(cuotasTest);

        PrestamoEntity prestamoEntityTest = new PrestamoEntity(prestamoTest, 3);

        assertNotNull(prestamoEntityTest);
        assertEquals(3, prestamoEntityTest.getId());


    }
}
