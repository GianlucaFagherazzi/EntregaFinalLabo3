package ar.edu.utn.frbb.tup.model;

import ar.edu.utn.frbb.tup.controller.PrestamoDto;

import java.util.ArrayList;
import java.util.List;

public class Prestamo {
    private int numeroCliente;
    private int idPrestamo;
    private Integer plazoMeses;
    private Double montoPrestamo;
    private String moneda;

    private String estado;
    private String mensaje;
    private Double interesTotal;

    // se guardan las cuotas pagadas, con su numero identificador y el monto
    private List<Cuota> planPagos = new ArrayList<>();

    // constructores
    public Prestamo() {
    }

    public Prestamo(PrestamoDto prestamoDto) {
        this.numeroCliente = prestamoDto.getNumeroCliente();
        this.plazoMeses = prestamoDto.getPlazoMeses();
        this.montoPrestamo = prestamoDto.getMontoPrestamo();
        this.moneda = prestamoDto.getMoneda();
    }

    public int getNumeroCliente() {
        return numeroCliente;
    }

    public void setNumeroCliente(int numeroCliente) {
        this.numeroCliente = numeroCliente;
    }

    public int getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public int getPlazoMeses() {
        return plazoMeses;
    }

    public void setPlazoMeses(Integer plazoMeses) {
        this.plazoMeses = plazoMeses;
    }

    public double getMontoPrestamo() {
        return montoPrestamo;
    }

    public void setMontoPrestamo(Double montoPrestamo) {
        this.montoPrestamo = montoPrestamo;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public List<Cuota> getPlanPagos() {
        return planPagos;
    }

    public void setPlanPagos(List<Cuota> planPagos) {
        this.planPagos = planPagos;
    }

    public void addCuota(Cuota cuota) {
        planPagos.add(cuota);
    }

    public double getInteresTotal() {
        return interesTotal;
    }

    public void setInteresTotal(Double interesTotal) {
        this.interesTotal = interesTotal;
    }
}
