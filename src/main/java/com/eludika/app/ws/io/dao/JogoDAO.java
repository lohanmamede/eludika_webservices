package com.eludika.app.ws.io.dao;

import com.eludika.app.ws.shared.dto.JogoDTO;
import java.util.List;
import javax.persistence.NoResultException;

/**
 * Esta interface define os métodos que abstraem o acesso aos dados da 
 * respectiva entidade
 *
 * @author eres
 */
public interface JogoDAO {
    
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
    
    /**
     * Este método é usado para inserir a respectiva entidade no banco de dados
     *
     * @param jogoDTO data acess object com as informações necessárias
     */
    public void salvarJogo(JogoDTO jogoDTO);
    
    public JogoDTO obterJogo(String idExterno) throws NoResultException;
    
    public List<JogoDTO> obterJogos(String nome, int inicio, int limite);
}
