package org.acme.model.DTO;

public class Teste_opcaoDTO {
    private int id_questao;
    private String texto;
    private int valor_escolha;
    private int id_carreira;

    public Teste_opcaoDTO() {}

    public int getId_questao() {
        return id_questao;
    }

    public void setId_questao(int id_questao) {
        this.id_questao = id_questao;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public int getValor_escolha() {
        return valor_escolha;
    }

    public void setValor_escolha(int valor_escolha) {
        this.valor_escolha = valor_escolha;
    }

    public int getId_carreira() {
        return id_carreira;
    }

    public void setId_carreira(int id_carreira) {
        this.id_carreira = id_carreira;
    }
}
