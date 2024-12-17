package ar.edu.utn.frbb.tup.persistence;

import ar.edu.utn.frbb.tup.model.Cliente;
import ar.edu.utn.frbb.tup.model.TipoPersona;
import ar.edu.utn.frbb.tup.persistence.entity.ClienteEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClienteDaoTest {

    private ClienteDao clienteDao;

    private Cliente cliente;

    @Mock
    private CuentaDao cuentaDao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        clienteDao = new ClienteDao();

        // creo un cliente para usar en los test
        cliente = new Cliente();
        cliente.setDni(12345678);
        cliente.setTipoPersona(TipoPersona.PERSONA_FISICA);
        cliente.setBanco("Banco Test");
        cliente.setNombre("Juan");
        cliente.setApellido("Perez");
        cliente.setFechaAlta(LocalDate.of(2023, 1, 1));
        cliente.setFechaNacimiento(LocalDate.of(1990, 5, 10));
    }

    // testeo la funcion find para ver si el cliente guardado lo encuentra con exito
    @Test
    void testFindDao() {
        int dni = 12345678;
        ClienteEntity clienteEntity = new ClienteEntity(cliente);
        clienteDao.getInMemoryDatabase().put(dni, clienteEntity);

        Cliente cliente = clienteDao.find(dni, false);

        assertNotNull(cliente);
        assertEquals("Juan", cliente.getNombre());
        assertEquals(dni, cliente.getDni());
        assertTrue(cliente.getCuentas().isEmpty());
    }

    // testeo la funcion save para ver si el cliente se guarda con exito
    @Test
    void testSaveDao() {
        clienteDao.save(cliente);

        ClienteEntity entity = (ClienteEntity) clienteDao.getInMemoryDatabase().get(12345678);
        assertNotNull(entity);
        assertEquals(12345678, entity.getId());
    }

    // testeo el buscar todos los clientes
    @Test
    void testFindAll() {
        Cliente cliente2 = new Cliente();
        cliente2.setDni(87654321);
        cliente2.setTipoPersona(TipoPersona.PERSONA_FISICA);
        cliente2.setBanco("Banco Test");
        cliente2.setNombre("Ana");
        cliente2.setApellido("Garcia");
        cliente2.setFechaAlta(LocalDate.of(2023, 1, 1));
        cliente2.setFechaNacimiento(LocalDate.of(1990, 5, 10));

        ClienteEntity clienteEntity1 = new ClienteEntity(cliente);
        ClienteEntity clienteEntity2 = new ClienteEntity(cliente2);

        clienteDao.getInMemoryDatabase().put(12345678, clienteEntity1);
        clienteDao.getInMemoryDatabase().put(87654321, clienteEntity2);

        // Act
        List<Cliente> clientes = clienteDao.findAll();

        // Assert
        assertNotNull(clientes);
        assertEquals(2, clientes.size());
        assertEquals("Juan", clientes.get(0).getNombre());
        assertEquals("Ana", clientes.get(1).getNombre());
    }
}
