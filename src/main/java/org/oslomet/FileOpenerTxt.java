package org.oslomet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class FileOpenerTxt {
    public static int readFile() throws Exception {
        FileReader file = new FileReader("filbehandling/kunde-backup.txt");
        BufferedReader reader = new BufferedReader(file);
        String line;
        int antallLinjer = 0;
        int linjeNr = 0;
        while((line = reader.readLine()) != null) { // parse txt fil
            linjeNr++;
            if (line.length() > 1) {
                try {
                    Datamaskin d = ParseDatamaskin.parseDatamaskin(line);
                    Register.setDatamaskinListe(d);
                } catch (Exception e) {
                    feilMeldinger.add(e.getMessage());
                    feilLinjeNr.add("Feil på linje "+linjeNr+" i kunde-backup.txt");
                }
                antallLinjer++;
            }
        }
        return antallLinjer;
    }

    public static ArrayList<String> feilMeldinger = new ArrayList<>();
    public static ArrayList<String> feilLinjeNr = new ArrayList<>();
}
