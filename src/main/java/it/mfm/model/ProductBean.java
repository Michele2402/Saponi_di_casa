package it.mfm.model;

public class ProductBean {
    private int id;
    private String nome;
    private String descrizione;
    private double prezzo;
    private String immagine;
    private int categoria_id;

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String name) {
        this.nome = name;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public void setImmagine(String immagine) {
        this.immagine = immagine;
    }

    public void setCategoria_id(int categoria_id) {
        this.categoria_id = categoria_id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public int getId() {
        return id;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public String getImmagine() {
        return immagine;
    }

    public int getCategoria_id() {
        return categoria_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductBean that = (ProductBean) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

}
