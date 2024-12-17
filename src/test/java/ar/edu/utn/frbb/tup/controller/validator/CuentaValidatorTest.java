package ar.edu.utn.frbb.tup.controller.validator;

import ar.edu.utn.frbb.tup.controller.CuentaDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CuentaValidatorTest {

    //valida que la cuenta sea valida
    @Test
    void validateCuentaValidaTest() {
        CuentaDto cuentaDto = new CuentaDto();
        cuentaDto.setMoneda("PESOS");
        cuentaDto.setTipoCuenta("CAJA_AHORRO");

        assertDoesNotThrow(() -> CuentaValidator.validate(cuentaDto));
    }

    //valida que la moneda sea PESOS o DOLARES
    @Test
    void validateTipoMonedaInvalidaTest() {
        CuentaDto cuentaDto = new CuentaDto();
        cuentaDto.setMoneda("EUROS");
        cuentaDto.setTipoCuenta("CAJA_AHORRO");

        Exception exception = assertThrows(IllegalArgumentException.class, 
            () -> CuentaValidator.validate(cuentaDto));
        assertEquals("La moneda: EUROS no es soportada", exception.getMessage());
    }

    //valida que la cuenta sea de tipo CAJA_AHORRO o CUENTA_CORRIENTE
    @Test
    void validateTipoCuentaInvalidaTest() {
        CuentaDto cuentaDto = new CuentaDto();
        cuentaDto.setMoneda("PESOS");
        cuentaDto.setTipoCuenta("PLAZO_FIJO");

        Exception exception = assertThrows(IllegalArgumentException.class, 
            () -> CuentaValidator.validate(cuentaDto));
        assertEquals("La cuenta del tipo PLAZO_FIJO no es correcta", exception.getMessage());
    }
}
