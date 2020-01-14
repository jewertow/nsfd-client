package nsfd.desktop.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class RootLayoutController implements Initializable {

    @FXML
    public Label javaInfo;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
        javaInfo.setText(String.format("Hello, JavaFX %s, running on Java %s.", javafxVersion, javaVersion));
    }
}
