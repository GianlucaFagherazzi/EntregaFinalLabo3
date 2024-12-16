package ar.edu.utn.frbb.tup.model;

public class Cuota {
    private int nroCuota;
    private double monto;

    public Cuota(int nroCuota, double monto){
        this.nroCuota = nroCuota;
        this.monto = monto;
    }

    public int getNroCuota() {
        return nroCuota;
    }
    public void setNroCuota(int nroCuota) {
        this.nroCuota = nroCuota;
    }

    public double getMonto() {
        return monto;
    }
    public void setMonto(double monto) {
        this.monto = monto;
    }

}
