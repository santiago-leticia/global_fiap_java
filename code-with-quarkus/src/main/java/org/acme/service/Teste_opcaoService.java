package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.model.Carreira;
import org.acme.model.DTO.Teste_opcaoDTO;
import org.acme.model.Teste_Questao;
import org.acme.model.Teste_opcao;
import org.acme.repository.CarreiraRepository;
import org.acme.repository.Teste_opcaoRepository;
import org.acme.repository.Teste_questaoRepository;

import java.sql.SQLException;
import java.util.List;

@ApplicationScoped
public class Teste_opcaoService {

    @Inject
    Teste_opcaoRepository testeOpcaoRepository;
    @Inject
    CarreiraRepository carreiraRepository;
    @Inject
    Teste_questaoRepository testeQuestaoRepository;

    public void inseriropcao(Teste_opcaoDTO testeOpcaoDTO) throws SQLException {
        valicaoinserir(testeOpcaoDTO);
        testeOpcaoRepository.inserirOpcao(testeOpcaoDTO);
    }
    public void valicaoinserir(Teste_opcaoDTO testeOpcaoDTO) throws SQLException {
        if (testeOpcaoDTO.getId_questao()<=0){
            throw new IllegalArgumentException("id da questao incorreto");
        }
        if (testeOpcaoDTO.getId_carreira()<=0) {
            throw new IllegalArgumentException("id da carreira incorreto");
        }
        if (testeOpcaoDTO.getTexto()==null || testeOpcaoDTO.getTexto().isEmpty()){
            throw new IllegalArgumentException("Texto incorreto");
        }
        if (testeOpcaoDTO.getValor_escolha()==0){
            throw new IllegalArgumentException("Valor incorreto");
        }
        if (!id_CarrreiraExiste(testeOpcaoDTO.getId_carreira())){
                throw new IllegalArgumentException("Carreira não existe");
        }
        if (!questaoExiste(testeOpcaoDTO.getId_questao())){
                throw new IllegalArgumentException("Opcao não existe");
        }
    }
    public boolean id_CarrreiraExiste(int id) throws SQLException{
        List<Carreira> f= carreiraRepository.lista();
        return f.contains(id);
    }
    public boolean questaoExiste(int id) throws SQLException{
        List<Teste_Questao> u= testeQuestaoRepository.lista();
        return u.contains(id);
    }

    public List<Teste_opcao> relatorioopcao(int id) throws SQLException{
        valiacaoRelatorio(id);
        return testeOpcaoRepository.relatorio(id);
    }
    public void valiacaoRelatorio(int id){
        List<Teste_opcao> l= testeOpcaoRepository.relatorio(id);
        if (l.isEmpty()){
            throw new IllegalArgumentException("Opção não existe no sistema");
        }
        if (id==0){
            throw new IllegalArgumentException("id incorreto");
        }
    }

    public void RemoverOpcao(int id) throws SQLException{
        valiacaoRemocao(id);
        testeOpcaoRepository.RemoverOpcao(id);
    }
    public void valiacaoRemocao(int id){
        if (id==0){
            throw new IllegalArgumentException("id incorreto");
        }
    }

    public void UpdanteOpcao(int id_questao, int id_carreira, String tx, int vl, int id_opcao) throws SQLException{
        UpdanteValiacao(id_questao, id_carreira, tx, vl, id_opcao);
        testeOpcaoRepository.updanteOpcao(id_questao, id_carreira, tx, vl, id_opcao);
    }
    public  void UpdanteValiacao(int id_questao, int id_carreira, String tx, int vl, int id_opcao){
        if (id_opcao<0 || id_opcao==0 ){
            throw new IllegalArgumentException("id incorreto");
        }
        if (tx==null || tx.isEmpty()){
            throw new IllegalArgumentException("Texto da opcao incorreto");
        }
        if (id_carreira==0 || id_carreira<0){
            throw new IllegalArgumentException("id da carreira incorreto.");
        }
        if (id_questao==0 || id_questao<0){
            throw new IllegalArgumentException("id da questao incorreta");
        }
        if (vl<0 || vl==0){
            throw new IllegalArgumentException("valor incorreta");
        }
    }
}
