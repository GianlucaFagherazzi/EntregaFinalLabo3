package ar.edu.utn.frbb.tup.persistence.entity;

import ar.edu.utn.frbb.tup.model.Cliente;
import ar.edu.utn.frbb.tup.model.TipoPersona;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ClienteEntityTest {

    //pruebo el constructor de clienteentity y el tocliente
    @Test
    public void testClienteEntityConstructor() {
        Cliente cliente = new Cliente();
        cliente.setDni(12345678);
        cliente.setTipoPersona(TipoPersona.PERSONA_FISICA);
        cliente.setBanco("Banco Test");
        cliente.setNombre("Juan");
        cliente.setApellido("Perez");
        cliente.setFechaAlta(LocalDate.of(2023, 1, 1));
        cliente.setFechaNacimiento(LocalDate.of(1990, 5, 10));

        ClienteEntity clienteEntityTest = new ClienteEntity(cliente);

        Cliente clienteTest = clienteEntityTest.toCliente();

        assertNotNull(clienteTest);
        assertEquals(clienteEntityTest.getId(), clienteTest.getDni());
    }
}
