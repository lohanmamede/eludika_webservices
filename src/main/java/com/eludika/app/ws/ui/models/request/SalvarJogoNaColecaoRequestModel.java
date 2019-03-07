package com.eludika.app.ws.ui.models.request;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SalvarJogoNaColecaoRequestModel {

    // ------------------------------------------------------------------ Campos    
    private String idExternoUsuario;
    private String idExternoJogo;
    

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
}
