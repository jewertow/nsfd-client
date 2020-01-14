package nsfd.desktop.api;

public class WatchServiceCreateRequest extends WatchServiceRequest {
    public WatchServiceCreateRequest(String domain, String port) {
        super(domain, port);
    }

    @Override
    public String getAction() {
        return "create";
    }
}
