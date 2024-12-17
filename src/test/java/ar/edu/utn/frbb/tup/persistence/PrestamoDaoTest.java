package ar.edu.utn.frbb.tup.persistence;

import ar.edu.utn.frbb.tup.model.Prestamo;
import ar.edu.utn.frbb.tup.persistence.entity.PrestamoEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PrestamoDaoTest {
    private PrestamoDao prestamoDao;

    @BeforeEach
    void setUp() {
        prestamoDao = new PrestamoDao();
        prestamoDao.getInMemoryDatabase().clear();
    }

    // verifico que el prestamo se almacene correctamente
    @Test
    void testAlmacenarDatosPrestamo() {
        Prestamo prestamo = new Prestamo();
        prestamo.setNumeroCliente(12345678);
        prestamo.setMontoPrestamo(50000.0);
        prestamo.setPlazoMeses(12);
        prestamo.setInteresTotal(500.0);

        prestamoDao.almacenarDatosPrestamo(prestamo);

        PrestamoEntity entity = (PrestamoEntity) prestamoDao.getInMemoryDatabase().values().iterator().next();
        assertNotNull(entity);
        assertEquals(12345678, entity.getNumeroCliente());
    }

    // obtener prestamos por cliente
    @Test
    void testGetPrestamosByClienteConPrestamos() {
        Prestamo prestamo1 = new Prestamo();
        prestamo1.setNumeroCliente(12345678);
        prestamo1.setMontoPrestamo(50000.0);
        prestamo1.setPlazoMeses(12);
        prestamo1.setInteresTotal(500.0);

        Prestamo prestamo2 = new Prestamo();
        prestamo2.setNumeroCliente(12345678);
        prestamo2.setMontoPrestamo(30000.0);
        prestamo2.setPlazoMeses(6);
        prestamo2.setInteresTotal(500.0);

        prestamoDao.almacenarDatosPrestamo(prestamo1);
        prestamoDao.almacenarDatosPrestamo(prestamo2);

        List<Prestamo> prestamos = prestamoDao.getPrestamosByCliente(12345678);

        assertNotNull(prestamos);
        assertEquals(2, prestamos.size());
        assertEquals(50000.0, prestamos.get(0).getMontoPrestamo());
        assertEquals(30000.0, prestamos.get(1).getMontoPrestamo());
    }

    // verifico que devuelva una lista vacia si no hay prestamos para el cliente
    @Test
    void testGetPrestamosByClienteSinPrestamos() {
        List<Prestamo> prestamos = prestamoDao.getPrestamosByCliente(99999999);

        assertNotNull(prestamos);
        assertTrue(prestamos.isEmpty());
    }

    //test para verificar que se genere un id unico
    @Test
    void testGenerarIdUnico() {
        Prestamo prestamo1 = new Prestamo();
        prestamo1.setNumeroCliente(12345678);
        prestamo1.setMontoPrestamo(50000.0);
        prestamo1.setPlazoMeses(12);
        prestamo1.setInteresTotal(500.0);

        Prestamo prestamo2 = new Prestamo();
        prestamo2.setNumeroCliente(87654321);
        prestamo2.setMontoPrestamo(75000.0);
        prestamo2.setPlazoMeses(24);
        prestamo2.setInteresTotal(500.0);

        prestamoDao.almacenarDatosPrestamo(prestamo1);
        prestamoDao.almacenarDatosPrestamo(prestamo2);

        PrestamoEntity entity1 = (PrestamoEntity) prestamoDao.getInMemoryDatabase().get(1);
        PrestamoEntity entity2 = (PrestamoEntity) prestamoDao.getInMemoryDatabase().get(2);

        assertNotNull(entity1);
        assertNotNull(entity2);
        assertNotEquals(entity1.getId(), entity2.getId());
    }
}
