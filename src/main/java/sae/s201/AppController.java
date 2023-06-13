package sae.s201;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AppController {
    //recuperation du csv
    private SeismeCSV init = new SeismeCSV("src/main/ressources/sae/s201/data.csv");
    //definition des variables dont on aura besoin
    int annee ;
    String lieu ;
    @FXML
    private Slider anneeSlider;
    @FXML
    private Label anneeLabel;
    @FXML
    private Label lieuLabel1;
    @FXML
    BarChart barIntens;
    @FXML
    BarChart barEvo;
    @FXML
    private ChoiceBox selectRegion;

    public ArrayList<String> getLieux(List<SeismeCSV> list){ // fonction qui prend en entrée une liste de seismes et qui renvoie une liste contenant tout les lieux presents
        ArrayList<String> lieux = new ArrayList();
        for (SeismeCSV seisme : list) {
            if (!(lieux.contains(seisme.getRegion()))){
                lieux.add(seisme.getRegion());
            }
        }
        return lieux;
    }

    @FXML
    public void initialize() {
        anneeSlider.valueProperty().addListener((observable, oldValue, newValue) -> { // initialisation du slider et mise a jour active de celui ci
            int annneeChoisie = newValue.intValue();
            anneeLabel.setText("Année choisie: " + annneeChoisie);
        });
        selectRegion.getItems().addAll(getLieux(triData(false,false))); //Initialisation de la choicebox grace a la liste des lieux presents recoltée avec la fonction getlieux
        selectRegion.setOnAction(this::getLieuCB);                                  // appel de getlieucb quand on selectionne un lieu
    }

    private void getLieuCB(Event Event){              // Mise a jour du lieu choisi
        lieu = (String) selectRegion.getValue();
        lieuLabel1.setText("Lieu choisi: " + lieu);
    }

    public List<SeismeCSV> triData(boolean bannee, boolean blieu) {         // trie les données en fonction de l'année et/ou du lieu selon les specifications
        if (bannee) {
            return init.filtreA(init.getListData(), annee);
        }
        if (blieu) {

            return init.filtreL(init.getListData(), lieu);
        }
        if (blieu && bannee) {
            return init.filtreAL(init.getListData(), annee, lieu);
        } else return init.getListData();
    }

    public ArrayList<Integer> GraphIntens(List<SeismeCSV> liste) { // genere la liste qui servira a afficher le graphe montrant le nombre de seismes de chaque intensité par année
        ArrayList<Integer> intensL = new ArrayList<>(Collections.nCopies(18, 0));
        for (SeismeCSV seisme : liste) {
            switch (seisme.intensite) {
                case "\"1\"":
                    intensL.set(0, intensL.get(0) + 1);
                case "\"1,5\"":
                    intensL.set(1, intensL.get(1) + 1);
                case "\"2\"":
                    intensL.set(2, intensL.get(2) + 1);
                case "\"2,5\"":
                    intensL.set(3, intensL.get(3) + 1);
                case "\"3\"":
                    intensL.set(4, intensL.get(4) + 1);
                case "\"3,5\"":
                    intensL.set(5, intensL.get(5) + 1);
                case "\"4\"":
                    intensL.set(6, intensL.get(6) + 1);
                case "\"4,5\"":
                    intensL.set(7, intensL.get(7) + 1);
                case "\"5\"":
                    intensL.set(8, intensL.get(8) + 1);
                case "\"5,5\"":
                    intensL.set(9, intensL.get(9) + 1);
                case "\"6\"":
                    intensL.set(10, intensL.get(10) + 1);
                case "\"6,5\"":
                    intensL.set(11, intensL.get(11) + 1);
                case "\"7\"":
                    intensL.set(12, intensL.get(12) + 1);
                case "\"7,5\"":
                    intensL.set(13, intensL.get(13) + 1);
                case "\"8\"":
                    intensL.set(14, intensL.get(14) + 1);
                case "\"8,5\"":
                    intensL.set(15, intensL.get(15) + 1);
                case "\"9\"":
                    intensL.set(16, intensL.get(16) + 1);
                case "\"\"": // si il n'y a pas d'intensité
                    intensL.set(17, intensL.get(17) + 1);

            }
        }
        return intensL;
    }

    public ArrayList<ArrayList<Integer>> GraphEvo(List<SeismeCSV> list){ // genere la liste qui servira a afficher le graphe montrant le nombre de seismes par année en fonction du lieu

        ArrayList<Integer> annees = new ArrayList();
        for (SeismeCSV seisme : list) {
            if (!(annees.contains(seisme.getDate().getYear()))){
                annees.add(seisme.date.getYear());
            }

        }

        ArrayList<Integer> nombre = new ArrayList<>(Collections.nCopies(annees.size(), 0));

        for (SeismeCSV seisme : list){
            nombre.set(annees.indexOf(seisme.date.getYear()), nombre.get(annees.indexOf(seisme.date.getYear())) + 1);
        }
        ArrayList<ArrayList<Integer>> data = new ArrayList();
        data.add(annees);
        data.add(nombre);
        return data;
    }

    public void updateIntens(BarChart<String, Integer> chart, ArrayList<Integer> data) {    // Fonction qui met a jour le graph grace a la liste generee par graphIntens
        ArrayList<String> intensités = new ArrayList<>(Arrays.asList("1","1.5","2","2.5","3","3.5","4","4.5","5","5.5","6","6.5","7","7.5","8","8.5","9","N/S"));

        XYChart.Series<String, Integer> dataSeries1 = new XYChart.Series<String, Integer>();
        for (int i = 0; i < data.size(); ++i) {
            dataSeries1.getData().add(new XYChart.Data<String, Integer>(intensités.get(i), data.get(i)));

        }
        dataSeries1.setName(Integer.toString(annee));
        chart.getData().add(dataSeries1);
    }

    public void updateEvoNbS(BarChart<String, Integer> chart, ArrayList<ArrayList<Integer>> data) {  // Fonction qui met a jour le graph grace a la liste generee par graphEvo
        XYChart.Series<String, Integer> dataSeries2 = new XYChart.Series<>();
        ArrayList<Integer> annees = data.get(0);
        ArrayList<Integer> quantite = data.get(1);
        for (int i = 0; i < data.get(0).size(); ++i) {
            dataSeries2.getData().add(new XYChart.Data<String, Integer>(Integer.toString(annees.get(i)),quantite.get(i)));
        }
        dataSeries2.setName(lieu);
        chart.getData().add(dataSeries2);
    }

    @FXML
    private void changeGraphAnnee(ActionEvent event) {
        annee = (int)anneeSlider.getValue();
        updateIntens(barIntens,GraphIntens(triData(true,false)));
    }
    @FXML
    private void changeGraphLieu(ActionEvent event) {
        updateEvoNbS(barEvo,GraphEvo(triData(false,true)));
    }
}
