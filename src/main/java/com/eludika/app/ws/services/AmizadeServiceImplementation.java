package com.eludika.app.ws.services;

import com.eludika.app.ws.io.dao.AmizadeDAO;
import com.eludika.app.ws.io.dao.AmizadeDAOMariaDB;
import com.eludika.app.ws.shared.dto.AmizadeDTO;

/**
 * Esta interface implementa os métodos que executam os serviços relacionados a
 * Entidade 'Amizade'
 * 
 * @author eres
 */
public class AmizadeServiceImplementation implements AmizadeService {

    // ------------------------------------------------------------------ Campos
    AmizadeDAO amizadeDAO;
    
    
    // ------------------------------------------------------------ Construtores
    public AmizadeServiceImplementation() {

        this.amizadeDAO = new AmizadeDAOMariaDB();
    }
    

    // -------------------------------------------------------- Métodos Override
     /**
     * Este método executa o serviço de requisição de amizade entre dois usuários
     *
     * @param amizadeDTO DTO com os dados da requisição
     */
    @Override
    public void requisitarAmizade(AmizadeDTO amizadeDTO) {
        
        try {
            
            this.amizadeDAO.abrirConexao();
            this.amizadeDAO.requisitarAmizade(amizadeDTO);
        }
        finally {
            
            this.amizadeDAO.fecharConexao();
        }  
    }
}
