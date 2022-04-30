package com.example.MyUniverse.constructors;

public class Posts {
    private int idPost;
    private String user;
    private String conteudo;
    private String dia;
    private int likes;
    private String edited;

    public Posts(int idPost, String user, String conteudo, String dia, int likes, String edited) {
        this.idPost = idPost;
        this.user = user;
        this.conteudo = conteudo;
        this.dia = dia;
        this.likes = likes;
        this.edited = edited;
    }

    public int getLikes() {
        return likes;
    }

    public String getEdited() {
        return edited;
    }

    public void setEdited(String edited) {
        this.edited = edited;
    }

    public void setLikes(int likes) {
        this.likes = likes;
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
