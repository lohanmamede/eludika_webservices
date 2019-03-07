package com.eludika.app.ws.ui.models.request;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SalvarJogoRequestModel {

    // ------------------------------------------------------------------ Campos    
    private String nome;
    private String descricao;
    private String downloadUrl;
    private String logotipo;
    private String oferecidoPor;
    private String classificacao;
    private int tamanho;
    

    // ------------------------------------------------------- Getters e Setters
    public String getNome() {
        
        return nome;
    }

    public void setNome(String nome) {
        
        this.nome = nome;
    }

    public String getDescricao() {
        
        return descricao;
    }

    public void setDescricao(String descricao) {
        
        this.descricao = descricao;
    }

    public String getDownloadUrl() {
        
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        
        this.downloadUrl = downloadUrl;
    }

    public String getLogotipo() {
        
        return logotipo;
    }

    public void setLogotipo(String logotipo) {
        
        this.logotipo = logotipo;
    }

    public String getOferecidoPor() {
        
        return oferecidoPor;
    }

    public void setOferecidoPor(String oferecidoPor) {
        
        this.oferecidoPor = oferecidoPor;
    }

    public String getClassificacao() {
        
        return classificacao;
    }

    public void setClassificacao(String classificacao) {
        
        this.classificacao = classificacao;
    }

    public int getTamanho() {
        
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        
        this.tamanho = tamanho;
    }   
}
