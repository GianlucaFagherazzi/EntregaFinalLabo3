package ar.edu.utn.frbb.tup.controller.validator;
import org.springframework.stereotype.Component;

import ar.edu.utn.frbb.tup.controller.dto.PrestamoDto;

@Component
public class PrestamoValidator {
    public static void validate(PrestamoDto prestamoDto){
        validateMonto(prestamoDto.getMontoPrestamo());
    }

    private static void validateMonto(double monto){
        if (monto < 0){
            throw new IllegalArgumentException("Debe establecer un monto en positivo");
        }
        if (monto == 0){
            throw new IllegalArgumentException("Debe establecer cuanto va a pedir");
        }
    }
}