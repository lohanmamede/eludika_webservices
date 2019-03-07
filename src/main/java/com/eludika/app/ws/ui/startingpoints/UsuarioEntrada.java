package com.eludika.app.ws.ui.startingpoints; // :D

import com.eludika.app.ws.annotations.Seguro;
import com.eludika.app.ws.io.entidades.Usuario;
import com.eludika.app.ws.services.UsuarioService;
import com.eludika.app.ws.services.UsuarioServiceImplementation;
import com.eludika.app.ws.services.UsuarioTemJogoService;
import com.eludika.app.ws.services.UsuarioTemJogoServiceImplementation;
import com.eludika.app.ws.shared.dto.UsuarioTemJogoDTO;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import com.eludika.app.ws.ui.models.response.GenericResponseModel;
import com.eludika.app.ws.ui.models.response.UsuarioResponseModel;
import com.eludika.app.ws.ui.models.response.UsuarioTemJogoResponseModel;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import org.springframework.beans.BeanUtils;

/**
 * Esta classe define os métodos de entrada que estão associados às 
 * requisições referentes às operações com um usuário
 * 
 * @author eres
 */
@Path("/usuarios")
public class UsuarioEntrada {

    /**
     * Este método é responsável pela criação de um usuário
     *
     * @param requisicao modelo de requisição com os atributos 
     * necessários para a criação de um novo usuário
     * @return modelo de resposta com os dados do usuário criado
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public GenericResponseModel criarUsuario(Usuario requisicao) {

        /* Filtra apenas os dados necessários para a realização do serviço */
        Usuario requisicaoFiltrada = new Usuario();
        
        requisicaoFiltrada.setTipo(requisicao.getTipo());
        requisicaoFiltrada.setCodinome(requisicao.getCodinome());
        requisicaoFiltrada.setNomeCompleto(requisicao.getNomeCompleto());
        requisicaoFiltrada.setEmail(requisicao.getEmail());
        requisicaoFiltrada.setSenha(requisicao.getSenha());
        requisicaoFiltrada.setDataNascimento(requisicao.getDataNascimento());

        /* Execução do serviço */
        UsuarioService usuarioService = new UsuarioServiceImplementation();
        usuarioService.criarUsuario(requisicaoFiltrada);
        
        /* Retorna o status da realização do serviço */
        GenericResponseModel status = new GenericResponseModel();
        status.setOperacao("POST");
        status.setStatus("SUCESSO");
 
