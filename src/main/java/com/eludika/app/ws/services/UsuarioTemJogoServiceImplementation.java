package com.eludika.app.ws.services;

import com.eludika.app.ws.exceptions.ImpossivelAtualizarRegistroException;
import com.eludika.app.ws.exceptions.ImpossivelDeletarRegistroException;
import com.eludika.app.ws.exceptions.NenhumRegistroEncontradoException;
import com.eludika.app.ws.io.dao.UsuarioTemJogoDAO;
import com.eludika.app.ws.io.dao.UsuarioTemJogoDAOMariaDB;
import com.eludika.app.ws.shared.dto.UsuarioTemJogoDTO;
import com.eludika.app.ws.ui.models.response.MensagensDeErro;
import static java.lang.System.out;
import java.util.List;
import javax.persistence.NoResultException;

/**
 * Esta interface implementa os métodos que executam os serviços relacionados a
 * Entidade 'Amizade'
 * 
 * @author eres
 */
public class UsuarioTemJogoServiceImplementation implements UsuarioTemJogoService {

    // ------------------------------------------------------------------ Campos
    UsuarioTemJogoDAO usuarioTemJogoDAO;
    
    
    // ------------------------------------------------------------ Construtores
    public UsuarioTemJogoServiceImplementation() {

        this.usuarioTemJogoDAO = new UsuarioTemJogoDAOMariaDB();
    }
    

    // -------------------------------------------------------- Métodos Override
    @Override
    public void salvarJogoNaColecao(UsuarioTemJogoDTO usuarioTemJogoDTO) {
        
        try {
            
            this.usuarioTemJogoDAO.abrirConexao();
            this.usuarioTemJogoDAO.salvarJogoNaColecao(usuarioTemJogoDTO);
        }
        finally {
            
            this.usuarioTemJogoDAO.fecharConexao();
        }  
    }
    
    @Override
    public void atualizarJogoNaColecao(UsuarioTemJogoDTO usuarioTemJogoDTO) {
        
        try {
            
            this.usuarioTemJogoDAO.abrirConexao();
            out.println("Conexão foi aberta!"); /* Log */
            this.usuarioTemJogoDAO.atualizarJogoNaColecao(usuarioTemJogoDTO);

        } 
        catch (Exception ex) {
            
            throw new ImpossivelAtualizarRegistroException(ex.getMessage());
        } 
        finally {
            
            this.usuarioTemJogoDAO.fecharConexao();
            out.println("Conexão foi fechada!"); /* Log */
        }
    }
    
    @Override
    public UsuarioTemJogoDTO obterJogoDoUsuario(String idExternoUsuario, String idExternoJogo) {
        
        UsuarioTemJogoDTO usuarioTemJogoDTO = null;
        
        try {

            this.usuarioTemJogoDAO.abrirConexao();
            usuarioTemJogoDTO = this.usuarioTemJogoDAO.obterJogoDoUsuario(idExternoUsuario, idExternoJogo);
        } 
        catch(NoResultException ex) {
            
            /* Se for lançada a exceção NoResultException, relança a exceção de 
            nenhum registro encontrado */
            throw new NenhumRegistroEncontradoException(MensagensDeErro
                    .NENHUM_REGISTRO_ENCONTRADO.getMensagemDeErro());
        }
        finally {

            this.usuarioTemJogoDAO.fecharConexao();
        }
        
        return usuarioTemJogoDTO;
    }
    
    @Override
    public List<UsuarioTemJogoDTO> obterJogosDoUsuario(String idExterno, int inicio, int limite) {

        List<UsuarioTemJogoDTO> jogosDoUsuario = null;

        /* Obtenção dos usuários do banco de dados */
        try {
            
            this.usuarioTemJogoDAO.abrirConexao();
            jogosDoUsuario = this.usuarioTemJogoDAO.obterJogosDoUsuario(idExterno, inicio, limite);
        } 
        finally {
            
            this.usuarioTemJogoDAO.fecharConexao();
        }

        return jogosDoUsuario;
    }
    
    @Override
    public void deletarJogoDoUsuario(String idExternoUsuario, String idExternoJogo) {
        
        try {
            
            this.usuarioTemJogoDAO.abrirConexao();
            this.usuarioTemJogoDAO.deletarJogoDoUsuario(idExternoUsuario, idExternoJogo);
        } 
        catch (Exception ex) {
            
            throw new ImpossivelDeletarRegistroException(ex.getMessage());
        } 
        finally {
            
            this.usuarioTemJogoDAO.fecharConexao();
        }
    }
}
