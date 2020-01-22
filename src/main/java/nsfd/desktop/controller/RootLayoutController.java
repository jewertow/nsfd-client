package nsfd.desktop.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import nsfd.desktop.api.MetricsRequest;
import nsfd.desktop.di.ApplicationContext;
import nsfd.desktop.metrics.MetricsService;

import java.net.URL;
import java.util.ResourceBundle;

public class RootLayoutController implements Initializable {

    @FXML
    public Label javaInfo;

    @FXML
    public Button addService;

    @FXML
    public Button refreshMetrics;

    @FXML
    public TextField domainMetricsInput;

    @FXML
    public LineChart<String, Double> metricsIcmpChart;

    @FXML
    public LineChart<String, Double> metricsTcpChart;

    private MetricsService metricsService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        metricsService = ApplicationContext.getContext().get(MetricsService.class);

        addService.setOnAction(actionEvent -> {
            Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("/fxml/AddServiceView.fxml"));
                var stage = new Stage();
                stage.setTitle("Dodaj serwis");
                stage.setScene(new Scene(root));
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        refreshMetrics.setOnAction(actionEvent -> {
            var domain = domainMetricsInput.getText();
            if (domain != null && !domain.isEmpty()) {
                var metricsRequest = new MetricsRequest(domain);
                var metrics = metricsService.getMetrics(metricsRequest);

                var icmpSeries = new XYChart.Series<String, Double>();
                metrics.forEach(metr ->
                    icmpSeries.getData().add(
                            new XYChart.Data<>(metr.getTimestamp().toString(), metr.getIcmpConnectionTime())
                    ));

                metricsIcmpChart.getData().clear();
                metricsIcmpChart.getData().add(icmpSeries);

                var tcpSeries = new XYChart.Series<String, Double>();
                metrics.forEach(metr ->
                        tcpSeries.getData().add(
                                new XYChart.Data<>(metr.getTimestamp().toString(), metr.getTcpConnectionTime())
                        ));

                metricsTcpChart.getData().clear();
                metricsTcpChart.getData().add(tcpSeries);
            }
        });
    }
}
