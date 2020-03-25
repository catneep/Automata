package org.tecnm.Lenguajes;

/**
 *   @org: Instituto Tecnológico de Toluca
 *   Lenguajes y Autómatas I
 *   @author: Humberto Avila Ortiz
 */

public class Token {
    
    private String token;
    private int id;
    private Automata automata;

    public Token(String token, int id, Automata automata) {
        this.token = token;
        this.id = id;
        this.automata = automata;
    }

    public Token(String token, int id) {
        this.token = token;
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Automata getAutomata() {
        return automata;
    }

    public void setAutomata(Automata automata) {
        this.automata = automata;
    }
    
}
