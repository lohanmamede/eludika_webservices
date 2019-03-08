package com.eludika.app.ws.filters;

import com.eludika.app.ws.annotations.Seguro;
import com.eludika.app.ws.exceptions.AutenticacaoException;
import com.eludika.app.ws.io.entidades.Usuario;
import com.eludika.app.ws.services.UsuarioService;
import com.eludika.app.ws.services.UsuarioServiceImplementation;
import com.eludika.app.ws.utils.EludikaUtils;
import java.io.IOException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;
import com.eludika.app.ws.utils.EludikaUtils;
import java.io.IOException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;

/**
 * Esta classe...
 * 
 * @author eres
 */
@Seguro
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AutenticacaoFilter implements ContainerRequestFilter {
 
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        
        /* Extração dos detalhes do cabeçalho de autorização */
        String cabecalhoDeAutorizacao = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        
        if (cabecalhoDeAutorizacao == null || !cabecalhoDeAutorizacao.startsWith("Portador")) {
            
            throw new AutenticacaoException("Cabeçalho de autorização deve ser fornecido");
        }

        /* Extração do token */
        String token = cabecalhoDeAutorizacao.substring("Portador".length()).trim();

        /* Extração do idExterno */
        String idExterno = requestContext.getUriInfo().getPathParameters().getFirst("idExterno");
 
        this.validarToken(token, idExterno);
        
    }
    
    private void validarToken(String token, String idExterno) throws AutenticacaoException {
      
        /* Obter detalhes do perfil do usuário */
        UsuarioService usuarioService = new UsuarioServiceImplementation();
        Usuario usuario = usuarioService.obterUsuario(idExterno);
        
        /* Montagem do token de acesso usando 2 partes: do DB e do pedido http */
        String tokenCompleto = usuario.getToken() + token;
        
        /* Criação do token material com o idExterno recebido e o sal do banco de dados */
        String senhaSegura = usuario.getSenhaCriptografada();
        String sal = usuario.getSal();
        String tokenMaterial = idExterno + sal;
        
        byte[] tokenCriptografado = null;
       
        try {
            
            tokenCriptografado = new EludikaUtils().criptografar(senhaSegura, tokenMaterial);
        } 
        catch (InvalidKeySpecException ex) {
            
            throw new AutenticacaoException("Falha ao emitir o token de acesso seguro");
        }
        
        String tokenCriptografadoCodificacaoBase64 = Base64.getEncoder()
                .encodeToString(tokenCriptografado);
       
        /* Comparação dos dois tokens de acesso */
        if (!tokenCriptografadoCodificacaoBase64.equalsIgnoreCase(tokenCompleto)) {
            
            throw new AutenticacaoException("O token de autorização não corresponde");
        }
    }
}