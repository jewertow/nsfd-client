package nsfd.desktop.watch;

import nsfd.desktop.api.WatchServiceRequest;
import nsfd.desktop.api.serializer.CsvSerializer;
import nsfd.desktop.network.TcpClient;

import java.io.IOException;

public class WatchServiceClient {

    private final TcpClient tcpClient;
    private final CsvSerializer<WatchServiceRequest> serializer;

    public WatchServiceClient(String host, int port, int timeout, CsvSerializer<WatchServiceRequest> serializer) {
        this.tcpClient = new TcpClient(host, port, timeout);
        this.serializer = serializer;
    }

    public String executeRequest(WatchServiceRequest watchServiceRequest) {
        var request = serializer.serialize(watchServiceRequest);
        try {
            return tcpClient.executeRequest(request);
        } catch (IOException e) {
            System.out.println("[ERROR] Request to watch-service server failed");
            return null;
        }
    }
}
