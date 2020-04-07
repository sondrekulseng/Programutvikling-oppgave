package org.oslomet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Register {
    // prosessorliste
    private static ObservableList<Komponent> prosessorListe = FXCollections.observableArrayList(
            new Komponent("Intel",100), new Komponent("AMD",200));

    // skjermkortliste
    private static ObservableList<Komponent> skjermkortListe = FXCollections.observableArrayList(
            new Komponent("Nvidia", 120), new Komponent("AMD",300));

    // minneliste
    private static ObservableList<Komponent> minneListe = FXCollections.observableArrayList(
            new Komponent("Corsair", 120), new Komponent("Samsung",300));

    // lagrinsliste
    private static ObservableList<Komponent> lagringListe = FXCollections.observableArrayList(
            new Komponent("Samsung SSD (500 GB)", 120), new Komponent("Samsung HDD (1000 GB)",300));

    // lagrinsliste
    private static ObservableList<Komponent> tastaturListe = FXCollections.observableArrayList(
            new Komponent("Logitech Keyboard", 300), new Komponent("Logitech Gamings",500));

    // lagrinsliste
    private static ObservableList<Komponent> datamusListe = FXCollections.observableArrayList(
            new Komponent("Logitech trådløs mus", 120), new Komponent("Logitech gaming mus",300));

    // lagrinsliste
    private static ObservableList<Komponent> skjermListe = FXCollections.observableArrayList(
            new Komponent("Samsung HD Skjerm", 120), new Komponent("Dell HD Skjerm",300));

    public static ObservableList<Komponent> getProsessorListe() {
        return prosessorListe;
    }

    public static ObservableList<Komponent> getSkjermkortListe() {
        return skjermkortListe;
    }

    public static ObservableList<Komponent> getMinneListe() {
        return minneListe;
    }

    public static ObservableList<Komponent> getLagringListe() {
        return lagringListe;
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
}
