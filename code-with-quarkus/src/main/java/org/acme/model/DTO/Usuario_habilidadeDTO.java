package org.acme.model.DTO;

public class Usuario_habilidadeDTO {
    private int id_usuario;
    private int id_habilidade;
    private String lv_proficiencia;

    public Usuario_habilidadeDTO() {
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getId_habilidade() {
        return id_habilidade;
    }

    public void setId_habilidade(int id_habilidade) {
        this.id_habilidade = id_habilidade;
    }

    public String getLv_proficiencia() {
        return lv_proficiencia;
    }

    public void setLv_proficiencia(String lv_proficiencia) {
        this.lv_proficiencia = lv_proficiencia;
    }
}
