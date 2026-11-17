/**
 * Programa que calcula el Índice de Masa Corporal (IMC),
 * determina su clasificación y muestra una recomendación general.
 *
 * Autor: Thomas Alejandro Herreno Sanchez
 * Fecha: 2026-11-16
 */

import java.util.Scanner; // Importa Scanner para leer datos por teclado

// Clase principal del programa
public class Java_imc2 {

    // Método principal: punto de entrada del programa
    public static void main(String[] args) {

        // Crea el objeto Scanner para leer datos desde la terminal
        Scanner teclado = new Scanner(System.in);

        // Se repite el programa mientras el usuario desee continuar
        do {
            System.out.println("\n--- Calculadora y Clasificador de IMC ---");

            // Solicita el peso y la estatura al usuario
            double peso = leerDatoPositivo(
                teclado,
                "Ingrese su peso en kilogramos: "
            );

            double estatura = leerDatoPositivo(
                teclado,
                "Ingrese su estatura en metros: "
            );

            // Calcula y muestra los resultados
            mostrarResultado(peso, estatura);

        } while (deseaContinuar(teclado));

        System.out.println(
            "Gracias por usar la calculadora de IMC. ¡Hasta luego!"
        );

        teclado.close(); // Libera el recurso Scanner
    }

    // Lee un número positivo y repite la solicitud si la entrada no es válida
    private static double leerDatoPositivo(
            Scanner teclado,
            String mensaje) {

        while (true) {
            System.out.print(mensaje);

            // Lee el texto, elimina espacios y cambia la coma por punto
            String entrada = teclado.nextLine()
                    .trim()
                    .replace(',', '.');

            try {
                // Convierte el texto ingresado en un número decimal
                double valor = Double.parseDouble(entrada);

                // Verifica que el número sea mayor que cero y finito
                if (valor > 0 && Double.isFinite(valor)) {
                    return valor;
                }

                System.out.println(
                    "Dato inválido: el valor debe ser mayor que cero."
                );

            } catch (NumberFormatException e) {
                // Se ejecuta cuando el usuario ingresa letras u otro texto
                System.out.println(
                    "Entrada inválida. Ingrese únicamente un número."
                );
            }
        }
    }

    // Calcula el IMC usando el peso y la estatura
    private static double calcularIMC(
            double peso,
            double estatura) {

        double imc = peso / (estatura * estatura);

        // Redondea el resultado a dos cifras decimales
        return Math.round(imc * 100.0) / 100.0;
    }

    // Determina la clasificación de acuerdo con el valor del IMC
    private static String clasificarIMC(double imc) {

        if (imc < 18.5) {
            return "Bajo peso";

        } else if (imc < 25.0) {
            return "Peso saludable";

        } else if (imc < 30.0) {
            return "Sobrepeso";

        } else {
            return "Obesidad";
        }
    }

    // Devuelve una recomendación general según la clasificación
    private static String obtenerRecomendacion(
            String clasificacion) {

        if (clasificacion.equals("Bajo peso")) {
            return "Procure mantener una alimentación equilibrada "
                    + "y consulte a un profesional de la salud.";

        } else if (clasificacion.equals("Peso saludable")) {
            return "Mantenga una alimentación equilibrada "
                    + "y realice actividad física regularmente.";

        } else if (clasificacion.equals("Sobrepeso")) {
            return "Mejore sus hábitos de alimentación y aumente "
                    + "la actividad física con orientación profesional.";

        } else {
            return "Consulte a un profesional de la salud para recibir "
                    + "una valoración y orientación adecuada.";
        }
    }

    // Calcula y muestra el IMC, la clasificación y la recomendación
    private static void mostrarResultado(
            double peso,
            double estatura) {

        double imc = calcularIMC(peso, estatura);
        String clasificacion = clasificarIMC(imc);

        String recomendacion =
                obtenerRecomendacion(clasificacion);

        System.out.println("\n--- Resultado del IMC ---");
        System.out.printf("Peso: %.2f kg%n", peso);
        System.out.printf("Estatura: %.2f m%n", estatura);
        System.out.printf("IMC: %.2f%n", imc);
        System.out.println("Clasificación: " + clasificacion);
        System.out.println("Recomendación: " + recomendacion);
    }

    // Pregunta al usuario si desea realizar otro cálculo
    private static boolean deseaContinuar(Scanner teclado) {

        System.out.println(
            "\nÚnicamente se aceptará 's' para continuar;"
        );

        System.out.println(
            "cualquier otra entrada terminará el programa."
        );

        System.out.print(
            "¿Desea calcular otro IMC? (s/n): "
        );

        String respuesta = teclado.nextLine()
                .trim()
                .toLowerCase();

        // El programa continúa únicamente si se escribe la letra "s"
        return respuesta.equals("s");
    }
}
