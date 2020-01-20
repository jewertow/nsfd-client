package nsfd.desktop.service;

import nsfd.desktop.api.MetricsRequest;
import nsfd.desktop.network.MetricsClient;

public class MetricsService {

    private final MetricsClient client;

    public MetricsService(MetricsClient client) {
        this.client = client;
    }

    public void getMetrics(MetricsRequest request) {
        var response = client.executeRequest(request);
        if (response != null) {
            System.out.println(String.format("Response from metrics server: %s", response));
        }
    }
}
