package com.eludika.app.ws.exceptions;

/**
 *
 * @author eres
 */
public class ImpossivelDeletarRegistroException  extends RuntimeException {

    private static final long serialVersionUID = -3544851409849602464L;

    public ImpossivelDeletarRegistroException(String mensagem)
    {
        super(mensagem);
    }
}
