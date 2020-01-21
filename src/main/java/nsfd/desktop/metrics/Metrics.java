package nsfd.desktop.metrics;

import java.time.Instant;
import java.util.Objects;

public class Metrics {
    private final boolean icmpConnected;
    private final double icmpConnectionTime;
    private final boolean tcpConnected;
    private final double tcpConnectionTime;
    private final Instant timestamp;

    public Metrics(
            boolean icmpConnected,
            double icmpConnectionTime,
            boolean tcpConnected,
            double tcpConnectionTime,
            Instant timestamp
    ) {
        this.icmpConnected = icmpConnected;
        this.icmpConnectionTime = icmpConnectionTime;
        this.tcpConnected = tcpConnected;
        this.tcpConnectionTime = tcpConnectionTime;
        this.timestamp = timestamp;
    }

    public boolean isIcmpConnected() {
        return icmpConnected;
    }

    public double getIcmpConnectionTime() {
        return icmpConnectionTime;
    }

    public boolean isTcpConnected() {
        return tcpConnected;
    }

    public double getTcpConnectionTime() {
        return tcpConnectionTime;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Metrics metrics = (Metrics) o;
        return icmpConnected == metrics.icmpConnected &&
                Double.compare(metrics.icmpConnectionTime, icmpConnectionTime) == 0 &&
                tcpConnected == metrics.tcpConnected &&
                Double.compare(metrics.tcpConnectionTime, tcpConnectionTime) == 0 &&
                Objects.equals(timestamp, metrics.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(icmpConnected, icmpConnectionTime, tcpConnected, tcpConnectionTime, timestamp);
    }

    @Override
    public String toString() {
        return "Metrics{" +
                "icmpConnected=" + icmpConnected +
                ", icmpConnectionTime=" + icmpConnectionTime +
                ", tcpConnected=" + tcpConnected +
                ", tcpConnectionTime=" + tcpConnectionTime +
                ", timestamp=" + timestamp +
                '}';
    }
}
