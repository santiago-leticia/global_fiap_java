package org.acme.model.DTO;

public class Teste_QuestaoDTO {
    private  String texto_questao;
    private String tipo_questao;


    public Teste_QuestaoDTO() {}

    public String getTexto_questao() {
        return texto_questao;
    }

    public void setTexto_questao(String texto_questao) {
        this.texto_questao = texto_questao;
    }

    public String getTipo_questao() {
        return tipo_questao;
    }

    public void setTipo_questao(String tipo_questao) {
        this.tipo_questao = tipo_questao;
    }
}
