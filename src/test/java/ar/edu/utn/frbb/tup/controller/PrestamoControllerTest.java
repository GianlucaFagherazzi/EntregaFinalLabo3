package ar.edu.utn.frbb.tup.controller;

import ar.edu.utn.frbb.tup.controller.dto.PrestamoConsultaDto;
import ar.edu.utn.frbb.tup.controller.dto.PrestamoDto;
import ar.edu.utn.frbb.tup.controller.dto.PrestamoOutputDto;
import ar.edu.utn.frbb.tup.controller.validator.PrestamoValidator;
import ar.edu.utn.frbb.tup.model.exception.PrestamoRechazadoException;
import ar.edu.utn.frbb.tup.service.PrestamoService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PrestamoControllerTest {

    @Mock
    private PrestamoService prestamoService;

    public PrestamoDto prestamoDto = new PrestamoDto();

    @BeforeEach
    public void prestamoDtoSetting() {

        prestamoDto.setNumeroCliente(12345678);
        prestamoDto.setPlazoMeses(36);
        prestamoDto.setMontoPrestamo(4568.31);
        prestamoDto.setMoneda("ARS");
    }

    @BeforeAll
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // verificar que se haya pedido el prestamo con exito
    @Test
    void solicitarPrestamoExito() throws PrestamoRechazadoException {
        PrestamoOutputDto prestamoOutputDtoMock = mock(PrestamoOutputDto.class);
        Mockito.when(prestamoService.pedirPrestamo(prestamoDto)).thenReturn(prestamoOutputDtoMock);

        prestamoService.pedirPrestamo(prestamoDto);

        assertEquals(prestamoService.pedirPrestamo(prestamoDto), prestamoOutputDtoMock);
        assertDoesNotThrow(() -> prestamoService.pedirPrestamo(prestamoDto));
        assertDoesNotThrow(() -> PrestamoValidator.validate(prestamoDto));
    }

    // Devpñver los prestamos de un cliente
    @Test
    void retornarPrestamosCliente() {
        long clienteDni = 12345678L;
        PrestamoConsultaDto prestamoConsultaDtoMock = mock(PrestamoConsultaDto.class);

        Mockito.when(prestamoService.pedirConsultaPrestamos(clienteDni)).thenReturn(prestamoConsultaDtoMock);
        PrestamoConsultaDto resultado = prestamoService.pedirConsultaPrestamos(clienteDni);

        assertNotNull(resultado); 
        assertEquals(prestamoConsultaDtoMock, resultado); 

        Mockito.verify(prestamoService, Mockito.times(1)).pedirConsultaPrestamos(clienteDni);
    }
}
