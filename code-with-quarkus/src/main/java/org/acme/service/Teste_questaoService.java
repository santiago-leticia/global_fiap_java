package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.model.DTO.Teste_QuestaoDTO;
import org.acme.model.Teste_Questao;
import org.acme.repository.Teste_questaoRepository;

import java.sql.SQLException;
import java.util.List;

@ApplicationScoped
public class Teste_questaoService {
    @Inject
    Teste_questaoRepository testeQuestaoRepository;

    public void inserirQuestao(Teste_QuestaoDTO testeQuestaoDTO) throws SQLException {
        valicaoinserir(testeQuestaoDTO);
        testeQuestaoRepository.inserirQuestao(testeQuestaoDTO);
    }
    public void valicaoinserir(Teste_QuestaoDTO testeQuestaoDTO){
        if (testeQuestaoDTO.getTexto_questao()==null || testeQuestaoDTO.getTexto_questao().isEmpty()){
            throw new IllegalArgumentException("Texto da questão está vazio");
        }
    }

    public  List<Teste_Questao> relatorioQuestao(int id) throws SQLException{
        try{
            valiacaoRelatorio(id);
            return testeQuestaoRepository.relatorio(id);
        }catch (Exception e){
            throw new IllegalArgumentException(e);
        }

    }
    public void valiacaoRelatorio(int id){
        List<Teste_Questao> l= testeQuestaoRepository.relatorio(id);
        if (l.isEmpty()){
            throw new IllegalArgumentException("Opção não existe no sistema");
        }
        if (id==0){
            throw new IllegalArgumentException("id incorreto");
        }
    }

    public void RemoverQuestao(int id) throws SQLException{
        valiacaoRemocao(id);
        testeQuestaoRepository.RemoverQuestao(id);
    }
    public void valiacaoRemocao(int id){
        try{
            if (id==0){
                throw new IllegalArgumentException("id incorreto");
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public void UpdanteQuestao(int id, String tx) throws SQLException{
        UpdanteValiacao(id, tx);
        testeQuestaoRepository.updanteQuestao(id,tx);
    }
    public  void UpdanteValiacao(int id, String tx){
        try{
            if (id<0 ||id==0 ){
                throw new IllegalArgumentException("id incorreto");
            }
            if (tx==null || tx.isEmpty()){
                throw new IllegalArgumentException("Texto da opcao incorreto");
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
