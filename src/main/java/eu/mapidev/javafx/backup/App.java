package eu.mapidev.javafx.backup;

import eu.mapidev.javafx.backup.controller.MainController;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {

    public static final String APP_HEADER = "Simple Backup Tool v1.1";

    @Override
    public void start(Stage stage) throws Exception {
	FXMLLoader loader = new FXMLLoader();
	loader.setLocation(getClass().getResource("/fxml/Scene.fxml"));
	Parent root = loader.load();

	Scene scene = new Scene(root);
	scene.getStylesheets().add("/styles/Styles.css");

	MainController controller = (MainController) loader.getController();
	controller.setStageAndSetupListeners(stage, APP_HEADER);

	stage.setTitle(APP_HEADER);
	stage.getIcons().add(new Image("/images/main.png"));
	stage.setScene(scene);
	stage.show();

    }

    public static void main(String[] args) {
	launch(args);
    }

}
