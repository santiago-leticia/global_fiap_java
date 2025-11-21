package org.acme.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.model.DTO.UsuarioDTO;
import org.acme.model.Usuario;
import org.acme.service.UsuarioService;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/futureForge")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioResource {


    @Inject
    UsuarioService usuarioService;

    @POST
    @Path("/cadastro")
    public Response CadastrarUsuario(UsuarioDTO usuarioDTO){
        try{

            usuarioService.cadastraUsuario(usuarioDTO);
            return Response.status(Response.Status.CREATED)
                    .entity("Criando com sucesso").build();
        }catch (SQLException e){
            Map<String, String> erro = new HashMap<>();
            erro.put("erro", "Erro no servido");
            return Response.serverError().entity(erro).build();
        }catch (IllegalArgumentException e){
            return  Response.status(422).entity(e.getMessage()).build();
        }
    }
    @GET
    @Path("login")
    public Response login(Usuario usuario){
        try{
            List<Usuario> l= usuarioService.relatorioUsuario(
                    usuario.getEmail(),
                    usuario.getSenha());

            return Response.status(Response.Status.OK).entity(l).build();
        }catch (SQLException e){
            Map<String, String> erro = new HashMap<>();
            erro.put("mensagem", "Erro ao conectar.");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(erro).build();
        }catch (IllegalArgumentException e){
            Map<String, String> erro = new HashMap<>();
            erro.put("erro", "Usuário não encontrado");
            return Response.status(Response.Status.NOT_FOUND).entity(erro).build();
        }
    }
    @DELETE
    @Path("/login/deleta")
    public Response RemoverCarreira(Usuario usuario){
        try {
            usuarioService.RemoverUsuario(usuario.getEmail(),
                    usuario.getSenha());
            return  Response.status(Response.Status.OK)
                    .entity("Removido com sucesso").build();
        }catch (SQLException e){
            Map<String, String> erro = new HashMap<>();
            erro.put("erro",e.getMessage());
            return Response.serverError().entity(erro).build();
        }catch (IllegalArgumentException e){
            Map<String, String> erro = new HashMap<>();
            erro.put("erro", "Usuario não encontrado");
            return Response.status(Response.Status.NOT_FOUND).entity(erro).build();
        }
    }
    @PUT
    @Path("/usuario/atualizar")
    public Response atualizarUsuario(Usuario usuario){
        try{
            usuarioService.UpdanteUsuario(usuario.getNome(),
                    usuario.getCpf(),usuario.getIdade(),
                    usuario.getEmail(), usuario.getSenha(),
                    usuario.getId_usuario(),usuario.getEmail(),usuario.getSenha());

            return Response.status(Response.Status.OK)
                    .entity("Dados atualizando")
                    .build();
        }catch (SQLException e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage()).build();
        }catch (IllegalArgumentException e){
            return Response.
                    status(422)
                    .entity(e.getMessage()).build();
        }
    }
}
