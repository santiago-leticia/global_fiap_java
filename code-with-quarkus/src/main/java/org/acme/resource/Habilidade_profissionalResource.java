package org.acme.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.model.Cursos;
import org.acme.model.DTO.CursosDTO;
import org.acme.model.DTO.Habilidade_profissionalDTO;
import org.acme.model.Habilidade_profissional;
import org.acme.service.CursosService;
import org.acme.service.Habilidade_profissionalService;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/futureForge")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class Habilidade_profissionalResource {



    @Inject
    Habilidade_profissionalService habilidadeProfissionalService;

    @POST
    @Path("/habilidadeprofissional/inseri")
    public Response Inserirhp(Habilidade_profissionalDTO habilidadeProfissionalDTO){
        try{

            habilidadeProfissionalService.cadastra(habilidadeProfissionalDTO);
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
    @Path("/habilidadeprofissional/{id}")
    public Response relatorioHp(@PathParam("id") int id){
        try{
            List<Habilidade_profissional> l= habilidadeProfissionalService.relatorio(id);

            return Response.status(Response.Status.OK).entity(l).build();
        }catch (SQLException e){
            Map<String, String> erro = new HashMap<>();
            erro.put("mensagem", "Erro ao conectar.");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(erro).build();
        }catch (IllegalArgumentException e){
            Map<String, String> erro = new HashMap<>();
            erro.put("erro", "Habilidade Profissional não encontrado");
            return Response.status(Response.Status.NOT_FOUND).entity(erro).build();
        }
    }
    @DELETE
    @Path("/habilidadeprofissional/deleta/{id}")
    public Response RemoverH_p(@PathParam("id") int id){
        try {
            habilidadeProfissionalService.Remover(id);
            return  Response.status(Response.Status.OK)
                    .entity("Removido com sucesso").build();
        }catch (SQLException e){
            Map<String, String> erro = new HashMap<>();
            erro.put("erro",e.getMessage());
            return Response.serverError().entity(erro).build();
        }catch (IllegalArgumentException e){
            Map<String, String> erro = new HashMap<>();
            erro.put("erro", "Habilidade Profissional não encontrado");
            return Response.status(Response.Status.NOT_FOUND).entity(erro).build();
        }
    }
    @PUT
    @Path("/habilidadeprofissional/atualizar")
    public Response atualizarhp(Habilidade_profissional habilidadeProfissional){
        try{
            habilidadeProfissionalService.Updante(habilidadeProfissional.getId_habilidade_profissional(),
                    habilidadeProfissional.getNivel_habilidade_profissional(),
                    habilidadeProfissional.getNv_importancia(),habilidadeProfissional.getId_habilidade(),
                    habilidadeProfissional.getId_carreira());

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
