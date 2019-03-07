package com.eludika.app.ws.services;

import com.eludika.app.ws.exceptions.AutenticacaoException;
import com.eludika.app.ws.io.dao.UsuarioDAO;
import com.eludika.app.ws.io.dao.UsuarioDAOMariaDB;
import com.eludika.app.ws.io.entidades.Usuario;
import com.eludika.app.ws.ui.models.response.MensagensDeErro;
import com.eludika.app.ws.utils.EludikaUtils;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Esta classe ...
 * 
 * @author eres
 */
public class AutenticacaoServiceImplementation implements AutenticacaoService {

    UsuarioDAO database;

    /**
     * Este método tenta realizar a autenticação de um usuário com os dados
     * de login informados
     * 
     * @param email informação necessária para autenticação
     * @param senha informação necessária para autenticação
     * @return informações do usuário auntenticado
     * @throws AutenticacaoException caso o email não seja encontrado ou o email 
     * e senha informados não correspondam com os salvos no banco de dados
     */
    @Override
    public Usuario/*:O*/ autenticar(String email, String senha) 
            throws AutenticacaoException {
        
        UsuarioService usuarioService = new UsuarioServiceImplementation();
        
        /* Busca por um usuário com o email informado e traz suas informações */
        Usuario/*:O*/ usuarioSalvo = usuarioService.obterUsuarioPorEmail(email); 
        
        /* Checagem se o email informado está cadastrado no banco de dados */
        if (usuarioSalvo == null) {
            
            throw new AutenticacaoException(
                    MensagensDeErro.AUTENTICACAO_FALHOU.getMensagemDeErro());
        }
        
        /* Criptografia da senha informada */
        String senhaCriptograda = new EludikaUtils().gerarSenhaSegura(
                senha, usuarioSalvo.getSal());

        /* Definição do usuário como não autenticado até que a comparação do
        email e da senha provem o contrario */
        boolean autenticado = false;

        /* Comparação da senha criptografada acima com a gravada no banco de dados */
        if (senhaCriptograda != null && 
                senhaCriptograda.equalsIgnoreCase(usuarioSalvo.getSenhaCriptografada())) {
            
            /* Comparação do email informado com o gravado no banco de dados */
            if (email != null && email.equalsIgnoreCase(usuarioSalvo.getEmail())) {
                
                /* Autenticação do usuário, se as comparações forem bem sucedidas */
                autenticado = true;
            }
        }
        
        /* Lançamento de exceção caso a autenticação não for bem-sucedida */
        if (!autenticado) {
            
            throw new AutenticacaoException(MensagensDeErro
                    .AUTENTICACAO_FALHOU.getMensagemDeErro());
        }

        return usuarioSalvo;
    }

    /**
     * Este método ...
     * 
     * @param usuarioDTO
     * @return
     * @throws AutenticacaoException
     */
    @Override
    public String emitirTokenDeAcesso(Usuario/*:O*/ usuarioDTO) throws AutenticacaoException {
        
        String novoSalPosfixado = usuarioDTO.getSal();
        String tokenMaterial = usuarioDTO.getIdExterno() + novoSalPosfixado;

        byte[] tokenCriptografado = null;
        
        try {
            
            tokenCriptografado = new EludikaUtils().criptografar(
                    usuarioDTO.getSenhaCriptografada(), tokenMaterial);
        } 
        catch (InvalidKeySpecException ex) {
            
            Logger.getLogger(AutenticacaoServiceImplementation.class.getName())
                    .log(Level.SEVERE, null, ex);
            
            throw new AutenticacaoException("Faled to issue secure access token");
        }

        String tokenCriptografadoCodificacaoBase64 = Base64.getEncoder()
                .encodeToString(tokenCriptografado);

        /* Divisão do token em partes iguais */
        int tokenComprimento = tokenCriptografadoCodificacaoBase64.length();

        String tokenParaOBancoDeDados = tokenCriptografadoCodificacaoBase64
                .substring(0, tokenComprimento / 2);
        
        String tokenRetorno = tokenCriptografadoCodificacaoBase64
                .substring(tokenComprimento / 2, tokenComprimento);

        /* Armazenamento de parte do token no banco de dados */
        usuarioDTO.setToken(tokenParaOBancoDeDados);
        this.atualizarUsuario(usuarioDTO);

        /* Retorno de parte do token para o método chamador */
        return tokenRetorno;
    }

    /**
     * Este método ...
     * 
     * @param senha
     * @param usuarioDTO
     */
    @Override
    public void redefinirCredenciaisDeSeguranca(String senha, Usuario/*:O*/ usuarioDTO) {
        
        /* Geração de um novo sal */
        EludikaUtils usuarioUtils = new EludikaUtils();
        String sal = usuarioUtils.gerarSal(30);
        
        /* Geração de uma nova senha */
        String senhaSegura = usuarioUtils.gerarSenhaSegura(senha, sal);
        usuarioDTO.setSal(sal);
        usuarioDTO.setSenhaCriptografada(senhaSegura);
        
        /* Atualização do perfil do usuário */
        this.atualizarUsuario(usuarioDTO);
    }
    
    // ---------------------------------- Métodos auxiliares internos (privados)
    /**
     * Este método ...
     * 
     * @param usuarioDTO
     */
    private void atualizarUsuario(Usuario/*:O*/ usuarioDTO) {
        
        this.database = new UsuarioDAOMariaDB();
        
        try {
            
            database.abrirConexao();
            database.atualizarUsuario(usuarioDTO);
        } 
        finally {
            
            database.fecharConexao();
        }
    }
}
