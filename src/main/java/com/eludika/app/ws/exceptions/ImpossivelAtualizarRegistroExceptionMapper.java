package com.eludika.app.ws.exceptions;

import com.eludika.app.ws.ui.models.response.MensagemDeErroResponseModel;
import com.eludika.app.ws.ui.models.response.MensagensDeErro;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author eres
 */
@Provider
public class ImpossivelAtualizarRegistroExceptionMapper implements 
        ExceptionMapper<ImpossivelAtualizarRegistroException>{

    public Response toResponse(ImpossivelAtualizarRegistroException exception) {
        MensagemDeErroResponseModel mensagemDeErro = new MensagemDeErroResponseModel(
                exception.getMessage(),
                MensagensDeErro.IMPOSSIVEL_ATUALIZAR_REGISTRO.name(), 
                "Sem documentação");
        
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
              .entity(mensagemDeErro).build();
    }
    
}

