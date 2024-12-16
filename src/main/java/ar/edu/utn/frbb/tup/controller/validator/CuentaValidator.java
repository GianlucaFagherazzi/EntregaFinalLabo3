package ar.edu.utn.frbb.tup.controller.validator;
import ar.edu.utn.frbb.tup.controller.CuentaDto;
import org.springframework.stereotype.Component;

@Component
public class CuentaValidator {
    public static void validate(CuentaDto cuentaDto) {
        validateTipoMoneda(cuentaDto);
        validateTipoCuenta(cuentaDto);
    }

    protected static void validateTipoMoneda(CuentaDto cuentaDto) {
        if ((!"PESOS".equals(cuentaDto.getMoneda())) && (!"DOLARES".equals(cuentaDto.getMoneda()))) {
            throw new IllegalArgumentException("La moneda: " + cuentaDto.getMoneda() + " no es soportada");
        }
    }

    protected static void validateTipoCuenta(CuentaDto cuentaDto) {
        if ((!"CAJA_AHORRO".equals(cuentaDto.getTipoCuenta())) && (!"CUENTA_CORRIENTE".equals(cuentaDto.getTipoCuenta()))) {
            throw new IllegalArgumentException("La cuenta del tipo " + cuentaDto.getTipoCuenta() + " no es correcta");
        }
    }
}
