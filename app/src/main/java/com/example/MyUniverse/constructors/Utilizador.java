package com.example.MyUniverse.constructors;

import java.io.Serializable;

public class Utilizador implements Serializable {

    private String loginId;
    private String nome;
    private String email;
    private String pass;
    private String desc;


    public Utilizador(String loginId,String nome, String email, String desc){
        this.loginId = loginId;
        this.nome = nome;
        this.email = email;
        this.desc = desc;
    }
    public Utilizador(String loginId, String nome, String desc){
        this.loginId = loginId;
        this.nome = nome;
        this.desc = desc;
    }
    public Utilizador(String loginId){
        this.loginId = loginId;
    }

    public Utilizador(){

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
