package ar.edu.utn.frbb.tup.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.Period;

public class PersonaTest {

    private Persona persona;

    @BeforeEach
    public void setUp() {
        persona = new Persona(12345678, "Garcia", "Juan", "1990-05-15");
    }

    //testeo el constructor
    @Test
    public void testConstructor() {
        assertEquals(12345678, persona.getDni());
        assertEquals("Garcia", persona.getApellido());
        assertEquals("Juan", persona.getNombre());
        assertEquals(LocalDate.parse("1990-05-15"), persona.getFechaNacimiento());
    }

    //verifico la funcion de calcular edad
    @Test
    public void testGetEdad() {
        LocalDate currentDate = LocalDate.now();
        Period agePeriod = Period.between(persona.getFechaNacimiento(), currentDate);
        assertEquals(agePeriod.getYears(), persona.getEdad());
    }
}
