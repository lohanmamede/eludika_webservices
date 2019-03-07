package com.eludika.app.ws.exceptions;

/**
 * Esta classe ...
 *
 * @author eres
 */
public class ImpossivelCriarRegistroException extends RuntimeException {

    private static final long serialVersionUID = -4766931241055062417L;
    
    public ImpossivelCriarRegistroException(String mensagem) {

        super(mensagem);
    }
}
