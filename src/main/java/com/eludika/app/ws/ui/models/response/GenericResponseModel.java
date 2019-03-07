package com.eludika.app.ws.ui.models.response;

public class GenericResponseModel {

    // ------------------------------------------------------------------ Campos
    private String operacao;
    private String status;
    

    // ------------------------------------------------------- Getters e Setters    
    public String getOperacao() {
        
        return operacao;
    }

    public void setOperacao(String operacao) {
        
        this.operacao = operacao;
    }

    public String getStatus() {
        
        return status;
    }

    public void setStatus(String status) {
        
        this.status = status;
    }
    
}