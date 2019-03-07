package com.eludika.app.ws.utils; // :D

import static java.lang.System.err;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/** 
 * Esta classe cria uma fábrica de Sessões para a aplicação, configurada 
 * apropriadamente.
 * 
 * OBS: SessionFactory é um objeto pesado, e a nossa aplicaçao deve usar 
 * apenas um objeto SessionFactory para cada instância de base de dados que 
 * interage com a nossa aplicação. No entanto um SessionFactory é thread-safe e 
 * podemos reusar a SessionFactory em aplicações com múltiplas threads, já que é
 * o que é esperado, e levando em conta que cada sessão deve apenas ser acessada 
 * dentro de uma única thread de execução.
 * Isso acontece, por uma sessão também representar informações armazenadas de 
 * uma base de dados, então é desejável mantê-la para uso dentro de uma thread 
 * até que qualquer coisa (qualquer exceção do Hibernate) torne-a inválida.
 * 
 * Fonte: https://goo.gl/aupU3m
 *
 * @author eres
 */
public class HibernateUtils {
    
    // ------------------------------------------------------------------ Campos
    private static final SessionFactory fabricaDeSessoes;
    
    
    // ------------------------------------------------------------ Construtores
    /* Bloco inicializador estático, que é usado para inicializar os membros 
    estáticos da classe. É executado quando a classe é inicializada. */
    static {
        
        Configuration c = new Configuration();
        c.configure();
 
        try {
            
            /* Adquire uma informação de configuração do Hibernate e usa isto 
            para gerar uma instância de SessionFactory apropriada. Essa 
            informação de configuração é o arquivo hibernate.cfg.xml. */
            fabricaDeSessoes = new Configuration().configure().buildSessionFactory();
        } 
        catch (HibernateException e) {
            
            err.println("A criação da fabrica de sessões falhou. " + e);
            throw new ExceptionInInitializerError(e);
        }       
    }
    
     
    // ------------------------------------------------------- Getters e Setters
    /** Método usado para obter a instância da fábrica de sessões
     *
     * @return fábrica de sessões
     */
    public static SessionFactory getFabricaDeSessoes() {
        
        return fabricaDeSessoes;
    } 
}
