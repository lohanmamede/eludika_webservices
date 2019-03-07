package com.eludika.app.ws.io.entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedQueries({@NamedQuery
        (name = "JogoEntidade.buscarPorIdExterno", 
                query = "SELECT jogo FROM JogoEntidade jogo "
                        + "WHERE jogo.idExterno = :idExterno")})
@Entity
@Table(name = "jogos")
public class JogoEntidade implements Serializable {

    // ------------------------------------------------------------------ Campos
    private static final long serialVersionUID = 5258922873844662640L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_db")
    private long idDb;
    
    @Column(name = "id_externo", nullable = false, insertable = true, 
            updatable = false, unique = true, length = 40)
    private String idExterno;
    
    @Column(name = "nome", nullable = false, insertable = true, 
            updatable = true, unique = true, length = 50)
    private String nome;
    
    @Column(name = "descricao", nullable = false, insertable = true, 
            updatable = true, unique = false, length = 5000)
    private String descricao;
    
    @Column(name = "download_url", nullable = false, insertable = true, 
            updatable = true, unique = true, length = 200)
    private String downloadUrl;
    
    @Column(name = "logotipo", nullable = false, insertable = true, 
            updatable = true, unique = true, length = 200)
    private String logotipo;
    
    @Column(name = "oferecido_por", nullable = false, insertable = true, 
            updatable = true, unique = false, length = 50)
    private String oferecidoPor;
    
    @Column(name = "classificacao", nullable = false, insertable = true, 
            updatable = true, unique = false, length = 2)
    private String classificacao;
    
    @Column(name = "tamanho", nullable = false, insertable = true, 
            updatable = true, unique = false)
    private int tamanho;
    
    
    // ------------------------------------------------------- Getters e Setters
    public long getIdDb() {
        
        return idDb;
    }

    public void setIdDb(long idDb) {
        
        this.idDb = idDb;
    }

    public String getIdExterno() {
        
        return idExterno;
    }

    public void setIdExterno(String idExterno) {
        
        this.idExterno = idExterno;
    }

    public String getNome() {
        
        return nome;
    }

    public void setNome(String nome) {
        
        this.nome = nome;
    }

    public String getDescricao() {
        
        return descricao;
    }

    public void setDescricao(String descricao) {
        
        this.descricao = descricao;
    }

    public String getDownloadUrl() {
        
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        
        this.downloadUrl = downloadUrl;
    }

    public String getLogotipo() {
        
        return logotipo;
    }

    public void setLogotipo(String logotipo) {
        
        this.logotipo = logotipo;
    }

    public String getOferecidoPor() {
        
        return oferecidoPor;
    }

    public void setOferecidoPor(String oferecidoPor) {
        
        this.oferecidoPor = oferecidoPor;
    }

    public String getClassificacao() {
        
        return classificacao;
    }

    public void setClassificacao(String classificacao) {
        
        this.classificacao = classificacao;
    }

    public int getTamanho() {
        
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        
        this.tamanho = tamanho;
    }
}
    