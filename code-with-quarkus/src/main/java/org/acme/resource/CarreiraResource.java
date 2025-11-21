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
    public Response adicionarCarreira(CarreiraDTO carreiraDTO){
        try{
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
    @Path("carreira/resultado")
    public Response relatorio_carreira(Carreira carreira){
        try{
            List<Carreira> l= carreiraService.relatorioCarreira(carreira.getId_carreira(),
                    carreira.getNome_carreira());
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
    @Path("carreira/delete/{id}")
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
    @Path("carreira/atualizar/{id}")
    public Response atualizarUsuario(@PathParam("id") int id, CarreiraDTO carreiraDTO){
        try{
            carreiraService.UpdanteCarreira(id,carreiraDTO.getNome_carreira(),
                    carreiraDTO.getAno_formacao(), carreiraDTO.getArea_de_trabalho(),
                    carreiraDTO.getInformacao_trabalho(), carreiraDTO.getDica_treino(),
                    carreiraDTO.getSalario_max(), carreiraDTO.getSalario_min(), carreiraDTO.getTempo_preparo_meses());

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
