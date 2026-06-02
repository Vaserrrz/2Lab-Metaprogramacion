package com.example.metaprogramacion.chess.ex1;

/**
 * Clase `Queen` (Reina) que implementa `MovimientoDiagonalReina`.
 *
 * Comentarios pedagógicos:
 * - La Reina combina movimientos en línea recta y diagonal en ajedrez; aquí
 *   usamos la interfaz marcador para demostrar detección mediante reflexión.
 * - El método `movimiento` es deliberadamente simple; servirá para pruebas e
 *   interceptación en el Ejercicio 2.
 */
public class Queen extends Piece implements MovimientoDiagonalReina {

    public Queen(String color, String posicion) {
        super(color, posicion);
    }

    @Override
    public void imprimir() {
        System.out.println("Reina(" + getColor() + ") en " + getPosicion());
    }

    @Override
    public void movimiento(String destino) {
        System.out.println("Reina moviéndose de " + getPosicion() + " a " + destino);
        setPosicion(destino);
    }
}
