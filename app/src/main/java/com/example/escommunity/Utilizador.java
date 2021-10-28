package com.example.escommunity;

import java.io.Serializable;

public class Utilizador implements Serializable {

        private String user;
        private Boolean memorizar;

    public Utilizador(String user, Boolean memorizar){
        this.user = user;
        this.memorizar = memorizar;
    }

    public Boolean getMemorizar() {
        return memorizar;
    }

    public void setMemorizar(Boolean memorizar) {
        this.memorizar = memorizar;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
