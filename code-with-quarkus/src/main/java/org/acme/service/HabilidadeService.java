package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.model.DTO.HabilidadeDTO;
import org.acme.model.DTO.Links_TrabalhoDTO;
import org.acme.model.Habilidade;
import org.acme.model.Links_Trabalho;
import org.acme.repository.HabilidadeRepository;
import org.acme.repository.Links_trabalhoRepository;

import java.sql.SQLException;
import java.util.List;

@ApplicationScoped
public class HabilidadeService {
    @Inject
    HabilidadeRepository habilidadeRepository;

    public void cadastra(HabilidadeDTO habilidadeDTO) throws SQLException {
        valicaoInserir(habilidadeDTO);
        habilidadeRepository.inserirHabilidade(habilidadeDTO);
    }
    public void valicaoInserir(HabilidadeDTO habilidadeDTO) {

        if (habilidadeDTO.getNm_habilidade() == null || habilidadeDTO.getNm_habilidade().isEmpty()) {
            throw new IllegalArgumentException("Nome da habilidade incorreto");
        }
        if (habilidadeDTO.getDm_mercado().isEmpty() || habilidadeDTO.getDm_mercado() == null) {
            throw new RuntimeException("a demanda no mercado esta incorreto");
        }
        if (habilidadeDTO.getNm_categoria().isEmpty() || habilidadeDTO.getNm_categoria() == null) {
            throw new RuntimeException("Nome da categoria incorreto");
        }
    }

    public List<Habilidade> relatorio(String n) throws SQLException{
        try{
            valiacaoRelatorio(n);
            return habilidadeRepository.RelatorioHabilidade(n);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    public void valiacaoRelatorio(String n) throws SQLException{
        List<Habilidade>l= habilidadeRepository.RelatorioHabilidade(n);
        if (l.isEmpty()){
            throw new IllegalArgumentException("Nao existe no sistema");
        }
        if (n.isEmpty()){
            throw new IllegalArgumentException("nome incorreto");
        }

    }

    public void Remover(int id,String nm) throws  SQLException{
        valiacaoRemocao(id,nm);
        habilidadeRepository.RemoverHabilidade(id,nm);
    }
    public void valiacaoRemocao(int id,String nm){
        try{
            if (id==0 || id<0){
                throw new IllegalArgumentException("ID incorreto");
            }
            if (nm.isEmpty()){
                throw new IllegalArgumentException("nome da habilidade incorreto");
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public void Updante(int id, String nm,String dm_m,String nm_c) throws SQLException{
        UpdanteValiacao(id, nm, dm_m, nm_c);
        habilidadeRepository.updanteHabilidade(id, nm, dm_m, nm_c);
    }
    public  void UpdanteValiacao(int id, String nm,String dm_m,String nm_c){
        try{
            if (id>0){
                throw new IllegalArgumentException("id incorreto");
            }
            if (nm==null || nm.isEmpty()){
                throw new IllegalArgumentException("Está incorreto nome");
            }
            if (dm_m==null || dm_m.isEmpty()){
                throw new IllegalArgumentException("valor da demanda incorreto.");
            }
            if (nm_c==null || nm_c.isEmpty()){
                throw new IllegalArgumentException("nome classifição incorreto.");
            }

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
