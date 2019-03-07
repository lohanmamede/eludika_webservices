package com.eludika.app.ws.io.dao;

import com.eludika.app.ws.shared.dto.AmizadeDTO;

/**
 * Esta interface define os métodos que abstraem o acesso aos dados da 
 * respectiva entidade
 *
 * @author eres
 */
public interface AmizadeDAO {
    
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
     * Este método é usado para persistir a entidade 'Amizade' no banco de dados
     *
     * @param amizadeDTO DTO com os dados necessários para a persistência
     */
    public void requisitarAmizade(AmizadeDTO amizadeDTO);
}
