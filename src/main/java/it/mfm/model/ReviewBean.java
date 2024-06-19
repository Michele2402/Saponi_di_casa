package it.mfm.model;

import java.sql.Date;

public class ReviewBean {
    private int id;
    private String utente_username;
    private int valutazione;
    private String testo;
    private Date data;
    private int prodotto_id;

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public int getProdotto_id() {
        return prodotto_id;
    }

    public void setProdotto_id(int prodotto_id) {
        this.prodotto_id = prodotto_id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getValutazione() {
        return valutazione;
    }

    public void setValutazione(int valutazione) {
        this.valutazione = valutazione;
    }

    public String getUtente_username() {
        return utente_username;
    }

    public void setUtente_username(String utente_username) {
        this.utente_username = utente_username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
