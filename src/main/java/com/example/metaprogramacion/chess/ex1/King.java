package com.example.metaprogramacion.chess.ex1;

/**
 * Clase `King` (Rey).
 *
 * Comentarios pedagógicos:
 * - Extiende `Piece` y concreta `imprimir()` y `movimiento(String)`.
 * - El comportamiento es deliberadamente simple: imprime la acción y actualiza
 *   la posición. La sencillez facilita centrar la atención en la reflexión e
 *   intercesión, no en las reglas completas del ajedrez.
 */
public class King extends Piece {
    private boolean hasMoved = false; // bandera usada para condiciones de enroque

    /** Constructor delega a la clase base. */
    public King(String color, String posicion) {
        super(color, posicion);
    }

    /** Imprime una representación legible para humanos. */
    @Override
    public void imprimir() {
        System.out.println("Rey(" + getColor() + ") en " + getPosicion() + (hasMoved ? " [movido]" : ""));
    }

    public boolean hasMoved() {
        return hasMoved;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    /**
     * Método de movimiento simplificado.
     * Actualiza la posición y marca que el rey se ha movido (condición relevante para enroque).
     */
    @Override
    public void movimiento(String destino) {
        System.out.println("Rey moviéndose de " + getPosicion() + " a " + destino);
        setPosicion(destino);
        this.hasMoved = true;
    }
}
