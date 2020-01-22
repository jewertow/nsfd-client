package nsfd.desktop.metrics;

import nsfd.desktop.api.MetricsRequest;
import nsfd.desktop.api.deserializer.MetricsResponseCsvDeserializer;

import java.util.List;

import static java.util.Collections.emptyList;

public class MetricsService {

    private final MetricsClient client;
    private final MetricsResponseCsvDeserializer deserializer;

    public MetricsService(MetricsClient client, MetricsResponseCsvDeserializer deserializer) {
        this.client = client;
        this.deserializer = deserializer;
    }

    public List<Metrics> getMetrics(MetricsRequest request) {
        var response = client.executeRequest(request);
        if (response != null) {
            System.out.println(String.format("Response from metrics server: %s", response));
            return deserializer.deserialize(response);
        }
        return emptyList();
    }
}
