package com.eludika.app.ws.services;

import com.eludika.app.ws.exceptions.AutenticacaoException;
import com.eludika.app.ws.io.entidades.Usuario;

/**
 * Esta classe ...
 * 
 * @author eres
 */
public interface AutenticacaoService {
    
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
    Usuario/*:O*/ autenticar(String email, String senha) throws AutenticacaoException;
    
    
    String emitirTokenDeAcesso(Usuario /*:O*/ perfilDeUsuario) throws AutenticacaoException;
    
    
    public void redefinirCredenciaisDeSeguranca(String senha, Usuario/*:O*/ perfilDeUsuario);
}
