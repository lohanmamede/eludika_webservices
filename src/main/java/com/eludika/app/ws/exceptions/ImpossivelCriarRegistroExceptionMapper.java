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
public class ImpossivelCriarRegistroExceptionMapper implements
        ExceptionMapper<ImpossivelCriarRegistroException> {

    @Override
    public Response toResponse(ImpossivelCriarRegistroException exception) {

        MensagemDeErroResponseModel mensagemDeErro = new MensagemDeErroResponseModel(
                exception.getMessage(),
                MensagensDeErro.NENHUM_REGISTRO_ENCONTRADO.name(),
                "Sem documentação");

        return Response.status(Response.Status.BAD_REQUEST)
                .entity(mensagemDeErro).build();
    }
}
