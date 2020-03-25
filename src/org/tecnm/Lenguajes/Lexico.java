package org.tecnm.Lenguajes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *   @org: Instituto Tecnológico de Toluca
 *   Lenguajes y Autómatas I
 *   @author: Humberto Avila Ortiz
 */

public class Lexico {
    
    private String entrada;
    private ArrayList<Token> tokens;
    private ArrayList<Lexema> salida;

    public Lexico(String entrada, ArrayList<Token> tokens) {
        this.entrada = entrada;
        this.tokens = tokens;
        salida = new ArrayList<Lexema>();
    }
    
    public Lexico(File entrada, ArrayList<Token> tokens) throws FileNotFoundException{
        Scanner sc = new Scanner(entrada);
        this.entrada = "";
        while(sc.hasNext()){
            this.entrada += sc.nextLine();
            this.entrada += "\n";
        }
        sc.close();
        this.tokens = tokens;
        salida = new ArrayList<Lexema>();
    }
    
    private void generar(){
        Scanner sc = new Scanner(entrada);
        String linea = ""; //variable para comparar con elementos de lista de Tokens
        int i = 0; //Línea actual en la entrada
        while(sc.hasNext()){
            linea = sc.nextLine();
            if (linea.length() >= 5 && linea.substring(0, 5).equals("<line")) { //Condición encargada de omitir evaluación números de línea
                //System.out.println("Línea Pre Sub: " + linea);
                i++; //asigna línea actual en código fuente
                linea = linea.substring(6);
                //System.out.println("Línea Post Sub:" + linea);
            } else if (linea.length() == 6 && linea.substring(0, 5).equals("<line>")) {
                i++; //asigna línea actual en código fuente
            }
            
            for (int j = 0; j < tokens.size(); j++){
                if (tokens.get(j).getToken().equals(linea)) salida.add(new Lexema(linea, tokens.get(j).getId(), i));
            }
        }
        sc.close();
   
    }
    
    public ArrayList<Lexema> getSalida() throws IOException{
        return salida;
    }
    
    public void generarArchivo() throws IOException{
        generar();
        String s = "";
        for (int i = 0; i < salida.size(); i++){
            s += salida.get(i).getLexema() + "   " + salida.get(i).getId() + "   "  + salida.get(i).getLinea() + "\n";
        }
        File f = new File("lexico.txt");
        
        if (f.createNewFile())
        {
            System.out.println(f.getName() + " creado");
        } else {
            System.out.println(f.getName() + " ya existe");
        }
        
        FileWriter writer = new FileWriter(f);
        writer.write(s);
        writer.close();
        
    }
}
