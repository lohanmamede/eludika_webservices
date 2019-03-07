package com.eludika.app.ws.io.entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.hibernate.annotations.ColumnDefault;

/**
 * Esta classe representa o modelo de dados da respectiva entidade com atributos 
 * a serem salvos no banco de dados
 *
 * @author eres
 */
@NamedQueries({
    @NamedQuery(name = "UsuarioTemJogoEntidade.buscarJogoDoUsuario", 
            query = "SELECT usuarioTemJogo FROM UsuarioTemJogoEntidade usuarioTemJogo "
                    + "WHERE usuarioTemJogo.usuario.idExterno = :idExternoUsuario "
                    + "AND usuarioTemJogo.jogo.idExterno = :idExternoJogo"),
    @NamedQuery(name = "UsuarioTemJogoEntidade.buscarJogosDoUsuario", 
            query = "SELECT usuarioTemJogo FROM UsuarioTemJogoEntidade usuarioTemJogo "
                    + "WHERE usuarioTemJogo.usuario.idExterno = :idExterno")})
@Entity
@Table(name = "usuario_tem_jogo")
public class UsuarioTemJogoEntidade implements Serializable {

    // ------------------------------------------------------------------ Campos
    private static final long serialVersionUID = -6495189190294488651L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_db")
    private long idDb;

    @JoinColumn(name = "id_usuario", nullable = false, insertable = true, 
            updatable = false, unique = false, referencedColumnName = "id_db")
    @ManyToOne
    private Usuario usuario;
     
    @JoinColumn(name = "id_jogo", nullable = false, insertable = true, 
            updatable = false, unique = false, referencedColumnName = "id_db")
    @ManyToOne
    private JogoEntidade jogo;
    
    @ColumnDefault("0") /* Somente Hibernate */
    @Column(name = "pontos", nullable = false, insertable = false, 
            updatable = true, unique = false)
    private int pontos;

    @ColumnDefault("0") /* Somente Hibernate */
    @Column(name = "niveis_completos", nullable = false, insertable = false, 
            updatable = true, unique = false)
    private int niveisCompletos;
    
    @ColumnDefault("0") /* Somente Hibernate */
    @Column(name = "niveis_total", nullable = false, insertable = false, 
            updatable = true, unique = false)
    private int niveisTotal;

    @ColumnDefault("0") /* Somente Hibernate */
    @Column(name = "moedas", nullable = false, insertable = false, 
            updatable = true, unique = false)
    private int moedas;
    
    @ColumnDefault("0") /* Somente Hibernate */
    @Column(name = "jogo_preferido", nullable = false, insertable = false, 
            updatable = true, unique = false)
    private int jogoPreferido;
    
    
    // ------------------------------------------------------- Getters e Setters

    public long getIdDb() {
        return idDb;
    }

    public void setIdDb(long idDb) {
        this.idDb = idDb;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public JogoEntidade getJogo() {
        return jogo;
    }

    public void setJogo(JogoEntidade jogo) {
        this.jogo = jogo;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public int getNiveisCompletos() {
        return niveisCompletos;
    }

    public void setNiveisCompletos(int niveisCompletos) {
        this.niveisCompletos = niveisCompletos;
    }

    public int getNiveisTotal() {
        return niveisTotal;
    }

    public void setNiveisTotal(int niveisTotal) {
        this.niveisTotal = niveisTotal;
    }

    public int getMoedas() {
        return moedas;
    }

    public void setMoedas(int moedas) {
        this.moedas = moedas;
    }

    public int isJogoPreferido() {
        return jogoPreferido;
    }

    public void setJogoPreferido(int jogoPreferido) {
        this.jogoPreferido = jogoPreferido;
    }
   
}
