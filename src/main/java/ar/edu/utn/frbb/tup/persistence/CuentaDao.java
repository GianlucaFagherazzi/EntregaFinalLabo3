package ar.edu.utn.frbb.tup.persistence;

import ar.edu.utn.frbb.tup.model.Cuenta;
import ar.edu.utn.frbb.tup.persistence.entity.CuentaEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class CuentaDao extends AbstractBaseDao {

    public Cuenta find(int id) {
        if ((getInMemoryDatabase().get(id)) == null) {
            return null;
        }
        return ((CuentaEntity) getInMemoryDatabase().get(id)).toCuenta();
    }

    public void save(Cuenta cuenta) {
        CuentaEntity entity = new CuentaEntity(cuenta);
        getInMemoryDatabase().put(entity.getId(), entity);
    }

    public void update(Cuenta cuenta) {
        // Obtener la base de datos en memoria
        Map<Integer, Object> database = getInMemoryDatabase();

        // Recorrer la base de datos
        for (Object object : database.values()) {
            CuentaEntity cuentaEntity = (CuentaEntity) object;
            // Verificar si el número de cuenta coincide
            if (cuentaEntity.getId() == cuenta.getNumeroCuenta()) {
                // Actualizar el balance de la cuenta
                cuentaEntity.setBalance(cuenta.getBalance());

                // Salir del bucle una vez que la cuenta ha sido actualizada
                break;
            }
        }
    }

    public List<Cuenta> getCuentasByCliente(Integer dni) {
        List<Cuenta> cuentasDelCliente = new ArrayList<>();

        for (Object object : getInMemoryDatabase().values()) {
            if (object.getClass().equals(CuentaEntity.class)) {
                CuentaEntity cuenta = (CuentaEntity) object;
                if (cuenta.getTitular().equals(dni)) {
                    Cuenta cuentaB = cuenta.toCuenta();
                    cuentaB.setTitular(dni);
                    cuentasDelCliente.add(cuentaB);
                }
            }

        }
        return cuentasDelCliente;
    }

    @Override
    protected String getEntityName() {
        return "Cuenta";
    }
}
