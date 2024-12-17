package ar.edu.utn.frbb.tup.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.utn.frbb.tup.controller.CuentaDto;

import java.time.LocalDateTime;

public class CuentaTest {

    private Cuenta cuenta;

    @BeforeEach
    public void setUp() {
        cuenta = new Cuenta();
    }

    //pruebo el constructor sin parametros
    @Test
    public void testConstructorSinParametros() {
        assertNotEquals(0, cuenta.getNumeroCuenta());
        assertEquals(0, cuenta.getBalance());
        assertNotNull(cuenta.getFechaCreacion());
    }

    //creo una cuenta a partir de una cuentaDto
    @Test
    public void testConstructorConCuentaDto() {
        CuentaDto cuentaDto = new CuentaDto();
        cuentaDto.setTipoCuenta("CUENTA_CORRIENTE");
        cuentaDto.setMoneda("PESOS");
        
        Cuenta cuentaDtoTest = new Cuenta(cuentaDto);
        
        assertNotNull(cuentaDtoTest.getTipoCuenta());
        assertNotNull(cuentaDtoTest.getMoneda());
        assertNotEquals(0, cuentaDtoTest.getNumeroCuenta());
        assertEquals(0, cuentaDtoTest.getBalance());
        assertNotNull(cuentaDtoTest.getFechaCreacion());
    }

    //pruebo que el numero de cuenta sea un numero de 8 digitos
    @Test
    public void testGenerarNumeroCuenta() {
        int numeroCuenta = cuenta.getNumeroCuenta();
        assertTrue(numeroCuenta >= 10000000 && numeroCuenta <= 99999999);
    }

    //pruebo la funcion de generar fecha de creacion
    @Test
    public void testGenerarFechaCreacion() {
        LocalDateTime fechaCreacion = cuenta.getFechaCreacion();
        assertNotNull(fechaCreacion);
        assertTrue(fechaCreacion.isBefore(LocalDateTime.now().plusSeconds(1)));
    }
}
