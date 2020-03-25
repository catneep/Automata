package org.tecnm.Lenguajes;

/**
 *   @org: Instituto Tecnológico de Toluca
 *   Lenguajes y Autómatas I
 *   @author: Humberto Avila Ortiz
 */

public class Lexema {
    
    private String lexema,id;
    private int linea;

    public Lexema(String lexema, int id, int linea) {
        this.lexema = lexema;
        this.id = String.valueOf(id);
        this.linea = linea;
    }
    
    public Lexema(String lexema, String id, int linea) {
        this.lexema = lexema;
        this.id = String.valueOf(id);
        this.linea = linea;
    }

    public String getLexema() {
        return lexema;
    }

    public void setLexema(String lexema) {
        this.lexema = lexema;
    }

    public String getId() {
        return id;
    }

    public void setId(int id) {
        this.id = String.valueOf(id);
    }
    
    public void setId(String id) {
        this.id = id;
    }

    public int getLinea() {
        return linea;
    }

    public void setLinea(int linea) {
        this.linea = linea;
    }
    
}
