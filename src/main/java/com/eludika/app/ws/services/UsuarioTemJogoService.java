package com.eludika.app.ws.services;

import com.eludika.app.ws.shared.dto.UsuarioTemJogoDTO;
import java.util.List;

/**
 * Esta interface define os métodos que executam os serviços relacionados a
 * Entidade 'Amizade'
 * 
 * @author eres
 */
public interface UsuarioTemJogoService {

    public void salvarJogoNaColecao(UsuarioTemJogoDTO usuarioTemJogoDTO);
    
    public void atualizarJogoNaColecao(UsuarioTemJogoDTO usuarioTemJogoDTO);
    
    public UsuarioTemJogoDTO obterJogoDoUsuario(String idExternoUsuario, String idExternoJogo);
    
    public List<UsuarioTemJogoDTO> obterJogosDoUsuario(String idExterno, int inicio, int limite);

    public void deletarJogoDoUsuario(String idExternoUsuario, String idExternoJogo);
}
