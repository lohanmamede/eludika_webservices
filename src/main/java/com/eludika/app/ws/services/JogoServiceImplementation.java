package com.eludika.app.ws.services;

import com.eludika.app.ws.exceptions.NenhumRegistroEncontradoException;
import com.eludika.app.ws.io.dao.JogoDAO;
import com.eludika.app.ws.io.dao.JogoDAOMariaDB;
import com.eludika.app.ws.shared.dto.JogoDTO;
import com.eludika.app.ws.ui.models.response.MensagensDeErro;
import com.eludika.app.ws.utils.EludikaUtils;
import static java.lang.System.out;
import java.util.List;
import javax.persistence.NoResultException;

/**
 * Esta interface implementa os métodos que executam os serviços relacionados a
 * Entidade 'Amizade'
 * 
 * @author eres
 */
public class JogoServiceImplementation implements JogoService {

    // ------------------------------------------------------------------ Campos
    JogoDAO jogoDAO;
    EludikaUtils eludikaUtils;
    
    
    // ------------------------------------------------------------ Construtores
    public JogoServiceImplementation() {

        this.jogoDAO = new JogoDAOMariaDB();
        this.eludikaUtils = new EludikaUtils();
    }
    

    // -------------------------------------------------------- Métodos Override
    /**
     * Este método executa o serviço de criação da respectiva entidade
     *
     * @param jogoDTO data acess object com os dados da requisição
     */
    @Override
    public void salvarJogo(JogoDTO jogoDTO) {
        
        /* Geração e atribuição de um id externo para o usuário */
        String idExterno = this.eludikaUtils.gerarIdExterno(30);
        
        jogoDTO.setIdExterno(idExterno);
        
        try {
            
            this.jogoDAO.abrirConexao();
            this.jogoDAO.salvarJogo(jogoDTO);
        }
        finally {
            
            this.jogoDAO.fecharConexao();
        }  
    }
    
    @Override
    public JogoDTO obterJogo(String idExterno) {
       
        JogoDTO jogoDTO = null;
        
        try {

            this.jogoDAO.abrirConexao();
            jogoDTO = this.jogoDAO.obterJogo(idExterno);
        } 
        catch(NoResultException ex) {
            
            /* Se for lançada a exceção NoResultException, relança a exceção de 
            nenhum registro encontrado */
            throw new NenhumRegistroEncontradoException(MensagensDeErro
                    .NENHUM_REGISTRO_ENCONTRADO.getMensagemDeErro());
        }
        finally {

            this.jogoDAO.fecharConexao();
        }
        
        return jogoDTO;
    }
    
    @Override
    public List<JogoDTO> obterJogos(String nome, int inicio, int tamanhoMaximo) {

        List<JogoDTO> jogosDTO = null;

        /* Obtenção dos usuários do banco de dados */
        try {
            
            this.jogoDAO.abrirConexao();
            jogosDTO = this.jogoDAO.obterJogos(nome, inicio, tamanhoMaximo);
        } 
        finally {
            
            this.jogoDAO.fecharConexao();
        }

        return jogosDTO;
    }
}
