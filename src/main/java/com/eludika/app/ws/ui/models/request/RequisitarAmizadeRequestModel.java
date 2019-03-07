package com.eludika.app.ws.ui.models.request;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RequisitarAmizadeRequestModel {

    // ------------------------------------------------------------------ Campos    
    private String idExternoSolicitante;
    private String idExternoSolicitado;
    

    // ------------------------------------------------------- Getters e Setters
    public String getIdExternoSolicitante() {
        
        return idExternoSolicitante;
    }
    
    public void setIdExternoSolicitante(String idExternoSolicitante) {
        
        this.idExternoSolicitante = idExternoSolicitante;
    }

    public String getIdExternoSolicitado() {
        
        return idExternoSolicitado;
    }

    public void setIdExternoSolicitado(String idExternoSolicitado) {
        
        this.idExternoSolicitado = idExternoSolicitado;
    }    
}
