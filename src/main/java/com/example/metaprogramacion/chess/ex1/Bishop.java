package com.example.metaprogramacion.chess.ex1;

/**
 * Clase `Bishop` (Alfil) que implementa `MovimientoDiagonalAlfil`.
 *
 * Comentarios pedagógicos:
 * - Implementa una interfaz marcador para demostrar `getInterfaces()` en introspección.
 * - El comportamiento del movimiento es simplificado: imprimimos y actualizamos posición.
 * - La interfaz no declara métodos; su propósito es didáctico (marcar capacidades de movimiento).
 */
public class Bishop extends Piece implements MovimientoDiagonalAlfil {

    public Bishop(String color, String posicion) {
        super(color, posicion);
    }

    @Override
    public void imprimir() {
        System.out.println("Alfil(" + getColor() + ") en " + getPosicion());
    }

    @Override
    public void movimiento(String destino) {
        System.out.println("Alfil moviéndose de " + getPosicion() + " a " + destino);
        setPosicion(destino);
    }
}
