package org.oslomet;

import javafx.concurrent.Task;

public class AdminThread extends Task<Integer> {

    @Override
    protected Integer call() throws Exception {
        int antall = 0;
        try {
            Thread.sleep(3000);
            Register.getKomponentListe().clear();
            Register.getKomponentListe().addAll(FileOpenerJobj.readFile());
            antall = Register.getKomponentListe().size();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw new Exception(e.getMessage());
        }
        return antall;
    }
}