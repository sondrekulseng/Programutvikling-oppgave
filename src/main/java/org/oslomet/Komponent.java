package org.oslomet;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class Komponent {
    private SimpleStringProperty navn, kategori;
    private SimpleDoubleProperty pris;

    public Komponent(String navn, double pris, String kategori) {
        this.navn = new SimpleStringProperty(navn);
        this.pris = new SimpleDoubleProperty(pris);
        this.kategori = new SimpleStringProperty(kategori);
    }

    public String getNavn() {
        return navn.getValue();
    }

    public void setNavn(String navn) {
        this.navn.set(navn);
    }

    public double getPris() {
        return pris.getValue();
    }

    public void setPris(double pris) {
        this.pris.set(pris);
    }

    public String getKategori() {
        return kategori.getValue();
    }

    public void setKategori(String kategori) {
        this.kategori.set(kategori);
    }

    @Override
    public String toString() {
        return navn.getValue()+", "+pris.getValue()+" kr";
    }
}
