package ar.edu.utn.frbb.tup.controller;

import ar.edu.utn.frbb.tup.model.Cuota;
import ar.edu.utn.frbb.tup.model.Prestamo;
import ar.edu.utn.frbb.tup.model.PrestamoConsultaCliente;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PrestamoConsultaDtoTest {

    @BeforeAll
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    //verificar que el constructor funcione correctamente
    @Test
    void prestamoCrearConsultaDto() {
        PrestamoConsultaDto prestamoConsultaDto = new PrestamoConsultaDto(12345678L);

        List<PrestamoConsultaCliente> prestamosCliente = new ArrayList<PrestamoConsultaCliente>();

        Cuota cuota1 = new Cuota(1,4000.20);
        Cuota cuota2 = new Cuota(2,3000.20);
        List<Cuota> cuotasLista = new ArrayList<Cuota>(List.of(cuota1, cuota2));

        Prestamo prestamo = new Prestamo();
        prestamo.setNumeroCliente(12345678);
        prestamo.setIdPrestamo(1);
        prestamo.setPlazoMeses(3);
        prestamo.setMontoPrestamo(150.3);
        prestamo.setMoneda("PESOS");
        prestamo.setEstado("APROBADO");
        prestamo.setMensaje("aprobado");
        prestamo.setPlanPagos(cuotasLista);
        prestamo.setInteresTotal(10.5);

        PrestamoConsultaCliente prestamoConsultaCliente = new PrestamoConsultaCliente(prestamo);

        prestamosCliente.add(prestamoConsultaCliente);
        prestamoConsultaDto.setPrestamos(prestamosCliente);

        assertFalse(cuotasLista.isEmpty());
        assertFalse(prestamo.getPlanPagos().isEmpty());
        assertFalse(prestamosCliente.isEmpty());
        assertFalse(prestamoConsultaDto.getPrestamos().isEmpty());
        assertEquals(prestamoConsultaDto.getNumeroCliente(), prestamo.getNumeroCliente());
    }
}