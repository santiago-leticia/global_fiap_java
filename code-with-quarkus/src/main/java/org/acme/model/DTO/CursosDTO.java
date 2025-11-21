package org.acme.model.DTO;

public class CursosDTO {
    private String nm_curso;
    private String url_curso;
    private String gratuito;
    private int duracao;
    private int id_carreira;

    public CursosDTO() {
    }

    public String getNm_curso() {
        return nm_curso;
    }

    public void setNm_curso(String nm_curso) {
        this.nm_curso = nm_curso;
    }

    public String getUrl_curso() {
        return url_curso;
    }

    public void setUrl_curso(String url_curso) {
        this.url_curso = url_curso;
    }

    public String getGratuito() {
        return gratuito;
    }

    public void setGratuito(String gratuito) {
        this.gratuito = gratuito;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public int getId_carreira() {
        return id_carreira;
    }

    public void setId_carreira(int id_carreira) {
        this.id_carreira = id_carreira;
    }
}
