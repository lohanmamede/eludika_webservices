package com.eludika.app.ws.ui.startingpoints;

import com.eludika.app.ws.io.entidades.Usuario;
import com.eludika.app.ws.services.JogoService;
import com.eludika.app.ws.services.JogoServiceImplementation;
import com.eludika.app.ws.services.UsuarioService;
import com.eludika.app.ws.services.UsuarioServiceImplementation;
import com.eludika.app.ws.services.UsuarioTemJogoService;
import com.eludika.app.ws.services.UsuarioTemJogoServiceImplementation;
import com.eludika.app.ws.shared.dto.JogoDTO;
import com.eludika.app.ws.shared.dto.UsuarioTemJogoDTO;
import com.eludika.app.ws.ui.models.request.AtualizarJogoNaColecaoRequestModel;
import com.eludika.app.ws.ui.models.request.SalvarJogoNaColecaoRequestModel;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import com.eludika.app.ws.ui.models.response.UsuarioTemJogoResponseModel;
import javax.ws.rs.PUT;
import org.springframework.beans.BeanUtils;

/**
 * Esta classe define os métodos de entrada que estão associados às 
 * requisições referentes às operações do relacionamento entre usuários
 * 
 * @author eres
 */
@Path("/jogos-do-usuario")
public class UsuarioTemJogoEntrada {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public UsuarioTemJogoResponseModel salvarJogoNaColecao(SalvarJogoNaColecaoRequestModel requisicao) {
        
        /* Obtenção dos dados das entidades envolvidas no banco de dados */
        UsuarioTemJogoDTO usuarioTemJogoDTO = new UsuarioTemJogoDTO();
        
        UsuarioService usuarioService = new UsuarioServiceImplementation();
        Usuario/*:O*/ usuarioArmazenado = usuarioService.obterUsuario(requisicao.getIdExternoUsuario());
        usuarioTemJogoDTO.setUsuario(usuarioArmazenado);
        
        JogoService jogoService = new JogoServiceImplementation();
        JogoDTO jogoArmazenado = jogoService.obterJogo(requisicao.getIdExternoJogo());
        usuarioTemJogoDTO.setJogo(jogoArmazenado);
        
        /* Criação da respectiva classe de serviço e execução do serviço desejado */
        UsuarioTemJogoService usuarioTemJogoService = new UsuarioTemJogoServiceImplementation();
        
        usuarioTemJogoService.salvarJogoNaColecao(usuarioTemJogoDTO);
        
        return new UsuarioTemJogoResponseModel();
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public UsuarioTemJogoResponseModel atualizarJogoNaColecao(
            AtualizarJogoNaColecaoRequestModel requisicao) {
        
        /* Retorno do usuário com o id externo especificado */
        UsuarioTemJogoService usuarioTemJogoService = new UsuarioTemJogoServiceImplementation();
        
        UsuarioTemJogoDTO jogoDoUsuarioArmazenado;
        jogoDoUsuarioArmazenado = usuarioTemJogoService.obterJogoDoUsuario(
                requisicao.getIdExternoUsuario(),
                requisicao.getIdExternoJogo());
        
        if(requisicao.getMoedas() != -1){
            
            jogoDoUsuarioArmazenado.setMoedas(jogoDoUsuarioArmazenado.getMoedas()
                    + requisicao.getMoedas());
        }
            
        if(requisicao.getPontos() != -1) {
            
            jogoDoUsuarioArmazenado.setPontos(jogoDoUsuarioArmazenado.getPontos()
                    + requisicao.getPontos());
        }
        
        if(requisicao.getNiveisCompletos() != -1) {
            
            jogoDoUsuarioArmazenado.setNiveisCompletos(
                    jogoDoUsuarioArmazenado.getNiveisCompletos()
                            + requisicao.getNiveisCompletos());
        }
        
        if(requisicao.getNiveisTotal()!= -1) {
            
            jogoDoUsuarioArmazenado.setNiveisTotal(
                    jogoDoUsuarioArmazenado.getNiveisTotal()
                            + requisicao.getNiveisTotal());
        }
        
        if(requisicao.isJogoPreferido()!= -1) {
            
            jogoDoUsuarioArmazenado.setJogoPreferido(requisicao.isJogoPreferido());
        }
        
        /* Atualizar detalhes do usuário */
        usuarioTemJogoService.atualizarJogoNaColecao(jogoDoUsuarioArmazenado);
        
        /* Preparação do valor de retorno */
        UsuarioTemJogoResponseModel usuarioTemJogoResponseModel = new UsuarioTemJogoResponseModel();
        BeanUtils.copyProperties(jogoDoUsuarioArmazenado, usuarioTemJogoResponseModel);

        return usuarioTemJogoResponseModel;
    }
}
