package ar.edu.utn.frbb.tup.service;

import ar.edu.utn.frbb.tup.controller.ClienteDto;
import ar.edu.utn.frbb.tup.model.Cliente;
import ar.edu.utn.frbb.tup.model.Cuenta;
import ar.edu.utn.frbb.tup.model.exception.ClienteAlreadyExistsException;
import ar.edu.utn.frbb.tup.model.exception.TipoCuentaAlreadyExistsException;
import ar.edu.utn.frbb.tup.persistence.ClienteDao;
import ar.edu.utn.frbb.tup.persistence.CuentaDao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    @Autowired
    ClienteDao clienteDao;

    @Autowired
    CuentaDao cuentaDao;

    public Cliente darDeAltaCliente(ClienteDto clienteDto) throws  ClienteAlreadyExistsException {
        Cliente cliente = new Cliente(clienteDto);
        validatorAlta(cliente);
        clienteDao.save(cliente);
        return cliente;
    }

    public void agregarCuenta(Cuenta cuenta, int dni) throws TipoCuentaAlreadyExistsException {
        Cliente titular = buscarClientePorDni(dni);
        cuenta.setTitular(titular.getDni());
        validatorAgregarCuenta(titular, cuenta);

        titular.addCuenta(cuenta);
        cuentaDao.save(cuenta);
        clienteDao.save(titular);
    }
        
    public Cliente buscarClientePorDni(int dni) {
        Cliente cliente = clienteDao.find(dni, true);
        if(cliente == null){
            throw  new IllegalArgumentException("El cliente buscado no existe");
        }
        return cliente;
    }
    
    public List<Cuenta> getCuentasCliente(Integer dni){
        return cuentaDao.getCuentasByCliente(dni);
    }

    private void validatorAlta(Cliente cliente) throws ClienteAlreadyExistsException {
        if(clienteDao.find(cliente.getDni(), false) != null){
            throw new ClienteAlreadyExistsException("Ya existe el cliente" + cliente.getDni());
        }

        if(cliente.getEdad() < 18){
            throw new IllegalArgumentException("El cliente debe ser mayor de edad(18) para poder tener una cuenta en el banco");
        }
    }

    private  void validatorAgregarCuenta(Cliente titular, Cuenta cuentaTitular) throws TipoCuentaAlreadyExistsException {
        if(titular.tieneCuenta(cuentaTitular.getTipoCuenta(), cuentaTitular.getMoneda())){
            throw new TipoCuentaAlreadyExistsException("El cliente ya posee una cuenta de ese tipo y moneda");
        }
    }

    public List<Cliente> obtenerTodosClientes() {
        List<Cliente> clientes = clienteDao.findAll();
        return clientes.isEmpty() ? null : clientes;
    }
    
}
