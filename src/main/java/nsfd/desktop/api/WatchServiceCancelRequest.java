package nsfd.desktop.api;

public class WatchServiceCancelRequest extends WatchServiceRequest {
    public WatchServiceCancelRequest(String domain, String port) {
        super(domain, port);
    }

    @Override
    public String getAction() {
        return "cancel";
    }
}
