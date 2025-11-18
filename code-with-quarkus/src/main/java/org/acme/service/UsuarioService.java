package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.model.DTO.UsuarioDTO;
import org.acme.model.Usuario;
import org.acme.repository.UsuarioRepository;

import java.sql.SQLException;
import java.util.List;

@ApplicationScoped
public class UsuarioService {
    @Inject
    UsuarioRepository usuarioRepository;

    public void cadastraUsuario(UsuarioDTO usuario) throws SQLException {
        valicaoCadastro(usuario);
        usuarioRepository.cadastrarUsuario(usuario);
    }
    public void valicaoCadastro(UsuarioDTO usuario){
        if (usuarioRepository.existeEmail(usuario.getEmail())){
            throw new IllegalArgumentException("Email ja cadastrado");
        }
        if (usuario.getNome()==null || usuario.getNome().isEmpty()){
            throw new IllegalArgumentException("Nome incorreto");
        }
        if (usuario.getSenha()== null || usuario.getSenha().isEmpty()) {
            throw new IllegalArgumentException("Cpf incorreto");
        }
        if (usuario.getIdade()==0){
            throw new IllegalArgumentException("Idade incorreto");
        }
        if (usuario.getEmail()==null || usuario.getEmail().isEmpty()){
            throw new IllegalArgumentException("Email incorreto");
        }
    }

    public  List<Usuario> relatorioUsuario(String email, String senha) throws SQLException{
        try{
            valiacaoRelatorio(email, senha);
            return usuarioRepository.login(email,senha);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    public void valiacaoRelatorio(String email, String senha){
        List<Usuario>l= usuarioRepository.login(email,senha);
        if (l.isEmpty()){
            throw new IllegalArgumentException("Usuario nao existe no sistema");
        }
        if (email==null || email.isEmpty()){
            throw new IllegalArgumentException("Email incorreto");
        }
        if (senha==null || senha.isEmpty()){
            throw new RuntimeException();
        }
    }

    public void RemoverUsuario(String email, String senha) throws  SQLException{
        valiacaoRemocao(email, senha);
        usuarioRepository.DeletarConta(email,senha);
    }
    public void valiacaoRemocao(String email, String senha){
        try{
            if (email==null || email.isEmpty()){
                throw new IllegalArgumentException("Email incorreto");
            }
            if (senha==null || senha.isEmpty()){
                throw new IllegalArgumentException("senha incorreta");
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public void UpdanteUsuario(Usuario usuario) throws SQLException{
        UpdanteValiacao(usuario);
        usuarioRepository.updanteConta(usuario);
    }
    public  void UpdanteValiacao(Usuario usuario){
        try{
            if (usuario.getId_usuario()>0){
                throw new IllegalArgumentException("id incorreto");
            }
            if (usuario.getEmail()==null || usuario.getEmail().isEmpty()){
                throw new IllegalArgumentException("Emaill incorreto");
            }
            if (usuario.getNome()==null || usuario.getNome().isEmpty()){
                throw new IllegalArgumentException("Nome incorreto.");
            }
            if (usuario.getSenha()==null || usuario.getSenha().isEmpty()){
                throw new IllegalArgumentException("Senha incorreta");
            }
            if (usuario.getIdade()<0){
                throw new IllegalArgumentException("Idade incorreta");
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
