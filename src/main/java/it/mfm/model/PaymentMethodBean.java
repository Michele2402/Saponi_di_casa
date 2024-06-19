package it.mfm.model;

import java.util.Date;

public class PaymentMethodBean {

    private int numero_di_carta;
    private String tipo;
    private String cvv;
    private String nome;
    private String cognome;
    private Date data_di_Scadenza;
    private String utente_username;


    public PaymentMethodBean() { }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setData_di_Scadenza(Date data_di_Scadenza) {
        this.data_di_Scadenza = data_di_Scadenza;
    }

    public Date getData_di_Scadenza() {
        return data_di_Scadenza;
    }

    public void setNumero_di_carta(int numero_di_carta) {
        this.numero_di_carta = numero_di_carta;
    }

    public int getNumero_di_carta() {
        return numero_di_carta;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getCvv() {
        return cvv;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setUtente_username(String utente_username) {
        this.utente_username = utente_username;
    }

    public String getUtente_username() {
        return utente_username;
    }


}
