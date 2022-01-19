package com.example.escommunity;

public class Posts {
    private int idPost;
    private String user;
    private String conteudo;
    private String dia;

    public Posts(int idPost, String user, String conteudo, String dia) {
        this.idPost = idPost;
        this.user = user;
        this.conteudo = conteudo;
        this.dia = dia;
    }

    public int getIdPost() {
        return idPost;
    }

    public void setIdPost(int idPost) {
        this.idPost = idPost;
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
