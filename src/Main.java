import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;

import java.io.IOException;
public class Main extends Application{


    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        Group group = new Group();
        Parent content = FXMLLoader.load(getClass().getResource("GUI/fxml/MainWindow.fxml"));

        BorderPane root = new BorderPane();
        root.setCenter(content);

        group.getChildren().add(root);

        stage.setScene(new Scene(group,1280, 720));
        stage.setTitle("writer's Realtime Logs");
        stage.show();
    }
}

