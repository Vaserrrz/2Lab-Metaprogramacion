package com.example.metaprogramacion.chess.ex1;

/**
 * Clase demostrativa para el Ejercicio 1.
 *
 * Objetivos pedagógicos de `Main`:
 * - Crear instancias concretas de la jerarquía de piezas.
 * - Mostrar comportamiento básico de las piezas (método `imprimir`).
 * - Invocar `IntrospectionClass.informacionClase(...)` para demostrar qué
 *   información pueden obtener las herramientas en tiempo de ejecución.
 *
 * Observación práctica para el profesor:
 * - Este `main` está diseñado para ejecutarse sin entorno complejo; permite
 *   revisar en consola cómo la reflexión expone la estructura interna de las clases.
 */
public class Main {
    public static void main(String[] args) {
        // Crear ejemplos de piezas con diferentes colores y posiciones.
        King rey = new King("BLANCO", "1,e");
        Pawn peon = new Pawn("BLANCO", "2,e");
        Bishop alfil = new Bishop("NEGRO", "8,c");
        Queen reina = new Queen("NEGRO", "8,d");

        // Mostrar su representación humana.
        rey.imprimir();
        peon.imprimir();
        alfil.imprimir();
        reina.imprimir();

        // Usar la utilidad de introspección para inspeccionar cada objeto.
        IntrospectionClass.informacionClase(rey);
        IntrospectionClass.informacionClase(peon);
        IntrospectionClass.informacionClase(alfil);
        IntrospectionClass.informacionClase(reina);
    }
}
