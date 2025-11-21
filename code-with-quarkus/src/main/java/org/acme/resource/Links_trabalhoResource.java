package org.acme.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.model.Cursos;
import org.acme.model.DTO.CursosDTO;
import org.acme.model.DTO.Links_TrabalhoDTO;
import org.acme.model.Links_Trabalho;
import org.acme.service.CursosService;
import org.acme.service.Links_trabalhoService;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/futureForge")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class Links_trabalhoResource {



    @Inject
    Links_trabalhoService linksTrabalhoService;

    @POST
    @Path("/link")
    public Response CadastrarLink(Links_TrabalhoDTO linksTrabalhoDTO){
        try{

            linksTrabalhoService.cadastra(linksTrabalhoDTO);
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
    @Path("/link/informacao/{vagaNm}")
    public Response relatorioLink(@PathParam("vagaNm") String nm_vaga){
        try{
            List<Links_Trabalho> l= linksTrabalhoService.relatorio(nm_vaga);

            return Response.status(Response.Status.OK).entity(l).build();
        }catch (SQLException e){
            Map<String, String> erro = new HashMap<>();
            erro.put("mensagem", "Erro ao conectar.");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(erro).build();
        }catch (IllegalArgumentException e){
            Map<String, String> erro = new HashMap<>();
            erro.put("erro", "Vaga não encontrada");
            return Response.status(Response.Status.NOT_FOUND).entity(erro).build();
        }
    }
    @DELETE
    @Path("/link/deleta/{nmvaga}")
    public Response Removervaga(@PathParam("nmvaga") String nm_vaga){
        try {
            linksTrabalhoService.Remover(nm_vaga);
            return  Response.status(Response.Status.OK)
                    .entity("Removido com sucesso").build();
        }catch (SQLException e){
            Map<String, String> erro = new HashMap<>();
            erro.put("erro",e.getMessage());
            return Response.serverError().entity(erro).build();
        }catch (IllegalArgumentException e){
            Map<String, String> erro = new HashMap<>();
            erro.put("erro", "Vaga não encontrado");
            return Response.status(Response.Status.NOT_FOUND).entity(erro).build();
        }
    }
    @PUT
    @Path("/vaga/atualizar")
    public Response atualizarlink(Links_Trabalho linksTrabalho){
        try{
            linksTrabalhoService.Updante(linksTrabalho.getId_links(),
                    linksTrabalho.getNm_empresa(), linksTrabalho.getNm_vaga(),
                    linksTrabalho.getLink_url(),linksTrabalho.getId_carreira());

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
