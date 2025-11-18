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
    public Response CadastrarQuestao(@QueryParam("textoQuestao") String t_q){
        try{
            Teste_QuestaoDTO testeQuestaoDTO= new Teste_QuestaoDTO();
            testeQuestaoDTO.setTexto_questao(t_q);
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
    @Path("teste")
    public Response relatorio_carreira(@QueryParam("id_questao") int id){
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
    @Path("/deleta")
    public Response RemoverQuestao(@QueryParam("id_questao") int id){
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
    @Path("/atualizar")
    public Response atualizarQuestao(Teste_Questao testeQuestao){
        try{
            testeQuestaoService.UpdanteQuestao(testeQuestao);

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
