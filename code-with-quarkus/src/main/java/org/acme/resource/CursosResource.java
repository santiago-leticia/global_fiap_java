package org.acme.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.model.Cursos;
import org.acme.model.DTO.CursosDTO;
import org.acme.model.DTO.UsuarioDTO;
import org.acme.model.Usuario;
import org.acme.service.CursosService;
import org.acme.service.UsuarioService;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/futureForge")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CursosResource {



    @Inject
    CursosService cursosService;

    @POST
    @Path("/curso/inseri")
    public Response CadastrarCurso(CursosDTO cursosDTO){
        try{

            cursosService.cadastra(cursosDTO);
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
    @Path("/cursos/{nome}")
    public Response relatorioCurso(@PathParam("nome") String nm_curso){
        try{
            List<Cursos> l= cursosService.relatorio(
                    nm_curso);

            return Response.status(Response.Status.OK).entity(l).build();
        }catch (SQLException e){
            Map<String, String> erro = new HashMap<>();
            erro.put("mensagem", "Erro ao conectar.");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(erro).build();
        }catch (IllegalArgumentException e){
            Map<String, String> erro = new HashMap<>();
            erro.put("erro", "Curso não encontrado");
            return Response.status(Response.Status.NOT_FOUND).entity(erro).build();
        }
    }
    @DELETE
    @Path("/cursos/delete/{nc}")
    public Response RemoverCurso(@PathParam("nc") String nm_curso){
        try {
            cursosService.Remover(nm_curso);
            return  Response.status(Response.Status.OK)
                    .entity("Removido com sucesso").build();
        }catch (SQLException e){
            Map<String, String> erro = new HashMap<>();
            erro.put("erro",e.getMessage());
            return Response.serverError().entity(erro).build();
        }catch (IllegalArgumentException e){
            Map<String, String> erro = new HashMap<>();
            erro.put("erro", "Curso não encontrado");
            return Response.status(Response.Status.NOT_FOUND).entity(erro).build();
        }
    }
    @PUT
    @Path("/cursos/atualizar")
    public Response atualizarCurso(Cursos cursos){
        try{
            cursosService.Updante(cursos.getId_curso(),
                    cursos.getNm_curso(), cursos.getUrl_curso(), cursos.getGratuito(),cursos.getDuracao(), cursos.getId_carreira());

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
