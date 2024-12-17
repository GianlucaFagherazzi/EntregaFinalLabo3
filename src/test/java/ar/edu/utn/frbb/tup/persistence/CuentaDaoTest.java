package ar.edu.utn.frbb.tup.persistence;

import ar.edu.utn.frbb.tup.model.Cuenta;
import ar.edu.utn.frbb.tup.model.TipoCuenta;
import ar.edu.utn.frbb.tup.model.TipoMoneda;
import ar.edu.utn.frbb.tup.persistence.entity.CuentaEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CuentaDaoTest {

    private CuentaDao cuentaDao;

    @BeforeEach
    void setUp() {
        cuentaDao = new CuentaDao();
    }

    // Test para guardar una cuenta y buscarla por su id
    @Test
    void testFindCuentaExistente() {
        Cuenta cuenta = new Cuenta();
        cuenta.setNumeroCuenta(1);
        cuenta.setBalance(1000.0);
        cuenta.setTitular(12345678);
        cuenta.setTipoCuenta(TipoCuenta.CAJA_AHORRO);
        cuenta.setMoneda(TipoMoneda.PESOS);
        cuentaDao.save(cuenta);

        Cuenta resultado = cuentaDao.find(1);

        assertNotNull(resultado);
        assertEquals(1, resultado.getNumeroCuenta());
        assertEquals(1000.0, resultado.getBalance());
        assertEquals(TipoCuenta.CAJA_AHORRO, resultado.getTipoCuenta());
        assertEquals(TipoMoneda.PESOS, resultado.getMoneda());
    }

    // Test para buscar una cuenta inexistente
    @Test
    void testFindCuentaInexistente() {
        Cuenta resultado = cuentaDao.find(999);

        assertNull(resultado);
    }

    //testeo para ver si una cuenta se actualiza correctamente
    @Test
    void testUpdateCuenta() {
        Cuenta cuenta = new Cuenta();
        cuenta.setNumeroCuenta(1);
        cuenta.setBalance(500.0);
        cuenta.setTitular(12345678);
        cuenta.setTipoCuenta(TipoCuenta.CAJA_AHORRO);
        cuenta.setMoneda(TipoMoneda.PESOS);
        cuentaDao.save(cuenta);

        cuenta.setBalance(750.0);
        cuentaDao.update(cuenta);

        CuentaEntity entity = (CuentaEntity) cuentaDao.getInMemoryDatabase().get(1);
        assertNotNull(entity);

        Cuenta cuentaTest = entity.toCuenta();
        assertEquals(750.0, cuentaTest.getBalance());
    }

    //obtener todas las cuentas de un cliente
    @Test
    void testGetCuentasByCliente() {
        Cuenta cuenta1 = new Cuenta();
        cuenta1.setNumeroCuenta(1);
        cuenta1.setBalance(1000.0);
        cuenta1.setTitular(12345678);
        cuenta1.setTipoCuenta(TipoCuenta.CAJA_AHORRO);
        cuenta1.setMoneda(TipoMoneda.PESOS);

        Cuenta cuenta2 = new Cuenta();
        cuenta2.setNumeroCuenta(2);
        cuenta2.setBalance(2000.0);
        cuenta2.setTitular(12345678);
        cuenta2.setTipoCuenta(TipoCuenta.CAJA_AHORRO);
        cuenta2.setMoneda(TipoMoneda.PESOS);

        Cuenta cuenta3 = new Cuenta();
        cuenta3.setNumeroCuenta(3);
        cuenta3.setBalance(1500.0);
        cuenta3.setTitular(87654321);
        cuenta3.setTipoCuenta(TipoCuenta.CAJA_AHORRO);
        cuenta3.setMoneda(TipoMoneda.PESOS);

        cuentaDao.save(cuenta1);
        cuentaDao.save(cuenta2);
        cuentaDao.save(cuenta3);

        List<Cuenta> cuentasDelCliente = cuentaDao.getCuentasByCliente(12345678);

        assertNotNull(cuentasDelCliente);
        assertEquals(2, cuentasDelCliente.size());
        assertEquals(1, cuentasDelCliente.get(0).getNumeroCuenta());
        assertEquals(2, cuentasDelCliente.get(1).getNumeroCuenta());
    }

    // Intentar obtener cuentas para un cliente sin cuentas
    @Test
    void testGetCuentasByClienteSinCuentas() {
        List<Cuenta> cuentasDelCliente = cuentaDao.getCuentasByCliente(99999999);

        assertNotNull(cuentasDelCliente);
        assertTrue(cuentasDelCliente.isEmpty());
    }
}
