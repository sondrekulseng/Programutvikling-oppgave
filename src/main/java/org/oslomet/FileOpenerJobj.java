package org.oslomet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class FileOpenerJobj {
    // les binære filer og returner som liste
    public static ObservableList<Komponent> readFile() throws Exception {
        ObservableList<Komponent> listeUt;
        try {
            FileInputStream fileInputStream = new FileInputStream("backup.jobj");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            listeUt = FXCollections.observableArrayList((ArrayList) objectInputStream.readObject());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return listeUt;
    }
}

