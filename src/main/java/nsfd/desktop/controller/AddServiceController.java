package nsfd.desktop.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import nsfd.desktop.api.WatchServiceCreateRequest;
import nsfd.desktop.di.ApplicationContext;
import nsfd.desktop.service.NsfdService;

import java.net.URL;
import java.util.ResourceBundle;

public class AddServiceController implements Initializable {

    @FXML
    private TextField domainNameInput;

    @FXML
    private TextField portInput;

    @FXML
    private Button submit;

    private NsfdService service;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        service = ApplicationContext.getContext().get(NsfdService.class);
        submit.setOnAction(actionEvent -> {
            var createRequest = new WatchServiceCreateRequest(domainNameInput.getText(), portInput.getText());
            service.watchService(createRequest);
        });
    }
}
