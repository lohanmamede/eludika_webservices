package com.eludika.app.ws.ui.models.response; // :D

import javax.xml.bind.annotation.XmlRootElement;

/** Esta classe representa o modelo de dados que será enviado para a aplicação
 * cliente após a criação ou a atualização de um usuário ou quando solicitado
 * 
 * @author eres
 */
@XmlRootElement /* Permite que a classe possa ser convertida em JSON/XML */
public class UsuarioResponseModel {

    // ------------------------------------------------------------------ Campos
    private char tipo;
    private String codinome;
    private String nomeCompleto;
    private int nivel;
    private int pontos;
    private String biografia;
    private String cidade;
    private String estado;
    private String email;
    private String imagemPerfil;
    private char sexo;
    

    // ------------------------------------------------------- Getters e Setters
    /**
     * @return o tipo
     */
    public char getTipo() {
        
        return tipo;
    }

    /**
     * @param tipo o tipo a ser definido
     */
    public void setTipo(char tipo) {
        
        this.tipo = tipo;
    }
    
    /**
     * @return o codinome
     */
    public String getCodinome() {
        
        return codinome;
    }

    /**
     * @param codinome o codinome a ser definido
     */
    public void setCodinome(String codinome) {
        
        this.codinome = codinome;
    }

    /**
     * @return o nomeCompleto
     */
    public String getNomeCompleto() {
        
        return nomeCompleto;
    }

    /**
     * @param nomeCompleto o nomeCompleto a ser definido
     */
    public void setNomeCompleto(String nomeCompleto) {
        
        this.nomeCompleto = nomeCompleto;
    }
    
    /**
     * @return o nivel
     */
    public int getNivel() {
        
        return nivel;
    }

    /**
     * @param nivel o nivel a ser definido
     */
    public void setNivel(int nivel) {
        
        this.nivel = nivel;
    }

    /**
     * @return os pontos
     */
    public int getPontos() {
        
        return pontos;
    }

    /**
     * @param pontos os pontos a serem definidos
     */
    public void setPontos(int pontos) {
        
        this.pontos = pontos;
    }

    /**
     * @return a biografia
     */
    public String getBiografia() {
        
        return biografia;
    }

    /**
     * @param biografia a biografia a ser definida
     */
    public void setBiografia(String biografia) {
        
        this.biografia = biografia;
    }

    /**
     * @return a cidade
     */
    public String getCidade() {
        
        return cidade;
    }

    /**
     * @param cidade a cidade a ser definida
     */
    public void setCidade(String cidade) {
        
        this.cidade = cidade;
    }

    /**
     * @return o estado
     */
    public String getEstado() {
        
        return estado;
    }

    /**
     * @param estado o estado a ser definido
     */
    public void setEstado(String estado) {
        
        this.estado = estado;
    }

    /**
     * @return o email
     */
    public String getEmail() {
        
        return email;
    }

    /**
     * @param email o email a ser definido
     */
    public void setEmail(String email) {
        
        this.email = email;
    }

    /**
     * @return a imagemPerfil
     */
    public String getImagemPerfil() {
        
        return imagemPerfil;
    }

    /**
     * @param imagemPerfil a imagemPerfil a ser definida
     */
    public void setImagemPerfil(String imagemPerfil) {
        
        this.imagemPerfil = imagemPerfil;
    }

    /**
     * @return o sexo
     */
    public char getSexo() {
        
        return sexo;
    }

    /**
     * @param sexo o sexo a definir
     */
    public void setSexo(char sexo) {
        
        this.sexo = sexo;
    }
}