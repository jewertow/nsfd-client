package nsfd.desktop.service;

import nsfd.desktop.api.WatchServiceRequest;
import nsfd.desktop.api.serializer.CsvSerializer;
import nsfd.desktop.network.NsfdTcpClient;

import java.io.IOException;

public class NsfdService {

    private final NsfdTcpClient client;
    private final CsvSerializer<WatchServiceRequest> serializer;

    public NsfdService(NsfdTcpClient client, CsvSerializer<WatchServiceRequest> serializer) {
        this.client = client;
        this.serializer = serializer;
    }

    public void watchService(WatchServiceRequest request) {
        var data = serializer.serialize(request);
        try {
            var response = client.executeRequest(data);
            System.out.println(String.format("Response: %s", response));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
