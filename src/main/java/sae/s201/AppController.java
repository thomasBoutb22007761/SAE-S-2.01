package sae.s201;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

public class AppController {

    @FXML
    private Slider dateSlider;

    @FXML
    private Label taDateLabel;

    @FXML
    public void initialize() {
        // Ajoutez ici le code d'initialisation du contrôleur
        dateSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            // Mise à jour de la date sélectionnée dans le label "taDateLabel"
            taDateLabel.setText(newValue.toString());
        });
    }
}
