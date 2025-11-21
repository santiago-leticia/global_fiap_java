package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.model.DTO.Links_TrabalhoDTO;
import org.acme.model.DTO.Usuario_habilidadeDTO;
import org.acme.model.Links_Trabalho;
import org.acme.model.Usuario_habilidade;
import org.acme.repository.Links_trabalhoRepository;
import org.acme.repository.Usuario_habilidadeRepository;

import java.sql.SQLException;
import java.util.List;
@ApplicationScoped
public class Links_trabalhoService {
    @Inject
    Links_trabalhoRepository linksTrabalhoRepository;

    public void cadastra(Links_TrabalhoDTO lt) throws SQLException {
        valicaoInserir(lt);
        linksTrabalhoRepository.inserirLink(lt);
    }
    public void valicaoInserir(Links_TrabalhoDTO lt) {
        if (lt.getId_carreira() == 0 || lt.getId_carreira() < 0) {
            throw new IllegalArgumentException("id incorreto");
        }
        if (lt.getLink_url() == null || lt.getLink_url().isEmpty()) {
            throw new IllegalArgumentException("id usuario incorreto");
        }
        if (lt.getNm_vaga().isEmpty() || lt.getNm_vaga() == null) {
            throw new RuntimeException("Nome da vaga incorreto");
        }
        if (lt.getNm_empresa().isEmpty() || lt.getNm_empresa() == null) {
            throw new RuntimeException("Nome da empresa incorreto");
        }
    }

    public List<Links_Trabalho> relatorio(String n) throws SQLException{
        try{
            valiacaoRelatorio(n);
            return linksTrabalhoRepository.RelatorioLinks(n);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    public void valiacaoRelatorio(String n) throws SQLException{
        List<Links_Trabalho>l= linksTrabalhoRepository.RelatorioLinks(n);
        if (l.isEmpty()){
            throw new IllegalArgumentException("Nao existe no sistema");
        }
        if (n.isEmpty()){
            throw new IllegalArgumentException("ID incorreto");
        }

    }

    public void Remover(String nm) throws  SQLException{
        valiacaoRemocao(nm);
        linksTrabalhoRepository.removerLink(nm);
    }
    public void valiacaoRemocao(String n){
        try{
            if (n.isEmpty()){
                throw new IllegalArgumentException("nome incorreto");
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public void Updante(int id_l, String nm_empresa, String nm_vaga, String link_url, int id_carreira) throws SQLException{
        UpdanteValiacao(id_l, nm_empresa, nm_vaga, link_url, id_carreira);
        linksTrabalhoRepository.updanteLink(id_l, nm_empresa, nm_vaga, link_url, id_carreira);
    }
    public  void UpdanteValiacao(int id_l, String nm_empresa, String nm_vaga, String link_url, int id_carreira){
        try{
            if (id_l>0){
                throw new IllegalArgumentException("id incorreto");
            }
            if (nm_empresa==null || nm_empresa.isEmpty()){
                throw new IllegalArgumentException("Esta incorreto nome empres");
            }
            if (nm_vaga==null || nm_vaga.isEmpty()){
                throw new IllegalArgumentException("nome da vaga incorreto.");
            }
            if (link_url==null || link_url.isEmpty()){
                throw new IllegalArgumentException("Link incorreto.");
            }
            if (id_carreira==0 || id_carreira<0){
                throw new IllegalArgumentException("id carreira incorreto.");
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}

