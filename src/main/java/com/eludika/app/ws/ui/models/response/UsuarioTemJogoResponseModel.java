package com.eludika.app.ws.ui.models.response;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class UsuarioTemJogoResponseModel {

    // ------------------------------------------------------------------ Campos
    private String idExternoUsuario;
    private String idExternoJogo;
    private int pontos;
    private int niveisCompletos;
    private int niveisTotal;
    private int moedas;
    private int jogoPreferido;
    

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