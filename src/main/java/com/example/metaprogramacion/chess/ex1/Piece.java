package com.example.metaprogramacion.chess.ex1;

/**
 * Clase abstracta base para piezas de ajedrez (Ejercicio 1 - Introspección).
 *
 * Propósito pedagógico extendido:
 * - Mostrar una jerarquía de tipos que será inspeccionada por la API de reflexión.
 * - Campos privados (`color`, `posicion`) representan estado encapsulado que
 *   normalmente no es accesible desde fuera; con reflexión podremos leerlos.
 * - Los métodos abstractos `imprimir()` y `movimiento(String)` permiten definir
 *   comportamiento concreto en subclases sin exponer su implementación aquí.
 *
 * Notas sobre la JVM y la metaprogramación:
 * - En tiempo de compilación sólo conocemos la API; en tiempo de ejecución la JVM
 *   mantiene un objeto `Class` con metadatos (campos, métodos, constructores).
 * - Este diseño permite demostrar cómo frameworks (p. ej. serializadores)
 *   inspeccionan y manipulan objetos sin conocer sus clases concretas.
 */
public abstract class Piece implements Movable {
    /** Color de la pieza: "BLANCO" o "NEGRO". Campo privado para demostrar lectura por reflexión. */
    private String color;

    /**
     * Posición representada de forma simplificada como "fila,columna" (ej: "2,e").
     * En un motor real usaríamos coordenadas numéricas; aquí priorizamos claridad.
     */
    private String posicion;

    /**
     * Constructor de la clase base.
     * @param color color de la pieza
     * @param posicion posición inicial (cadena simple)
     */
    public Piece(String color, String posicion) {
        this.color = color;
        this.posicion = posicion;
    }

    // ------------------ Getters / Setters (API pública) ------------------
    // Estos métodos existen para uso normal en código; la reflexión permite
    // además acceder a `color` y `posicion` aun cuando sean privados.
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    /**
     * Método que imprime una representación de la pieza.
     * Implementado por cada subclase para mostrar su tipo y estado.
     */
    public abstract void imprimir();

    /**
     * Ejecuta un movimiento hacia `destino`.
     * La implementación concreta cambiará la posición y mostrará el movimiento.
     * Diseñado para ser interceptado por `Proxy` en el Ejercicio 2.
     * @param destino posición destino (cadena simplificada)
     */
    public abstract void movimiento(String destino);
}
