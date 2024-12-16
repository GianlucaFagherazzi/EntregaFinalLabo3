package ar.edu.utn.frbb.tup.model;

public class EstadoPrestamo {
    private double monto;
    private double intereses;
    private int plazoMeses;
    private int pagosRealizados;
    private double saldoRestante;

    public EstadoPrestamo(Prestamo prestamo) {
        this.monto = prestamo.getMontoPrestamo();
        this.plazoMeses = prestamo.getPlazoMeses();
        this.intereses = prestamo.getInteresTotal();
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public double getIntereses() {
        return intereses;
    }

    public void setIntereses(double intereses) {
        this.intereses = intereses;
    }

    public int getPlazoMeses() {
        return plazoMeses;
    }

    public void setPlazoMeses(int plazoMeses) {
        this.plazoMeses = plazoMeses;
    }

    public int getPagosRealizados() {
        return pagosRealizados;
    }

    public void setPagosRealizados(int pagosRealizados) {
        this.pagosRealizados = pagosRealizados;
    }

    public double getSaldoRestante() {
        return saldoRestante;
    }

    public void setSaldoRestante(double saldoRestante) {
        this.saldoRestante = saldoRestante;
    }
}
