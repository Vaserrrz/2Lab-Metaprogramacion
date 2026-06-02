package com.example.metaprogramacion.chess.ex2;

import com.example.metaprogramacion.chess.ex1.*;

/**
 * Demo del Ejercicio 2: intercesión con `Proxy` e `InvocationHandler`.
 *
 * Comportamiento mostrado:
 * - Interceptación del enroque: se verifica si rey/torre han sido movidos.
 * - Interceptación de la promoción de peones: detecta llegada a la última fila
 *   y crea una Reina por reflexión.
 */
public class Ex2Main {
    public static void main(String[] args) {
        // Creamos piezas originales
        King rey = new King("BLANCO", "1,e");
        Rook torre = new Rook("BLANCO", "1,h");
        Pawn peon = new Pawn("BLANCO", "7,e");

        // Creamos proxies que envolverán las piezas y aplicarán la lógica de intercesión.
        Movable reyProxy = MovementInvocationHandler.createProxy(rey, torre);
        Movable peonProxy = MovementInvocationHandler.createProxy(peon, null);

        System.out.println("--- Intento de enroque (Rey a 1,g) ---");
        reyProxy.movimiento("1,g");

        System.out.println("--- Intento de promoción (Peón a 8,e) ---");
        peonProxy.movimiento("8,e");
    }
}
