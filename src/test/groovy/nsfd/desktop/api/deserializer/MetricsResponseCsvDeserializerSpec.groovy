package nsfd.desktop.api.deserializer

import nsfd.desktop.metrics.Metrics
import spock.lang.Specification

import java.time.Instant

class MetricsResponseCsvDeserializerSpec extends Specification {

    def deserializer = new MetricsResponseCsvDeserializer()

    def metrics1 = new Metrics(true, 11.5032, false, -1.0, Instant.ofEpochMilli(1579641160000))
    def metrics2 = new Metrics(true, 15.6107, true, 19.665, Instant.ofEpochMilli(1579641165000))
    def metrics3 = new Metrics(true, 15.8418, true, 12.7196, Instant.ofEpochMilli(1579641170000))

    def "should parse string to list of metrics"() {
        given:
        def str = "1;11.5032;0;-1;1579641160000|1;15.6107;1;19.665;1579641165000|1;15.8418;1;12.7196;1579641170000"

        when:
        def metrics = deserializer.deserialize(str)

        then:
        metrics == [metrics1, metrics2, metrics3]
    }

    def "should parse empty string to empty list of metrics"() {
        when:
        def metrics = deserializer.deserialize("")

        then:
        metrics == []
    }

    def "should skip empty metrics string"() {
        given:
        def str = "1;11.5032;0;-1;1579641160000||1;15.8418;1;12.7196;1579641170000"

        when:
        def metrics = deserializer.deserialize(str)

        then:
        metrics == [metrics1, metrics3]
    }

    def "should skip incorrect metrics string"() {
        given:
        def incorrect = "1;11.5032;0;-1"
        def str = "1;11.5032;0;-1;1579641160000|$incorrect|1;15.8418;1;12.7196;1579641170000"

        when:
        def metrics = deserializer.deserialize(str)

        then:
        metrics == [metrics1, metrics3]
    }
}
