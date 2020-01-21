package nsfd.desktop.metrics;

import nsfd.desktop.api.MetricsRequest;
import nsfd.desktop.api.serializer.CsvSerializer;
import nsfd.desktop.network.TcpClient;

import java.io.IOException;

public class MetricsClient {

    private final TcpClient tcpClient;
    private final CsvSerializer<MetricsRequest> serializer;

    public MetricsClient(String host, int port, int timeout, CsvSerializer<MetricsRequest> serializer) {
        this.tcpClient = new TcpClient(host, port, timeout);
        this.serializer = serializer;
    }

    public String executeRequest(MetricsRequest metricsRequest) {
        var request = serializer.serialize(metricsRequest);
        try {
            return tcpClient.executeRequest(request);
        } catch (IOException e) {
            System.out.println("[ERROR] Request to metrics server failed");
            return null;
        }
    }
}
