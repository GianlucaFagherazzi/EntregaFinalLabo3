package ar.edu.utn.frbb.tup.model;

import ar.edu.utn.frbb.tup.controller.ClienteDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

public class ClienteTest {

    private ClienteDto clienteDto;

    @BeforeEach
    public void setUp() {
        clienteDto = new ClienteDto();
        clienteDto.setDni(12345678);
        clienteDto.setApellido("Perez");
        clienteDto.setNombre("Juan");
        clienteDto.setFechaNacimiento(LocalDate.of(1990, 1, 1).toString());
        clienteDto.setBanco("Banco Nación");
        clienteDto.setTipoPersona("F");
    }

    //verifico que se haya construido bien el cliente
    @Test
    public void testClienteConstructor_FromClienteDto() {
        Cliente cliente = new Cliente(clienteDto);

        assertEquals(clienteDto.getDni(), cliente.getDni());
        assertEquals(clienteDto.getApellido(), cliente.getApellido());
        assertEquals(clienteDto.getNombre(), cliente.getNombre());
        assertEquals(clienteDto.getFechaNacimiento(), cliente.getFechaNacimiento().toString());
        assertEquals(clienteDto.getBanco(), cliente.getBanco());
        assertEquals(TipoPersona.fromString(clienteDto.getTipoPersona()), cliente.getTipoPersona());
        assertNotNull(cliente.getFechaAlta());
        assertEquals(LocalDate.now(), cliente.getFechaAlta());
    }
}
