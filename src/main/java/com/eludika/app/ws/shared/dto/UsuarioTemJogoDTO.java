package com.eludika.app.ws.shared.dto;

import com.eludika.app.ws.io.entidades.Usuario;
import java.io.Serializable;
import lombok.Data;

@Data
public class UsuarioTemJogoDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private long idDb;
    private Usuario /*:O*/ usuario;
    private JogoDTO jogo;
    private int pontos;
    private int niveisCompletos;
    private int niveisTotal;
    private int moedas;
    private int jogoPreferido;
}