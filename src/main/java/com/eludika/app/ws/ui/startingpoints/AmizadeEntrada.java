package com.eludika.app.ws.ui.startingpoints;

import com.eludika.app.ws.services.AmizadeService;
import com.eludika.app.ws.services.AmizadeServiceImplementation;
import com.eludika.app.ws.services.UsuarioService;
import com.eludika.app.ws.services.UsuarioServiceImplementation;
import com.eludika.app.ws.shared.dto.AmizadeDTO;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import com.eludika.app.ws.ui.models.request.RequisitarAmizadeRequestModel;
import com.eludika.app.ws.ui.models.response.AmizadeResponseModel;

/**
 * Esta classe define os métodos de entrada que estão associados às 
 * requisições referentes às operações do relacionamento entre usuários
 * 
 * @author eres
 */
@Path("/amizade")
public class AmizadeEntrada {

    /**
     * Este método é responsável pela criação de uma relação de amizade
     *
     * @param requisitarAmizadeRequestModel modelo de requisição com os atributos 
     * necessários para a realização do serviço
     * @return modelo de resposta com os dados da relação de amizade
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public AmizadeResponseModel requisitarAmizade(RequisitarAmizadeRequestModel 
            requisitarAmizadeRequestModel) {
        
        /* Obtenção dos dados do solicitante e do solicitado no banco de dados e
        atribuição a um Data Transfer Object */
        UsuarioService usuarioService = new UsuarioServiceImplementation();
        AmizadeDTO amizadeDTO = new AmizadeDTO();
        
        amizadeDTO.setSolicitante(usuarioService
                .obterUsuario(requisitarAmizadeRequestModel.getIdExternoSolicitante()));
        
        amizadeDTO.setSolicitado(usuarioService
                .obterUsuario(requisitarAmizadeRequestModel.getIdExternoSolicitado()));
        
        /* Criação da respectiva classe de serviço e execução do serviço desejado */
        AmizadeService amizadeService = new AmizadeServiceImplementation();
        
        amizadeService.requisitarAmizade(amizadeDTO);
        
        return new AmizadeResponseModel();
    }
}
