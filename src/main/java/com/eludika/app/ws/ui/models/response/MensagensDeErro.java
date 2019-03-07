package com.eludika.app.ws.ui.models.response; // :D

/**
 * Esta classe contém os possíveis erros conhecidos e registrados no webservice
 * que podem ser enviados para a aplicação cliente
 * 
 * @author eres
 */
public enum MensagensDeErro {

    // ------------------------------------------------------------------ Campos
    CAMPO_OBRIGATORIO_AUSENTE("Campo obrigatório ausente. Por favor, verifique "
            + "a documentação a respeito dos campos obrigatórios"),
    REGISTRO_JA_EXISTE("Registro já existe"),
    ERRO_INTERNO_DO_SERVIDOR("Erro interno do servidor"),
    NENHUM_REGISTRO_ENCONTRADO("Nenhum registro encontrado"),
    IMPOSSIVEL_ATUALIZAR_REGISTRO("Impossível atualizar registro"),
    IMPOSSIVEL_DELETAR_REGISTRO("Impossível deletar registro"),
    AUTENTICACAO_FALHOU("Autenticação falhou");

    private String mensagemDeErro;
    

    // ------------------------------------------------------------ Construtores
    MensagensDeErro(String mensagemDeErro) {

        this.mensagemDeErro = mensagemDeErro;
    }
    

    // ------------------------------------------------------- Getters e Setters
    /**
     * @return a mensagemDeErro
     */
    public String getMensagemDeErro() {
        
        return mensagemDeErro;
    }

    /**
     * @param mensagemDeErro a mensagemDeErro a ser definida
     */
    public void setMensagemDeErro(String mensagemDeErro) {
        
        this.mensagemDeErro = mensagemDeErro;
    }
    
}
