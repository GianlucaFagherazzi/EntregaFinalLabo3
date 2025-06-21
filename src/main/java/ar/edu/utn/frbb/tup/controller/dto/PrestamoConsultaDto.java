package ar.edu.utn.frbb.tup.controller.dto;

import java.util.ArrayList;
import java.util.List;

import ar.edu.utn.frbb.tup.model.PrestamoConsultaCliente;

public class PrestamoConsultaDto {
    private long numeroCliente;
    private List<PrestamoConsultaCliente> prestamos;

    public PrestamoConsultaDto(long dni) {
        this.numeroCliente = dni;
        List<PrestamoConsultaCliente> prestamos = new ArrayList<PrestamoConsultaCliente>();
    }

    public long getNumeroCliente() {
        return numeroCliente;
    }

    public void setNumeroCliente(long numeroCliente) {
        this.numeroCliente = numeroCliente;
    }

    public List<PrestamoConsultaCliente> getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(List<PrestamoConsultaCliente> prestamos) {
        this.prestamos = prestamos;
    }

    public void addPrestamos(PrestamoConsultaCliente prestamo) {
        this.prestamos.add(prestamo);
    }
}
