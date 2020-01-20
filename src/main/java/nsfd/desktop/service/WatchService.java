package nsfd.desktop.service;

import nsfd.desktop.api.WatchServiceRequest;
import nsfd.desktop.network.WatchServiceClient;

public class WatchService {

    private final WatchServiceClient client;

    public WatchService(WatchServiceClient client) {
        this.client = client;
    }

    public void watchService(WatchServiceRequest request) {
        var response = client.executeRequest(request);
        if (response != null) {
            System.out.println(String.format("Response from watch-service server: %s", response));
        }
    }
}
