package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.model.Carreira;
import org.acme.model.DTO.CarreiraDTO;
import org.acme.repository.CarreiraRepository;

import java.sql.SQLException;
import java.util.List;

@ApplicationScoped
public class CarreiraService {
    @Inject
    CarreiraRepository carreiraRepository;

    public void inserirCarreira(CarreiraDTO carreiraDTO) throws SQLException {
        valicaoinserir(carreiraDTO);
        carreiraRepository.inserirCarreira(carreiraDTO);
    }
    public void valicaoinserir(CarreiraDTO carreiraDTO){
        if (carreiraDTO.getNome_carreira()==null || carreiraDTO.getNome_carreira().isEmpty()){
            throw new IllegalArgumentException("Texto da questão está vazio");
        }
        if (carreiraDTO.getAno_formacao()==0 || carreiraDTO.getAno_formacao()<0){
            throw new IllegalArgumentException("Anos do curso incorreta");
        }
        if (carreiraDTO.getArea_de_trabalho()==null || carreiraDTO.getArea_de_trabalho().isEmpty()){
            throw new IllegalArgumentException("Area incorreta");
        }
        if (carreiraDTO.getDica_treino()==null || carreiraDTO.getDica_treino().isEmpty()){
            throw new IllegalArgumentException("Dica de treino ao curso incorreta");
        }
        if (carreiraDTO.getInformacao_trabalho()==null || carreiraDTO.getInformacao_trabalho().isEmpty()){
            throw new IllegalArgumentException("Incorreta incorreta");
        }
    }

    public List<Carreira> relatorioCarreira(int id) throws SQLException {
        valiacaoRelatorio(id);
        return carreiraRepository.RelatorioCarreira(id);
    }
    public void valiacaoRelatorio(int id) throws SQLException {
        List<Carreira> l= carreiraRepository.RelatorioCarreira(id,nome);
        if (l.isEmpty()){
            throw new IllegalArgumentException("Opção não existe no sistema");
        }
        if (id==0 || id<0){
            throw new IllegalArgumentException("id incorreto");
        }
    }

    public void RemoverCarreira(int id, String nome) throws SQLException{
        valiacaoRemocao(id, nome);
        carreiraRepository.RemoverCadeira(id,nome);
    }
    public void valiacaoRemocao(int id, String nome){
        try{
            if (id==0){
                throw new IllegalArgumentException("id incorreto");
            }
            if (nome.isEmpty()){
                throw new IllegalArgumentException("Nome incorreto da carreira");
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public void UpdanteCarreira(int id, String n, int ano, String area, String in, String di, double s_max, double s_min, int t_p_m) throws SQLException{
        UpdanteValiacao(id, n, ano, area, in, di, s_max, s_min, t_p_m);
        carreiraRepository.updanteCarreira(id, n, ano, area, in, di, s_max, s_min, t_p_m);
    }
    public  void UpdanteValiacao(int id, String n, int ano, String area, String in, String di, double s_max, double s_min, int t_p_m){
        try{
            if (id<0 ||id==0 ){
                throw new IllegalArgumentException("id incorreto");
            }
            if (n==null || n.isEmpty()){
                throw new IllegalArgumentException("Texto da opcao incorreto");
            }
            if (ano==0 || ano<0){
                throw new IllegalArgumentException("Ano incorreto");
            }
            if (di==null || di.isEmpty()){
                throw new IllegalArgumentException("Dica de treino incorreto");
            }
            if (area==null || area.isEmpty()){
                throw new IllegalArgumentException("Area de trabalho incorreto");
            }
            if (in==null || in.isEmpty()){
                throw new IllegalArgumentException("Informacao do trabalho incorreta incorreto");
            }
            if (s_min==0 || s_min<0){
                throw new IllegalArgumentException("Salario minimo incorreto");
            }
            if (s_max==0 || s_max<0){
                throw new IllegalArgumentException("Salario maximo incorreto");
            }
            if (t_p_m==0 || t_p_m<0){
                throw new IllegalArgumentException("tempo do curso incorreto");
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
