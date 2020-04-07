package org.oslomet;

import javafx.beans.property.SimpleDoubleProperty;

public class Datamaskin {
    private Komponent prosessor, skjermkort, minne, harddisk, tastatur, datamus, skjerm;
    private SimpleDoubleProperty totPris;

    public Datamaskin(Komponent prosessor, Komponent skjermkort, Komponent minne, Komponent harddisk, Komponent tastatur, Komponent datamus, Komponent skjerm, double totPris) {
        this.prosessor = prosessor;
        this.skjermkort = skjermkort;
        this.minne = minne;
        this.harddisk = harddisk;
        this.tastatur = tastatur;
        this.datamus = datamus;
        this.skjerm = skjerm;
        this.totPris = new SimpleDoubleProperty(totPris);
    }

    public String getProsessor() {
        return prosessor.getNavn();
    }

    public void setProsessor(Komponent prosessor) {
        this.prosessor = prosessor;
    }

    public String getSkjermkort() {
        return skjermkort.getNavn();
    }

    public void setSkjermkort(Komponent skjermkort) {
        this.skjermkort = skjermkort;
    }

    public String getMinne() {
        return minne.getNavn();
    }

    public void setMinne(Komponent minne) {
        this.minne = minne;
    }

    public String getHarddisk() {
        return harddisk.getNavn();
    }

    public void setHarddisk(Komponent harddisk) {
        this.harddisk = harddisk;
    }

    public String getTastatur() {
        return tastatur.getNavn();
    }

    public void setTastatur(Komponent tastatur) {
        this.tastatur = tastatur;
    }

    public String getDatamus() {
        return datamus.getNavn();
    }

    public void setDatamus(Komponent datamus) {
        this.datamus = datamus;
    }

    public String getSkjerm() {
        return skjerm.getNavn();
    }

    public void setSkjerm(Komponent skjerm) {
        this.skjerm = skjerm;
    }

    public double getTotPris() {
        return totPris.getValue();
    }
}
