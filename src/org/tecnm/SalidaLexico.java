package org.tecnm;

/**
 *   org: Instituto Tecnológico de Toluca
 *   Lenguajes y Autómatas I
 *   @author: Humberto Avila Ortiz
 */

public class SalidaLexico {
    
    private int linea, token;
    private String lexema;
    
    public SalidaLexico(String lexema, int token, int linea){
        this.lexema = lexema;
        this.token = token;
        this.linea = linea;
    }

    public int getLinea() {
        return linea;
    }

    public void setLinea(int linea) {
        this.linea = linea;
    }

    public int getToken() {
        return token;
    }

    public void setToken(int token) {
        this.token = token;
    }

    public String getLexema() {
        return lexema;
    }

    public void setLexema(String lexema) {
        this.lexema = lexema;
    }
    
    
    
}
