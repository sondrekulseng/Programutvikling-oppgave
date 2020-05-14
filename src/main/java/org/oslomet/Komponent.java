package org.oslomet;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Komponent implements Serializable {
    private transient SimpleStringProperty navn, kategori;
    private transient SimpleDoubleProperty pris;

    // konstruktør
    public Komponent(String navn, double pris, String kategori) throws InvalidPriceException, InvalidCategoriException {
        setNavn(navn);
        setPris(pris);
        setKategori(kategori);
    }

    // serialisering av objektene
    private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
        s.writeUTF(navn.getValue());
        s.writeDouble(pris.getValue());
        s.writeUTF(kategori.getValue());
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        String navn = s.readUTF();
        double pris = s.readDouble();
        String kategori = s.readUTF();

        this.navn = new SimpleStringProperty(navn);
        this.pris = new SimpleDoubleProperty(pris);
        this.kategori = new SimpleStringProperty(kategori);
    }

    // get metoder
    public String getNavn() {
        return navn.getValue();
    }

    public double getPris() {
        return pris.getValue();
    }

    public String getKategori() {
        return kategori.getValue();
    }

    // set metoder
    public void setNavn(String navn) {
        this.navn = new SimpleStringProperty(navn);
    }

    public void setPris(double pris) throws InvalidPriceException {
        if (pris>=100)
            // pris er 100 kr eller mer
            this.pris = new SimpleDoubleProperty(pris);
        else
            // pris er mindre enn 100 kr
            throw new InvalidPriceException("Pris må være minst 100 kr");
    }

    public void setKategori(String kategori) throws InvalidCategoriException {
        if (gyldigKategori(kategori))
            // gyldig kategori
            this.kategori = new SimpleStringProperty(kategori.toLowerCase());
        else
            // ugyldig kategori
            throw new InvalidCategoriException(kategori+" er ikke en gyldig kategori");
    }

    // validering kategori
    public boolean gyldigKategori(String txt) {
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

    // toString
    @Override
    public String toString() {
        return navn.getValue()+", "+pris.getValue()+" kr";
    }
}
