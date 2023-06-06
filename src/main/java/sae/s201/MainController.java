import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class MainController {

    @FXML
    private ComboBox<String> sceneComboBox;

    @FXML
    private StackPane sceneContainer;

    @FXML
    private void onSceneSelectionChanged() throws IOException {
        String selectedScene = sceneComboBox.getValue();

        Node sceneNode;
        switch (selectedScene) {
            case "Scene 1":
                FXMLLoader loader1 = new FXMLLoader(getClass().getResource("scene1.fxml"));
                sceneNode = loader1.load();
                sceneNode.getStylesheets().add(getClass().getResource("scene1.css").toExternalForm());
                Scene1Controller scene1Controller = loader1.getController();
                // Faire quelque chose avec le contrôleur de la scène 1 si nécessaire
                break;
            case "Scene 2":
                FXMLLoader loader2 = new FXMLLoader(getClass().getResource("scene2.fxml"));
                sceneNode = loader2.load();
                sceneNode.getStylesheets().add(getClass().getResource("scene2.css").toExternalForm());
                Scene2Controller scene2Controller = loader2.getController();
                // Faire quelque chose avec le contrôleur de la scène 2 si nécessaire
                break;
            case "Scene 3":
                FXMLLoader loader3 = new FXMLLoader(getClass().getResource("scene3.fxml"));
                sceneNode = loader3.load();
                sceneNode.getStylesheets().add(getClass().getResource("scene3.css").toExternalForm());
                Scene3Controller scene3Controller = loader3.getController();
                // Faire quelque chose avec le contrôleur de la scène 3 si nécessaire
                break;
            default:
                throw new IllegalArgumentException("Unknown scene: " + selectedScene);
        }

        sceneContainer.getChildren().setAll(sceneNode);
    }

    @FXML
    private void onBackButtonClicked() {
        sceneComboBox.getSelectionModel().clearSelection();
        sceneContainer.getChildren().clear();
    }
}
