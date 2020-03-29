package org.tecnm.Lenguajes;

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *   @org: Instituto Tecnológico de Toluca
 *   Lenguajes y Autómatas I
 *   @author: Humberto Avila Ortiz
 */

public class Separador {
    
    private ArrayList<String> separadores;
    
    public Separador(){
        separadores = new ArrayList<String>();
    }
    
    /**
     * Inicia una nueva instancia de la clase Separador,
     * usa una lista de cadenas y establece a sus miembros como separadores.
     * @param separadores
     */
    public Separador(ArrayList<String> separadores){
        this.separadores = separadores;
    }
    
    /**
     * Inicia una nueva instancia de la clase Separador,
     * evalúa una cadena y establece todos sus caracteres como separadores.
     * @param separadores
     */
    public Separador(String separadores){
        this.separadores = new ArrayList<String>();
        //separadores = separadores.trim();
        for (int i = 0; i < separadores.length(); i++){
            this.separadores.add(String.valueOf(separadores.charAt(i)));
        }
    }
    
    /**
     * Procesa un archivo y genera una salida en el directorio del proyecto,
     * ambas usando la extensión {@code .txt}.
     * @param archivoEntrada
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public void procesar(File archivoEntrada) throws FileNotFoundException, IOException{
        String entrada = "";
        Scanner sc = new Scanner(archivoEntrada);
        String s;
        
        while (sc.hasNext()) {
            s = sc.nextLine().trim();
            entrada = entrada + "\n<line>" + s + "\n";
        }
        
        sc.close();
        
        crearArchivo(entrada);
        
    }
    
    /**
     * Procesa una cadena y genera un archivo de salida con la extensión {@code .txt}
     * encontrado en el directorio del proyecto.
     * @param entrada
     * @throws IOException 
     */
    public void procesar(String entrada) throws IOException{
        Scanner sc = new Scanner(entrada);
        String s;
        String temp = "";
        
        while (sc.hasNext()) {
            s = sc.nextLine().trim();
            temp = temp + "<line>" + s + "\n";
        }
        
        sc.close();
        
        crearArchivo(temp);
        
    }
    
    /**
     * Analiza una cadea y retorna la salida generada por la clase sin generar un archivo nuevo.
     * @param entrada
     * @return 
     */
    public String getSalida(String entrada){
        if (separadores.size() <= 0) return entrada;
        
        String salida = "";
        String temp;
        for (int i = 0; i < entrada.length(); i++){
            if ((separadores.contains(String.valueOf(entrada.charAt(i))) && entrada.charAt(i - 1) != entrada.charAt(i)) ||
                    (separadores.contains(String.valueOf(entrada.charAt(i))) && i == 0)){
                //Nuevo separador encontrado
                //salida = salida + "\n" + entrada.charAt(i) + "\n";
                salida = salida + "\n";
            } else if (separadores.contains(String.valueOf(entrada.charAt(i))) && entrada.charAt(i - 1) == entrada.charAt(i)){
                //Se repite un mismo separador
                //salida = salida + entrada.charAt(i);
            } else salida = salida + entrada.charAt(i);
        }
        return salida;
    }
    
    private void crearArchivo(String entrada) throws IOException{
        File file = new File("salida.txt");
  
        if (file.createNewFile())
        {
            System.out.println(file.getName() + " creado");
        } else {
            System.out.println(file.getName() + " ya existe");
        }

        FileWriter writer = new FileWriter(file);
        writer.write(getSalida(entrada));
        writer.close();
    }
    
}
