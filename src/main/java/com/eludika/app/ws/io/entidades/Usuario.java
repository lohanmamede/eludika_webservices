package com.eludika.app.ws.io.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import static javax.persistence.TemporalType.DATE;
import static javax.persistence.TemporalType.TIMESTAMP;
import javax.persistence.Transient;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

@NamedQueries({
    @NamedQuery(name = "Usuario.listar", 
            query = "SELECT u FROM Usuario u")
    , @NamedQuery(name = "Usuario.buscarPorIdExterno", 
            query = "SELECT u FROM Usuario u WHERE u.idExterno = :idExterno")
    , @NamedQuery(name = "Usuario.buscarPorEmail",
            query = "SELECT u FROM Usuario u WHERE u.email = :email")})
@Data
@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

    // ------------------------------------------------------------------ Campos
    private static final long serialVersionUID = -5306089421121936344L;
    
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_db")
    private long idDb;
    
    @Column(name = "id_externo", length = 40,
            nullable = false, insertable = true, updatable = false, unique = true)
    private String idExterno;
    
    @Column(name = "tipo", 
            nullable = false, insertable = true, updatable = false, unique = false)
    private char tipo;
    
    @Column(name = "codinome", length = 15,
            nullable = false, insertable = true, updatable = false, unique = true)
    private String codinome;
    
    @Column(name = "nome_completo", length = 50,
            nullable = false, insertable = true, updatable = true, unique = false)
    private String nomeCompleto;
    
    @Column(name = "email", length = 50,
            nullable = false, insertable = true, updatable = true, unique = true)
    private String email;
    
    @Transient
    private String senha;
    
    @Column(name = "senha_criptografada", length = 50,
            nullable = false, insertable = true, updatable = true, unique = false)
    private String senhaCriptografada;
    
    @Column(name = "sexo", 
            nullable = true, insertable = false, updatable = true, unique = false)
    private char sexo;
    
    @Column(name = "biografia", length = 150,
            nullable = true, insertable = true, updatable = true, unique = false)
    private String biografia;
    
    @Temporal(TIMESTAMP)
    @ColumnDefault("NOW()")
    @Column(name = "data_cadastro", 
            nullable = false, insertable = false, updatable = false, unique = false)
    private Date dataCadastro;
    
    @Temporal(DATE)
    @Column(name = "data_nascimento", 
            nullable = false, insertable = true, updatable = true, unique = false)
    private Date dataNascimento;
    
    @Column(name = "cidade", length = 50,
            nullable = true, insertable = true, updatable = true, unique = false)
    private String cidade;
    
    @Column(name = "estado", length = 50,
            nullable = true, insertable = true, updatable = true, unique = false)
    private String estado;
    
    @ColumnDefault("0")
    @Column(name = "pontos", 
            nullable = false, insertable = false, updatable = true, unique = false)
    private int pontos;
    
    @ColumnDefault("0")
    @Column(name = "nivel", 
            nullable = false, insertable = false, updatable = true, unique = false)
    private int nivel;
    
    @ColumnDefault("0")
    @Column(name = "moedas", 
            nullable = false, insertable = false, updatable = true, unique = false)
    private int moedas;
    
    @ColumnDefault("0")
    @Column(name = "imagem_perfil", length = 200,
            nullable = false, insertable = false, updatable = true, unique = false)
    private String imagemPerfil;
    
    @Column(name = "sal", length = 40,
            nullable = false, insertable = true, updatable = true, unique = false)
    private String sal;
    
    @Column(name = "token", length = 30,
            nullable = true, insertable = true, updatable = true, unique = false)
    private String token;
    
    
    // -------------------------------------------------------------- Construtor
    public Usuario() {
        
        this.idDb = -1;       
        this.pontos = -1;
        this.nivel = -1;
        this.moedas = -1;
        this.tipo = '@';
        this.sexo = '@';
        this.idExterno = null;
        this.nomeCompleto = null;
        this.codinome = null;
        this.email = null;
        this.senha = null;
        this.senhaCriptografada = null;
        this.biografia = null;
        this.dataCadastro = null;
        this.dataNascimento = null;
        this.cidade = null;
        this.estado = null;
        this.imagemPerfil = null;
        this.sal = null;
        this.token = null;
    }
}
