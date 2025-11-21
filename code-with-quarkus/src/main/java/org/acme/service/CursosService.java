package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.model.Cursos;
import org.acme.model.DTO.CursosDTO;
import org.acme.model.DTO.Habilidade_profissionalDTO;
import org.acme.model.Habilidade_profissional;
import org.acme.repository.CursosRepository;
import org.acme.repository.Habilidade_profissionalRepository;

import java.sql.SQLException;
import java.util.List;

@ApplicationScoped
public class CursosService {
    @Inject
    CursosRepository cursosRepository;

    public void cadastra(CursosDTO c) throws SQLException {
        valicaoInserir(c);
        cursosRepository.inserirCursos(c);
    }
    public void valicaoInserir(CursosDTO c) {

        if (c.getNm_curso()== null || c.getNm_curso().isEmpty()) {
            throw new IllegalArgumentException("Nome do curso incorreto");
        }
        if (c.getUrl_curso().isEmpty() || c.getUrl_curso()==null) {
            throw new RuntimeException("A url do curso esta incorreta");
        }
        if (c.getId_carreira()==0 || c.getId_carreira()<0) {
            throw new RuntimeException("id carreira esta incorreto");
        }
        if (c.getDuracao()==0 || c.getDuracao()<0) {
            throw new RuntimeException("duração incorreto");
        }
    }

    public List<Cursos> relatorio(String nome) throws SQLException{
        try{
            valiacaoRelatorio(nome);
            return cursosRepository.RelatorioCursos(nome);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    public void valiacaoRelatorio(String nome) throws SQLException{
        List<Cursos>l= cursosRepository.RelatorioCursos(nome);
        if (l.isEmpty()){
            throw new IllegalArgumentException("Não existe no sistema");
        }
        if (nome.isEmpty() || nome==null){
            throw new IllegalArgumentException("nome incorreto");
        }

    }

    public void Remover(String n) throws  SQLException{
        valiacaoRemocao(n);
        cursosRepository.RemoverCurso(n);
    }
    public void valiacaoRemocao(String n){
        try{
            if (n.isEmpty() || n==null){
                throw new IllegalArgumentException("nome do curso incorreto");
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public void Updante(int id_curso, String nm_curso, String url_curso, String gratuito, int duracao, int id_carreira) throws SQLException{
        UpdanteValiacao(id_curso, nm_curso, url_curso, gratuito, duracao, id_carreira);
        cursosRepository.updanteCursos(id_curso, nm_curso, url_curso, gratuito, duracao, id_carreira);
    }
    public  void UpdanteValiacao(int id_curso, String nm_curso, String url_curso, String gratuito, int duracao, int id_carreira){
        try{
            if (id_curso>0 || id_curso==0){
                throw new IllegalArgumentException("id incorreto");
            }
            if (nm_curso==null || nm_curso.isEmpty()){
                throw new IllegalArgumentException("Nome de curso incorreta");
            }
            if (url_curso==null || url_curso.isEmpty()){
                throw new IllegalArgumentException("url do curso está incorreto.");
            }
            if (gratuito==null || gratuito.isEmpty()){
                throw new IllegalArgumentException("informacao se é gratuito incorreto.");
            }
            if (duracao<0 || duracao==0){
                throw new IllegalArgumentException("duracao incorreto");
            }
            if (id_carreira<0 || id_carreira==0){
                throw new IllegalArgumentException("id carreira incorreto");
            }

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
