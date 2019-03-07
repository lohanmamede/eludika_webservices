package com.eludika.app.ws.io.dao;

import com.eludika.app.ws.io.entidades.JogoEntidade;
import com.eludika.app.ws.io.entidades.Usuario;
import com.eludika.app.ws.io.entidades.UsuarioTemJogoEntidade;
import com.eludika.app.ws.shared.dto.JogoDTO;
import com.eludika.app.ws.shared.dto.UsuarioTemJogoDTO;
import com.eludika.app.ws.utils.HibernateUtils;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.BeanUtils;


/**
 * Esta classe é a implementação da interface DAO da respectiva interface para o
 * banco de dados MariaDB/MYSQL
 *
 * @author eres
 */
public class UsuarioTemJogoDAOMariaDB implements UsuarioTemJogoDAO {

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
    
    @Override
    public void salvarJogoNaColecao(UsuarioTemJogoDTO usuarioTemJogoDTO) {

        /* Atribuição dos dados no Data Transfer Object a classe representante
        da respectiva entidade no banco de dados */
        UsuarioTemJogoEntidade usuarioTemJogoEntidade = new UsuarioTemJogoEntidade();
        
        usuarioTemJogoEntidade.setUsuario(new Usuario());
        BeanUtils.copyProperties(usuarioTemJogoDTO.getUsuario(), usuarioTemJogoEntidade.getUsuario());
        
        usuarioTemJogoEntidade.setJogo(new JogoEntidade());
        BeanUtils.copyProperties(usuarioTemJogoDTO.getJogo(), usuarioTemJogoEntidade.getJogo());
       
        /* Abertura de uma transação no banco de dados para que as operações 
        necessárias sejam feitas e commitadas (tornadas permanente) */
        this.sessao.beginTransaction();
        this.sessao.save(usuarioTemJogoEntidade);
        this.sessao.getTransaction().commit();
    }
    
    @Override
    public void atualizarJogoNaColecao(UsuarioTemJogoDTO usuarioTemJogoDTO) {

        UsuarioTemJogoEntidade usuarioTemJogoEntidade = new UsuarioTemJogoEntidade();
        BeanUtils.copyProperties(usuarioTemJogoDTO, usuarioTemJogoEntidade);

        /* Abertura de uma transação no banco de dados para que as operações 
        necessárias sejam feitas e commitadas (tornadas permanente) */
        this.sessao.beginTransaction();
        this.sessao.update(usuarioTemJogoEntidade);
        this.sessao.getTransaction().commit();
    }

    /**
     * Este método é usado para obter informações de um jogo de um usuário
     *
     * @param idExternoUsuario id externo do usuário
     * @param idExternoJogo  id externo do jogo
     * @return Data Acess Object com as informações do jogo do usuário
     * @throws NoResultException caso não for encontrado nenhum resultado
     */
    @Override
    public UsuarioTemJogoDTO obterJogoDoUsuario(String idExternoUsuario, 
            String idExternoJogo) throws NoResultException {

        /* Inicio da transação, definição e execução da consulta, e encerramento */
        this.sessao.beginTransaction();

        TypedQuery<UsuarioTemJogoEntidade> consulta = this.sessao
                .getNamedQuery("UsuarioTemJogoEntidade.buscarJogoDoUsuario");
        
        consulta.setParameter("idExternoUsuario", idExternoUsuario);
        consulta.setParameter("idExternoJogo", idExternoJogo);

        /* Aqui pode ocorrer a exceção NoResultException */
        UsuarioTemJogoEntidade usuarioTemJogoEntidade = consulta.getSingleResult();

        /* Se for encontrado algum resultado, logo não haverá a exceção acima 
        e o método commita e continua sua execução normalmente */
        this.sessao.getTransaction().commit();  
        
        /* Transfere os dados a um Data Acess Object da respectiva classe e retorna */
        UsuarioTemJogoDTO usuarioTemJogoDTO = new UsuarioTemJogoDTO();
        BeanUtils.copyProperties(usuarioTemJogoEntidade, usuarioTemJogoDTO);
        
        return usuarioTemJogoDTO;
    }
    
    @Override
    public List<UsuarioTemJogoDTO> obterJogosDoUsuario(String idExterno, int inicio, int limite) {
        
        /* Inicio da transação, definição e execução da consulta, e encerramento */
        this.sessao.beginTransaction();

        TypedQuery<UsuarioTemJogoEntidade> consulta = this.sessao
                .getNamedQuery("UsuarioTemJogoEntidade.buscarJogosDoUsuario");
        
        consulta.setParameter("idExterno", idExterno);

        /* Buscar resultados do início até um limite */
        List<UsuarioTemJogoEntidade> resultados;
        resultados = consulta.setFirstResult(inicio).setMaxResults(limite).getResultList();

        List<UsuarioTemJogoDTO> valorDeRetorno = new ArrayList<UsuarioTemJogoDTO>();
        
        for (UsuarioTemJogoEntidade usuarioTemJogoEntidade : resultados) {
            
            UsuarioTemJogoDTO usuarioTemJogoDTO = new UsuarioTemJogoDTO();
            BeanUtils.copyProperties(usuarioTemJogoEntidade, usuarioTemJogoDTO);
            
            usuarioTemJogoDTO.setUsuario(new Usuario/*:O*/());
            
            BeanUtils.copyProperties(usuarioTemJogoEntidade.getUsuario(), usuarioTemJogoDTO.getUsuario());
            
            usuarioTemJogoDTO.setJogo(new JogoDTO());
            BeanUtils.copyProperties(usuarioTemJogoEntidade.getJogo(), usuarioTemJogoDTO.getJogo());
            
            valorDeRetorno.add(usuarioTemJogoDTO);
        }

        return valorDeRetorno;
    }
    
    @Override
    public void deletarJogoDoUsuario(String idExternoUsuario, 
            String idExternoJogo) {
        
        /* Inicio da transação, definição e execução da consulta, e encerramento */
        this.sessao.beginTransaction();
//        UsuarioTemJogoDTO jogoDoUsuarioArmazenado;
//        jogoDoUsuarioArmazenado = this.obterJogoDoUsuario(idExternoUsuario, idExternoJogo);

        TypedQuery<UsuarioTemJogoEntidade> consulta = this.sessao
                .getNamedQuery("UsuarioTemJogoEntidade.buscarJogoDoUsuario");
        
        consulta.setParameter("idExternoUsuario", idExternoUsuario);
        consulta.setParameter("idExternoJogo", idExternoJogo);

        /* Aqui pode ocorrer a exceção NoResultException */
        UsuarioTemJogoEntidade usuarioTemJogoEntidade = consulta.getSingleResult();
        
        this.sessao.delete(usuarioTemJogoEntidade);
        
        /* Se for encontrado algum resultado, logo não haverá a exceção acima 
        e o método commita e continua sua execução normalmente */
        this.sessao.getTransaction().commit();  
    }
}