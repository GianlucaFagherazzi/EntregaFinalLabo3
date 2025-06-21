package ar.edu.utn.frbb.tup.persistence.dao;

import ar.edu.utn.frbb.tup.model.Cliente;
import ar.edu.utn.frbb.tup.model.Cuenta;
import ar.edu.utn.frbb.tup.persistence.entity.ClienteEntity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteDao extends AbstractBaseDao{

    @Autowired
    CuentaDao cuentaDao;

    public Cliente find(int dni, boolean loadComplete) {
        if (getInMemoryDatabase().get(dni) == null)
            return null;
            
        Cliente cliente = ((ClienteEntity) getInMemoryDatabase().get(dni)).toCliente();
        if (loadComplete) {
            for (Cuenta cuenta :
                    cuentaDao.getCuentasByCliente(dni)) {
                cliente.addCuenta(cuenta);
            }
        }
        return cliente;
    }

    public void save(Cliente cliente) {
        ClienteEntity entity = new ClienteEntity(cliente);
        getInMemoryDatabase().put(entity.getId(), entity);
    }

    // Método para obtener todos los clientes
    public List<Cliente> findAll() {
        List<Cliente> clientes = new ArrayList<>();
        for (Object entity : getInMemoryDatabase().values()) {
            if (entity instanceof ClienteEntity) {
                Cliente cliente = ((ClienteEntity) entity).toCliente();
                clientes.add(cliente);
            }
        }
        return clientes;
    }

    @Override
    protected String getEntityName() {
        return "CLIENTE";
    }
}
