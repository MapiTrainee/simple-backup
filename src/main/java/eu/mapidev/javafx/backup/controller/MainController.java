package eu.mapidev.javafx.backup.controller;

import eu.mapidev.javafx.backup.App;
import eu.mapidev.javafx.backup.service.FileService;
import eu.mapidev.javafx.backup.service.SimpleBackupServiceImpl;
import eu.mapidev.javafx.backup.service.FileServiceImpl;
import eu.mapidev.javafx.backup.service.SimpleBackupService;
import java.io.File;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

public class MainController {

    @FXML
    private Label labHeader;
    @FXML
    private TextField txtSource;
    @FXML
    private TextField txtDest;
    @FXML
    private Button btnStart;
    @FXML
    private Button btnStop;
    @FXML
    private Button btnSource;
    @FXML
    private Button btnDest;

    private static final String SRC_DIRECTORY_CHOOSER_TITLE = "Select source directory:";
    private static final String DEST_DIRECTORY_CHOOSER_TITLE = "Select destination directory:";

    private final DirectoryChooser directoryChooser = new DirectoryChooser();
    private Window window;

    private FileService fileService = new FileServiceImpl();
    private SimpleBackupService backupService = new SimpleBackupServiceImpl();

    private final Logger logger = Logger.getLogger(this.getClass().getName());

    @FXML
    private void handleSource(ActionEvent event) {
	directoryChooser.setTitle(SRC_DIRECTORY_CHOOSER_TITLE);
	fileService.setSource(directoryChooser.showDialog(window));
	if (fileService.getSource() != null) {
	    txtSource.setText(fileService.getSource().getAbsolutePath());
	}
    }

    @FXML
    private void handleDest(ActionEvent event) {
	directoryChooser.setTitle(DEST_DIRECTORY_CHOOSER_TITLE);
	fileService.setDestination(directoryChooser.showDialog(window));
	if (fileService.getDestination() != null) {
	    txtDest.setText(fileService.getDestination().getAbsolutePath());
	}
    }

    @FXML
    private void handleStop(ActionEvent event) {
	backupService.stopBackup();
	btnStart.setDisable(false);
	btnStop.setDisable(true);
	txtSource.setDisable(false);
	txtDest.setDisable(false);
	btnSource.setDisable(false);
	btnDest.setDisable(false);
    }

    @FXML
    private void handleStart(ActionEvent event) {
	boolean isStarted = backupService.startBackup(fileService.getSource(), fileService.getDestination());
	if (isStarted) {
	    btnStart.setDisable(true);
	    btnStop.setDisable(false);
	    txtSource.setDisable(true);
	    txtDest.setDisable(true);
	    btnSource.setDisable(true);
	    btnDest.setDisable(true);
	}
    }

    public void setStageAndSetupListeners(Stage stage, String title) {
	labHeader.setText(App.APP_HEADER);
	window = stage;
	directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));
	if (fileService.getSource() != null) {
	    txtSource.setText(fileService.getSource().getAbsolutePath());
	}
	if (fileService.getDestination() != null) {
	    txtDest.setText(fileService.getDestination().getAbsolutePath());
	}
    }

}
