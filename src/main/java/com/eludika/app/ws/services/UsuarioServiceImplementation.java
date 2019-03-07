package com.eludika.app.ws.services; // :D

import com.eludika.app.ws.exceptions.AutenticacaoException;
import com.eludika.app.ws.exceptions.ImpossivelAtualizarRegistroException;
import com.eludika.app.ws.exceptions.NenhumRegistroEncontradoException;
import com.eludika.app.ws.io.dao.UsuarioDAOMariaDB;
import com.eludika.app.ws.utils.EludikaUtils;
import com.eludika.app.ws.io.dao.UsuarioDAO;
import com.eludika.app.ws.io.entidades.Usuario;
import com.eludika.app.ws.ui.models.response.MensagensDeErro;
import static java.lang.System.*;
import java.util.List;
import javax.persistence.NoResultException;

/**
 * Esta classe implementa métodos que manipulam uma instância de EludikaUtils e 
 um DAO de usuário. O primeiro a fim de preparar o usuário, inserindo 
 * criptografia, e fazendo validações por exemplo, antes que as operações entre 
 * a aplicação e o banco de dados possam ser realizadas. O segundo, para 
 * efetivamente realizar as operações de persistência e gerenciar a abertura e o 
 * encerramento de conexões.
 * 
 * @author eres
 */
public class UsuarioServiceImplementation implements UsuarioService {

    // ------------------------------------------------------------------ Campos
    UsuarioDAO usuarioDAO;
    EludikaUtils eludikaUtils;

    
    // ------------------------------------------------------------ Construtores
    public UsuarioServiceImplementation() {

        this.eludikaUtils = new EludikaUtils();
        this.usuarioDAO = new UsuarioDAOMariaDB();
    }
    

    // ----------------------------- Métodos Override (Interface UsuarioService)
    /**
     * Este método se assegura de algumas atividades que devem ser feitas antes
     * que um novo usuário possa ser persistido, como validação e criptografia,
     * e por fim efetivamente persiste o usuário. 
     * OBS: o parâmetro é modificado internamente
     *
     * @param usuario DTO com os dados do novo usuário
     */
    @Override
    public void criarUsuario(Usuario/*:O*/ usuario) {

        /* Validação dos campos obrigatórios */
        this.eludikaUtils.validarCamposObrigatorios(usuario);

        /* Geração de um id externo para o usuário */
        usuario.setIdExterno(eludikaUtils.gerarIdExterno(30));

        /* Geração de um sal (dado aleatório concatenado a senha) */
        usuario.setSal(eludikaUtils.gerarSal(30));

        /* Geração de uma senha criptograda */
        String senhaGerada = eludikaUtils.gerarSenhaSegura(usuario.getSenha(), usuario.getSal());
        usuario.setSenhaCriptografada(senhaGerada);

        /* Abre uma conexão, tenta salvar o usuário e encerra a conexão */
        try {

            this.usuarioDAO.abrirConexao();
            this.usuarioDAO.salvarUsuario(usuario);
        } 
        finally {

            this.usuarioDAO.fecharConexao();
        }
    }
    
    @Override
    public void atualizarUsuario(String idExterno, Usuario/*:O*/ requisicaoFiltrada) {
        
        try {
            
            this.usuarioDAO.abrirConexao();
            
            Usuario/*:O*/ usuarioArmazenado = this.usuarioDAO.obterUsuario(idExterno);
            this.validarAtualizacao(requisicaoFiltrada, usuarioArmazenado);
            this.usuarioDAO.atualizarUsuario(usuarioArmazenado);

        } 
        catch (Exception ex) {
            
            throw new ImpossivelAtualizarRegistroException(ex.getMessage());
        } 
        finally {
            
            this.usuarioDAO.fecharConexao();
        }
    }
    
