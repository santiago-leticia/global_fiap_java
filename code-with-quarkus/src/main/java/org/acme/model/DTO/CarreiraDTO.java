package org.acme.model.DTO;

public class CarreiraDTO {
    private String nome_carreira;
    private int ano_formacao;
    private String area_de_trabalho;
    private String informacao_trabalho;
    private String dica_treino;
    private Double salario_max;
    private Double salario_min;
    private int tempo_preparo_meses;

    public CarreiraDTO() {}


    public String getNome_carreira() {
        return nome_carreira;
    }

    public void setNome_carreira(String nome_carreira) {
        this.nome_carreira = nome_carreira;
    }

    public int getAno_formacao() {
        return ano_formacao;
    }

    public void setAno_formacao(int ano_formacao) {
        this.ano_formacao = ano_formacao;
    }

    public String getArea_de_trabalho() {
        return area_de_trabalho;
    }

    public void setArea_de_trabalho(String area_de_trabalho) {
        this.area_de_trabalho = area_de_trabalho;
    }

    public String getInformacao_trabalho() {
        return informacao_trabalho;
    }

    public void setInformacao_trabalho(String informacao_trabalho) {
        this.informacao_trabalho = informacao_trabalho;
    }

    public String getDica_treino() {
        return dica_treino;
    }

    public void setDica_treino(String dica_treino) {
        this.dica_treino = dica_treino;
    }

    public Double getSalario_max() {
        return salario_max;
    }

    public void setSalario_max(Double salario_max) {
        this.salario_max = salario_max;
    }

    public Double getSalario_min() {
        return salario_min;
    }

    public void setSalario_min(Double salario_min) {
        this.salario_min = salario_min;
    }

    public int getTempo_preparo_meses() {
        return tempo_preparo_meses;
    }

    public void setTempo_preparo_meses(int tempo_preparo_meses) {
        this.tempo_preparo_meses = tempo_preparo_meses;
    }
}
