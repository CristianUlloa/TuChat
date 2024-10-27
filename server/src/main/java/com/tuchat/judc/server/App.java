package com.tuchat.judc.server;

public class App {

    public static void main2(String[] args) {
        int numero = 1234; // Número cuyas cifras queremos sumar
        int resultado = sumaCifrasRecursiva(numero);
        System.out.println("La suma de las cifras es: " + resultado);
    }

    // Función recursiva para sumar las cifras de un número
    public static int sumaCifrasRecursiva(int numero) {
        // Caso base: Si el número es 0, ya no hay más cifras que sumar
        if (numero == 0) {
            return 0;
        } else {
            // Extraer el último dígito y sumar de manera binaria
            int digito = numero % 10;
            return sumaBinaria(digito, sumaCifrasRecursiva(numero / 10)); // Llamada recursiva
        }
    }

    // Método que suma dos números utilizando solo operadores binarios
    public static int sumaBinaria(int a, int b) {
        if (b == 0) {
            return a;  // Caso base: cuando no hay acarreo
        } else {
            int carry = a & b;  // El acarreo se obtiene con AND
            a = a ^ b;          // La suma sin acarreo se obtiene con XOR
            b = carry << 1;     // Desplazamos el acarreo a la izquierda
            return sumaBinaria(a, b);  // Llamada recursiva hasta que no haya acarreo
        }
    }
}
