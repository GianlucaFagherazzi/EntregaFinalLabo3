package ar.edu.utn.frbb.tup.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import ar.edu.utn.frbb.tup.model.Prestamo;
import ar.edu.utn.frbb.tup.persistence.entity.PrestamoEntity;

import org.springframework.stereotype.Component;

@Component
public class PrestamoDao extends AbstractBaseDao {
    private static final AtomicInteger idCounter = new AtomicInteger(1); // Comienza desde 1

    private int generarIdUnico() {
        return idCounter.getAndIncrement(); // Obtiene el valor actual y luego lo incrementa
    }

    public void almacenarDatosPrestamo(Prestamo prestamo) {
        PrestamoEntity prestamoEntity = new PrestamoEntity(prestamo, generarIdUnico());
        getInMemoryDatabase().put(prestamoEntity.getId(), prestamoEntity);
    }

    public List<Prestamo> getPrestamosByCliente(int dni) {
        List<Prestamo> prestamosCliente = new ArrayList<Prestamo>();

        for (Object valor : getInMemoryDatabase().values()) {
            if (valor.getClass().equals(PrestamoEntity.class)) {
                PrestamoEntity prestamo = (PrestamoEntity) valor;
                if (prestamo.getNumeroCliente() == dni) {
                    prestamosCliente.add(prestamo.toPrestamo());
                }
            }
        }

        return prestamosCliente;
    }

    @Override
    protected String getEntityName() {
        return "Prestamo";
    }

}
