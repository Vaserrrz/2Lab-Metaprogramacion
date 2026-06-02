package com.example.metaprogramacion.chess.ex4;

/**
 * Demo del Ejercicio 4.
 *
 * Este main usa reflexión para analizar la clase `Par` estricta y luego genera
 * el código fuente de su equivalente genérico como un String impreso en consola.
 */
public class Ex4Main {
    public static void main(String[] args) {
        Class<Par> clasePar = Par.class;
        System.out.println("Clase analizada: " + clasePar.getName());
        GenericClassGenerator.mostrarCodigoGenerico(clasePar);
    }
}
