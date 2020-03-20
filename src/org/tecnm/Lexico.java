package org.tecnm;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author havil
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
        }
        this.tokens = tokens;
        salida = new ArrayList<Lexema>();
    }
    
    public void generar(){
        Scanner sc = new Scanner(entrada);
        String linea; //variable para comparar con elementos de lista de Tokens
        int i = 1; //Línea actual en la entrada
        while(sc.hasNext()){
            linea = sc.nextLine();
            for (int j = 0; j < tokens.size(); j++){
                if (tokens.get(j).getToken().equals(linea)){
                    salida.add(new Lexema(linea, tokens.get(j).getId(), i));
                }
            }
            i++;
        }
    }
    
    public ArrayList<Lexema> getSalida() throws IOException{
        return salida;
    }
    
    public void generarArchivo() throws IOException{
        generar();
        String s = "";
        for (int i = 0; i < salida.size(); i++){
            s += salida.get(i).getLexema() + salida.get(i).getId() + salida.get(i).getLinea() + "\n";
        }
        File f = new File("lexico.txt");
        
        if (f.createNewFile())
        {
            System.out.println("lexico.txt creado");
        } else {
            System.out.println("lexico.txt ya existe");
        }
        
        FileWriter writer = new FileWriter(f);
        writer.write(s);
        writer.close();
        
    }
}