        return status;
    }
    
    @Seguro
    @PUT
    @Path("/{idExterno}/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public GenericResponseModel atualizarUsuario(
            @PathParam("idExterno") String idExterno, Usuario requisicao) {
        
        /* Filtra apenas os dados necessários para a realização do serviço */
        Usuario requisicaoFiltrada = new com.eludika.app.ws.io.entidades.Usuario();
        
        requisicaoFiltrada.setNomeCompleto(requisicao.getNomeCompleto());
        requisicaoFiltrada.setNivel(requisicao.getNivel());
        requisicaoFiltrada.setPontos(requisicao.getPontos());
        requisicaoFiltrada.setBiografia(requisicao.getBiografia());
        requisicaoFiltrada.setCidade(requisicao.getCidade());
        requisicaoFiltrada.setEstado(requisicao.getEstado());
        requisicaoFiltrada.setEmail(requisicao.getEmail());
        requisicaoFiltrada.setImagemPerfil(requisicao.getImagemPerfil());
        requisicaoFiltrada.setSexo(requisicao.getSexo());
        
        /* Executa o serviço */
        UsuarioService usuarioService = new UsuarioServiceImplementation();
        usuarioService.atualizarUsuario(idExterno, requisicaoFiltrada);
        
        /* Retorna o status da realização do serviço */
        GenericResponseModel status = new GenericResponseModel();
        status.setOperacao("PUT");
        status.setStatus("SUCESSO");
 
        return status;
    }

    /**
     * Este método retorna as informações de um usuário com o idSeguro passado 
     * como parâmetro, se for encontrado algum
     *
     * @param idExterno informação necessária para a pesquisa
     * @return modelo de resposta com os dados do usuário encontrado
     */
    @Seguro
    @GET
    @Path("/{idExterno}")
    @Produces(MediaType.APPLICATION_JSON)
    public UsuarioResponseModel obterUsuario(@PathParam("idExterno") String idExterno) {
        
        /* Executa o serviço */
        UsuarioService usuarioService = new UsuarioServiceImplementation();
        Usuario usuarioDTO = usuarioService.obterUsuario(idExterno);
          
        /* Atribuição das informações contidas no DTO, à classe que representa o
        modelo de resposta onde há apenas as informações do usuário 
        que devem ser retornadas */
        UsuarioResponseModel usuarioResponseModel = new UsuarioResponseModel();
        BeanUtils.copyProperties(usuarioDTO, usuarioResponseModel);
        
        return usuarioResponseModel;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<UsuarioResponseModel> obterUsuarios(
            @DefaultValue("") @QueryParam("nome") String nome,
            @DefaultValue("0") @QueryParam("inicio") int inicio, 
            @DefaultValue("50") @QueryParam("limite") int limite) {
  
        UsuarioService usuarioService = new UsuarioServiceImplementation();
        List<Usuario/*:O*/> usuariosDTO = usuarioService.obterUsuarios(nome, inicio, limite);
        
        /* Preparação do valor de retorno */
        List<UsuarioResponseModel> retorno = new ArrayList<UsuarioResponseModel>();
        
        for (Usuario /*:O*/ usuarioDTO : usuariosDTO) {
            
            UsuarioResponseModel usuarioModelo = new UsuarioResponseModel();
            
            BeanUtils.copyProperties(usuarioDTO, usuarioModelo);
            
            // usuarioModelo.setHref("/usuario/" + usuarioDTO.getIdExterno());
            
            retorno.add(usuarioModelo);
        }
        
        return retorno;
    }

    @GET
    @Path("/{idExternoUsuario}/jogos/{idExternoJogo}")
    @Produces(MediaType.APPLICATION_JSON)
    public UsuarioTemJogoResponseModel obterJogoDoUsuario(
            @PathParam("idExternoUsuario") String idExternoUsuario,
            @PathParam("idExternoJogo") String idExternoJogo) {
        
        /* Criação da respectiva classe de serviço e execução do serviço desejado */
        UsuarioTemJogoService usuarioTemJogoService = new UsuarioTemJogoServiceImplementation();
        
        UsuarioTemJogoDTO usuarioTemJogoDTO;
        usuarioTemJogoDTO = usuarioTemJogoService.obterJogoDoUsuario(idExternoUsuario, idExternoJogo);
        
        UsuarioTemJogoResponseModel usuarioTemJogoResponseModel = new UsuarioTemJogoResponseModel();
        BeanUtils.copyProperties(usuarioTemJogoDTO, usuarioTemJogoResponseModel);
        
        usuarioTemJogoResponseModel.setIdExternoUsuario(idExternoUsuario);
        usuarioTemJogoResponseModel.setIdExternoJogo(idExternoJogo);
        
        return usuarioTemJogoResponseModel;
    }
    
    @GET
    @Path("/{idExterno}/jogos/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<UsuarioTemJogoResponseModel> obterJogosDoUsuario(
            @PathParam("idExterno") String idExterno,
            @DefaultValue("0") @QueryParam("inicio") int inicio, 
            @DefaultValue("50") @QueryParam("limite") int limite) {
  
        UsuarioTemJogoService usuarioTemJogoService = new UsuarioTemJogoServiceImplementation();
        
        List<UsuarioTemJogoDTO> usuariosTemJogoDTO;
        usuariosTemJogoDTO = usuarioTemJogoService.obterJogosDoUsuario(idExterno, inicio, limite);
        
        /* Preparação do valor de retorno */
        List<UsuarioTemJogoResponseModel> retorno = new ArrayList<UsuarioTemJogoResponseModel>();
        
        for (UsuarioTemJogoDTO usuarioTemJogoDTO : usuariosTemJogoDTO) {
            
            UsuarioTemJogoResponseModel usuarioTemJogoModelo = new UsuarioTemJogoResponseModel();
            
            BeanUtils.copyProperties(usuarioTemJogoDTO, usuarioTemJogoModelo);
            
            usuarioTemJogoModelo.setIdExternoUsuario(usuarioTemJogoDTO.getUsuario().getIdExterno());
            usuarioTemJogoModelo.setIdExternoJogo(usuarioTemJogoDTO.getJogo().getIdExterno());
            
            retorno.add(usuarioTemJogoModelo);
        }
        
        return retorno;
    }
    
    @DELETE
    @Path("/{idExternoUsuario}/jogos/{idExternoJogo}")
    @Produces(MediaType.APPLICATION_JSON)
    public GenericResponseModel deletarJogoDoUsuario(
            @PathParam("idExternoUsuario") String idExternoUsuario,
            @PathParam("idExternoJogo") String idExternoJogo) {
        
        
        UsuarioTemJogoService usuarioTemJogoService = new UsuarioTemJogoServiceImplementation();
 
        usuarioTemJogoService.deletarJogoDoUsuario(idExternoUsuario, idExternoJogo);

        GenericResponseModel retorno = new GenericResponseModel();
        retorno.setOperacao("DELETE");
        retorno.setStatus("SUCESSO");
 
        return retorno;
    } 
}
