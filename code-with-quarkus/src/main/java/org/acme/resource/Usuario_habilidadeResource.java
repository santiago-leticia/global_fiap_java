package org.acme.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.model.Cursos;
import org.acme.model.DTO.CursosDTO;
import org.acme.model.DTO.Usuario_habilidadeDTO;
import org.acme.model.Usuario_habilidade;
import org.acme.service.CursosService;
import org.acme.service.Usuario_habilidadeService;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/futureForge")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class Usuario_habilidadeResource {



    @Inject
    Usuario_habilidadeService usuarioHabilidadeService;

    @POST
    @Path("/usuario/habilidade/inseri")
    public Response CadastrarUsuarioHabilidade(Usuario_habilidadeDTO usuarioHabilidadeDTO){
        try{

            usuarioHabilidadeService.cadastra(usuarioHabilidadeDTO);
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
    @Path("/usuario/habilidade/{id}")
    public Response ralatorioHabilidadeU(@PathParam("id") int id){
        try{
            List<Usuario_habilidade> l= usuarioHabilidadeService.relatorio(
                    id);

            return Response.status(Response.Status.OK).entity(l).build();
        }catch (SQLException e){
            Map<String, String> erro = new HashMap<>();
            erro.put("mensagem", "Erro ao conectar.");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(erro).build();
        }catch (IllegalArgumentException e){
            Map<String, String> erro = new HashMap<>();
            erro.put("erro", "Habilidade usuario não encontrado");
            return Response.status(Response.Status.NOT_FOUND).entity(erro).build();
        }
    }
    @DELETE
    @Path("/usuario/habilidade/deleta/{id}")
    public Response RemoverU_habilidade(@PathParam("id") int id){
        try {
            usuarioHabilidadeService.Remover(id);
            return  Response.status(Response.Status.OK)
                    .entity("Removido com sucesso").build();
        }catch (SQLException e){
            Map<String, String> erro = new HashMap<>();
            erro.put("erro",e.getMessage());
            return Response.serverError().entity(erro).build();
        }catch (IllegalArgumentException e){
            Map<String, String> erro = new HashMap<>();
            erro.put("erro", "Habilidade usuario não encontrado");
            return Response.status(Response.Status.NOT_FOUND).entity(erro).build();
        }
    }
    @PUT
    @Path("/usuario/habilidade/atualizar")
    public Response atualizarUsuarioHabilidade(Usuario_habilidade usuarioHabilidade){
        try{
            usuarioHabilidadeService.Updante(usuarioHabilidade.getId_habilidade_usuario(),
                    usuarioHabilidade.getId_usuario(), usuarioHabilidade.getId_habilidade(),
                    usuarioHabilidade.getLv_proficiencia());

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
