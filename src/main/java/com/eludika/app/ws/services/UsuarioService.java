package com.eludika.app.ws.services; // :D

import com.eludika.app.ws.io.entidades.Usuario;
import java.util.List;

/**
 * Esta interface define métodos que manipulam uma instância de UsuarioUtils e 
 * um DAO de usuário. O primeiro a fim de preparar o usuário, inserindo 
 * criptografia, e fazendo validações por exemplo, antes que as operações entre 
 * a aplicação e o banco de dados possam ser realizadas. O segundo, efetivamente,
 * realizando as operações de persistência e gerenciando a abertura e o 
 * encerramento de conexões.
 * 
 * @author eres
 */
public interface UsuarioService {

    /**
     * Este método se assegura de algumas atividades que devem ser feitas antes
     * que um novo usuário possa ser persistido, como validação e criptografia,
     * e por fim efetivamente persiste o usuário. 
     * OBS: o parâmetro é modificado internamente
     *
     * @param usuario DTO com os dados do novo usuário
     */
    public void criarUsuario(Usuario/*:O*/ usuario);
    
    /**
     * Este método invoca as funções do DAO do usuário para buscar por um 
     * idExterno no banco de dados
     *
     * @param idExterno String com o idExterno do usuário
     * @return DTO com as informações do usuário encontrado, ou nulo
     */
    public Usuario/*:O*/ obterUsuario(String idExterno);
    
    /**
     * Este método invoca as funções do DAO do usuário para trazer um limite
     * de usuários do banco de dados
     *
     * @param nome
     * @param inicio
     * @param limite
     * 
     * @return List com DTOS com as informações do usuário encontrado, ou nulo
     */
    public List<Usuario/*:O*/> obterUsuarios(String nome, int inicio, int limite);
    
    
    public void atualizarUsuario(String idExterno, Usuario/*:O*/ usuarioDTO);
    
    /**
     * Este método invoca as funções do DAO do usuário para buscar por um 
     * email no banco de dados
     *
     * @param email String com o email do usuário
     * @return DTO com as informações do usuário encontrado, ou nulo
     */
    public Usuario/*:O*/ obterUsuarioPorEmail(String email);
}
