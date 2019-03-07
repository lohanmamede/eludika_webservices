package com.eludika.app.ws.io.dao;

import com.eludika.app.ws.io.entidades.AmizadeEntidade;
import com.eludika.app.ws.io.entidades.Usuario;
import com.eludika.app.ws.shared.dto.AmizadeDTO;
import com.eludika.app.ws.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.BeanUtils;


/**
 * Esta classe é a implementação da interface DAO da respectiva interface para o
 * banco de dados MariaDB/MYSQL
 *
 * @author eres
 */
public class AmizadeDAOMariaDB implements AmizadeDAO {

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
     * Este método é usado para persistir a entidade 'Amizade' no banco de dados
     *
     * @param amizadeDTO DTO com os dados necessários para a persistência
     */
    @Override
    public void requisitarAmizade(AmizadeDTO amizadeDTO) {

        /* Atribuição dos dados no Data Transfer Object a classe representante
        da respectiva entidade no banco de dados */
        AmizadeEntidade amizadeEntidade = new AmizadeEntidade();
        
        amizadeEntidade.setSolicitante(new Usuario());
        BeanUtils.copyProperties(amizadeDTO.getSolicitante(), amizadeEntidade.getSolicitante());
        
        amizadeEntidade.setSolicitado(new Usuario());
        BeanUtils.copyProperties(amizadeDTO.getSolicitado(), amizadeEntidade.getSolicitado());
       
        /* Abertura de uma transação no banco de dados para que as operações 
        necessárias sejam feitas e commitadas (tornadas permanente) */
        this.sessao.beginTransaction();
        this.sessao.save(amizadeEntidade);
        this.sessao.getTransaction().commit();
    }
}