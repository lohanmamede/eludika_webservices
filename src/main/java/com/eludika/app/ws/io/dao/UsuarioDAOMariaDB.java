package com.eludika.app.ws.io.dao; // :D

import com.eludika.app.ws.io.entidades.Usuario;
import com.eludika.app.ws.utils.HibernateUtils;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.BeanUtils;


/**
 * Esta classe é a implementação da interface DAO da respectiva interface para o
 * banco de dados MariaDB/MYSQL
 *
 * @author eres
 */
public class UsuarioDAOMariaDB implements UsuarioDAO {

    // ------------------------------------------------------------------ Campos
    private Session sessao;
    

    // ----------------------------------------------------------------- Métodos
    /**
     * Este método é usado para criar uma sessão a partir da fábrica de sessões.
     * Através dessa sessão efetuamos operações de persistência.
     */
    @Override
    public void abrirConexao() {

        SessionFactory fabricaDeSessoes = HibernateUtils.getFabricaDeSessoes();
        this.sessao = fabricaDeSessoes.openSession();
    }

    /**
     * Este método é usado para fechar uma sessão, após as operações necessárias
     * serem realizadas.
     */
    @Override
    public void fecharConexao() {

        if (this.sessao != null) {

            this.sessao.close();
        }
    }

    /**
     * Este método é usado para obter o usuário pelo idExterno
     *
     * @param idExterno valor usado na consulta
     * @return DTO com as informações do usuário que possua o idExterno
     * informado
     * @throws NoResultException caso não for encontrado nenhum usuário com o idExterno
     */
    @Override
    public Usuario/*:O*/ obterUsuario(String idExterno) throws NoResultException {

        this.sessao.beginTransaction();

        TypedQuery<Usuario> consulta = this.sessao
                .getNamedQuery("UsuarioEntidade.buscarPorIdExterno");
        
        consulta.setParameter("idExterno", idExterno);

        /* Aqui pode ocorrer a exceção NoResultException, tratada no método 
        obterUsuario da classe UsuarioServiceImplementation */
        Usuario usuarioEntidade = consulta.getSingleResult();

        /* Se for encontrado algum resultado, logo não haverá a exceção acima 
        e o método commita e continua sua execução normalmente */
        this.sessao.getTransaction().commit();  
        
        Usuario/*:O*/ usuarioDTO = new Usuario/*:O*/();
        BeanUtils.copyProperties(usuarioEntidade, usuarioDTO);
        
        return usuarioDTO;
    }
    
    @Override
    public List<Usuario/*:O*/> obterUsuarios(String nome, int inicio, int limite) {

        CriteriaBuilder cb = this.sessao.getCriteriaBuilder();

        /* Criar Criteria a partir de uma determinada classe persistente */
        CriteriaQuery<Usuario> criteria = cb.createQuery(Usuario.class);

        /* Raízes da consulta sempre referenciam entidades */
        Root<Usuario> usuarioRoot = criteria.from(Usuario.class);
        
        criteria.select(usuarioRoot).where(
                cb.like(usuarioRoot.get("nomeCompleto").as(String.class), "%" + nome + "%"));

        /* Buscar resultados do início até um limite */
        List<Usuario> resultados = this.sessao.createQuery(criteria).
                setFirstResult(inicio).
                setMaxResults(limite).
                getResultList();
 
        List<Usuario/*:O*/> valorDeRetorno = new ArrayList<Usuario/*:O*/>();
        
        for (Usuario usuarioEntidade : resultados) {
            
            Usuario/*:O*/ usuarioDTO = new Usuario/*:O*/();
            BeanUtils.copyProperties(usuarioEntidade, usuarioDTO);
            valorDeRetorno.add(usuarioDTO);
        }

        return valorDeRetorno;
    }
    
    /**
     * Este método é usado para obter o usuário pelo email
     *
     * @param email valor usado na consulta
     * @return DTO com as informações do usuário que possua o email
     * informado
     * @throws NoResultException caso não for encontrado nenhum usuário com o email
     */
    @Override
    public Usuario/*:O*/ obterUsuarioPorEmail(String email) throws NoResultException {
        
        this.sessao.beginTransaction();

        TypedQuery<Usuario> consulta = this.sessao
                .getNamedQuery("UsuarioEntidade.buscarPorEmail");
        
        consulta.setParameter("email", email);

        /* Aqui pode ocorrer a exceção NoResultException, tratada no método 
        obterUsuarioPorEmail da classe UsuarioServiceImplementation */
        Usuario usuarioEntidade = consulta.getSingleResult();

        /* Se for encontrado algum resultado, logo não haverá a exceção acima 
        e o método commita e continua sua execução normalmente */
        this.sessao.getTransaction().commit();  
        
        Usuario/*:O*/ usuarioDTO = new Usuario/*:O*/();
        BeanUtils.copyProperties(usuarioEntidade, usuarioDTO);
        
        return usuarioDTO;
    }

