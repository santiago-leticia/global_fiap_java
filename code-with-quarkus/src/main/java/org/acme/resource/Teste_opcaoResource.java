package org.acme.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.model.DTO.Teste_opcaoDTO;
import org.acme.model.Teste_opcao;
import org.acme.service.Teste_opcaoService;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/futureForge")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class Teste_opcaoResource {
    @Inject
    Teste_opcaoService testeOpcaoService;

    @POST
    @Path("/carreira")
    public Response CadastraOpcao(@QueryParam("id_questao") int id_questao,
                                  @QueryParam("texto") String texto,
                                  @QueryParam("valor_escolha") int valor_escolha,
                                  @QueryParam("id_carreira") int id_carreira){
        try{
            Teste_opcaoDTO testeOpcaoDTO= new Teste_opcaoDTO();
            testeOpcaoDTO.setId_questao(id_questao);
            testeOpcaoDTO.setTexto(texto);
            testeOpcaoDTO.setValor_escolha(valor_escolha);
            testeOpcaoDTO.setId_carreira(id_carreira);
            testeOpcaoService.inseriropcao(testeOpcaoDTO);
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
            List<Teste_opcao> l= testeOpcaoService.relatorioopcao(id);
            return Response.status(Response.Status.OK).entity(l).build();
        }catch (SQLException e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao conectar").build();
        }catch (IllegalArgumentException e){
            Map<String, String> erro = new HashMap<>();
            erro.put("erro", "Opção não encontrado");
            return Response.status(Response.Status.NOT_FOUND).entity(erro).build();
        }
    }
    @DELETE
    @Path("/deleta")
    public Response RemoverQuestao(@QueryParam("id_questao") int id){
        try {
            testeOpcaoService.RemoverOpcao(id);
            return  Response.status(Response.Status.OK)
                    .entity("Removido com sucesso").build();
        }catch (SQLException e){
            Map<String, String> erro = new HashMap<>();
            erro.put("erro",e.getMessage());
            return Response.serverError().entity(erro).build();
        }catch (IllegalArgumentException e){
            Map<String, String> erro = new HashMap<>();
            erro.put("erro", "Opção não encontrado");
            return Response.status(Response.Status.NOT_FOUND).entity(erro).build();
        }
    }
    @PUT
    @Path("/atualizar")
    public Response atualizarQuestao(Teste_opcao testeOpcao){
        try{
            testeOpcaoService.UpdanteOpcao(testeOpcao);

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
