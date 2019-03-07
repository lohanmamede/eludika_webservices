package com.eludika.app.ws.io.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import static javax.persistence.TemporalType.TIMESTAMP;
import org.hibernate.annotations.ColumnDefault;

/**
 * Esta classe representa o modelo de dados do relacionamento entre dois usuários
 * com atributos a serem salvos no banco de dados
 *
 * @author eres
 */
@Entity
@Table(name = "amizade")
public class AmizadeEntidade implements Serializable {

    // ------------------------------------------------------------------ Campos
    private static final long serialVersionUID = -1787487984553201398L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_db")
    private long idDb;
    
    @JoinColumn(name = "id_solicitante", nullable = false, insertable = true, 
            updatable = false, unique = false, referencedColumnName = "id_db")
    @ManyToOne
    private Usuario solicitante;
     
    @JoinColumn(name = "id_solicitado", nullable = false, insertable = true, 
            updatable = false, unique = false, referencedColumnName = "id_db")
    @ManyToOne
    private Usuario solicitado;
    
    @Temporal(TIMESTAMP)
    @ColumnDefault("NOW()") 
    @Column(name = "data_solicitacao", nullable = false, insertable = false, 
            updatable = false, unique = false)
    private Date dataSolicitacao;

    @Temporal(TIMESTAMP)
    @Column(name = "data_confirmacao", nullable = true, insertable = false, 
            updatable = true, unique = false)
    private Date dataConfirmacao;

    
    @Column(name = "situacao", nullable = false, insertable = false, 
            updatable = true, unique = false)
    @ColumnDefault("'P'")
    private char situacao;
    
    
    // ------------------------------------------------------- Getters e Setters
    /**
     * @return o idDb
     */
    public long getIdDb() {
        
        return idDb;
    }

    /**
     * @param idDb o idDb a ser definido
     */
    public void setIdDb(long idDb) {
        
        this.idDb = idDb;
    }

    /**
     * @return o solicitante
     */
    public Usuario getSolicitante() {
        
        return solicitante;
    }

    /**
     * @param solicitante o solicitante a ser definido
     */
    public void setSolicitante(Usuario solicitante) {
        
        this.solicitante = solicitante;
    }

    /**
     * @return o solicitado
     */
    public Usuario getSolicitado() {
        
        return solicitado;
    }

    /**
     * @param solicitado o solicitado a ser definido
     */
    public void setSolicitado(Usuario solicitado) {
        
        this.solicitado = solicitado;
    }

    /**
     * @return a dataSolicitacao
     */
    public Date getDataSolicitacao() {
        
        return dataSolicitacao;
    }

    /**
     * @param dataSolicitacao a dataSolicitacao a ser definida
     */
    public void setDataSolicitacao(Date dataSolicitacao) {
        
        this.dataSolicitacao = dataSolicitacao;
    }

    /**
     * @return a dataConfirmacao
     */
    public Date getDataConfirmacao() {
        
        return dataConfirmacao;
    }

    /**
     * @param dataConfirmacao a dataConfirmacao a ser definida
     */
    public void setDataConfirmacao(Date dataConfirmacao) {
        
        this.dataConfirmacao = dataConfirmacao;
    }

    /**
     * @return a situacao
     */
    public char getSituacao() {
        
        return situacao;
    }

    /**
     * @param situacao a situacao a ser definida
     */
    public void setSituacao(char situacao) {
        
        this.situacao = situacao;
    }
}
    