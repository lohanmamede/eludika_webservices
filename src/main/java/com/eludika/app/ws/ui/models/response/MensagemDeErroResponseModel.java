package com.eludika.app.ws.ui.models.response; // :D

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Esta classe representa o modelo de dados que será enviado para a aplicação
 * cliente quando algum tipo de erro acontecer
 * 
 * @author eres
 */
@XmlRootElement /* Permite que a classe possa ser convertida em JSON/XML */
public class MensagemDeErroResponseModel {

    // ------------------------------------------------------------------ Campos
    private String mensagemDeErro;
    
    /* Nome da constante definina em MensagensDeErro */
    private String mensagemDeErroChave;
    
    /* Link para a documentação definindo a chave e a mensagem */
    private String link;


    // ------------------------------------------------------------ Construtores
    public MensagemDeErroResponseModel() {

        //...
    }

    public MensagemDeErroResponseModel(String mensagemDeErro, String mensagemDeErroChave,
            String link) {
        
        this.mensagemDeErro = mensagemDeErro;
        this.mensagemDeErroChave = mensagemDeErroChave;
        this.link = link;
    }

    // ------------------------------------------------------- Getters e Setters
    /**
     * @return a mensagem de erro
     */
    public String getMensagemDeErro() {

        return mensagemDeErro;
    }

    /**
     * @param mensagemDeErro a mensagem de erro a ser definida
     */
    public void setMensagemDeErro(String mensagemDeErro) {

        this.mensagemDeErro = mensagemDeErro;
    }

    /**
     * @return a chave da mensagem de erro
     */
    public String getMensagemDeErroChave() {

        return mensagemDeErroChave;
    }

    /**
     * @param mensagemDeErroChave a chave da mensagem de erro a ser definida
     */
    public void setMensagemDeErroChave(String mensagemDeErroChave) {

        this.mensagemDeErroChave = mensagemDeErroChave;
    }

    /**
     * @return o link
     */
    public String getLink() {

        return link;
    }

    /**
     * @param link o link a ser definido
     */
    public void setLink(String link) {

        this.link = link;
    }
}
