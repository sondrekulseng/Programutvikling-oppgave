package org.oslomet;

public class Komponent {
    private String navn;
    private double pris;

    public Komponent(String navn, double pris) {
        this.navn = navn;
        this.pris = pris;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public double getPris() {
        return pris;
    }

    public void setPris(double pris) {
        this.pris = pris;
    }

    @Override
    public String toString() {
        return navn+", "+pris+" kr";
    }
}
