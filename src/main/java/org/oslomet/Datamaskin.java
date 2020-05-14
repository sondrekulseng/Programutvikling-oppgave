package org.oslomet;

import javafx.beans.property.SimpleDoubleProperty;

public class Datamaskin {
    private Komponent prosessor, skjermkort, minne, harddisk, tastatur, datamus, skjerm;
    private SimpleDoubleProperty totPris;

    // konstruktør
    public Datamaskin(Komponent prosessor, Komponent skjermkort, Komponent minne, Komponent harddisk, Komponent tastatur, Komponent datamus, Komponent skjerm, double totPris) throws InvalidPriceException {
        this.prosessor = prosessor;
        this.skjermkort = skjermkort;
        this.minne = minne;
        this.harddisk = harddisk;
        this.tastatur = tastatur;
        this.datamus = datamus;
        this.skjerm = skjerm;
        setTotPris(totPris);
    }

    // get/set metoder
    public Komponent getProsessor() {
        return prosessor;
    }

    public void setProsessor(Komponent prosessor) {
        this.prosessor = prosessor;
    }

    public Komponent getSkjermkort() {
        return skjermkort;
    }

    public void setSkjermkort(Komponent skjermkort) {
        this.skjermkort = skjermkort;
    }

    public Komponent getMinne() {
        return minne;
    }

    public void setMinne(Komponent minne) {
        this.minne = minne;
    }

    public Komponent getHarddisk() {
        return harddisk;
    }

    public void setHarddisk(Komponent harddisk) {
        this.harddisk = harddisk;
    }

    public Komponent getTastatur() {
        return tastatur;
    }

    public void setTastatur(Komponent tastatur) {
        this.tastatur = tastatur;
    }

    public Komponent getDatamus() {
        return datamus;
    }

    public void setDatamus(Komponent datamus) {
        this.datamus = datamus;
    }

    public Komponent getSkjerm() {
        return skjerm;
    }

    public void setSkjerm(Komponent skjerm) {
        this.skjerm = skjerm;
    }

    public void setTotPris(double totPris) throws InvalidPriceException {
        if (totPris>=700)
            // total prisen er 700 kr eller mer
            this.totPris = new SimpleDoubleProperty(totPris);
        else
            // total pris er mindre enn 700 kr
            throw new InvalidPriceException("Total prisen må være minst 700 kr");
    }

    public double getTotPris() {
        return totPris.getValue();
    }
}
