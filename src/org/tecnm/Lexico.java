package org.tecnm;

import java.util.ArrayList;

/**
 *   org: Instituto Tecnológico de Toluca
 *   Lenguajes y Autómatas I
 *   @author: Humberto Avila Ortiz
 */

public class Lexico {
    
    private ArrayList<SalidaLexico> salida;
    
    public Lexico (){
        salida = new ArrayList<SalidaLexico>();
    }
    
    public void setEntrada(String fuente){
        int linea = 1;
        /*TODO:
            - analizar código fuente y generar tabla de salida léxico
            - asignacion de tokens (probablemente agregar bean de token)
        */
    }
    
    public ArrayList<SalidaLexico> getSalida(){
        return salida;
    }
    
    
}
