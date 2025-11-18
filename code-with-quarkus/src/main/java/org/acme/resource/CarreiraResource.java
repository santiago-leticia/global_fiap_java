package org.acme.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.model.Carreira;
import org.acme.model.DTO.CarreiraDTO;
import org.acme.service.CarreiraService;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/futureForge")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CarreiraResource {

    @Inject
    CarreiraService carreiraService;

    @POST
    @Path("/carreira")
    public Response adicionarCarreira(@QueryParam("nome") String n,
                                      @QueryParam("ano_formacao") int ano,
                                      @QueryParam("area_trabalho") String a_t,
                                      @QueryParam("informacao_trabalho") String i_t, @QueryParam("dica_treino") String d_t){
        try{
            CarreiraDTO carreiraDTO= new CarreiraDTO();
            carreiraDTO.setNome_carreira(n);
            carreiraDTO.setAno_formacao(ano);
            carreiraDTO.setArea_de_trabalho(a_t);
            carreiraDTO.setInformacao_trabalho(i_t);
            carreiraDTO.setDica_treino(d_t);
            carreiraService.inserirCarreira(carreiraDTO);
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
    @Path("resultado/{id}")
    public Response relatorio_carreira(@PathParam("id") int id, @QueryParam("nome") String nome){
        try{
            List<Carreira> l= carreiraService.relatorioCarreira(id,nome);
            return Response.status(Response.Status.OK).entity(l).build();
        }catch (SQLException e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao conectar").build();
        }catch (IllegalArgumentException e){
            Map<String, String> erro = new HashMap<>();
            erro.put("erro", "Carreira não encontrado");
            return Response.status(Response.Status.NOT_FOUND).entity(erro).build();
        }
    }
    @DELETE
    @Path("/delete/{id}")
    public Response RemoverCarreira(@PathParam("id") int id,@QueryParam("nome") String nome){
        try {
            carreiraService.RemoverCarreira(id, nome);
            return  Response.status(Response.Status.OK)
                    .entity("Removido com sucesso").build();
        }catch (SQLException e){
            Map<String, String> erro = new HashMap<>();
            erro.put("erro",e.getMessage());
            return Response.serverError().entity(erro).build();
        }catch (IllegalArgumentException e){
            Map<String, String> erro = new HashMap<>();
            erro.put("erro", "Carreira não encontrado");
            return Response.status(Response.Status.NOT_FOUND).entity(erro).build();
        }
    }
    @PUT
    @Path("/atualizar/{id}")
    public Response atualizarUsuario(@PathParam("id") int id, @QueryParam("nome") String n,
                                     @QueryParam("ano") int ano,
                                     @QueryParam("area_trabalho") String area, @QueryParam("informacao_curso") String in,@QueryParam("dicas") String di){
        try{
            carreiraService.UpdanteCarreira(id, n, ano, area, in, di);

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
