package org.tecnm;

/**
 *
 * @author havil
 */
public class Lexema {
    
    private String lexema;
    private int id, linea;

    public Lexema(String lexema, int id, int linea) {
        this.lexema = lexema;
        this.id = id;
        this.linea = linea;
    }

    public String getLexema() {
        return lexema;
    }

    public void setLexema(String lexema) {
        this.lexema = lexema;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLinea() {
        return linea;
    }

    public void setLinea(int linea) {
        this.linea = linea;
    }
    
}
