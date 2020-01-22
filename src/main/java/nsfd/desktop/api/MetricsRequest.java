package nsfd.desktop.api;

import java.util.Objects;

public class MetricsRequest {
    private final String domain;

    public MetricsRequest(String domain) {
        this.domain = domain;
    }

    public String getDomain() {
        return domain;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MetricsRequest that = (MetricsRequest) o;
        return Objects.equals(domain, that.domain);
    }

    @Override
    public int hashCode() {
        return Objects.hash(domain);
    }

    @Override
    public String toString() {
        return "MetricsRequest{" +
                "domain='" + domain + '\'' +
                '}';
    }
}
