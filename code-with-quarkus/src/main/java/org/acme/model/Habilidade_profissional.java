package org.acme.model;

public class Habilidade_profissional {
    private int id_habilidade_profissional;
    private  String nivel_habilidade_profissional;
    private String nv_importancia;
    private int id_habilidade;
    private int id_carreira;

    public Habilidade_profissional() {
    }

    public int getId_habilidade_profissional() {
        return id_habilidade_profissional;
    }

    public void setId_habilidade_profissional(int id_habilidade_profissional) {
        this.id_habilidade_profissional = id_habilidade_profissional;
    }

    public String getNivel_habilidade_profissional() {
        return nivel_habilidade_profissional;
    }

    public void setNivel_habilidade_profissional(String nivel_habilidade_profissional) {
        this.nivel_habilidade_profissional = nivel_habilidade_profissional;
    }

    public String getNv_importancia() {
        return nv_importancia;
    }

    public void setNv_importancia(String nv_importancia) {
        this.nv_importancia = nv_importancia;
    }

    public int getId_habilidade() {
        return id_habilidade;
    }

    public void setId_habilidade(int id_habilidade) {
        this.id_habilidade = id_habilidade;
    }

    public int getId_carreira() {
        return id_carreira;
    }

    public void setId_carreira(int id_carreira) {
        this.id_carreira = id_carreira;
    }
}
