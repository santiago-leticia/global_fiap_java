package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.model.DTO.UsuarioDTO;
import org.acme.model.DTO.Usuario_habilidadeDTO;
import org.acme.model.Usuario;
import org.acme.model.Usuario_habilidade;
import org.acme.repository.UsuarioRepository;
import org.acme.repository.Usuario_habilidadeRepository;

import java.sql.SQLException;
import java.util.List;

@ApplicationScoped
public class Usuario_habilidadeService {
    @Inject
    Usuario_habilidadeRepository usuario_habilidadeRepository;

    public void cadastra(Usuario_habilidadeDTO u) throws SQLException {
        valicaoCadastro(u);
        usuario_habilidadeRepository.inserirU_H(u);
    }
    public void valicaoCadastro(Usuario_habilidadeDTO u){
        if (u.getId_habilidade()==0 || u.getId_habilidade()<0){
            throw new IllegalArgumentException("id incorreto");
        }
        if (u.getId_usuario()==0 || u.getId_usuario()<0) {
            throw new IllegalArgumentException("id usuario incorreto");
        }
        if (u.getLv_proficiencia()==null || u.getLv_proficiencia().isEmpty()){
            throw new IllegalArgumentException("Level proficinal incorreto");
        }
    }

    public List<Usuario_habilidade> relatorio(int id) throws SQLException{
        try{
            valiacaoRelatorio(id);
            return usuario_habilidadeRepository.RelatorioUsuarioH(id);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    public void valiacaoRelatorio(int id) throws SQLException{
        List<Usuario_habilidade>l= usuario_habilidadeRepository.RelatorioUsuarioH(id);
        if (l.isEmpty()){
            throw new IllegalArgumentException("Nao existe no sistema");
        }
        if (id==0){
            throw new IllegalArgumentException("ID incorreto");
        }

    }

    public void Remover(int id) throws  SQLException{
        valiacaoRemocao(id);
        usuario_habilidadeRepository.RemoverU_h(id);
    }
    public void valiacaoRemocao(int id){
        try{
            if (id==0 || id<0){
                throw new IllegalArgumentException("id incorreto");
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public void Updante(int id, int id_u, int id_h, String lv) throws SQLException{
        UpdanteValiacao(id, id_u, id_h, lv);
        usuario_habilidadeRepository.updanteU_h(id, id_u, id_h, lv);
    }
    public  void UpdanteValiacao(int id, int id_u, int id_h, String lv){
        try{
            if (id>0){
                throw new IllegalArgumentException("id incorreto");
            }
            if (lv==null || lv.isEmpty()){
                throw new IllegalArgumentException("Esta incorreto o nivel de proficiencia");
            }
            if (id_u==0 || id_u<0){
                throw new IllegalArgumentException("ID do usuario incorreto.");
            }
            if (id_h==0 || id_h<0){
                throw new IllegalArgumentException("id habilidade incorreto.");
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
