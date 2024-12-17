package ar.edu.utn.frbb.tup.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class BancoTest {

    private Banco banco;
    private Cliente cliente1;
    private Cliente cliente2;

    @BeforeEach
    public void setUp() {
        banco = new Banco();
        cliente1 = new Cliente();
        cliente1.setDni(12345678);
        cliente1.setNombre("Juan");
        cliente1.setApellido("Perez");
        
        cliente2 = new Cliente();
        cliente2.setDni(87654321);
        cliente2.setNombre("Ana");
        cliente2.setApellido("Lopez");
    }

    //devuelve una lista vacia cuando no hay clientes
    @Test
    public void testGetClientes() {
        List<Cliente> clientes = banco.getClientes();
        assertNotNull(clientes);
        assertTrue(clientes.isEmpty());
    }

    //ingreso 2 entidades al banco y luego se verifica que se hayan agregado correctamente
    @Test
    public void testSetClientes() {
        // Establecer una lista de clientes y verificar si se establece correctamente
        List<Cliente> clientesList = new ArrayList<>();
        clientesList.add(cliente1);
        clientesList.add(cliente2);
        
        banco.setClientes(clientesList);
        
        List<Cliente> clientes = banco.getClientes();
        assertEquals(2, clientes.size());
        assertEquals(cliente1, clientes.get(0));
        assertEquals(cliente2, clientes.get(1));
    }
}
