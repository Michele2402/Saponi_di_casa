package it.mfm.model;

import java.sql.Date;

public class OrderBean {
    private int id;
    private double totale;
    private Date data_creazione;
    private String utente_username;

    public double getTotale() {
        return totale;
    }

    public void setTotale(double totale) {
        this.totale = totale;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getData_creazione() {
        return data_creazione;
    }

    public void setData_creazione(Date data_creazione) {
        this.data_creazione = data_creazione;
    }

    public String getUtente_username() {
        return utente_username;
    }

    public void setUtente_username(String utente_username) {
        this.utente_username = utente_username;
    }
}
