package org.acme.model;

public class Links_Trabalho {
    private int id_links;
    private String nm_empresa;
    private String nm_vaga;
    private String link_url;
    private int id_carreira;

    public Links_Trabalho() {
    }

    public int getId_links() {
        return id_links;
    }

    public void setId_links(int id_links) {
        this.id_links = id_links;
    }

    public String getNm_empresa() {
        return nm_empresa;
    }

    public void setNm_empresa(String nm_empresa) {
        this.nm_empresa = nm_empresa;
    }

    public String getNm_vaga() {
        return nm_vaga;
    }

    public void setNm_vaga(String nm_vaga) {
        this.nm_vaga = nm_vaga;
    }

    public String getLink_url() {
        return link_url;
    }

    public void setLink_url(String link_url) {
        this.link_url = link_url;
    }

    public int getId_carreira() {
        return id_carreira;
    }

    public void setId_carreira(int id_carreira) {
        this.id_carreira = id_carreira;
    }
}
