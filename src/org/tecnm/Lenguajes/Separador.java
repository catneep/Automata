package org.tecnm.Lenguajes;

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *   org: Instituto Tecnológico de Toluca
 *   Lenguajes y Autómatas I
 *   @author: Humberto Avila Ortiz
 */

public class Separador {
    
    private ArrayList<String> separadores;
    
    public Separador(){
        separadores = new ArrayList<String>();
    }
    
    public Separador(ArrayList<String> separadores){
        this.separadores = separadores;
    }
    
    public Separador(String separadores){
        this.separadores = new ArrayList<String>();
        separadores = separadores.trim();
        for (int i = 0; i < separadores.length(); i++){
            this.separadores.add(String.valueOf(separadores.charAt(i)));
        }
    }
    
    public void procesar(File archivoEntrada) throws FileNotFoundException, IOException{
        String entrada = "";
        Scanner sc = new Scanner(archivoEntrada);
        
        while (sc.hasNext()) entrada = entrada + sc.nextLine();
        
        sc.close();
        
        generarSalida(entrada);
        
    }
    
    public String getSalida(String entrada){
        if (separadores.size() <= 0) return entrada;
        
        String salida = "";
        for (int i = 0; i < entrada.length(); i++){
            if (separadores.contains(String.valueOf(entrada.charAt(i))) && entrada.charAt(i - 1) != entrada.charAt(i)){
                //Nuevo separador encontrado
                salida = salida + "\n" + entrada.charAt(i) + "\n";
            } else if (separadores.contains(String.valueOf(entrada.charAt(i))) && entrada.charAt(i - 1) == entrada.charAt(i)){
                //Se repite un mismo separador
                salida = salida + " " + entrada.charAt(i);
            } else salida = salida + entrada.charAt(i);
        }
        return salida;
    }
    
    public void generarSalida(String entrada) throws IOException{
        File file = new File("salida.txt");
  
        if (file.createNewFile())
        {
            System.out.println("salida.txt creado");
        } else {
            System.out.println("salida.txt ya existe");
        }

        FileWriter writer = new FileWriter(file);
        writer.write(getSalida(entrada));
        writer.close();
    }
    
}
