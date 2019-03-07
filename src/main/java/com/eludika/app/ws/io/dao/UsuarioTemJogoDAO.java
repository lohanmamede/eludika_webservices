package com.eludika.app.ws.io.dao;

import com.eludika.app.ws.shared.dto.UsuarioTemJogoDTO;
import java.util.List;
import javax.persistence.NoResultException;

/**
 * Esta interface define os métodos que abstraem o acesso aos dados da 
 * respectiva entidade
 *
 * @author eres
 */
public interface UsuarioTemJogoDAO {
    
    /**
     * Este método é usado para criar uma sessão a partir da fábrica de sessões,
     * através dessa sessão efetuamos operações de persistência
     */
    public void abrirConexao();
    
    /**
     * Este método é usado para fechar uma sessão, após as operações necessárias
     * serem realizadas
     */
    public void fecharConexao();

    public void salvarJogoNaColecao(UsuarioTemJogoDTO usuarioTemJogoDTO);
    
    public void atualizarJogoNaColecao(UsuarioTemJogoDTO usuarioTemJogoDTO);
    
    /**
     * Este método é usado para obter informações de um jogo de um usuário
     *
     * @param idExternoUsuario id externo do usuário
     * @param idExternoJogo  id externo do jogo
     * @return Data Acess Object com as informações do jogo do usuário
     * @throws NoResultException caso não for encontrado nenhum resultado
     */
    public UsuarioTemJogoDTO obterJogoDoUsuario(String idExternoUsuario, 
            String idExternoJogo) throws NoResultException;
    
    public List<UsuarioTemJogoDTO> obterJogosDoUsuario(String idExterno, int inicio, int limite);
    
    public void deletarJogoDoUsuario(String idExternoUsuario, String idExternoJogo);
}
