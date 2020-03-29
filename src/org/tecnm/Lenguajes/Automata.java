package org.tecnm.Lenguajes;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *   @org: Instituto Tecnológico de Toluca
 *   Lenguajes y Autómatas I
 *   @author: Humberto Avila Ortiz
 */

public class Automata {
    
    private int[][] tablaEstados;
    private ArrayList<Integer> estados;
    private ArrayList<Integer> estadosAceptacion;
    private String alfabeto;
    
    public Automata(){
        estados = new ArrayList<Integer>();
        estadosAceptacion = new ArrayList<Integer>();
    }
    
    /**
     * Crea una nueva instancia de la clase Autómata.
     * @param alfabeto: Define el conjunto de caracteres que se utilizaran como el
     * alfabeto para la evaluación del autómata, este es reordenado por el método {@code Arrays.sort().}
     * @param numEstados: Número de estados que se evaluarán en el autómata.
     * @param tablaEstados: Tabla de estados para la evaluación del autómata, conteniendo estados nulos como -1,
     * es evaluado con el formato Alfabeto x Estados, ejemplo:
     *           q0,q1,q2
     *      A  {-1, 1,-1},
     *      B  { 2,-1, 2},
     *      C  {-1, 2, 1}
     * @param estadosAceptacion: Arreglo de enteros que contiene los estados de aceptación del autómata, i.e. {@code {1,3,9}}.
     */
    public Automata(String alfabeto,
            int numEstados,
            int[][] tablaEstados,
            int[] estadosAceptacion){
        //Se inicializan los ArrayList
        this.estados = new ArrayList<Integer>();
        this.estadosAceptacion = new ArrayList<Integer>();
        
        //Se asignan los parámetros del autómata
        setAlfabeto(alfabeto);
        setNumEstados(numEstados);
        setAceptacion(estadosAceptacion);
        setTablaEstados(tablaEstados);
    }
    
        public void setNumEstados(int n){
        for (int i = 0; i < n; i++) estados.add(i);
    }
    
    public void setAceptacion(int[] n) {
        if (estados.size() < n.length) {
            throw new IndexOutOfBoundsException
        ("Hay un número inválido de estados de aceptación");
        } else {
            for (int i = 0; i < n.length; i++){
                if (!estados.contains(n[i])) { //No existe un estado en el autómata
                    estadosAceptacion = new ArrayList<Integer>(); //Limpiamos el arreglo de estados de aceptación
                    throw new NullPointerException //Arrojamos error
        ("Hay estados de aceptación que no existen en el autómata");
                } else estadosAceptacion.add(n[i]);
            }
        }
    }
    
    /**
     * Evalúa la cadena ingresada con los datos dados en el constructor de la clase.
     * @param s: Cadena a evaluar.
     * @return {@code true} cuando la cadena es aceptada y {@code false} cuando la cadena es rechazada.
     * @throws {@code NullPointerException} cuando o hay más estados de aceptación que estados a evaluar,
     * o el alfabeto está vacío, o no existen estados de aceptación, o no está establecida una tabla de estados.
     */
    public boolean probarCadena(String s){
        if (alfabeto.equals("") || estados.size()<=0 || estadosAceptacion.size() <= 0 || tablaEstados == null)
            throw new NullPointerException
        ("No se tienen los parámetros necesarios para la evaluación.");
        if (estados.size() < estadosAceptacion.size())
            throw new NullPointerException
        ("Hay demasiados estados de aceptación.");
        if (estadosAceptacion.contains(Integer.valueOf(0)) && s.equals(""))
            return true; //Valida el vacío como una cadena válida
        
        else { //Comienza el proceso de validación de la cadena
            
            char[] alf = alfabeto.toCharArray();
            for (int i = 0; i < s.length(); i++){
                if (Arrays.binarySearch(alf, s.charAt(i)) == -1){ return false;
                    /*throw new NullPointerException
                    ("La cadena contiene caracteres que no están en el alfabeto: " + s.charAt(i));*/
                }
            }
            //if (tablaEstados == null) iniciarEstados(); //Inicializa la tabla de estados si no está asignada
            int estadoActual = 0; //Guarda el estado en el que se encuentra la verificación
            int posAlfabeto = 0; //Guarda la posicion del caracter actual en el alfabeto
            for (int i = 0; i < s.length(); i++){ //Recorrido de la cadena
                if (estadoActual > -1) {
                    for (int j = 0; j < alfabeto.length(); j++){
                        if (alfabeto.charAt(j) == s.charAt(i)) {
                            posAlfabeto = j;
                            break;
                        }
                    }//Encuentra la posicion en el alfabeto

                estadoActual = tablaEstados[posAlfabeto][estadoActual]; //Se asigna el siguiente estado
                }
            }
            if (estadosAceptacion.contains(estadoActual)) return true;
        }
        return false; //Se niega la cadena al llegar a un estado no existente
    }
    
    private void iniciarEstados(){
        tablaEstados = new int[alfabeto.length()][estados.size()];
        for (int i = 0; i < tablaEstados[0].length; i++){
            Arrays.fill(tablaEstados[i], -1);
        }
    }

    public int[][] getTablaEstados() {
        return tablaEstados;
    }

    /**
     * Establece la tabla de estados del autómata.
     * @param tablaEstados: tabla de estados que utiliza el tipo de 
     * dato {@code int}, usa -1 para indicar un estado nulo.
     */
    public void setTablaEstados(int[][] tablaEstados) {
        this.tablaEstados = tablaEstados;
    }

    public ArrayList<Integer> getEstados() {
        return estados;
    }

    public void setEstados(ArrayList<Integer> estados) {
        this.estados = estados;
    }

    public String getAlfabeto() {
        return alfabeto;
    }

    /**
     * Establece el alfabeto que el autómata usará para evaluar cadenas, este
     * es reordenado en tiempo lineal usando el método {@code Arrays.sort()}.
     * @param alfabeto cadena que NO evalúa caracteres duplicados.
     */
    public void setAlfabeto(String alfabeto) {
        alfabeto = alfabeto.trim();
        char[] alfArray = alfabeto.toCharArray();
        Arrays.sort(alfArray); //El Alfabeto debe estar ordenado para completar la búsqueda binaria
        char[] tempArray = new char[alfArray.length];
        int j = 0;
        char car = ' ';
        for (int i = 0; i < alfArray.length; i++){
            if (alfArray[i] != ' ' && alfArray[i] != car){
                tempArray[j] = alfArray[i];
                car = alfArray[i];
                j++;
            }
        }
        alfabeto = new String(tempArray);
        this.alfabeto = alfabeto;
    }

    public ArrayList<Integer> getEstadosAceptacion() {
        return estadosAceptacion;
    }

    public void setEstadosAceptacion(ArrayList<Integer> estadosAceptacion) {
        this.estadosAceptacion = estadosAceptacion;
    }

}