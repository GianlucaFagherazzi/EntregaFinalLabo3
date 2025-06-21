package ar.edu.utn.frbb.tup.service;

import ar.edu.utn.frbb.tup.model.Cuenta;
import ar.edu.utn.frbb.tup.model.exception.ClienteAlreadyExistsException;
import ar.edu.utn.frbb.tup.model.exception.TipoCuentaAlreadyExistsException;
import ar.edu.utn.frbb.tup.persistence.dao.ClienteDao;
import ar.edu.utn.frbb.tup.persistence.dao.CuentaDao;
import ar.edu.utn.frbb.tup.controller.dto.ClienteDto;
import ar.edu.utn.frbb.tup.model.Cliente;
import ar.edu.utn.frbb.tup.model.TipoPersona;
import ar.edu.utn.frbb.tup.model.TipoMoneda;
import ar.edu.utn.frbb.tup.model.TipoCuenta;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ClienteServiceTest {
    @Mock
    private ClienteDao clienteDao;

    @Mock
    private CuentaDao cuentaDao;

    @InjectMocks
    private ClienteService clienteService;

    @BeforeAll
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // error porque el cliente es menor de edad
     @Test
    void testValidatorAlta() {
        ClienteDto clienteMenorDeEdad = new ClienteDto();
        clienteMenorDeEdad.setFechaNacimiento(String.valueOf(LocalDate.of(2010, 1, 1)));
        clienteMenorDeEdad.setDni(12345678);

        assertThrows(IllegalArgumentException.class, () -> clienteService.darDeAltaCliente(clienteMenorDeEdad));
    }


    // El cliente ya existe
    @Test
    void testClienteAlreadyExistsException() throws ClienteAlreadyExistsException {
        ClienteDto pepeRino = new ClienteDto();
        pepeRino.setDni(26456437);
        pepeRino.setNombre("Pepe");
        pepeRino.setApellido("Rino");
        pepeRino.setFechaNacimiento(String.valueOf(LocalDate.of(1978, 3, 25)));
        pepeRino.setTipoPersona("F");

        when(clienteDao.find(26456437, false)).thenReturn(new Cliente());

        assertThrows(ClienteAlreadyExistsException.class, () -> clienteService.darDeAltaCliente(pepeRino));
    }

    // Cuenta ya existe
    @Test
    void testCuentaYaExiste() throws TipoCuentaAlreadyExistsException {
        Cliente luciano = new Cliente();
        luciano.setDni(12345678);
        luciano.setNombre("Lucho");
        luciano.setApellido("Rino");
        luciano.setFechaNacimiento(LocalDate.of(2002, 5, 3));
        luciano.setTipoPersona(TipoPersona.PERSONA_FISICA);

        Cuenta cuenta = new Cuenta();
        cuenta.setMoneda(TipoMoneda.PESOS);
        cuenta.setBalance(500000);
        cuenta.setTipoCuenta(TipoCuenta.CAJA_AHORRO);

        when(clienteDao.find(12345678, true)).thenReturn(luciano);

        clienteService.agregarCuenta(cuenta, luciano.getDni());

        Cuenta cuenta2 = new Cuenta();
        cuenta2.setMoneda(TipoMoneda.PESOS);
        cuenta2.setBalance(500000);
        cuenta2.setTipoCuenta(TipoCuenta.CAJA_AHORRO);

        when(clienteDao.find(12345678, true)).thenReturn(luciano);

        assertThrows(TipoCuentaAlreadyExistsException.class,
                () -> clienteService.agregarCuenta(cuenta2, luciano.getDni()));

        assertEquals(1, luciano.getCuentas().size());
        assertEquals(luciano.getDni(), cuenta.getTitular());
    }

    // Agregar una cuenta
    @Test
    void testAgregarCuentaAClienteExito() throws TipoCuentaAlreadyExistsException {
        Cliente cliente = new Cliente();
        cliente.setDni(12345678);
        cliente.setTipoPersona(TipoPersona.PERSONA_FISICA);
        cliente.setBanco("Banco Test");
        cliente.setNombre("Juan");
        cliente.setApellido("Perez");
        cliente.setFechaAlta(LocalDate.of(2023, 1, 1));
        cliente.setFechaNacimiento(LocalDate.of(1990, 5, 10));

        Cuenta cuenta = new Cuenta();
        cuenta.setNumeroCuenta(1);
        cuenta.setBalance(1000.0);
        cuenta.setTitular(12345678);
        cuenta.setTipoCuenta(TipoCuenta.CAJA_AHORRO);
        cuenta.setMoneda(TipoMoneda.PESOS);

        when(clienteDao.find(12345678, true)).thenReturn(cliente);
        clienteService.agregarCuenta(cuenta, cliente.getDni());

        assertEquals(1, cliente.getCuentas().size());
        assertEquals(cliente.getDni(), cuenta.getTitular());
    }

    // Agregan 2 cuentas con exito
    @Test
    void testAgregarDosCuentasSucess() throws TipoCuentaAlreadyExistsException {
        Cliente luciano = new Cliente();
        luciano.setDni(12345678);
        luciano.setNombre("Lucho");
        luciano.setApellido("Rino");
        luciano.setFechaNacimiento(LocalDate.of(2002, 5, 3));
        luciano.setTipoPersona(TipoPersona.PERSONA_FISICA);

        Cuenta cuenta = new Cuenta();
        cuenta.setMoneda(TipoMoneda.PESOS);
        cuenta.setBalance(500000);
        cuenta.setTipoCuenta(TipoCuenta.CAJA_AHORRO);

        when(clienteDao.find(12345678, true)).thenReturn(luciano);
        clienteService.agregarCuenta(cuenta, luciano.getDni());

        Cuenta cuenta2 = new Cuenta();
        cuenta2.setMoneda(TipoMoneda.PESOS);
        cuenta2.setBalance(500000);
        cuenta2.setTipoCuenta(TipoCuenta.CUENTA_CORRIENTE);

        when(clienteDao.find(12345678, true)).thenReturn(luciano);
        clienteService.agregarCuenta(cuenta2, luciano.getDni());

        when(cuentaDao.getCuentasByCliente(anyInt())).thenReturn(List.of(cuenta, cuenta2));
        List<Cuenta> cuentas = cuentaDao.getCuentasByCliente(12345678);

        assertEquals(2, luciano.getCuentas().size());
        assertEquals(2, cuentas.size());
        assertEquals(luciano.getDni(), cuentas.get(0).getTitular());

        assertEquals(TipoCuenta.CAJA_AHORRO, cuenta.getTipoCuenta());
        assertEquals(TipoCuenta.CUENTA_CORRIENTE, cuenta2.getTipoCuenta());

        assertEquals(luciano.getDni(), cuenta.getTitular());
        assertEquals(luciano.getDni(), cuenta2.getTitular());
    }

    // Agregar dos cuentas con exito, una cuenta corriente y una caja de ahorro
    @Test
    void testAgregarDosCajasDeAhorroDifMonedaSucess() throws TipoCuentaAlreadyExistsException {
        Cliente luciano = new Cliente();
        luciano.setDni(12345678);
        luciano.setNombre("Pepe");
        luciano.setApellido("Rino");
        luciano.setFechaNacimiento(LocalDate.of(2002, 5, 3));
        luciano.setTipoPersona(TipoPersona.PERSONA_FISICA);

        Cuenta cuenta = new Cuenta();
        cuenta.setMoneda(TipoMoneda.PESOS);
        cuenta.setBalance(500000);
        cuenta.setTipoCuenta(TipoCuenta.CAJA_AHORRO);

        when(clienteDao.find(12345678, true)).thenReturn(luciano);
        clienteService.agregarCuenta(cuenta, luciano.getDni());

        Cuenta cuenta2 = new Cuenta();
        cuenta2.setMoneda(TipoMoneda.DOLARES);
        cuenta2.setBalance(250);
        cuenta2.setTipoCuenta(TipoCuenta.CAJA_AHORRO);

        when(clienteDao.find(12345678, true)).thenReturn(luciano);
        clienteService.agregarCuenta(cuenta2, luciano.getDni());

        assertEquals(TipoCuenta.CAJA_AHORRO, cuenta.getTipoCuenta());
        assertEquals(TipoCuenta.CAJA_AHORRO, cuenta2.getTipoCuenta());

        assertEquals(TipoMoneda.PESOS, cuenta.getMoneda());
        assertEquals(TipoMoneda.DOLARES, cuenta2.getMoneda());

        assertEquals(luciano.getDni(), cuenta.getTitular());
        assertEquals(luciano.getDni(), cuenta2.getTitular());
    }

    // Buscar cliente por dni
    @Test
    void testBuscarClientePorDniExito() {
        Cliente luciano = new Cliente();
        luciano.setDni(12345678);
        luciano.setNombre("Pepe");
        luciano.setApellido("Rino");
        luciano.setFechaNacimiento(LocalDate.of(2003, 5, 31));
        luciano.setTipoPersona(TipoPersona.PERSONA_FISICA);

        when(clienteDao.find(12345678, true)).thenReturn(luciano);

        clienteService.buscarClientePorDni(luciano.getDni());

        assertEquals("Pepe", luciano.getNombre());
        assertEquals(12345678, luciano.getDni());
    }

    // buscar cliente por dni falla
    @Test
    void testBuscarClientePorDniFalla() {
        when(clienteDao.find(12345678, true)).thenReturn(null);
        assertThrows(IllegalArgumentException.class, () -> clienteService.buscarClientePorDni(12345678));
    }

    //obtener todos los clientes
    @Test
    void testObtenerClientesExito() {
        Cliente cliente1 = new Cliente();
        cliente1.setDni(10000001);
        Cliente cliente2 = new Cliente();
        cliente2.setDni(10000002);

        when(clienteDao.findAll()).thenReturn(List.of(cliente1, cliente2));

        List<Cliente> clientes = clienteService.obtenerTodosClientes();

        assertEquals(2, clientes.size());
        assertTrue(clientes.contains(cliente1));
        assertTrue(clientes.contains(cliente2));
    }
    
    @Test
    void testObtenerClientesEmpty(){
        Cliente cliente1 = new Cliente();
        cliente1.setDni(10000001);
        Cliente cliente2 = new Cliente();
        cliente2.setDni(10000002);

        when(clienteDao.findAll()).thenReturn(new ArrayList<>());

        List<Cliente> clientes = clienteService.obtenerTodosClientes();

        assertTrue(clientes == null);
    }
}