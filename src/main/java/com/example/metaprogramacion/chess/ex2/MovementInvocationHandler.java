package com.example.metaprogramacion.chess.ex2;

import com.example.metaprogramacion.chess.ex1.Movable;
import com.example.metaprogramacion.chess.ex1.Pawn;
import com.example.metaprogramacion.chess.ex1.King;
import com.example.metaprogramacion.chess.ex1.Rook;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.Constructor;

/**
 * InvocationHandler que intercepta llamadas a `movimiento(String)` para aplicar
 * lógica de enroque (castling) y promoción de peones.
 *
 * Comentarios pedagógicos:
 * - `Proxy` crea un objeto que implementa una o varias interfaces y delega todas
 *   las llamadas a este handler. El handler puede ejecutar lógica antes/después
 *   de invocar el método real sobre el objeto objetivo.
 */
public class MovementInvocationHandler implements InvocationHandler {

    private final Movable target;
    // Opcional: referencia a la torre asociada para verificar condiciones de enroque.
    private final Rook associatedRook;

    public MovementInvocationHandler(Movable target, Rook associatedRook) {
        this.target = target;
        this.associatedRook = associatedRook;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // Interceptar solo el método movimiento
        if ("movimiento".equals(method.getName()) && args != null && args.length == 1) {
            String destino = (String) args[0];

            // Enroque: detectar un movimiento típico de enroque del rey (e.g., "1,g" desde "1,e").
            if (target instanceof King) {
                King rey = (King) target;
                // parseamos columna destino (ej: "1,g") -> columna 'g'
                String[] parts = destino.split(",");
                if (parts.length == 2) {
                    String columna = parts[1];
                    // detectamos enroque corto por columna 'g' y enroque largo por 'c'
                    if ("g".equalsIgnoreCase(columna) || "c".equalsIgnoreCase(columna)) {
                        System.out.println("[Interceptor] Intento de enroque detectado para el Rey hacia " + destino);
                        // Verificar condiciones: ni rey ni torre deben haber movido
                        boolean canCastle = true;
                        if (rey.hasMoved()) {
                            System.out.println("[Interceptor] Enroque inválido: el rey ya se movió antes.");
                            canCastle = false;
                        }
                        if (associatedRook == null) {
                            System.out.println("[Interceptor] No hay torre asociada para verificar enroque.");
                            canCastle = false;
                        } else if (associatedRook.hasMoved()) {
                            System.out.println("[Interceptor] Enroque inválido: la torre ya se movió antes.");
                            canCastle = false;
                        }

                        if (canCastle) {
                            System.out.println("[Interceptor] Enroque permitido: realizando movimientos del rey y la torre.");
                            // Ejecutamos el movimiento real del rey
                            Object result = method.invoke(target, args);
                            // Mover la torre a su nueva posición (simplificado):
                            // si enroque corto (columna g) mover torre a f
                            String fila = parts[0];
                            String nuevaPosTorre = fila + ",f";
                            associatedRook.movimiento(nuevaPosTorre);
                            return result;
                        } else {
                            System.out.println("[Interceptor] Enroque denegado; movimiento cancelado.");
                            return null; // cancelamos el movimiento
                        }
                    }
                }
            }

            // Promoción: si es peón y llega a la fila final, ofrecemos promoción.
            if (target instanceof Pawn) {
                Pawn peon = (Pawn) target;
                String[] parts = destino.split(",");
                if (parts.length == 2) {
                    String fila = parts[0];
                    // suposición: peones blancos promueven en fila "8", negros en "1"
                    boolean isWhite = "BLANCO".equalsIgnoreCase(getColor(target));
                    boolean promotionRow = (isWhite && "8".equals(fila)) || (!isWhite && "1".equals(fila));
                    if (promotionRow) {
                        System.out.println("[Interceptor] Promoción detectada para Peón en " + destino);
                        // Aquí podríamos presentar opciones; por simplicidad elegimos Reina.
                        System.out.println("[Interceptor] Promoviendo a Reina (Queen) por defecto.");
                        // Crear instancia de Queen vía reflexión y mostrarla.
                        try {
                            Class<?> queenClass = Class.forName("com.example.metaprogramacion.chess.ex1.Queen");
                            Constructor<?> ctor = queenClass.getConstructor(String.class, String.class);
                            Object nuevaReina = ctor.newInstance(peon.getColor(), destino);
                            // Llamamos imprimir() en la nueva pieza para demostrar la promoción.
                            Method imprimir = queenClass.getMethod("imprimir");
                            imprimir.invoke(nuevaReina);
                        } catch (Exception e) {
                            System.out.println("[Interceptor] Error al crear la pieza promovida: " + e.getMessage());
                        }
                        // Ejecutamos el movimiento del peón (actualiza su posición)
                        return method.invoke(target, args);
                    }
                }
            }
        }

        // Por defecto, delegamos la invocación al objeto real.
        return method.invoke(target, args);
    }

    // Helper para leer el color usando reflexión (evita depender de interfaz adicional).
    private String getColor(Movable target) {
        try {
            Method m = target.getClass().getMethod("getColor");
            Object res = m.invoke(target);
            return res != null ? res.toString() : null;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Crea un proxy dinámico que envuelve `target` y aplica este handler.
     */
    public static Movable createProxy(Movable target, Rook associatedRook) {
        MovementInvocationHandler handler = new MovementInvocationHandler(target, associatedRook);
        return (Movable) Proxy.newProxyInstance(
                Movable.class.getClassLoader(),
                new Class[]{Movable.class},
                handler
        );
    }
}
