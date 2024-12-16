package ar.edu.utn.frbb.tup.service;

import ar.edu.utn.frbb.tup.model.Cuenta;
import ar.edu.utn.frbb.tup.model.Prestamo;
import ar.edu.utn.frbb.tup.model.TipoCuenta;
import ar.edu.utn.frbb.tup.model.TipoMoneda;
import ar.edu.utn.frbb.tup.model.exception.*;
import ar.edu.utn.frbb.tup.persistence.CuentaDao;
import ar.edu.utn.frbb.tup.controller.CuentaDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component 
public class CuentaService {
    @Autowired
    ClienteService clienteService;
    @Autowired
    CuentaDao cuentaDao;

    public boolean tipoCuentaEstaSoportada(CuentaDto cuenta){
        if((cuenta.getMoneda().equals(TipoMoneda.PESOS.getDescripcion()) && (cuenta.getTipoCuenta().equals(TipoCuenta.CAJA_AHORRO.getDescripcion())))){
            return true;
        } else if((cuenta.getMoneda().equals(TipoMoneda.DOLARES.getDescripcion()) && (cuenta.getTipoCuenta().equals( TipoCuenta.CAJA_AHORRO.getDescripcion())))){
            return true;
        } else return (cuenta.getMoneda().equals(TipoMoneda.PESOS.getDescripcion()) && (cuenta.getTipoCuenta().equals( TipoCuenta.CUENTA_CORRIENTE.getDescripcion())));
    }

    public Cuenta darDeAltaCuenta(CuentaDto cuentaDto) throws CuentaAlreadyExistsException, TipoCuentaAlreadyExistsException, TipoCuentaNoSoportadaException {
        if(cuentaDao.find(cuentaDto.getTitularDni()) != null) { 
            throw new CuentaAlreadyExistsException("La cuenta con el dni " + cuentaDto.getTitularDni() + " ya existe");
        }

        if (!tipoCuentaEstaSoportada(cuentaDto)) {
            throw new TipoCuentaNoSoportadaException("El tipo de cuenta " + cuentaDto.getTipoCuenta() + " no esta soportada");
        }

        Cuenta cuenta = new Cuenta(cuentaDto);
        clienteService.agregarCuenta(cuenta, cuentaDto.getTitularDni());
        return cuenta;
    }

    public Cuenta find(int id) {
        return cuentaDao.find(id);
    }

    public void actualizarCuentaCliente(Prestamo prestamo){
        List<Cuenta> cuentas = clienteService.getCuentasCliente(prestamo.getNumeroCliente());
        for (Cuenta c : cuentas){
            if (c.getTipoCuenta().equals(TipoCuenta.CAJA_AHORRO) && c.getMoneda().equals(TipoMoneda.fromString(prestamo.getMoneda()))){
                double balanceActualizado = c.getBalance() + prestamo.getMontoPrestamo();
                c.setBalance(balanceActualizado);

                cuentaDao.update(c);
            }
        }

    }

}
