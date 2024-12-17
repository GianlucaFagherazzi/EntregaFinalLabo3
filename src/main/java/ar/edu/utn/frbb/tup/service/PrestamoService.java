package ar.edu.utn.frbb.tup.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.utn.frbb.tup.controller.PrestamoDto;
import ar.edu.utn.frbb.tup.controller.PrestamoOutputDto;
import ar.edu.utn.frbb.tup.controller.PrestamoConsultaDto;
import ar.edu.utn.frbb.tup.model.PrestamoConsultaCliente;
import ar.edu.utn.frbb.tup.model.Cuenta;
import ar.edu.utn.frbb.tup.model.exception.*;
import ar.edu.utn.frbb.tup.model.Prestamo;
import ar.edu.utn.frbb.tup.model.TipoMoneda;
import ar.edu.utn.frbb.tup.model.TipoCuenta;
import ar.edu.utn.frbb.tup.persistence.PrestamoDao;

import org.springframework.stereotype.Service;

@Service
public class PrestamoService {
    @Autowired
    ClienteService clienteService;

    @Autowired
    CuentaService cuentaService;

    @Autowired
    PrestamoDao prestamoDao;

    public PrestamoOutputDto pedirPrestamo(PrestamoDto prestamoDto) throws PrestamoRechazadoException {
        Prestamo prestamo = new Prestamo(prestamoDto);
        validator(prestamo);

        prestamo.setEstado(calcularScoring(prestamo.getNumeroCliente()));
        establecerMensajeScoring(prestamo);
        if (prestamo.getEstado().equals("RECHAZADO")) {
            return output(prestamo);
        }

        prestamo.setInteresTotal(calculaIntereses(prestamo.getMontoPrestamo(), 5));
        CuotaService.generarCuotas(prestamo);
        prestamoDao.almacenarDatosPrestamo(prestamo);
        cuentaService.actualizarCuentaCliente(prestamo);

        return output(prestamo);
    }

    double calculaIntereses(double monto, int valorInteres) {
        return monto * ((double) valorInteres / 12);
    }

    private String calcularScoring(int dni) {
        String scoring = verificaScore(dni);
        if (scoring.equals("ERROR")) {
            return "RECHAZADO";
        }
        return "APROBADO";
    }

    private void establecerMensajeScoring(Prestamo prestamo) {
        if (prestamo.getEstado().equals("RECHAZADO")) {
            prestamo.setMensaje("No posee el scoring suficiente para pedir un prestamo de este tipo");
        }
        if (prestamo.getEstado().equals("APROBADO")) {
            prestamo.setMensaje("El monto del prestamo fue acreditado a su cuenta");
        }
    }

    private PrestamoOutputDto output(Prestamo prestamo) {
        return new PrestamoOutputDto(prestamo);
    }

    private Cuenta getCuentaPermitida(int dni, String moneda) {
        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        cuentas.addAll(clienteService.getCuentasCliente(dni));

        for (Cuenta c : cuentas) {
            if (c.getTipoCuenta().equals(TipoCuenta.CAJA_AHORRO)
                    && c.getMoneda().equals(TipoMoneda.fromString(moneda))) {
                return c;
            }
        }
        return null;
    }

    public void validator(Prestamo prestamo) throws PrestamoRechazadoException {
        if ((getCuentaPermitida((int) prestamo.getNumeroCliente(), prestamo.getMoneda())) == null) {
            throw new PrestamoRechazadoException(
                    "No posee una cuenta que sea Caja de Ahorros, o una Caja de Ahorros en esa moneda");
        }

        if (prestamo.getMontoPrestamo() >= 6000000) {
            throw new PrestamoRechazadoException(
                    "El monto a solicitar es mayor al que se le puede ofrecer en este momento");
        }

        if ((getPrestamosCliente((int) prestamo.getNumeroCliente())).size() > 3) {
            throw new PrestamoRechazadoException(
                    "Es deudor de 3 prestamos. Finalice el pago de los mencionados antes de solicitar otro prestamo");
        }

    }

    public PrestamoConsultaDto pedirConsultaPrestamos(long dni) {
        PrestamoConsultaDto consulta = new PrestamoConsultaDto(dni);

        List<Prestamo> prestamosCliente = getPrestamosCliente((int) dni);
        if (prestamosCliente.isEmpty()) {
            throw new IllegalArgumentException("El cliente " + dni + " no ha pedido prestamos");
        }

        List<PrestamoConsultaCliente> prestamos = new ArrayList<PrestamoConsultaCliente>();
        for (Prestamo p : prestamosCliente) {
            PrestamoConsultaCliente prestamoCliente = new PrestamoConsultaCliente(p);
            prestamos.add(prestamoCliente);
        }
        consulta.setPrestamos(prestamos);

        return consulta;
    }

    List<Prestamo> getPrestamosCliente(int dni) {
        return prestamoDao.getPrestamosByCliente(dni);
    }

    public static String verificaScore(long dni) {
        Random random = new Random();
        long suma = 0;

        // Sumar los dígitos del número
        while (dni > 0) {
            suma += dni % 10;
            dni /= 10;
        }

        // Evaluar las condiciones
        if (suma <= 5 || !random.nextBoolean()) { // Tiene un 50/50 de ser true o false
            return "ERROR";
        }
        return "OK";
    }

}
