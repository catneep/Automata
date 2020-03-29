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
    
    /**
     * Llena el {@code ArrayList salida} con Lexemas encontrados dentro del código fuente separado.
     */
    private void generar(){
        Scanner sc = new Scanner(entrada);
        String linea = ""; //variable para comparar con elementos de lista de Tokens
        boolean evaluarAutomata = false;
        boolean noToken = false;
        int lastToken = -1;
        int i = 0; //Línea actual en la entrada
        
        while(sc.hasNext()){
            linea = sc.nextLine();
            
            if (linea.equals("<line>")) { //Condición encargada de omitir evaluación números de línea
                i++; //asigna línea actual en código fuente
                continue;
            }
            if (evaluarAutomata && lastToken != -1){
                if (!tokens.get(lastToken).getAutomata().probarCadena(linea)){ //Evalúa errores de sintaxis
                    salida.add(new Lexema(linea, "E" + tokens.get(lastToken).getId(), i)); //Asigna el error en salida
                }
                evaluarAutomata = false;
                lastToken = -1; //Se reinician los estados de las variables auxiliares para la próxima evaluación
            }

            for (int j = 0; j < tokens.size(); j++){
                if (tokens.get(j).getToken().equals(linea)) {
                    salida.add(new Lexema(linea, tokens.get(j).getId(), i));
                    try {
                        if (tokens.get(j).hasAutomata()){
                            evaluarAutomata = true;
                            lastToken = j;
                        }
                        else evaluarAutomata = false;
                    } catch (NullPointerException e){}
                    break;
                } else noToken = true;
            }
            if (noToken) {
                salida.add(new Lexema(linea, 0, i));
                noToken = false;
            }
        }
        sc.close();
   
    }
    
    /**
     * Retorna el {@code ArrayList<Lexema>} de salida, creado en {@code generar()}.
     * @return
     */
    public ArrayList<Lexema> getSalida(){
        return salida;
    }
    
    /**
     * Generar un archivo con el nombre especificado y agrega la extensión {@code .txt}.
     * @param name
     * @throws IOException 
     */
    public void generarArchivo(String name) throws IOException{
        generar();
        String s = "";
        for (int i = 0; i < salida.size(); i++){
            s += salida.get(i).getLexema() + " | " + salida.get(i).getId() + " | "  + salida.get(i).getLinea() + "\n";
        }
        File f = new File(name + ".txt");
        
        if (f.createNewFile()) System.out.println(f.getName() + " creado");
        else System.out.println(f.getName() + " ya existe");
        
        FileWriter writer = new FileWriter(f);
        writer.write(s);
        writer.close();
    }
    
    /**
     * Generación de archivo por defecto con el nombre {@code lexico.txt} en la ruta del proyecto.
     * @throws IOException 
     */
    public void generarArchivo() throws IOException{
        generar();
        String s = "";
        for (int i = 0; i < salida.size(); i++){
            s += salida.get(i).getLexema() + " | " + salida.get(i).getId() + " | "  + salida.get(i).getLinea() + "\n";
        }
        File f = new File("lexico.txt");
        
        if (f.createNewFile()) System.out.println(f.getName() + " creado");
        else System.out.println(f.getName() + " ya existe");
        
        FileWriter writer = new FileWriter(f);
        writer.write(s);
        writer.close();
    }
}
