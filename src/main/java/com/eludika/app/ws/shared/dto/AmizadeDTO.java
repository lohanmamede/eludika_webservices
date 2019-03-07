package com.eludika.app.ws.shared.dto;

import com.eludika.app.ws.io.entidades.Usuario;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data 
public class AmizadeDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private long idDb;
    private Usuario /*:O*/ solicitante;
    private Usuario /*:O*/ solicitado;
    private Date dataSolicitacao;
    private Date dataConfirmacao;
    private char situacao;
}