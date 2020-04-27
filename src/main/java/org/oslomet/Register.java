package org.oslomet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.Comparator;
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
            new Komponent("Samsung HD",500, "skjerm"),
            new Komponent("A prosessor",200,"prosessor"),
            new Komponent("A grafikkkort",700,"skjermkort"),
            new Komponent("A Ram",50, "minne"),
            new Komponent("A HDD",1200,"harddisk"),
            new Komponent("A Keyboard",4300,"tastatur"),
            new Komponent("A gaming",300, "datamus"),
            new Komponent("A HD",700, "skjerm")
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

    // remove metoder
    public static void slettKomponent(int index) {
        komponentListe.remove(index);
    }

    // filtrer komponentliste etter kategori
    public static ObservableList<Komponent> filtrerListe(String filter) {
        ObservableList<Komponent> liste = komponentListe.stream()
                    .filter(r->r.getKategori().toLowerCase().equals(filter.toLowerCase()))
                    .collect(Collectors.toCollection(FXCollections::observableArrayList));
        return liste;
    }

    // sorter liste alfabetisk, etter pris...
    public static ObservableList<Komponent> sorterListe(String sort, ObservableList<Komponent> list) {
        ObservableList<Komponent> liste = list;
        if (sort.equals("Pris (lav til høy)")) {
            liste = list.stream()
                    .sorted(Comparator.comparingDouble(Komponent::getPris))
                    .collect(Collectors.toCollection(FXCollections::observableArrayList));
        } else if (sort.equals("Pris (høy til lav)")) {
            liste = list.stream()
                    .sorted(Comparator.comparingDouble(Komponent::getPris)
                    .reversed())
                    .collect(Collectors.toCollection(FXCollections::observableArrayList));
        }
        return liste;

    }
}
