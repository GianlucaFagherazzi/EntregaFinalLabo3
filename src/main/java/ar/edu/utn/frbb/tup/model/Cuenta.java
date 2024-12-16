package ar.edu.utn.frbb.tup.model;
import ar.edu.utn.frbb.tup.controller.CuentaDto;

import java.time.LocalDateTime;
import java.util.Random;

public class Cuenta {
    private int titularDni;
    private int numeroCuenta;
    private LocalDateTime fechaCreacion;
    private double balance;
    private TipoCuenta tipoCuenta;
    private TipoMoneda moneda;

    //constructores
    public Cuenta() {
        this.numeroCuenta = generarNumeroCuenta();
        this.balance = 0;
        this.fechaCreacion = generarFechaCreacion();
    }

    public Cuenta(CuentaDto cuentaDto){
        this.tipoCuenta = TipoCuenta.fromString(cuentaDto.getTipoCuenta());
        this.moneda = TipoMoneda.fromString(cuentaDto.getMoneda());
        this.fechaCreacion = generarFechaCreacion();
        this.balance = 0.0;
        this.numeroCuenta = generarNumeroCuenta();
    }

    //auxiliares
    private int generarNumeroCuenta() {
        Random random = new Random();
        int numeroCuenta = random.nextInt(90000000) + 10000000;
        return numeroCuenta;
    }

    private LocalDateTime generarFechaCreacion() {
        return LocalDateTime.now();
    }


    //getters y setters
    public int getTitular() {
        return titularDni;
    }

    public void setTitular(int titularDni) {
        this.titularDni = titularDni;
    }

    public int getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(int numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public TipoCuenta getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(TipoCuenta tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public TipoMoneda getMoneda() {
        return moneda;
    }

    public void setMoneda(TipoMoneda moneda) {
        this.moneda = moneda;
    }

}
