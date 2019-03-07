package com.eludika.app.ws.services;

import com.eludika.app.ws.shared.dto.JogoDTO;
import java.util.List;

/**
 * Esta interface define os métodos que executam os serviços relacionados a
 * respectiva entidade
 * 
 * @author eres
 */
public interface JogoService {

    /**
     * Este método executa o serviço de criação da respectiva entidade
     *
     * @param jogoDTO data acess object com os dados da requisição
     */
    public void salvarJogo(JogoDTO jogoDTO);
    
    public JogoDTO obterJogo(String idExterno);
    
    public List<JogoDTO> obterJogos(String nome, int inicio, int tamanhoMaximo);
}
