package com.eludika.app.ws.ui.startingpoints;

import com.eludika.app.ws.io.entidades.Usuario;
import com.eludika.app.ws.services.AutenticacaoService;
import com.eludika.app.ws.services.AutenticacaoServiceImplementation;
import com.eludika.app.ws.ui.models.request.LoginRequestModel;
import com.eludika.app.ws.ui.models.response.AutenticacaoResponseModel;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Esta classe define os métodos de entrada que estão associados às requisições
 * referentes às operações de login
 *
 * @author eres
 */
@Path("/autenticacao")
public class AutenticacaoEntrada {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public AutenticacaoResponseModel userLogin(LoginRequestModel loginRequestModel) {

        AutenticacaoService autenticacaoService = new AutenticacaoServiceImplementation();
        
        Usuario /*:O*/ usuarioAutenticado = autenticacaoService.autenticar(
                loginRequestModel.getEmail(), loginRequestModel.getSenha());

        /* Redefinição do token de acesso */
        autenticacaoService.redefinirCredenciaisDeSeguranca(
                loginRequestModel.getSenha(), usuarioAutenticado);

        String tokenDeAcesso =  autenticacaoService.emitirTokenDeAcesso(usuarioAutenticado);

        AutenticacaoResponseModel resposta = new AutenticacaoResponseModel();
        
        resposta.setIdExterno(usuarioAutenticado.getIdExterno());
        resposta.setToken(tokenDeAcesso);
        resposta.setCodinome(usuarioAutenticado.getCodinome());
        resposta.setImagemPerfil(usuarioAutenticado.getImagemPerfil());

        return resposta;
    }
}
