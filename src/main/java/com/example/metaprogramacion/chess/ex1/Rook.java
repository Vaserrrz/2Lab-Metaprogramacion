package com.example.metaprogramacion.chess.ex1;

/**
 * Clase `Rook` (Torre) necesaria para demostrar el enroque (castling).
 * Contiene bandera `hasMoved` que se usa para verificar precondiciones del enroque.
 */
public class Rook extends Piece {

    // Bandera para saber si la torre se ha movido antes (condición del enroque).
    private boolean hasMoved = false;

    public Rook(String color, String posicion) {
        super(color, posicion);
    }

    public boolean hasMoved() {
        return hasMoved;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    @Override
    public void imprimir() {
        System.out.println("Torre(" + getColor() + ") en " + getPosicion() + (hasMoved ? " [movida]" : ""));
    }

    @Override
    public void movimiento(String destino) {
        System.out.println("Torre moviéndose de " + getPosicion() + " a " + destino);
        setPosicion(destino);
        this.hasMoved = true; // marcar que la torre ya se movió
    }
}
