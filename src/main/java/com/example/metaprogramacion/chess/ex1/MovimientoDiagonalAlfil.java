package com.example.metaprogramacion.chess.ex1;

/**
 * Interfaz marcador para piezas que se mueven en diagonal como el Alfil.
 *
 * Comentarios pedagógicos:
 * - No define métodos; su función es ser detectada por reflexión para conocer
 *   las capacidades de una clase (p. ej. `clazz.getInterfaces()`).
 * - Las interfaces marcadoras son un patrón usado en la JDK (ej: `Serializable`).
 */
public interface MovimientoDiagonalAlfil {
}
