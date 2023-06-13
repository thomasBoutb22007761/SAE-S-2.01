package sae.s201;



import javafx.application.Application;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("App.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Seismes App");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}