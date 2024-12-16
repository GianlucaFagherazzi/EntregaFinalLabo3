package ar.edu.utn.frbb.tup.model;

public enum TipoMoneda {
    PESOS("PESOS"),
    DOLARES("DOLARES");

    private final String descripcion;
    TipoMoneda(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String getDescripcion(){
        return descripcion;
    }

    public static TipoMoneda fromString(String texto){
        for (TipoMoneda tipo : TipoMoneda.values()){
            if(tipo.descripcion.equalsIgnoreCase(texto)){
                return tipo;
            }
        }
        throw new IllegalArgumentException("TipoMoneda invalido, "+texto+" no fue encontrado");
    }
}
