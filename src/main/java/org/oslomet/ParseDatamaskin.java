package org.oslomet;

public class ParseDatamaskin {
    public static Datamaskin parseDatamaskin(String str) throws InvalidDatamaskinFormatException {
        String[] k = str.split(";");
        if (k.length != 22) { // ugyldige tegn
            throw new InvalidDatamaskinFormatException("Bestillingen inneholder ugyldige tegn");
        }
        double prosessorPris, skjermkortPris, minnePris, harddiskPris, tastaturPris, datamusPris, skjermPris,totPris;
        // parse pris
        try {
            prosessorPris = Double.parseDouble(k[1]);
            skjermkortPris = Double.parseDouble(k[4]);
            minnePris = Double.parseDouble(k[7]);
            harddiskPris = Double.parseDouble(k[10]);
            tastaturPris = Double.parseDouble(k[13]);
            datamusPris = Double.parseDouble(k[16]);
            skjermPris = Double.parseDouble(k[19]);
            totPris = Double.parseDouble(k[21]);
        } catch (NumberFormatException e) { // pris inneholder noe annet enn tall
            throw new InvalidDatamaskinFormatException("Pris må være et tall");
        }

        Datamaskin d = null;

        try {
            Komponent prosessor = new Komponent(k[0],prosessorPris,k[2]);
            Komponent skjermkort = new Komponent(k[3],skjermkortPris,k[5]);
            Komponent minne = new Komponent(k[6],minnePris,k[8]);
            Komponent harddisk = new Komponent(k[9],harddiskPris,k[11]);
            Komponent tastatur = new Komponent(k[12],tastaturPris,k[14]);
            Komponent datamus = new Komponent(k[15],datamusPris,k[17]);
            Komponent skjerm = new Komponent(k[18],skjermPris,k[20]);
            d = new Datamaskin(prosessor,skjermkort,minne,harddisk,tastatur,datamus,skjerm,totPris);
        } catch (Exception e) { // pris er under 100 kr eller ugyldig kategori
            throw new InvalidDatamaskinFormatException(e.getMessage());
        }

        return d;
    }
}
