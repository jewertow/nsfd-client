package nsfd.desktop.metrics;

import nsfd.desktop.api.MetricsRequest;

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
