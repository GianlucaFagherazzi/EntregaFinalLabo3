package ar.edu.utn.frbb.tup.model;

public enum TipoCuenta {
    CUENTA_CORRIENTE("CUENTA_CORRIENTE"),
    CAJA_AHORRO("CAJA_AHORRO");

    private final String descripcion;
    TipoCuenta(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getDescripcion(){
        return descripcion;
    }

    public static TipoCuenta fromString(String texto) {
        for (TipoCuenta tipo : TipoCuenta.values()){
            if(tipo.descripcion.equalsIgnoreCase(texto)){
                return tipo;
            }
        }
        throw new IllegalArgumentException("TipoDeCuenta invalido, "+texto+" no fue encontrado");
    }
}
