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
    @Path("/opacao/adicionar")
    public Response cadastraOpcao(Teste_opcaoDTO testeOpcaoDTO){
        try{
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
    @Path("/opcao/relatorio/{id}")
    public Response relatorio_opcao(@PathParam("id") int id_opcao){
        try{
            List<Teste_opcao> l= testeOpcaoService.relatorioopcao(id_opcao);
            return Response.status(Response.Status.OK).entity(l).build();
        }catch (SQLException e){
            Map<String, String> erro = new HashMap<>();
            erro.put("mensagem", "Erro ao conectar.");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(erro).build();
        }catch (IllegalArgumentException e){
            Map<String, String> erro = new HashMap<>();
            erro.put("erro", "Opção não encontrado");
            return Response.status(Response.Status.NOT_FOUND).entity(erro).build();
        }
    }
    @DELETE
    @Path("/opcao/deleta/{idopcao}")
    public Response removerOpcao(@PathParam("idopcao") int id){
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
    @Path("/opcao/atualizar")
    public Response atualizarOpcao(Teste_opcao testeOpcao){
        try{
            testeOpcaoService.UpdanteOpcao(testeOpcao.getId_questao(),
                    testeOpcao.getId_carreira(),testeOpcao.getTexto(),
                    testeOpcao.getValor_escolha(),
                    testeOpcao.getId_opcao());

            return Response.status(Response.Status.OK)
                    .entity("Dados atualizando")
                    .build();
        }catch (SQLException e){
            Map<String, String> erro= new HashMap<>();
            erro.put("mensagem", "Erro no servidor.");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(erro).build();
        }catch (IllegalArgumentException e){
            return Response.
                    status(422)
                    .entity(e.getMessage()).build();
        }
    }
}
