package org.oslomet;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class Komponent {
    private SimpleStringProperty navn;
    private SimpleDoubleProperty pris;

    public Komponent(String navn, double pris) {
        this.navn = new SimpleStringProperty(navn);
        this.pris = new SimpleDoubleProperty(pris);
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

    @Override
    public String toString() {
        return navn.getValue()+", "+pris.getValue()+" kr";
    }
}
