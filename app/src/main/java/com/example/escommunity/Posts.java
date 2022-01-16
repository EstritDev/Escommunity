package com.example.escommunity;

public class Posts {
    private String user;
    private String conteudo;
    private String dia;

    public Posts(String user, String conteudo, String dia) {
        this.user = user;
        this.conteudo = conteudo;
        this.dia = dia;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }


}
