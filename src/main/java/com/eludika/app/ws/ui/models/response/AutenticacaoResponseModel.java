package com.eludika.app.ws.ui.models.response; // :D

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Esta classe representa o modelo de dados que será enviado para a aplicação
 * cliente caso uma tentativa de login seja bem-sucedida
 * 
 * @author eres
 */
@XmlRootElement /* Permite que a classe possa ser convertida em JSON/XML */
public class AutenticacaoResponseModel {
    
    // ------------------------------------------------------------------ Campos
    private String idExterno;
    private String token;
    private String codinome;
    private String imagemPerfil;

    
    // ------------------------------------------------------- Getters e Setters
    /**
     * @return o idExterno
     */
    public String getIdExterno() {
        
        return idExterno;
    }

    /**
     * @param idExterno o idExterno a definir
     */
    public void setIdExterno(String idExterno) {
        
        this.idExterno = idExterno;
    }

    /**
     * @return o token
     */
    public String getToken() {
        
        return token;
    }

    /**
     * @param token o token a definir
     */
    public void setToken(String token) {
        
        this.token = token;
    }

    /**
     * @return o codinome
     */
    public String getCodinome() {
        
        return codinome;
    }

    /**
     * @param codinome o codinome a definir
     */
    public void setCodinome(String codinome) {
        
        this.codinome = codinome;
    }  

    /**
     * @return a imagemPerfil
     */
    public String getImagemPerfil() {
        
        return imagemPerfil;
    }

    /**
     * @param imagemPerfil a imagemPerfil definir
     */
    public void setImagemPerfil(String imagemPerfil) {
        
        this.imagemPerfil = imagemPerfil;
    }
}