    /**
     * Este método invoca as funções do DAO do usuário para buscar por um 
     * idExterno no banco de dados e retornar um usuário
     *
     * @param idExterno String com o idExterno do usuário
     * @return DTO com as informações do usuário encontrado, ou nulo
     */
    @Override
    public Usuario/*:O*/ obterUsuario(String idExterno) {
       
        Usuario/*:O*/ usuarioDTO = null;
        
        try {

            this.usuarioDAO.abrirConexao();
            usuarioDTO = this.usuarioDAO.obterUsuario(idExterno);
        } 
        catch(NoResultException ex) {
            
            /* Se for lançada a exceção NoResultException, relança a exceção de 
            nenhum registro encontrado */
            throw new NenhumRegistroEncontradoException(MensagensDeErro
                    .NENHUM_REGISTRO_ENCONTRADO.getMensagemDeErro());
        }
        finally {

            this.usuarioDAO.fecharConexao();
        }
        
        return usuarioDTO;
    }
    
    /**
     * Este método invoca as funções do DAO do usuário para trazer um limite
     * de usuários do banco de dados
     *
     * @param nome
     * @param inicio
     * @param limite
     * 
     * @return List com DTOS com as informações do usuário encontrado, ou nulo
     */
    @Override
    public List<Usuario/*:O*/> obterUsuarios(String nome, int inicio, int limite) {

        List<Usuario/*:O*/> usuarios = null;

        /* Obtenção dos usuários do banco de dados */
        try {
            
            this.usuarioDAO.abrirConexao();
            usuarios = this.usuarioDAO.obterUsuarios(nome, inicio, limite);
        } 
        finally {
            
            this.usuarioDAO.fecharConexao();
        }

        return usuarios;
    }
    
    /**
     * Este método invoca as funções do DAO do usuário para buscar por um 
     * email no banco de dados
     *
     * @param email String com o email do usuário
     * @return DTO com as informações do usuário encontrado, ou nulo
     */
    @Override
    public Usuario/*:O*/ obterUsuarioPorEmail(String email) {
        
        Usuario/*:O*/ usuarioDTO = null;
        
        /* Se email não for informado, evita conexão com o banco de dados */
        if (email == null || email.isEmpty()) {
            
            return usuarioDTO;
        }
        
        /* Conexão com o banco de dados: tenta abrir a conexão e buscar o 
        email no banco de dados, independente do que aconteça, ao final a 
        conexão deve ser fechada */ 
        try {

            this.usuarioDAO.abrirConexao();
            out.println("Conexão foi aberta!"); /* Log */
            usuarioDTO = this.usuarioDAO.obterUsuarioPorEmail(email);
        }
        catch(NoResultException ex) {
            
            /* Se for lançada a exceção de que nenhum resultado foi encotrando, 
            relança a exceção de falha na autenticação */
            throw new AutenticacaoException(
                    MensagensDeErro.AUTENTICACAO_FALHOU.getMensagemDeErro());
        }
        finally {

            this.usuarioDAO.fecharConexao();
            out.println("Conexão foi fechada!"); /* Log */
        }
        
        return usuarioDTO;
    }
    
    
    private void validarAtualizacao(Usuario/*:O*/ requisicaoFiltrada, Usuario/*:O*/ usuarioArmazenado) {
        
        if(requisicaoFiltrada.getNomeCompleto() != null)
            usuarioArmazenado.setNomeCompleto(requisicaoFiltrada.getNomeCompleto());  
        
        if(requisicaoFiltrada.getBiografia() != null)
            usuarioArmazenado.setBiografia(requisicaoFiltrada.getBiografia());  
        
        if(requisicaoFiltrada.getCidade() != null)
            usuarioArmazenado.setCidade(requisicaoFiltrada.getCidade());  
        
        if(requisicaoFiltrada.getEstado() != null)
            usuarioArmazenado.setCidade(requisicaoFiltrada.getCidade());  
        
        if(requisicaoFiltrada.getEmail() != null)
            usuarioArmazenado.setEmail(requisicaoFiltrada.getEmail());
        
        if(requisicaoFiltrada.getImagemPerfil() != null)
            usuarioArmazenado.setImagemPerfil(requisicaoFiltrada.getImagemPerfil());
        
        if(requisicaoFiltrada.getPontos() != -1)
            usuarioArmazenado.setPontos(requisicaoFiltrada.getPontos());
        
        if(requisicaoFiltrada.getNivel() != -1)
            usuarioArmazenado.setNivel(requisicaoFiltrada.getNivel());
        
        if(requisicaoFiltrada.getSexo() == 'F' || requisicaoFiltrada.getSexo() == 'M')
            usuarioArmazenado.setSexo(requisicaoFiltrada.getSexo());
    }
}
