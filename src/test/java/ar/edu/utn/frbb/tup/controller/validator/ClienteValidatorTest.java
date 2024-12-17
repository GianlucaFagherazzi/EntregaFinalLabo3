package ar.edu.utn.frbb.tup.controller.validator;

import ar.edu.utn.frbb.tup.controller.ClienteDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClienteValidatorTest {

    //el formato de persona es valido (F y J) y el formato de fecha es valido (aaaa-mm-dd)
    @Test
    void validateTipoPersonaValidoTest() {
        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setTipoPersona("F");
        clienteDto.setFechaNacimiento("1990-01-01");

        assertDoesNotThrow(() -> new ClienteValidator().validate(clienteDto));
    }

    //el tipo de persona es invalido
    @Test
    void validateTipoPersonaInvalidoTest() {
        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setTipoPersona("X");
        clienteDto.setFechaNacimiento("01/01/1990");

        Exception exception = assertThrows(IllegalArgumentException.class, 
            () -> new ClienteValidator().validate(clienteDto));
        assertEquals("El tipo de persona no es correcto", exception.getMessage());
    }

    //el formato de fecha de nacimiento es invalido (dd/mm/aaaa)
    @Test
    void validateFechaNacimientoInvalidaTest() {
        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setTipoPersona("F");
        clienteDto.setFechaNacimiento("01-01-1990");

        Exception exception = assertThrows(IllegalArgumentException.class, 
            () -> new ClienteValidator().validate(clienteDto));
        assertEquals("Error en el formato de fecha", exception.getMessage());
    }
}
