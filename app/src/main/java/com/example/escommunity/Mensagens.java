package com.example.escommunity;

import java.io.Serializable;

public class Mensagens implements Serializable {
    private String utilizador;
    private String hora;
    private String mensagem;

    public Mensagens(String utilizador, String hora, String mensagem) {
        this.utilizador = utilizador;
        this.hora = hora;
        this.mensagem = mensagem;
    }

    public String getUtilizador() {
        return utilizador;
    }

    public void setUtilizador(String utilizador) {
        this.utilizador = utilizador;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }


}
