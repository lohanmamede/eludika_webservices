package com.eludika.app.ws.exceptions;

/**
 *
 * @author eres
 */
public class ImpossivelAtualizarRegistroException extends RuntimeException {

    private static final long serialVersionUID = 1835272504889465316L;

    public ImpossivelAtualizarRegistroException(String mensagem)
    {
        super(mensagem);
    }
    
}
