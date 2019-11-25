import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;

import java.io.File;
import java.io.IOException;
public class Main extends Application{


    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        Initializer.init();
        Parent content = FXMLLoader.load(getClass().getResource("View/fxml/MainWindow.fxml"));

        stage.setScene(new Scene(content));
        stage.setTitle("writer's Realtime Logs");
        stage.show();
    }
}

