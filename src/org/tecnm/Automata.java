package org.tecnm;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *   org: Instituto Tecnológico de Toluca
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
    
    public boolean probarCadena(String s){
        if (alfabeto.equals("") || estados.size()<=0 || estadosAceptacion.size() <= 0)
            throw new NullPointerException
        ("No se tienen los parámetros necesarios para la evaluación.");
        if (estadosAceptacion.contains(Integer.valueOf(0)) && s.equals(""))
            return true; //Valida el vacío como una cadena válida
        
        else { //Comienza el proceso de validación de la cadena
            
            char[] alf = alfabeto.toCharArray();
            for (int i = 0; i < s.length(); i++){
                if (Arrays.binarySearch(alf, s.charAt(i)) == -1){
                    throw new NullPointerException
                    ("La cadena contiene caracteres que no están en el alfabeto.");
                }
            }
            
            if (tablaEstados == null) iniciarEstados(); //Inicializa la tabla de estados si no está asignada
            int estadoActual = 0; //Guarda el estado en el que se encuentra la verificación
            int posAlfabeto = 0; //Guarda la posicion del caracter actual en el alfabeto
            for (int i = 0; i < s.length(); i++){ //Recorrido de la cadena
                
                for (int j = 0; j < alfabeto.length(); j++){
                    if (alfabeto.charAt(j) == s.charAt(i)) posAlfabeto = j;
                }//Encuentra la posicion en el alfabeto
                
                if (estadoActual >= 0) {
                    estadoActual = tablaEstados[posAlfabeto][estadoActual];
                } //Se asigna el siguiente estado
                else return false; //Se niega la cadena al llegar a un estado no existente                
                
            }
            
            if (estadosAceptacion.contains(estadoActual)) return true;
            
        }
        return false;
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

    public void setAlfabeto(String alfabeto) {
        alfabeto = alfabeto.trim();
        char[] alfArray = alfabeto.toCharArray();
        Arrays.sort(alfArray);
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