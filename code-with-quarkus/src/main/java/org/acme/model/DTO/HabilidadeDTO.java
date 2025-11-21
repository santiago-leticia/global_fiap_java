package org.acme.model.DTO;

public class HabilidadeDTO {
    private String nm_habilidade;
    private String dm_mercado;
    private String nm_categoria;

    public HabilidadeDTO() {
    }

    public String getNm_habilidade() {
        return nm_habilidade;
    }

    public void setNm_habilidade(String nm_habilidade) {
        this.nm_habilidade = nm_habilidade;
    }

    public String getDm_mercado() {
        return dm_mercado;
    }

    public void setDm_mercado(String dm_mercado) {
        this.dm_mercado = dm_mercado;
    }

    public String getNm_categoria() {
        return nm_categoria;
    }

    public void setNm_categoria(String nm_categoria) {
        this.nm_categoria = nm_categoria;
    }
}
