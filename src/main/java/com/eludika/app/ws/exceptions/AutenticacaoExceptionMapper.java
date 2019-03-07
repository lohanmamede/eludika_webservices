package com.eludika.app.ws.exceptions;

import com.eludika.app.ws.ui.models.response.MensagensDeErro;
import com.eludika.app.ws.ui.models.response.MensagemDeErroResponseModel;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Esta classe...
 * 
 * @author eres
 */
@Provider
public class AutenticacaoExceptionMapper implements ExceptionMapper<AutenticacaoException> {

    public Response toResponse(AutenticacaoException exception) {
        
        MensagemDeErroResponseModel mensagemDeErro = new MensagemDeErroResponseModel(
                exception.getMessage(),
                MensagensDeErro.AUTENTICACAO_FALHOU.name(),
                "Sem documentação");
        
        return Response.status(Response.Status.UNAUTHORIZED)
                .entity(mensagemDeErro)
                .build();
    } 
}