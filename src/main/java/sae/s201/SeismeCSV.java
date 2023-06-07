package sae.s201;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SeismeCSV {
    private List<SeismeCSV> listData = new ArrayList<>();

    private static final String CSV_DELIMITER = ",";

    private static final int aID = 1;
    private static final int aDate = 2;
    private static final int aHeure = 3;
    private static final int aIntensite = 4;
    private static final int aQual = 5;
    private static final int aNom = 6;
    private static final int aRegion = 7;
    private static final int aChoc = 8;

    String identifiant;

    public LocalDate getDate() {
        return date;
    }

    public String getRegion() {
        return region;
    }

    LocalDate date;
    String heure;
    String intensite;
    String qualite;
    String nom;
    String region;
    String choc;

    public SeismeCSV(String csvFilePath) {
        this.importDataFromCSV(new File(csvFilePath));
    }

    public SeismeCSV(String identifiant, LocalDate date, String heure, String intensite, String qualite, String nom, String region, String choc) {
        this.identifiant = identifiant;
        this.date = date;
        this.heure = heure;
        this.intensite = intensite;
        this.qualite = qualite;
        this.nom = nom;
        this.region = region;
        this.choc = choc;
    }


    public void importDataFromCSV(File file) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            reader.readLine(); // Ignorer la première ligne (en-têtes des colonnes)
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(CSV_DELIMITER);

                // Récupérer les valeurs des attributs depuis le tableau data
                String id = data[aID];
                //recuperation de la date et conversion au format LocalDate
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                String datetxt = data[aDate];
                datetxt=datetxt.replace("\"", "");
                LocalDate date = LocalDate.parse(datetxt, formatter);

                String heure = data[aHeure];
                String intensite = data[aIntensite];
                String qual = data[aQual];
                String nom = data[aNom];
                String region = data[aRegion];
                String choc = data[aChoc];

                // Créer un objet SeismeData avec les valeurs des attributs
                SeismeCSV seismeData = new SeismeCSV(id,date,heure,intensite,qual,nom,region,choc);

                // Ajouter l'objet SeismeData à la liste
                listData.add(seismeData);
            }
            reader.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
    public List<SeismeCSV> filtreAL(List<SeismeCSV> listData, int annee, String lieu) { //Filtre les données par année et par lieu
        List<SeismeCSV> filteredList = new ArrayList<>();

        for (SeismeCSV seismeData : listData) {
            if (seismeData.getDate().getYear() == annee && seismeData.getRegion().equalsIgnoreCase(lieu)) {
                filteredList.add(seismeData);
            }
        }

        return filteredList;
    }
    public List<SeismeCSV> filtreA(List<SeismeCSV> listData, int annee) {
        List<SeismeCSV> filteredList = new ArrayList<>();

        for (SeismeCSV seismeData : listData) {
            if (seismeData.getDate().getYear() == annee) {
                filteredList.add(seismeData);
            }
        }

        return filteredList;
    }
    public List<SeismeCSV> filtreL(List<SeismeCSV> listData, String lieu) {
        List<SeismeCSV> filteredList = new ArrayList<>();

        for (SeismeCSV seismeData : listData) {
            if (seismeData.getRegion().equalsIgnoreCase(lieu)) {
                filteredList.add(seismeData);
            }
        }

        return filteredList;
    }

}