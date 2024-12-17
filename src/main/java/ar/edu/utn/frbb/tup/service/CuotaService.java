package ar.edu.utn.frbb.tup.service;

import java.util.Random;

import ar.edu.utn.frbb.tup.model.Cuota;
import ar.edu.utn.frbb.tup.model.Prestamo;

public class CuotaService {
    public static void generarCuotas(Prestamo prestamo){
        double pagoMensual = calcularMontoCuota(prestamo);
        int random = generarRandomCantCuotas(prestamo.getPlazoMeses());

        for (int i = 1; i < random; i++) {
            Cuota cuota = new Cuota(i, pagoMensual);
            prestamo.addCuota(cuota);
        }
    }

    //Genera un numero random entre 1 y la cantidad de cuotas del prestamo
    protected static int generarRandomCantCuotas(int cantCuotas){
        Random random = new Random(System.currentTimeMillis());
        return random.nextInt(cantCuotas) + 1; // Rango: 1 a cantCuotas
    }
    

    protected static double calcularMontoCuota(Prestamo prestamo){
        return ( prestamo.getMontoPrestamo() + prestamo.getInteresTotal() ) / prestamo.getPlazoMeses();
    }
}
