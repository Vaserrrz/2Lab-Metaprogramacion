package com.example.metaprogramacion.chess.ex4;

/**
 * Clase Par estricta para el Ejercicio 4.
 *
 * Tiene dos atributos privados de tipo String, un constructor, y getters/setters.
 * Esta clase será analizada por reflexión para generar su equivalente genérico.
 */
public class Par {

    private String primero;
    private String segundo;

    /**
     * Constructor que inicializa ambos valores.
     * @param primero primer valor
     * @param segundo segundo valor
     */
    public Par(String primero, String segundo) {
        this.primero = primero;
        this.segundo = segundo;
    }

    public String getPrimero() {
        return primero;
    }

    public void setPrimero(String primero) {
        this.primero = primero;
    }

    public String getSegundo() {
        return segundo;
    }

    public void setSegundo(String segundo) {
        this.segundo = segundo;
    }
}
