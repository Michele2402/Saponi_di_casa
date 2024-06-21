package it.mfm.fakeModel;

public class ProductBean {

    private String nome;
    private double prezzo;
    private String descrizione;
    private int ID;
    private String immagine;
    private int IDcategoria;


    public int getIDcategoria() {
        return IDcategoria;
    }

    public void setIDcategoria(int IDcategoria) {
        this.IDcategoria = IDcategoria;
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

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getImmagine() {
        return immagine;
    }

    public void setImmagine(String immagine) {
        this.immagine = immagine;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductBean that = (ProductBean) o;

        return ID == that.ID;
    }

}