package ar.edu.utn.frbb.tup.controller.dto;

import java.util.ArrayList;
import java.util.List;

import ar.edu.utn.frbb.tup.model.Cuota;
import ar.edu.utn.frbb.tup.model.Prestamo;

public class PrestamoOutputDto {
    private String estado;
    private String mensaje;
    private List<Cuota> planPagos = new ArrayList<>();

    public PrestamoOutputDto(Prestamo prestamo) {
        this.setEstado(prestamo.getEstado());
        this.setMensaje(prestamo.getMensaje());
        this.setPlanPagos(prestamo.getPlanPagos());
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

}
