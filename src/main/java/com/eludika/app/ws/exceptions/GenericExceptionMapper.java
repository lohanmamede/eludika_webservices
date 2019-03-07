package com.eludika.app.ws.exceptions;

import com.eludika.app.ws.ui.models.response.MensagemDeErroResponseModel;
import com.eludika.app.ws.ui.models.response.MensagensDeErro;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Esta classe ...
 * 
 * @author eres
 */
@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

    @Override
    public Response toResponse(Throwable exception) {

        MensagemDeErroResponseModel mensagemDeErro = new MensagemDeErroResponseModel(
                exception.getMessage() + ": " + exception.getCause().getMessage(),
                MensagensDeErro.ERRO_INTERNO_DO_SERVIDOR.name(),
                "Sem documentação");

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(mensagemDeErro).build();
    }
}
