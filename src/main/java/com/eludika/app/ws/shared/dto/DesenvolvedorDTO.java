package com.eludika.app.ws.shared.dto;

import java.io.Serializable;
import lombok.Data;

@Data
public class DesenvolvedorDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private long idDb;
    private String idExterno;
    private String nome;
}