package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.model.DTO.HabilidadeDTO;
import org.acme.model.DTO.Habilidade_profissionalDTO;
import org.acme.model.Habilidade;
import org.acme.model.Habilidade_profissional;
import org.acme.repository.HabilidadeRepository;
import org.acme.repository.Habilidade_profissionalRepository;

import java.sql.SQLException;
import java.util.List;

@ApplicationScoped
public class Habilidade_profissionalService {
    @Inject
    Habilidade_profissionalRepository habilidadeProfissionalRepository;

    public void cadastra(Habilidade_profissionalDTO h) throws SQLException {
        valicaoInserir(h);
        habilidadeProfissionalRepository.inserirH_P(h);
    }
    public void valicaoInserir(Habilidade_profissionalDTO h) {

        if (h.getNivel_habilidade_profissional()== null || h.getNivel_habilidade_profissional().isEmpty()) {
            throw new IllegalArgumentException("nivel habilidade incorreto");
        }
        if (h.getNv_importancia().isEmpty() || h.getNv_importancia()==null) {
            throw new RuntimeException("Nivel de importancia esta incorreto");
        }
        if (h.getId_habilidade()==0 || h.getId_habilidade()<0) {
            throw new RuntimeException("Id da habilidade incorreto");
        }
        if (h.getId_carreira()==0 || h.getId_carreira()<0) {
            throw new RuntimeException("id da carreira incorreto");
        }
    }

    public List<Habilidade_profissional> relatorio(int i) throws SQLException{
        try{
            valiacaoRelatorio(i);
            return habilidadeProfissionalRepository.RelatorioUsuarioH(i);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    public void valiacaoRelatorio(int i) throws SQLException{
        List<Habilidade_profissional>l= habilidadeProfissionalRepository.RelatorioUsuarioH(i);
        if (l.isEmpty()){
            throw new IllegalArgumentException("Nao existe no sistema");
        }
        if (i==0 || i<0){
            throw new IllegalArgumentException("id incorreto");
        }

    }

    public void Remover(int id) throws  SQLException{
        valiacaoRemocao(id);
        habilidadeProfissionalRepository.RemoverU_h(id);
    }
    public void valiacaoRemocao(int id){
        try{
            if (id==0 || id<0){
                throw new IllegalArgumentException("ID incorreto");
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public void Updante(int id, String nv_ha_p, String nv_i, int id_h, int id_carreira) throws SQLException{
        UpdanteValiacao(id, nv_ha_p, nv_i, id_h, id_carreira);
        habilidadeProfissionalRepository.updante(id, nv_ha_p, nv_i, id_h, id_carreira);
    }
    public  void UpdanteValiacao(int id, String nv_ha_p, String nv_i, int id_h, int id_carreira){
        try{
            if (id>0){
                throw new IllegalArgumentException("id incorreto");
            }
            if (nv_ha_p==null || nv_ha_p.isEmpty()){
                throw new IllegalArgumentException("nivel de habilidade incorreta");
            }
            if (nv_i==null || nv_i.isEmpty()){
                throw new IllegalArgumentException("nivel incorreto.");
            }
            if (id_h>0 || id_h==0){
                throw new IllegalArgumentException("id habilidade incorreto");
            }
            if (id_carreira>0 || id_carreira==0){
                throw new IllegalArgumentException("id carreira incorreto");
            }

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
