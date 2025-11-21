package org.acme.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.model.Cursos;
import org.acme.model.DTO.CursosDTO;
import org.acme.model.DTO.HabilidadeDTO;
import org.acme.model.Habilidade;
import org.acme.model.Habilidade_profissional;
import org.acme.service.CursosService;
import org.acme.service.HabilidadeService;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/futureForge")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class HabilidadeResource {



    @Inject
    HabilidadeService habilidadeService;

    @POST
    @Path("/habilidade/inseri")
    public Response habilidade(HabilidadeDTO habilidadeDTO){
        try{

            habilidadeService.cadastra(habilidadeDTO);
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
    @Path("/habilidade/informacao/{nmh}")
    public Response RelatorioHabilidade(@PathParam("nmh") String nm_habilidade){
        try{
            List<Habilidade> l= habilidadeService.relatorio(
                    nm_habilidade);

            return Response.status(Response.Status.OK).entity(l).build();
        }catch (SQLException e){
            Map<String, String> erro = new HashMap<>();
            erro.put("mensagem", "Erro ao conectar.");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(erro).build();
        }catch (IllegalArgumentException e){
            Map<String, String> erro = new HashMap<>();
            erro.put("erro", "Habilidade não encontrado");
            return Response.status(Response.Status.NOT_FOUND).entity(erro).build();
        }
    }
    @DELETE
    @Path("/habilidade/deleta/{det}")
    public Response RemoverHabilidade(@PathParam("det") int id_habilidade, @QueryParam("nm") String nm_habilidade){
        try {
            habilidadeService.Remover(id_habilidade, nm_habilidade);
            return  Response.status(Response.Status.OK)
                    .entity("Removido com sucesso").build();
        }catch (SQLException e){
            Map<String, String> erro = new HashMap<>();
            erro.put("erro",e.getMessage());
            return Response.serverError().entity(erro).build();
        }catch (IllegalArgumentException e){
            Map<String, String> erro = new HashMap<>();
            erro.put("erro", "Habilidade não encontrado");
            return Response.status(Response.Status.NOT_FOUND).entity(erro).build();
        }
    }
    @PUT
    @Path("/habilidade/atualizar")
    public Response atualizarHabilidade(Habilidade habilidade){
        try{
            habilidadeService.Updante(
                    habilidade.getId_habilidade(),
                    habilidade.getNm_habilidade(),
                    habilidade.getDm_mercado(),
                    habilidade.getNm_categoria()
            );

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
