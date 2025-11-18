package org.acme.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.model.DTO.Teste_QuestaoDTO;
import org.acme.model.Teste_Questao;
import org.acme.service.Teste_questaoService;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/futureForge")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class Teste_questaoResource {
    @Inject
    Teste_questaoService testeQuestaoService;

    @POST
    @Path("/carreira")
    public Response CadastrarQuestao(Teste_QuestaoDTO testeQuestaoDTO){
        try{
            testeQuestaoService.inserirQuestao(testeQuestaoDTO);
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
    @Path("/teste_questao/{id_questao}")
    public Response relatorio_carreira(@PathParam("id_questao") int id){
        try{
            List<Teste_Questao> l= testeQuestaoService.relatorioQuestao(id);
            return Response.status(Response.Status.OK).entity(l).build();
        }catch (SQLException e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao conectar").build();
        }catch (IllegalArgumentException e){
            Map<String, String> erro = new HashMap<>();
            erro.put("erro", "Questao não encontrado");
            return Response.status(Response.Status.NOT_FOUND).entity(erro).build();
        }
    }
    @DELETE
    @Path("/deleta/{id_questao}")
    public Response RemoverQuestao(@PathParam("id_questao") int id){
        try {
            testeQuestaoService.RemoverQuestao(id);
            return  Response.status(Response.Status.OK)
                    .entity("Removido com sucesso").build();
        }catch (SQLException e){
            Map<String, String> erro = new HashMap<>();
            erro.put("erro",e.getMessage());
            return Response.serverError().entity(erro).build();
        }catch (IllegalArgumentException e){
            Map<String, String> erro = new HashMap<>();
            erro.put("erro", "Questao não encontrado");
            return Response.status(Response.Status.NOT_FOUND).entity(erro).build();
        }
    }
    @PUT
    @Path("/atualizar/{id}")
    public Response atualizarQuestao(@PathParam("id") int id, String tx){
        try{
            testeQuestaoService.UpdanteQuestao(id, tx);

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
