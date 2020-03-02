package org.tecnm;

//Clase principal con ejemplos de implementación

import java.io.File;
import java.io.IOException;

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

        /* Autómata por parámetros:
              Alfabeto, # Estados, Tabla Estados, Estados de Aceptación */
        Automata b = new Automata("cbsaaa", 3, estados, acep);
        if (b.probarCadena("bcaaaa")) System.out.println("Aceptado");
        else System.out.println("No Aceptado");
        
        Separador s = new Separador(",.;");
        try {
            s.procesar(new File("entrada.txt"));
        } catch (IOException ex) {
            System.out.println("El archivo no fue encontrado.");
        }
        
    }
}
