package com.eludika.app.ws.utils;

import com.eludika.app.ws.exceptions.CampoObrigatorioAusenteException;
import com.eludika.app.ws.io.entidades.Usuario;
import com.eludika.app.ws.ui.models.response.MensagensDeErro;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
// import java.util.UUID;

/**
 * Esta classe ...
 * 
 * @author eres
 */
public class EludikaUtils {

    // ------------------------------------------------------------------ Campos
    private final Random RANDOM = new SecureRandom();
    private final String ALFABETO = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"
            + "abcdefghijklmnopqrstuvwxyz";
    private final int ITERACOES = 10000;
    private final int COMPRIMENTO_DA_CHAVE = 256;

    
    // ----------------------------------------------------------------- Métodos
    /**
     * Este método ...
     * 
     * @param usuarioDTO
     * @throws CampoObrigatorioAusenteException
     */
    public void validarCamposObrigatorios(Usuario/*:O*/ usuarioDTO) throws
            CampoObrigatorioAusenteException {

        if ((usuarioDTO.getTipo() != 'A'
                && usuarioDTO.getTipo() != 'P')
                || usuarioDTO.getCodinome() == null
                || usuarioDTO.getCodinome().isEmpty()
                || usuarioDTO.getNomeCompleto() == null
                || usuarioDTO.getNomeCompleto().isEmpty()
                || usuarioDTO.getEmail() == null
                || usuarioDTO.getEmail().isEmpty()
                || usuarioDTO.getSenha() == null
                || usuarioDTO.getSenha().isEmpty()
                || usuarioDTO.getDataNascimento() == null
                || usuarioDTO.getDataNascimento().toString().isEmpty()) {

            throw new CampoObrigatorioAusenteException(MensagensDeErro
                    .CAMPO_OBRIGATORIO_AUSENTE.getMensagemDeErro());
        }
    }
    
    /**
     * Este método ...
     *
     * @param comprimento
     * @return
     */
    public String gerarIdExterno(int comprimento) {
        
        return gerarStringAleatoria(comprimento);
    }

    /**
     * Este método ...
     * 
     * @param length
     * @return
     */
    public String gerarSal(int length) {
        
        return gerarStringAleatoria(length);
    }

    /**
     * Este método ...
     * 
     * @param senha
     * @param sal
     * @return
     */
    public String gerarSenhaSegura(String senha, String sal) {
        
        byte[] senhaSegura = hash(senha.toCharArray(), sal.getBytes());
        return Base64.getEncoder().encodeToString(senhaSegura);
    }
    
    /**
     * Este método... 
     * 
     * @param senhaSegura
     * @param tokenMaterial
     * @return
     * @throws InvalidKeySpecException
     */
    public byte[] criptografar(String senhaSegura, String tokenMaterial) 
            throws InvalidKeySpecException {
        
        return hash(senhaSegura.toCharArray(), tokenMaterial.getBytes());
    }
    
    
    // -------------------------------------------- Métodos auxiliares (private)
    /**
     * Este método gera uma string aleatória com números e letras do alfabeto
     *
     * @param comprimento quantidade de caracteres aleatórios desejados
     * 
     * @return string formada por caracteres aleatórios
     */
    private String gerarStringAleatoria(int comprimento) {
        
        /* Criação de um StringBuilder de comprimento especificado pelo parâmetro
        de mesmo nome */
        StringBuilder stringBuilder = new StringBuilder(comprimento);

        /* Com o auxílio de RANDOM.nextInt é sorteado um valor numérico aleatório 
        entre 0 (inclusive) e o número que representa a largura do alfabeto 
        (exclusive). Esse valor é usado por ALFABETO.charAt para selecionar um 
        caractere na respectiva posição na String ALFABETO. Esse caractere é
        acrescentado no stringBuilder através do append. Esse processo é repetido
        pelo for quantas vezes forem necessárias para preencher a quantidade de 
        caracteres especificados pelo parâmetro comprimento */
        for (int i = 0; i < comprimento; i++) {
            
            stringBuilder.append(ALFABETO.charAt(RANDOM.nextInt(ALFABETO.length())));
        }

        /* O StringBuilder é convertido em uma nova String e retornado */
        return new String(stringBuilder);
    }
    
    /**
     * Este método...
     * 
     * @param senha
     * @param sal
     * @return
     */
    private byte[] hash(char[] senha, byte[] sal) {
        
        PBEKeySpec spec = new PBEKeySpec(senha, sal, ITERACOES,
                COMPRIMENTO_DA_CHAVE);
        
        Arrays.fill(senha, Character.MIN_VALUE);
        
        try {
            
            SecretKeyFactory skf;
            skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            
            return skf.generateSecret(spec).getEncoded();
        } 
        catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            
            throw new AssertionError("Erro ao codificar uma senha: " 
                    + e.getMessage(), e);
        } 
        finally {
            
            spec.clearPassword();
        }
    }
}


//    /** Um UUID (Universal Unique Identifier) é um número de 128 bits usado para
//     * identificar de forma exclusiva algum objeto ou entidade na Internet.
//     * O UUID é garantido como diferente ou é, pelo menos,
//     * muito provável que seja diferente de qualquer outro UUID gerado.
//     *
//     * @return
//     */
//    public String gerarUUID() {
//
//        String retorno = UUID.randomUUID().toString().replaceAll("-", "");
//        
//        return retorno;
//    }
//
//    /**
//     *
//     * @param comprimento
//     * @return
//     */
//    public String gerarVerificacaoEmailToken(int comprimento) {
//        
//        return gerarRandomString(comprimento);
//    }