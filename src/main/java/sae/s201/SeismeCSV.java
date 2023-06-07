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
    LocalDate date;
    LocalTime heure;
    double intensite;
    String qualite;
    String nom;
    String region;
    String choc;

    public SeismeCSV() {
    }

    public SeismeCSV(String identifiant, LocalDate date, LocalTime heure, double intensite, String qualite, String nom, String region, String choc) {
        this.identifiant = identifiant;
        this.date = date;
        this.heure = heure;
        this.intensite = intensite;
        this.qualite = qualite;
        this.nom = nom;
        this.region = region;
        this.choc = choc;
    }

    public static void main(String[] args) {
        SeismeCSV init = new SeismeCSV();
        init.importDataFromCSV(new File("data.csv"));
    }

    private void importDataFromCSV(File file) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            reader.readLine(); // Ignorer la première ligne (en-têtes des colonnes)
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(CSV_DELIMITER);

                // Récupérer les valeurs des attributs depuis le tableau data
                String id = data[aID];
                LocalDate date = LocalDate.parse(data[aDate], DateTimeFormatter.ofPattern("yyyy/MM/dd"));
                LocalTime heure = LocalTime.parse(data[aHeure]);
                double intensite = Double.parseDouble(data[aIntensite]);
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

}