package org.tecnm.Lenguajes;

//Clase principal con ejemplos de implementación

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        int [] acep = {0,2}; //Arreglo que contiene los estados de aceptación
        int [][] estados = { //Alfabeto x Estados
                    //q0,q1,q2
            /* A */ {-1, 1,-1},
            /* B */ { 2,-1, 2},
            /* C */ {-1, 2, 1}
        };

        //Autómata con constructor por defecto y asignación de parámetros por métodos
        Automata a = new Automata();
        a.setNumEstados(3);
        a.setAlfabeto("abc");
        a.setAceptacion(acep);
        a.setTablaEstados(estados);
        if (a.probarCadena("bcc")) System.out.println("Aceptado");
        else System.out.println("No Aceptado");
        
        int [][] tablaAutomataNumero = { //Alfabeto x Estados
                    //q0,q1,q2,q3,q4
            /* . */ {-1, 2,-1, 2,-1},
            /* 0 */ { 3, 1, 2,-1, 2},
            /* 1 */ { 1, 1, 4,-1, 4},
            /* 2 */ { 1, 1, 4,-1, 4},
            /* 3 */ { 1, 1, 4,-1, 4},
            /* 4 */ { 1, 1, 4,-1, 4},
            /* 5 */ { 1, 1, 4,-1, 4},
            /* 6 */ { 1, 1, 4,-1, 4},
            /* 7 */ { 1, 1, 4,-1, 4},
            /* 8 */ { 1, 1, 4,-1, 4},
            /* 9 */ { 1, 1, 4,-1, 4}
        };
        int [] aceptacionTablaNumeros = {1,4};
        

        /* Autómata por parámetros:
              Alfabeto, # Estados, Tabla Estados, Estados de Aceptación */
        Automata numeros = new Automata("0123456789.", 5, tablaAutomataNumero, aceptacionTablaNumeros);
        if (numeros.probarCadena("0.7201055")) System.out.println("Número Aceptado");
        else System.out.println("Número No Aceptado");
        
        /*****Ejemplo de aplicación para separador de código fuente*****/
        Separador s = new Separador(",;(){} =");
        try {
            s.procesar(new File("entrada.txt"));
        } catch (IOException ex) {
            System.out.println("El archivo no fue encontrado.");
        }
        
        /*****Ejemplo de aplicación para Salida Léxico de código fuente*****/
        try {
            ArrayList<Token> at = new ArrayList<Token>();
            at.add(new Token("Inicio",1));
            at.add(new Token("-palabra", 6));
            at.add(new Token("-numero", 3, numeros));
            at.add(new Token("-caracter", 8));
            at.add(new Token("//", 4));
            at.add(new Token("Fin",2));
            Lexico l = new Lexico(new File("salida.txt"), at);
            l.generarArchivo();
        } catch (IOException e){
            System.out.println("Archivo No Encontrado.");
        }
        
    }
}
