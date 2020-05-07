package org.oslomet;

import javafx.collections.ObservableList;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class FileSaverJobj implements Serializable {
    // lagring av data til binære filer
    public static void save() {
        try {
            // write object to file
            ObservableList<Komponent> komponenter = Register.getKomponentListe();
            FileOutputStream fileOut = new FileOutputStream("backup.jobj");
            ObjectOutputStream s = new ObjectOutputStream(fileOut);
            s.writeObject(new ArrayList<Komponent>(komponenter));
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
