package org.oslomet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Register {
    // prosessor liste
    private static ObservableList<Komponent> prosessorListe = FXCollections.observableArrayList(
            new Komponent("Intel",100), new Komponent("AMD",200));

    // skjermkort liste
    private static ObservableList<Komponent> skjermkortListe = FXCollections.observableArrayList(
            new Komponent("Nvidia", 120), new Komponent("AMD",300));

    // minne liste
    private static ObservableList<Komponent> minneListe = FXCollections.observableArrayList(
            new Komponent("Corsair", 120), new Komponent("Samsung",300));

    // harddisk liste
    private static ObservableList<Komponent> harddiskListe = FXCollections.observableArrayList(
            new Komponent("Samsung SSD (500 GB)", 120), new Komponent("Samsung HDD (1000 GB)",300));

    // tastatur liste
    private static ObservableList<Komponent> tastaturListe = FXCollections.observableArrayList(
            new Komponent("Logitech Keyboard", 300), new Komponent("Logitech Gamings",500));

    // datamus liste
    private static ObservableList<Komponent> datamusListe = FXCollections.observableArrayList(
            new Komponent("Logitech trådløs mus", 120), new Komponent("Logitech gaming mus",300));

    // skjerm liste
    private static ObservableList<Komponent> skjermListe = FXCollections.observableArrayList(
            new Komponent("Samsung HD Skjerm", 120), new Komponent("Dell HD Skjerm",300));

    // ferdig konfiguerte datamaskiner
    private static ObservableList<Datamaskin> datamaskinListe = FXCollections.observableArrayList();

    // get metoder
    public static ObservableList<Komponent> getProsessorListe() {
        return prosessorListe;
    }

    public static ObservableList<Komponent> getSkjermkortListe() {
        return skjermkortListe;
    }

    public static ObservableList<Komponent> getMinneListe() {
        return minneListe;
    }

    public static ObservableList<Komponent> getHarddiskListe() {
        return harddiskListe;
    }

    public static ObservableList<Komponent> getTastaturListe() {
        return tastaturListe;
    }

    public static ObservableList<Komponent> getDatamusListe() {
        return datamusListe;
    }

    public static ObservableList<Komponent> getSkjermListe() {
        return skjermListe;
    }

    public static ObservableList<Datamaskin> getDatamaskinListe() {
        return datamaskinListe;
    }

    // set metoder
    public static void setProsessorListe(String navn, double pris) {
        prosessorListe.add(new Komponent(navn,pris));
    }

    public static void setSkjermkortListe(String navn, double pris) {
        skjermkortListe.add(new Komponent(navn,pris));
    }

    public static void setMinneListe(String navn, double pris) {
        minneListe.add(new Komponent(navn,pris));
    }

    public static void setHarddiskListe(String navn, double pris) {
        harddiskListe.add(new Komponent(navn,pris));
    }

    public static void setTastaturListe(String navn, double pris) {
        tastaturListe.add(new Komponent(navn,pris));
    }

    public static void setDatamusListe(String navn, double pris) {
        datamusListe.add(new Komponent(navn,pris));
    }

    public static void setSkjermListe(String navn, double pris) {
        skjermListe.add(new Komponent(navn,pris));
    }

    public static void setDatamaskinListe(Datamaskin datamaskin) {
        datamaskinListe.add(datamaskin);
    }
}