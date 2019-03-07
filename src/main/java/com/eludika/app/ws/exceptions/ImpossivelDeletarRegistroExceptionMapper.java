package com.eludika.app.ws.exceptions;

import com.eludika.app.ws.ui.models.response.MensagemDeErroResponseModel;
import com.eludika.app.ws.ui.models.response.MensagensDeErro;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 *
 * @author eres
 */
public class ImpossivelDeletarRegistroExceptionMapper implements 
        ExceptionMapper<ImpossivelDeletarRegistroException> {

    public Response toResponse(ImpossivelDeletarRegistroException exception) {
        
        MensagemDeErroResponseModel mensagemDeErro = new MensagemDeErroResponseModel(
                exception.getMessage(),
                MensagensDeErro.IMPOSSIVEL_DELETAR_REGISTRO.name(), 
                "Sem documentação");
        
      return Response.status(Response.Status.BAD_REQUEST)
              .entity(mensagemDeErro).build();
    }
}

