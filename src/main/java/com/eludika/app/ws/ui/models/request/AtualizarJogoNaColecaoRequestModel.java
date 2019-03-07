package com.eludika.app.ws.ui.models.request;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AtualizarJogoNaColecaoRequestModel {
    
    // ------------------------------------------------------------------ Campos
    private String idExternoUsuario;
    private String idExternoJogo;
    private int pontos = -1;
    private int niveisCompletos = -1;
    private int niveisTotal = -1;
    private int moedas = -1;
    private int jogoPreferido = -1;
    
    
    // ------------------------------------------------------- Getters e Setters
    public String getIdExternoUsuario() {
        return idExternoUsuario;
    }

    public void setIdExternoUsuario(String idExternoUsuario) {
        this.idExternoUsuario = idExternoUsuario;
    }

    public String getIdExternoJogo() {
        return idExternoJogo;
    }

    public void setIdExternoJogo(String idExternoJogo) {
        this.idExternoJogo = idExternoJogo;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public int getNiveisCompletos() {
        return niveisCompletos;
    }

    public void setNiveisCompletos(int niveisCompletos) {
        this.niveisCompletos = niveisCompletos;
    }

    public int getNiveisTotal() {
        return niveisTotal;
    }

    public void setNiveisTotal(int niveisTotal) {
        this.niveisTotal = niveisTotal;
    }

    public int getMoedas() {
        return moedas;
    }

    public void setMoedas(int moedas) {
        this.moedas = moedas;
    }

    public int isJogoPreferido() {
        return jogoPreferido;
    }

    public void setJogoPreferido(int jogoPreferido) {
        this.jogoPreferido = jogoPreferido;
    }   
}