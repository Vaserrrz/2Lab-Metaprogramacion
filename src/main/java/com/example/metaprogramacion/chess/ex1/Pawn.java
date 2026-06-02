package com.example.metaprogramacion.chess.ex1;

/**
 * Clase `Pawn` (Peón).
 *
 * Comentarios pedagógicos:
 * - El peón tiene reglas especiales (avance, captura, promoción). Para el laboratorio
 *   sólo implementamos un movimiento que actualiza la posición, y dejaremos la
 *   lógica de promoción para el Ejercicio 2 (intercesión con Proxy).
 * - Este diseño permite que el `InvocationHandler` detecte cuando el peón alcanza
 *   la última fila y ofrezca la promoción.
 */
public class Pawn extends Piece {

    public Pawn(String color, String posicion) {
        super(color, posicion);
    }

    @Override
    public void imprimir() {
        System.out.println("Peón(" + getColor() + ") en " + getPosicion());
    }

    /**
     * Movimiento simplificado: actualiza la posición del peón.
     * En Ejercicio 2 interceptaremos esta llamada para implementar promoción.
     */
    @Override
    public void movimiento(String destino) {
        System.out.println("Peón moviéndose de " + getPosicion() + " a " + destino);
        setPosicion(destino);
        // No reemplazamos el objeto al promoverse; el InvocationHandler se encargará
        // de detectar la promoción y mostrar las opciones. Aquí sólo actualizamos la posición.
    }
}
