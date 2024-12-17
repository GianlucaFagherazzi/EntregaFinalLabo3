package ar.edu.utn.frbb.tup.persistence;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

public class AbstractBaseDaoTest {

    //creo una clase implementacion de la clase abstracta para probar sus metodos
    public class ImplementacionBaseDao extends AbstractBaseDao{
        private String entityName;
        @Override
        protected String getEntityName() {
            return entityName;
        }
        protected void setEntityName(String entityName){
            this.entityName = entityName;
        }
    }

    @BeforeEach
    public void setUp() {
    }

    //reviso la memoria de la base de datos esta vacia al inicio
    @Test
    public void testGetInMemoryDatabase() {
        ImplementacionBaseDao concreteBaseDao = new ImplementacionBaseDao();
        concreteBaseDao.setEntityName("entidad1");

        Map<Integer, Object> database = concreteBaseDao.getInMemoryDatabase();
        
        assertNotNull(database);
        assertTrue(database.isEmpty());
    }

    //llamo varias veces al método para verificar que no se cree una nueva instancia de hashmap cada vez llamo a la base
    @Test
    public void testGetInMemoryDatabaseVariasLlamadas() {
        ImplementacionBaseDao concreteBaseDao = new ImplementacionBaseDao();
        concreteBaseDao.setEntityName("entidad1");

        // Llamar varias 
        Map<Integer, Object> firstCallDatabase = concreteBaseDao.getInMemoryDatabase();
        Map<Integer, Object> secondCallDatabase = concreteBaseDao.getInMemoryDatabase();

        assertSame(firstCallDatabase, secondCallDatabase);
    }

    //ingreso 2 entidades y verifico que sean diferentes
    @Test
    public void testGetInMemoryDatabaseDiferentesEntidades() {
        ImplementacionBaseDao concreteBaseDao = new ImplementacionBaseDao();
        concreteBaseDao.setEntityName("entidad1");

        ImplementacionBaseDao anotherConcreteBaseDao = new ImplementacionBaseDao();
        anotherConcreteBaseDao.setEntityName("entidad 2");

        Map<Integer, Object> testEntityDatabase = concreteBaseDao.getInMemoryDatabase();
        Map<Integer, Object> anotherEntityDatabase = anotherConcreteBaseDao.getInMemoryDatabase();

        assertNotSame(testEntityDatabase, anotherEntityDatabase);
        assertNotEquals(concreteBaseDao.getEntityName(), anotherConcreteBaseDao.getEntityName());
    }
}
