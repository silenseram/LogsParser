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
        Group group = new Group();
        Parent content = FXMLLoader.load(getClass().getResource("View/fxml/MainWindow.fxml"));

        BorderPane root = new BorderPane();
        root.setCenter(content);

        group.getChildren().add(root);

        stage.setScene(new Scene(group));
        stage.setResizable(false);
        stage.setTitle("writer's Realtime Logs");
        stage.show();
    }
}

