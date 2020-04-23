package org.oslomet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.stream.Collectors;

public class Register {
    // komponent liste
    private static ObservableList<Komponent> komponentListe = FXCollections.observableArrayList(
            new Komponent("Intel i5",100,"prosessor"),
            new Komponent("Nvidea",400,"skjermkort"),
            new Komponent("Samsun Ram",500, "minne"),
            new Komponent("Samsung HDD",100,"harddisk"),
            new Komponent("Logitech Keyboard",400,"tastatur"),
            new Komponent("Logitech gaming",500, "datamus"),
            new Komponent("Samsung HD",500, "skjerm")
    );

    // datamaskin liste
    private static ObservableList<Datamaskin> datamaskinListe = FXCollections.observableArrayList();

    // get metoder
    public static ObservableList<Komponent> getKomponentListe() {
        return komponentListe;
    }

    public static ObservableList<Datamaskin> getDatamaskinListe() {
        return datamaskinListe;
    }

    // set metoder
    public static void setKomponentListe(Komponent k) {
        komponentListe.add(k);
    }

    public static void setDatamaskinListe(Datamaskin d) {
        datamaskinListe.add(d);
    }

    // filtrer komponentliste etter kategori
    public static ObservableList<Komponent> filtrerListe(String filter) {
        ObservableList<Komponent> liste = komponentListe.stream()
                    .filter(r->r.getKategori().toLowerCase().equals(filter.toLowerCase()))
                    .collect(Collectors.toCollection(FXCollections::observableArrayList));
        return liste;
    }
}
