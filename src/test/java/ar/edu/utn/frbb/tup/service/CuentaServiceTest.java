package ar.edu.utn.frbb.tup.service;

import ar.edu.utn.frbb.tup.controller.dto.CuentaDto;
import ar.edu.utn.frbb.tup.model.Cuenta;
import ar.edu.utn.frbb.tup.model.Prestamo;
import ar.edu.utn.frbb.tup.model.TipoCuenta;
import ar.edu.utn.frbb.tup.model.TipoMoneda;
import ar.edu.utn.frbb.tup.model.exception.CuentaAlreadyExistsException;
import ar.edu.utn.frbb.tup.model.exception.TipoCuentaNoSoportadaException;
import ar.edu.utn.frbb.tup.persistence.dao.CuentaDao;

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
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CuentaServiceTest {
    @Mock
    private CuentaDao cuentaDao;

    @Mock
    private ClienteService clienteService;

    @InjectMocks
    private CuentaService cuentaService;

    @BeforeAll
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    //Revisar que el balance se haya actualizado
    @Test
    void actualizarCuentaClienteTest() throws InstantiationException, IllegalAccessException {
        Prestamo prestamo = new Prestamo();
        prestamo.setMoneda(TipoMoneda.PESOS.getDescripcion());
        prestamo.setNumeroCliente(12345678);
        prestamo.setMontoPrestamo(1000.0);

        Cuenta cuentaMock = mock(Cuenta.class);
        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        cuentas.add(cuentaMock);

        when(clienteService.getCuentasCliente(anyInt())).thenReturn(cuentas);
        when(cuentaMock.getTipoCuenta()).thenReturn(TipoCuenta.CAJA_AHORRO);
        when(cuentaMock.getMoneda()).thenReturn(TipoMoneda.PESOS);
        when(cuentaMock.getBalance()).thenReturn(122.30);

        cuentaService.actualizarCuentaCliente(prestamo);

        verify(cuentaMock).setBalance(1122.30);
    }

    //Tipo de cuenta soportada
    @Test
    void tipoCuentaEstaSoportadaExito(){
        CuentaDto cuenta1 = new CuentaDto();
        cuenta1.setTipoCuenta(TipoCuenta.CAJA_AHORRO.getDescripcion());
        cuenta1.setMoneda("PESOS");

        CuentaDto cuenta2 = new CuentaDto();
        cuenta2.setTipoCuenta(TipoCuenta.CAJA_AHORRO.getDescripcion());
        cuenta2.setMoneda("DOLARES");

        CuentaDto cuenta3 = new CuentaDto();
        cuenta3.setTipoCuenta(TipoCuenta.CUENTA_CORRIENTE.getDescripcion());
        cuenta3.setMoneda("PESOS");

        assertDoesNotThrow( () -> cuentaService.tipoCuentaEstaSoportada(cuenta1) );
        assertTrue(cuentaService.tipoCuentaEstaSoportada(cuenta1));
        assertDoesNotThrow( () -> cuentaService.tipoCuentaEstaSoportada(cuenta1) );
        assertTrue(cuentaService.tipoCuentaEstaSoportada(cuenta2));
        assertDoesNotThrow( () -> cuentaService.tipoCuentaEstaSoportada(cuenta1) );
        assertTrue(cuentaService.tipoCuentaEstaSoportada(cuenta3));
    }

    @Test
    void testCuentaAlreadyExistException() {
        Cuenta cuenta = new Cuenta();

        CuentaDto cuentaDto = new CuentaDto();
        cuentaDto.setTitularDni(12345678);

        when(cuentaDao.find(12345678)).thenReturn(cuenta);

        assertThrows(CuentaAlreadyExistsException.class, () -> cuentaService.darDeAltaCuenta(cuentaDto));
    }

    @Test
    void testTipoCuentaNoSoportadaException() {
        CuentaDto cuentaDto = new CuentaDto();
        cuentaDto.setTitularDni(12345678);
        cuentaDto.setTipoCuenta("TipoCuentaNoSoportada");
        cuentaDto.setMoneda("PESOS");

        assertThrows(TipoCuentaNoSoportadaException.class, () -> cuentaService.darDeAltaCuenta(cuentaDto));
    }
}
