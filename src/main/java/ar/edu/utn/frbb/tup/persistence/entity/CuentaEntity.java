package ar.edu.utn.frbb.tup.persistence.entity;

import ar.edu.utn.frbb.tup.model.Cuenta;
import ar.edu.utn.frbb.tup.model.TipoCuenta;
import ar.edu.utn.frbb.tup.model.TipoMoneda;

import java.time.LocalDateTime;

public class CuentaEntity extends BaseEntity{
    private int titular;
    private LocalDateTime fechaCreacion;
    private double balance;
    private String tipoCuenta;
    private String tipoMoneda;

    public CuentaEntity(Cuenta cuenta) {
        super(cuenta.getNumeroCuenta());
        this.titular = cuenta.getTitular();
        this.fechaCreacion = cuenta.getFechaCreacion();
        this.balance = cuenta.getBalance();
        this.tipoCuenta = cuenta.getTipoCuenta().getDescripcion();
        this.tipoMoneda = cuenta.getMoneda().getDescripcion();
    }

    public Cuenta toCuenta() {
        Cuenta cuenta = new Cuenta();
        cuenta.setNumeroCuenta(this.getId());
        cuenta.setBalance(this.balance);
        cuenta.setFechaCreacion(this.fechaCreacion);
        cuenta.setTipoCuenta(TipoCuenta.fromString(String.valueOf(this.tipoCuenta)));
        cuenta.setMoneda(TipoMoneda.fromString(String.valueOf(this.tipoMoneda)));
        return cuenta;
    }

    public Integer getTitular() {
        return titular;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
