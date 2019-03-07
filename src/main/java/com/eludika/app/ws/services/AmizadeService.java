package com.eludika.app.ws.services;

import com.eludika.app.ws.shared.dto.AmizadeDTO;
import java.io.Serializable;

/**
 * Esta interface define os métodos que executam os serviços relacionados a
 * Entidade 'Amizade'
 * 
 * @author eres
 */
public interface AmizadeService {

    /**
     * Este método executa o serviço de requisição de amizade entre dois usuários
     *
     * @param amizadeDTO DTO com os dados da requisição
     */
    public void requisitarAmizade(AmizadeDTO amizadeDTO);
}
