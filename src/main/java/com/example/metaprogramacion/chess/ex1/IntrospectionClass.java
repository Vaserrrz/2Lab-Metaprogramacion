package com.example.metaprogramacion.chess.ex1;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Utilidad de introspección (Ejercicio 1).
 *
 * Comentarios pedagógicos ampliados:
 * - Usamos `Class<?> clazz = obj.getClass()` para obtener el metadato de la instancia.
 * - `getDeclaredFields()` devuelve TODOS los campos declarados en la clase (privados incluidos).
 * - `setAccessible(true)` permite evadir controles de acceso de Java a nivel de reflexión,
 *   lo cual modifica el comportamiento normal de encapsulación. Útil para herramientas,
 *   pero hay que usarlo con responsabilidad.
 * - `getDeclaredConstructors()` y `getDeclaredMethods()` muestran la signatura completa,
 *   incluidas sobrecargas y visibilidad.
 * - `getInterfaces()` y `getSuperclass()` permiten reconstruir la jerarquía que la JVM
 *   mantiene para la resolución de tipos y la dispatch dinámico.
 */
public class IntrospectionClass {

    /**
     * Imprime detalles relevantes de la clase de `obj`.
     * @param obj instancia a inspeccionar
     */
    public static void informacionClase(Object obj) {
        // Obtenemos el objeto Class que representa el tipo en tiempo de ejecución.
        Class<?> clazz = obj.getClass();

        System.out.println("=== Información de instancia ===");

        // Nombre completo (incluye paquete) — útil para localizar la clase cargada.
        System.out.println("Nombre de la clase: " + clazz.getName());

        // ------------------ Campos (Atributos) ------------------
        System.out.println("-- Atributos --");
        Field[] fields = clazz.getDeclaredFields();
        if (fields.length == 0) {
            System.out.println("(sin atributos declarados)");
        }
        for (Field f : fields) {
            try {
                // Rompemos la encapsulación para leer incluso campos privados.
                f.setAccessible(true);
                String mods = Modifier.toString(f.getModifiers());
                Object value = f.get(obj); // lectura del valor actual del campo
                // Mostramos: modificador, tipo simple, nombre y valor.
                System.out.printf("%s %s %s = %s\n", mods, f.getType().getSimpleName(), f.getName(), value);
            } catch (IllegalAccessException e) {
                System.out.println("No se pudo acceder a " + f.getName() + ": " + e.getMessage());
            }
        }

        // ------------------ Constructores ------------------
        System.out.println("-- Constructores --");
        Constructor<?>[] ctors = clazz.getDeclaredConstructors();
        for (Constructor<?> c : ctors) {
            String mods = Modifier.toString(c.getModifiers());
            Class<?>[] params = c.getParameterTypes();
            System.out.print(mods + " " + c.getName() + "(");
            for (int i = 0; i < params.length; i++) {
                System.out.print(params[i].getSimpleName());
                if (i < params.length - 1) System.out.print(", ");
            }
            System.out.println(")");
        }

        // ------------------ Métodos ------------------
        System.out.println("-- Métodos --");
        Method[] methods = clazz.getDeclaredMethods();
        if (methods.length == 0) {
            System.out.println("(sin métodos declarados)");
        }
        for (Method m : methods) {
            String mods = Modifier.toString(m.getModifiers());
            System.out.print(mods + " " + m.getReturnType().getSimpleName() + " " + m.getName() + "(");
            Class<?>[] params = m.getParameterTypes();
            for (int i = 0; i < params.length; i++) {
                System.out.print(params[i].getSimpleName());
                if (i < params.length - 1) System.out.print(", ");
            }
            System.out.println(")");
        }

        // ------------------ Superclase ------------------
        System.out.println("-- Superclase --");
        Class<?> sup = clazz.getSuperclass();
        if (sup != null) System.out.println(sup.getName()); else System.out.println("(no tiene)");

        // ------------------ Interfaces ------------------
        System.out.println("-- Interfaces implementadas (getInterfaces) --");
        Class<?>[] ifs = clazz.getInterfaces();
        if (ifs.length == 0) System.out.println("(ninguna)");
        for (Class<?> i : ifs) {
            System.out.println(i.getName());
        }

        // Verificación de interfaces específicas pedidas en el enunciado.
        System.out.println("-- Implementa interfaces específicas? --");
        boolean impAlfil = false;
        boolean impReina = false;
        for (Class<?> i : ifs) {
            if (i.equals(MovimientoDiagonalAlfil.class)) impAlfil = true;
            if (i.equals(MovimientoDiagonalReina.class)) impReina = true;
        }
        System.out.println("Implementa MovimientoDiagonalAlfil: " + impAlfil);
        System.out.println("Implementa MovimientoDiagonalReina: " + impReina);

        System.out.println("===============================\n");
    }
}
