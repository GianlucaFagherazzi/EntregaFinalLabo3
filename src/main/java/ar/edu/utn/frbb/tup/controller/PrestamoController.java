package ar.edu.utn.frbb.tup.controller;

import ar.edu.utn.frbb.tup.controller.validator.PrestamoValidator;
import ar.edu.utn.frbb.tup.model.exception.PrestamoRechazadoException;
import ar.edu.utn.frbb.tup.service.PrestamoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/prestamo")
public class PrestamoController {

    @Autowired
    PrestamoService prestamoService;

    @PostMapping
    public PrestamoOutputDto solicitarPrestamo(@RequestBody PrestamoDto prestamoDto) throws PrestamoRechazadoException {
        if (prestamoDto == null) {
            throw new IllegalArgumentException("El prestamo no puede ser nulo");
        }

        PrestamoValidator.validate(prestamoDto);
        return prestamoService.pedirPrestamo(prestamoDto);
    }

    @GetMapping("/{clienteDni}")
    public PrestamoConsultaDto retonarPrestamosCliente(@PathVariable long clienteDni) {
        return prestamoService.pedirConsultaPrestamos(clienteDni);
    }
}
