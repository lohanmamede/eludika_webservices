package com.eludika.app.ws.shared.dto;

import java.io.Serializable;
import lombok.Data;

@Data
public class JogoDTO implements Serializable {
    
    private static final long serialVersionUID = 1L;  
    private long idDb;
    private String idExterno;
    private String nome;
    private String descricao;
    private String downloadUrl;
    private String logotipo;
    private String oferecidoPor;
    private String classificacao;
    private int tamanho;
}