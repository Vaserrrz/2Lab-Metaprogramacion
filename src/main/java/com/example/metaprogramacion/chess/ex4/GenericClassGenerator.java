package com.example.metaprogramacion.chess.ex4;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Generador de código fuente equivalente a una clase rígida, pero usando genéricos.
 *
 * Este generador lee la estructura de una clase con reflexión y construye una
 * representación en forma de String de su versión genérica. Para el ejercicio 4
 * usamos como ejemplo la clase `Par`.
 */
public class GenericClassGenerator {

    /**
     * Genera el código fuente equivalente de la clase dada usando un tipo genérico `T`.
     *
     * La idea es transformar los campos `String` en `T` y adaptar las firmas del
     * constructor y los getters/setters.
     */
    public static String generarCodigoGenericoPara(Class<?> clazz) {
        StringBuilder builder = new StringBuilder();

        // Nombre de la clase original
        String nombreClase = clazz.getSimpleName();
        String nombreGenerico = nombreClase + "Generico";

        builder.append("public class ").append(nombreGenerico).append("<T> {\n\n");

        // Campos privados
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            String fieldName = field.getName();
            builder.append("    private T ").append(fieldName).append(";\n");
        }
        builder.append("\n");

        // Constructor genérico
        builder.append("    public ").append(nombreGenerico).append("(T ");
        for (int i = 0; i < fields.length; i++) {
            builder.append(fields[i].getName());
            if (i < fields.length - 1) {
                builder.append(", T ");
            }
        }
        builder.append(") {\n");
        for (Field field : fields) {
            builder.append("        this.").append(field.getName()).append(" = ").append(field.getName()).append(";\n");
        }
        builder.append("    }\n\n");

        // Métodos getters/setters genéricos
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            String methodName = method.getName();
            if (methodName.startsWith("get") && method.getParameterCount() == 0) {
                String fieldName = decapitalize(methodName.substring(3));
                builder.append("    public T ").append(methodName).append("() {\n");
                builder.append("        return ").append(fieldName).append(";\n");
                builder.append("    }\n\n");
            } else if (methodName.startsWith("set") && method.getParameterCount() == 1) {
                String fieldName = decapitalize(methodName.substring(3));
                builder.append("    public void ").append(methodName).append("(T ").append(fieldName).append(") {\n");
                builder.append("        this.").append(fieldName).append(" = ").append(fieldName).append(";\n");
                builder.append("    }\n\n");
            }
        }

        builder.append("}\n");
        return builder.toString();
    }

    /**
     * Genera el código fuente genérico de la clase `Par` a partir de su constructor y campos.
     * Este método demuestra cómo extraer metadatos de la clase original.
     */
    public static void mostrarCodigoGenerico(Class<?> clazz) {
        System.out.println("=== Generador de clase genérica para " + clazz.getSimpleName() + " ===");
        System.out.println(generarCodigoGenericoPara(clazz));
    }

    private static String decapitalize(String texto) {
        if (texto == null || texto.isEmpty()) {
            return texto;
        }
        return Character.toLowerCase(texto.charAt(0)) + texto.substring(1);
    }
}
