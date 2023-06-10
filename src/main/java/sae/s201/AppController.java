package sae.s201;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AppController {

    private SeismeCSV init = new SeismeCSV("src/main/ressources/sae/s201/data.csv");

    int annee = 2000;
    String lieu;
    @FXML
    private Slider dateSlider;

    @FXML
    private Label taDateLabel;

    //@FXML
    //CategoryAxis xAxis1;
    //@FXML
    //NumberAxis yAxis1;

    @FXML
    BarChart barIntens;

    @FXML
    public void initialize() {
        dateSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            taDateLabel.setText(newValue.toString());
        });
    }

    public List<SeismeCSV> triData(boolean bannee, boolean blieu) {
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

    public ArrayList<Integer> GraphIntens(List<SeismeCSV> liste) {
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

    public void updateIntens(BarChart<String, Integer> chart, ArrayList<Integer> data) {
        XYChart.Series<String, Integer> dataSeries1 = new XYChart.Series<String, Integer>();
        for (int i = 0; i < data.size(); ++i) {
            dataSeries1.getData().add(new XYChart.Data<String, Integer>(Integer.toString(i), data.get(i)));
        }
        chart.getData().add(dataSeries1);


    }

    @FXML
    private void changeGraph(ActionEvent event) {
        updateIntens(barIntens,GraphIntens(triData(true,false)));
    }
}
