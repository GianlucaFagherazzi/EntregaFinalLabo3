package ar.edu.utn.frbb.tup.persistence.entity;

import ar.edu.utn.frbb.tup.model.Cuenta;
import ar.edu.utn.frbb.tup.model.TipoCuenta;
import ar.edu.utn.frbb.tup.model.TipoMoneda;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class CuentaEntityTest {
    //pruebo el constructor de cuentaentity y el tocuenta
    @Test
    public void testToCuenta() {
        Cuenta cuentaTest = new Cuenta();
        cuentaTest.setNumeroCuenta(123456);
        cuentaTest.setTitular(98765432);
        cuentaTest.setFechaCreacion(LocalDateTime.of(2023, 12, 17, 10, 0));
        cuentaTest.setBalance(1000.50);
        cuentaTest.setTipoCuenta(TipoCuenta.CUENTA_CORRIENTE);
        cuentaTest.setMoneda(TipoMoneda.DOLARES); 

        CuentaEntity cuentaEntityTest = new CuentaEntity(cuentaTest);

        Cuenta cuentaTestGenerada = cuentaEntityTest.toCuenta();

        assertNotNull(cuentaTestGenerada);
        assertEquals(cuentaEntityTest.getId(), cuentaTestGenerada.getNumeroCuenta());
    }
}
