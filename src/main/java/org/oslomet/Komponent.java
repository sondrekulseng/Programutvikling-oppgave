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

    public void setPris(double pris) throws InvalidPriceException {
        if (validerPris(pris))
            this.pris.set(pris);
        else
            throw new InvalidPriceException("Pris må være et positivt tall");
    }

    public String getKategori() {
        return kategori.getValue();
    }

    public void setKategori(String kategori) throws InvalidCategoriException {
        if (validerKategori(kategori)) {
            this.kategori.set(kategori.toLowerCase());
        } else {
            throw new InvalidCategoriException("Ikke en gyldig kategori");
        }

    }

    // valider kategori
    public boolean validerKategori(String txt) {
        String[] k = {"prosessor","skjermkort","minne","harddisk","tastatur","datamus","skjerm"};
        boolean gyldig = false;
        for (String verdi:k) {
            if (verdi.equals(txt.toLowerCase())) {
                gyldig = true;
                break;
            }
        }
        return gyldig;
    }

    // valider pris
    public boolean validerPris(double pris) {
        if (pris<1)
            return false;
        else
            return true;
    }

    @Override
    public String toString() {
        return navn.getValue()+", "+pris.getValue()+" kr";
    }
}
