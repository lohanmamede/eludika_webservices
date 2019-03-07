package com.eludika.app.ws.io.dao; // :D

import com.eludika.app.ws.io.entidades.Usuario;
import java.util.List;
import javax.persistence.NoResultException;

/**
 * Esta interface define os métodos que abstraem o acesso aos dados da 
 * respectiva entidade
 *
 * @author eres
 */
public interface UsuarioDAO {

    /**
     * Este método é usado para criar uma sessão a partir da fábrica de sessões.
     * Através dessa sessão efetuamos operações de persistência
     */
    public void abrirConexao();
    
    /**
     * Este método é usado para fechar uma sessão, após as operações necessárias
     * serem realizadas.
     */
    public void fecharConexao();

    /**
     * Este método é usado para obter o usuário pelo idExterno
     *
     * @param idExterno valor usado na consulta
     * @return DTO com as informações do usuário que possua o idExterno
     * informado
     * @throws NoResultException caso não for encontrado nenhum usuário com o idExterno
     */
    public Usuario/*:O*/ obterUsuario(String idExterno) throws NoResultException;
    
    /**
     * Este método é usado para obter o usuário pelo email
     *
     * @param email valor usado na consulta
     * @return DTO com as informações do usuário que possua o email
     * informado
     * @throws NoResultException caso não for encontrado nenhum usuário com o email
     */
    public Usuario/*:O*/ obterUsuarioPorEmail(String email) throws NoResultException;
    
    public List<Usuario/*:O*/> obterUsuarios(String nome, int inicio, int limite);

    /**
     * Este método é usado para inserir um novo usuário no banco de dados
     *
     * @param usuarioDTO DTO com os dados necessários para a persistência
     */
    public void salvarUsuario(Usuario/*:O*/ usuarioDTO);
    
    /**
     * Este método é usado para atualizar um usuário no banco de dados
     *
     * @param usuarioDTO DTO com os dados necessários para a persistência
     */
    public void atualizarUsuario(Usuario/*:O*/ usuarioDTO);
}
