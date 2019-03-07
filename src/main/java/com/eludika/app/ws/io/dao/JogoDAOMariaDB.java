package com.eludika.app.ws.io.dao;

import com.eludika.app.ws.io.entidades.JogoEntidade;
import com.eludika.app.ws.shared.dto.JogoDTO;
import com.eludika.app.ws.utils.HibernateUtils;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.BeanUtils;


public class JogoDAOMariaDB implements JogoDAO {

    // ------------------------------------------------------------------ Campos
    private Session sessao;
    

    // ----------------------------------------------------------------- Métodos
    @Override
    public void abrirConexao() {

        SessionFactory fabricaDeSessoes = HibernateUtils.getFabricaDeSessoes();
        this.sessao = fabricaDeSessoes.openSession();
    }

    @Override
    public void fecharConexao() {

        if (this.sessao != null) {

            this.sessao.close();
        }
    }
    
    @Override
    public void salvarJogo(JogoDTO jogoDTO) {

        /* Atribuição dos dados no DTO ao respectivo modelo de entidade do banco de dados */
        JogoEntidade jogoEntidade = new JogoEntidade();
        BeanUtils.copyProperties(jogoDTO, jogoEntidade);

        /* Abertura de uma transação no banco de dados para que as operações 
        necessárias sejam feitas e commitadas (tornadas permanente) */
        this.sessao.beginTransaction();
        this.sessao.save(jogoEntidade);
        this.sessao.getTransaction().commit();
    }
    
    
    @Override
    public JogoDTO obterJogo(String idExterno) throws NoResultException {

        this.sessao.beginTransaction();

        TypedQuery<JogoEntidade> consulta;
        consulta = this.sessao.getNamedQuery("JogoEntidade.buscarPorIdExterno");
        consulta.setParameter("idExterno", idExterno);

        /* Aqui pode ocorrer a exceção NoResultException, tratada no método 
        obterUsuario da classe UsuarioServiceImplementation */
        JogoEntidade jogoEntidade = consulta.getSingleResult();

        /* Se for encontrado algum resultado, logo não haverá a exceção acima 
        e o método commita e continua sua execução normalmente */
        this.sessao.getTransaction().commit();  
        
        JogoDTO jogoDTO = new JogoDTO();
        BeanUtils.copyProperties(jogoEntidade, jogoDTO);
        
        return jogoDTO;
    }
    
    @Override
    public List<JogoDTO> obterJogos(String nome, int inicio, int maximo) {

        CriteriaBuilder cb = this.sessao.getCriteriaBuilder();

        /* Criar Criteria a partir de uma determinada classe persistente */
        CriteriaQuery<JogoEntidade> criteria = cb.createQuery(JogoEntidade.class);

        /* Raízes da consulta sempre referenciam entidades */
        Root<JogoEntidade> jogoRoot = criteria.from(JogoEntidade.class);
        
        Predicate filtro = cb.like(jogoRoot.get("nome").as(String.class), "%" + nome + "%");
        
        criteria.select(jogoRoot).where(filtro);

        /* Buscar resultados do início até um limite */
        List<JogoEntidade> resultados = this.sessao.createQuery(criteria).
                setFirstResult(inicio).
                setMaxResults(maximo).
                getResultList();
 
        List<JogoDTO> valorDeRetorno = new ArrayList<JogoDTO>();
        
        for (JogoEntidade jogoEntidade : resultados) {
            
            JogoDTO jogoDTO = new JogoDTO();
            BeanUtils.copyProperties(jogoEntidade, jogoDTO);
            valorDeRetorno.add(jogoDTO);
        }

        return valorDeRetorno;
    }
}