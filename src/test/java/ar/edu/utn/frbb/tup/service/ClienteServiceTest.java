package ar.edu.utn.frbb.tup.service;

import ar.edu.utn.frbb.tup.controller.ClienteDto;
import ar.edu.utn.frbb.tup.model.Cuenta;
import ar.edu.utn.frbb.tup.model.exception.ClienteAlreadyExistsException;
import ar.edu.utn.frbb.tup.model.exception.TipoCuentaAlreadyExistsException;
import ar.edu.utn.frbb.tup.persistence.ClienteDao;
import ar.edu.utn.frbb.tup.model.Cliente;
import ar.edu.utn.frbb.tup.model.TipoPersona;
import ar.edu.utn.frbb.tup.model.TipoMoneda;
import ar.edu.utn.frbb.tup.model.TipoCuenta;
import ar.edu.utn.frbb.tup.persistence.CuentaDao;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ClienteServiceTest {
    @Mock
    private ClienteDao clienteDao;

    @InjectMocks
    private ClienteService clienteService;

    @Mock
    private CuentaDao cuentaDao;

    @BeforeAll
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    //El cliente es menor de edad
    @Test
    public void testClienteMenor18Anios() {
        ClienteDto clienteMenorDeEdad = new ClienteDto();
        clienteMenorDeEdad.setFechaNacimiento(String.valueOf(LocalDate.of(2020, 4, 4)));
        assertThrows(IllegalArgumentException.class, () -> clienteService.darDeAltaCliente(clienteMenorDeEdad));
    }

    //Cliente guardado exitosamente
    @Test
    public void testClienteExito() throws ClienteAlreadyExistsException {
        ClienteDto cliente = new ClienteDto();
        cliente.setFechaNacimiento(String.valueOf(LocalDate.of(1978,3,25)));
        cliente.setDni(29857643);
        cliente.setTipoPersona("F");
        clienteService.darDeAltaCliente(cliente);

        Cliente clienteSave = new Cliente(cliente);
        clienteDao.save(clienteSave);

        verify(clienteDao, times(1)).save(clienteSave);
    }

    //El cliente ya existe
    @Test
    public void testClienteAlreadyExistsException() throws ClienteAlreadyExistsException {
        ClienteDto pepeRino = new ClienteDto();
        pepeRino.setDni(26456437);
        pepeRino.setNombre("Pepe");
        pepeRino.setApellido("Rino");
        pepeRino.setFechaNacimiento(String.valueOf(LocalDate.of(1978, 3,25)));
        pepeRino.setTipoPersona("F");

        when(clienteDao.find(26456437, false)).thenReturn(new Cliente());

        assertThrows(ClienteAlreadyExistsException.class, () -> clienteService.darDeAltaCliente(pepeRino));
    }

    //Agregar una cuenta
    @Test
    public void testAgregarCuentaAClienteExito() throws TipoCuentaAlreadyExistsException {
        Cliente pepeRino = new Cliente();
        pepeRino.setDni(26456439);
        pepeRino.setNombre("Pepe");
        pepeRino.setApellido("Rino");
        pepeRino.setFechaNacimiento(LocalDate.of(1978, 3,25));
        pepeRino.setTipoPersona(TipoPersona.PERSONA_FISICA);

        Cuenta cuenta = new Cuenta();
        cuenta.setMoneda(TipoMoneda.PESOS);
        cuenta.setBalance(500000);
        cuenta.setTipoCuenta(TipoCuenta.CAJA_AHORRO);

        when(clienteDao.find(26456439, true)).thenReturn(pepeRino);

        clienteService.agregarCuenta(cuenta, pepeRino.getDni());

        assertEquals(1, pepeRino.getCuentas().size());
        assertEquals(pepeRino.getDni(), cuenta.getTitular());
    }

    //Cuenta ya existe
    @Test
    public void testAgregaCuentaAClienteFalla() throws TipoCuentaAlreadyExistsException{
        Cliente luciano = new Cliente();
        luciano.setDni(12345678);
        luciano.setNombre("Lucho");
        luciano.setApellido("Rino");
        luciano.setFechaNacimiento(LocalDate.of(2002, 5,3));
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

        assertThrows(TipoCuentaAlreadyExistsException.class, () -> clienteService.agregarCuenta(cuenta2, luciano.getDni()));

        assertEquals(1, luciano.getCuentas().size());
        assertEquals(luciano.getDni(), cuenta.getTitular());
    }

    //Agregan 2 cuentas con exito
    @Test
    public void testAgregarDosCuentasSucess() throws TipoCuentaAlreadyExistsException{
        Cliente luciano = new Cliente();
        luciano.setDni(12345678);
        luciano.setNombre("Lucho");
        luciano.setApellido("Rino");
        luciano.setFechaNacimiento(LocalDate.of(2002, 5,3));
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

        assertEquals(2, luciano.getCuentas().size());

        // chequea el tipo de cuenta
        assertEquals(TipoCuenta.CAJA_AHORRO, cuenta.getTipoCuenta());
        assertEquals(TipoCuenta.CUENTA_CORRIENTE, cuenta2.getTipoCuenta());

        assertEquals(luciano.getDni(), cuenta.getTitular());
        assertEquals(luciano.getDni(), cuenta2.getTitular());
    }

    //Agregar dos cuentas con exito, una cuenta corriente y una caja de ahorro
    @Test
    public void agregarDosCajasDeAhorroDifMonedaSucess() throws TipoCuentaAlreadyExistsException{
        Cliente luciano = new Cliente();
        luciano.setDni(12345678);
        luciano.setNombre("Pepe");
        luciano.setApellido("Rino");
        luciano.setFechaNacimiento(LocalDate.of(2002, 5,3));
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

        // chequea el tipo de cuenta
        assertEquals(TipoCuenta.CAJA_AHORRO, cuenta.getTipoCuenta());
        assertEquals(TipoCuenta.CAJA_AHORRO, cuenta2.getTipoCuenta());

        // chequea las monedas de las cuentas
        assertEquals(TipoMoneda.PESOS, cuenta.getMoneda());
        assertEquals(TipoMoneda.DOLARES, cuenta2.getMoneda());

        //chequea los titulares de las cuentas
        assertEquals(luciano.getDni(), cuenta.getTitular());
        assertEquals(luciano.getDni(), cuenta2.getTitular());
    }

    //Buscar cliente por dni
    @Test
    public void buscarClientePorDniExito(){
        Cliente luciano = new Cliente();
        luciano.setDni(12345678);
        luciano.setNombre("Pepe");
        luciano.setApellido("Rino");
        luciano.setFechaNacimiento(LocalDate.of(2003,5,31));
        luciano.setTipoPersona(TipoPersona.PERSONA_FISICA);

        //mock
        when(clienteDao.find(12345678, true)).thenReturn(luciano); //no me deja pasarlo con false

        clienteService.buscarClientePorDni(luciano.getDni());

        assertEquals("Pepe", luciano.getNombre());
        assertEquals(12345678, luciano.getDni());
    }

    //buscar cliente por dni falla
    @Test
    public void buscarClientePorDniFalla(){
        //mock
        when(clienteDao.find(12345678, true)).thenReturn(null);
        assertThrows(IllegalArgumentException.class, () -> clienteService.buscarClientePorDni(12345678));
    }
}