    /**
     * Este método é usado para persistir um usuário no banco de dados
     *
     * @param usuarioDTO DTO com os dados necessários para a persistência
     */
    @Override
    public void salvarUsuario(Usuario/*:O*/ usuarioDTO) {

        /* Atribuição das informações no DTO a classe de usuario que representa
        uma entidade do banco de dados */
        Usuario usuarioEntidade = new Usuario();
        BeanUtils.copyProperties(usuarioDTO, usuarioEntidade);

        /* Abertura de uma transação no banco de dados para que as operações 
        necessárias sejam feitas e commitadas (tornadas permanente) */
        this.sessao.beginTransaction();
        this.sessao.save(usuarioEntidade);
        this.sessao.getTransaction().commit();
    }
    
    /**
     * Este método é usado para atualizar um usuário no banco de dados
     *
     * @param usuarioDTO DTO com os dados necessários para a persistência
     */
    @Override
    public void atualizarUsuario(Usuario/*:O*/ usuarioDTO) {

        /* Atribuição das informações no DTO a classe de usuario que representa
        uma entidade do banco de dados */
        Usuario usuarioEntidade = new Usuario();
        BeanUtils.copyProperties(usuarioDTO, usuarioEntidade);

        /* Abertura de uma transação no banco de dados para que as operações 
        necessárias sejam feitas e commitadas (tornadas permanente) */
        this.sessao.beginTransaction();
        this.sessao.merge(usuarioEntidade);
        this.sessao.getTransaction().commit();
    }
}

/* 
    // Atualização de parâmetros específicos com CriteriaBuilder
    @Override
    public void atualizarToken(Usuario usuarioDTO) {

        // Atribuição das informações no DTO a classe de usuario que representa
        // uma entidade do banco de dados
        Usuario usuarioEntidade = new Usuario();
        BeanUtils.copyProperties(usuarioDTO, usuarioEntidade);
     
        // Abertura de uma transação no banco de dados para que as operações 
        //necessárias sejam feitas e commitadas (tornadas permanente)
        sessao.beginTransaction();
        
        CriteriaBuilder builder = sessao.getCriteriaBuilder();
        CriteriaUpdate<UsuarioEntidade> criteria = builder.createCriteriaUpdate(Usuario.class);
        Root<UsuarioEntidade> root = criteria.from(Usuario.class);
        
        criteria.set(root.get("sal"), usuarioEntidade.getSal());
        criteria.set(root.get("senhaCriptografada"), usuarioEntidade.getSenhaCriptografada());
        criteria.set(root.get("token"), usuarioEntidade.getToken());
        
        criteria.where(builder.equal(root.get("idDb"), usuarioEntidade.getIdDb()));
        sessao.createQuery(criteria).executeUpdate();
        
        sessao.getTransaction().commit();
    }


    @Override
    public List<UsuarioDTO> obterUsuarios(int inicio, int limite) {

        CriteriaBuilder cb = this.sessao.getCriteriaBuilder();

        // Criar Criteria a partir de uma determinada classe persistente
        CriteriaQuery<UsuarioEntidade> criteria = cb.createQuery(Usuario.class);

        // Raízes da consulta sempre referenciam entidades
        Root<UsuarioEntidade> usuarioRoot = criteria.from(Usuario.class);
        criteria.select(usuarioRoot);

        // Buscar resultados do início até um limite
        List<UsuarioEntidade> resultados = this.sessao.createQuery(criteria).
                setFirstResult(inicio).
                setMaxResults(limite).
                getResultList();
 
        List<UsuarioDTO> valorDeRetorno = new ArrayList<UsuarioDTO>();
        
        for (Usuario usuarioEntidade : resultados) {
            
            Usuario usuarioDTO = new Usuario();
            BeanUtils.copyProperties(usuarioEntidade, usuarioDTO);
            valorDeRetorno.add(usuarioDTO);
        }

        return valorDeRetorno;
    }

*/
