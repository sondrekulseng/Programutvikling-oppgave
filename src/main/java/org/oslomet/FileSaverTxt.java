package org.oslomet;

import javafx.collections.ObservableList;
import java.io.File;
import java.io.PrintWriter;

public class FileSaverTxt {
    public static void save() throws Exception {
        ObservableList<Datamaskin> liste = Register.getDatamaskinListe();
        File file = new File("filbehandling/kunde-backup.txt");
        PrintWriter pw = new PrintWriter(file);
        for(int i=0;i<liste.size();i++) { // kjør igjennom personlisten og skriv alle personene til en .txt fil
            Datamaskin d = liste.get(i);
            // hent komponent verdier
            Komponent prosessor = d.getProsessor();
            Komponent skjermkort = d.getSkjermkort();
            Komponent minne = d.getMinne();
            Komponent harddisk = d.getHarddisk();
            Komponent tastatur = d.getTastatur();
            Komponent datamus = d.getDatamus();
            Komponent skjerm = d.getSkjerm();
            double totPris = d.getTotPris();
            // skriv til fil
            pw.println(prosessor.getNavn()+";"+prosessor.getPris()+";"+prosessor.getKategori()+";"+
                       skjermkort.getNavn()+";"+skjermkort.getPris()+";"+skjermkort.getKategori()+";"+
                       minne.getNavn()+";"+minne.getPris()+";"+minne.getKategori()+";"+
                       harddisk.getNavn()+";"+harddisk.getPris()+";"+harddisk.getKategori()+";"+
                       tastatur.getNavn()+";"+tastatur.getPris()+";"+tastatur.getKategori()+";"+
                       datamus.getNavn()+";"+datamus.getPris()+";"+datamus.getKategori()+";"+
                       skjerm.getNavn()+";"+skjerm.getPris()+";"+skjerm.getKategori()+";"+
                       totPris);
        }
        pw.close();
        System.out.println("lagret");
    }
}
