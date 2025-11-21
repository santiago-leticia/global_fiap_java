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
    @Path("/questao/cadastrar")
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
    @Path("/questao/{idquestao}")
    public Response relatorio_questao(@PathParam("idquestao") int id_questao){
        try{
            List<Teste_Questao> l= testeQuestaoService.relatorioQuestao(id_questao);
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
    @Path("/questao/deleta/{id}")
    public Response removerQuestao(@PathParam("id") int id_questao){
        try {
            testeQuestaoService.RemoverQuestao(id_questao);
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
    @Path("/questao/atualizar")
    public Response atualizarQuestao(Teste_Questao testeQuestao){
        try{
            testeQuestaoService.UpdanteQuestao(
                    testeQuestao.getId_questao(),
                    testeQuestao.getTexto_questao(),
                    testeQuestao.getTipo_questao());

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
