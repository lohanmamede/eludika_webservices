package com.eludika.app.ws.ui.startingpoints;

import com.eludika.app.ws.annotations.Seguro;
import com.eludika.app.ws.services.JogoService;
import com.eludika.app.ws.services.JogoServiceImplementation;
import com.eludika.app.ws.shared.dto.JogoDTO;
import com.eludika.app.ws.ui.models.request.SalvarJogoRequestModel;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import com.eludika.app.ws.ui.models.response.JogoResponseModel;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import org.springframework.beans.BeanUtils;

/**
 * Esta classe define os métodos de entrada que estão associados às 
 * requisições referentes às operações da respectiva entidade
 * 
 * @author eres
 */
@Path("/jogos")
public class JogoEntrada {
    
    /**
     * Este método é responsável por salvar os dados da respectiva entidade
     *
     * @param salvarJogoRequestModel modelo de requisição com os atributos 
     * necessários para salvar a entidade
     * @return modelo de resposta com os dados da entidade
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public JogoResponseModel salvarJogo(SalvarJogoRequestModel salvarJogoRequestModel) {

        /* Atribuição dos dados do modelo de requisição a um DTO para manipulação */
        JogoDTO jogoDTO = new JogoDTO();
        BeanUtils.copyProperties(salvarJogoRequestModel, jogoDTO);


        JogoService jogoService = new JogoServiceImplementation();
        jogoService.salvarJogo(jogoDTO);
        
        return new JogoResponseModel();
    }
    
    @GET
    @Path("/{idExterno}")
    @Produces(MediaType.APPLICATION_JSON)
    public JogoResponseModel obterJogo(@PathParam("idExterno") String idExterno) {
        
        JogoService jogoService = new JogoServiceImplementation();
        JogoDTO jogoDTO = jogoService.obterJogo(idExterno);
          
        JogoResponseModel jogoResponseModel = new JogoResponseModel();
        BeanUtils.copyProperties(jogoDTO, jogoResponseModel);
        
        return jogoResponseModel;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<JogoResponseModel> obterJogos(
            @DefaultValue("") @QueryParam("nome") String nome,
            @DefaultValue("0") @QueryParam("inicio") int inicio, 
            @DefaultValue("50") @QueryParam("limite") int tamanhoMaximo) {
  
        JogoService jogoService = new JogoServiceImplementation();
        List<JogoDTO> jogosDTO = jogoService.obterJogos(nome, inicio, tamanhoMaximo);
        
        /* Preparação do valor de retorno */
        List<JogoResponseModel> retorno = new ArrayList<JogoResponseModel>();
        
        for (JogoDTO jogoDTO : jogosDTO) {
            
            JogoResponseModel jogoResponseModel = new JogoResponseModel();
            
            BeanUtils.copyProperties(jogoDTO, jogoResponseModel);
            
            retorno.add(jogoResponseModel);
        }
        
        return retorno;
    }
}
