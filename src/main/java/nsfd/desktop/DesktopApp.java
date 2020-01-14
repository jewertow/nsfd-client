package nsfd.desktop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import nsfd.desktop.api.WatchServiceCancelRequest;
import nsfd.desktop.api.WatchServiceCreateRequest;
import nsfd.desktop.api.serializer.WatchServiceRequestCsvSerializer;
import nsfd.desktop.network.NsfdTcpClient;
import nsfd.desktop.service.NsfdService;

import java.io.IOException;

public class DesktopApp extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/RootLayout.fxml"));
        stage.setTitle("NSFD desktop client");
        stage.setScene(new Scene(root));
        stage.show();

        var client = new NsfdTcpClient();
        var serializer = new WatchServiceRequestCsvSerializer();
        var service = new NsfdService(client, serializer);

        var watchServiceCreateRequest = new WatchServiceCreateRequest("facebook.com", "80");
        var watchServiceCancelRequest = new WatchServiceCancelRequest("instagram.com", "80");

        service.watchService(watchServiceCreateRequest);
        service.watchService(watchServiceCancelRequest);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
