package nsfd.desktop.api;

import java.util.Objects;

public abstract class WatchServiceRequest {
    private final String domain;
    private final String port;

    public WatchServiceRequest(String domain, String port) {
        this.domain = domain;
        this.port = port;
    }

    abstract public String getAction();

    public String getDomain() {
        return domain;
    }

    public String getPort() {
        return port;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WatchServiceRequest that = (WatchServiceRequest) o;
        return Objects.equals(domain, that.domain) &&
                Objects.equals(port, that.port) &&
                Objects.equals(getAction(), that.getAction());
    }

    @Override
    public int hashCode() {
        return Objects.hash(domain, port, getAction());
    }

    @Override
    public String toString() {
        return "WatchServiceRequest{" +
                "action='" + getAction() + '\'' +
                "domain='" + domain + '\'' +
                ", port='" + port + '\'' +
                '}';
    }
}
