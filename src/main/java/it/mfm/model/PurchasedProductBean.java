package it.mfm.model;

public class PurchasedProductBean {
    private int id;
    private String nome;
    private double prezzo;
    private int quantita;
    private int ordine_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public int getOrdine_id() {
        return ordine_id;
    }

    public void setOrdine_id(int ordine_id) {
        this.ordine_id = ordine_id;
    }
}
