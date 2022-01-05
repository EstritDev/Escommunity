package com.example.escommunity;

public class Posts {
    private String user;
    private String conteudo;
    private String hora;

    public Posts(String user, String conteudo, String hora) {
        this.user = user;
        this.conteudo = conteudo;
        this.hora = hora;
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

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }


}
