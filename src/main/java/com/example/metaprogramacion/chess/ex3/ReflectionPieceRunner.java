package com.example.metaprogramacion.chess.ex3;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Ejercicio 3: Auto-modificación e Invocación Dinámica.
 *
 * Esta clase demuestra cómo crear objetos y llamar a sus métodos sin usar
 * cast directo a las clases concretas. En lugar de ello, usa reflexión para:
 * - cargar clases por nombre
 * - obtener constructores y crear instancias
 * - obtener métodos y ejecutar `invoke(...)`
 *
 * Así se logra un estilo de metaprogramación donde el programa trata a los
 * objetos como datos y decisiones en tiempo de ejecución.
 */
public class ReflectionPieceRunner {

    public static void main(String[] args) {
        // Mapa ordenado de clase -> par (color, posición inicial)
        Map<String, String[]> piezas = new LinkedHashMap<>();
        piezas.put("com.example.metaprogramacion.chess.ex1.King", new String[]{"BLANCO", "1,e"});
        piezas.put("com.example.metaprogramacion.chess.ex1.Pawn", new String[]{"BLANCO", "7,e"});
        piezas.put("com.example.metaprogramacion.chess.ex1.Bishop", new String[]{"NEGRO", "8,c"});
        piezas.put("com.example.metaprogramacion.chess.ex1.Queen", new String[]{"NEGRO", "8,d"});
        piezas.put("com.example.metaprogramacion.chess.ex1.Rook", new String[]{"BLANCO", "1,h"});

        System.out.println("=== Ejercicio 3: Instanciación dinámica e invocación de movimiento ===");

        // Para cada clase, creamos una instancia y ejecutamos su método "movimiento".
        for (Map.Entry<String, String[]> entrada : piezas.entrySet()) {
            String className = entrada.getKey();
            String color = entrada.getValue()[0];
            String posicion = entrada.getValue()[1];
            String destino = calcularDestino(className, posicion);

            try {
                Object pieza = crearInstanciaPorReflexion(className, color, posicion);
                imprimirInstancia(pieza);
                invocarMovimiento(pieza, destino);
                System.out.println();
            } catch (Exception e) {
                System.out.println("Error al procesar " + className + ": " + e.getMessage());
                e.printStackTrace(System.out);
            }
        }
    }

    /**
     * Crea una instancia de la clase con el constructor (String, String).
     * Usa reflexión para evitar cualquier casteo directo a la clase concreta.
     */
    private static Object crearInstanciaPorReflexion(String className, String color, String posicion) throws Exception {
        Class<?> clazz = Class.forName(className);
        Constructor<?> constructor = clazz.getConstructor(String.class, String.class);
        return constructor.newInstance(color, posicion);
    }

    /**
     * Calcula un destino de prueba para cada pieza según su tipo.
     * Así se demuestra que invocamos movimientos distintos sin usar tipos concretos.
     */
    private static String calcularDestino(String className, String posicionInicial) {
        if (className.endsWith("King")) {
            return "1,f";
        }
        if (className.endsWith("Pawn")) {
            return "8,e"; // simulamos promoción para el peón blanco
        }
        if (className.endsWith("Bishop")) {
            return "6,e";
        }
        if (className.endsWith("Queen")) {
            return "5,d";
        }
        if (className.endsWith("Rook")) {
            return "1,f";
        }
        return posicionInicial;
    }

    /**
     * Invoca dinámicamente el método `movimiento(String)` de la pieza.
     */
    private static void invocarMovimiento(Object pieza, String destino) throws Exception {
        Method movimiento = pieza.getClass().getMethod("movimiento", String.class);
        System.out.println("Invocando movimiento en " + pieza.getClass().getSimpleName() + " hacia " + destino);
        movimiento.invoke(pieza, destino);
    }

    /**
     * Invoca dinámicamente el método `imprimir()` de la pieza para mostrar su estado.
     */
    private static void imprimirInstancia(Object pieza) throws Exception {
        Method imprimir = pieza.getClass().getMethod("imprimir");
        imprimir.invoke(pieza);
    }
}
