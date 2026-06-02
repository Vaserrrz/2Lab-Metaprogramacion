package com.example.metaprogramacion.chess.ex1;

/**
 * Interfaz que expone los métodos que permitiremos interceptar mediante Proxy.
 * - `imprimir()` para representación
 * - `movimiento(String)` para ejecutar movimientos (punto de intercesión)
 *
 * Importante: `Proxy` puede crear proxies sólo para interfaces, por eso
 * definimos esta interfaz y hacemos que `Piece` la implemente.
 */
public interface Movable {
    void imprimir();
    void movimiento(String destino);
}
