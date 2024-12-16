package ar.edu.utn.frbb.tup.controller;

public class CuentaDto {
    private int titularDni;
    private String tipoCuenta;
    private String moneda;

    //setters y getters
    public int getTitularDni() {
        return titularDni;
    }
    public void setTitularDni(int titularDni) {
        this.titularDni = titularDni;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }
    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public String getMoneda() {
        return moneda;
    }
    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }
}
