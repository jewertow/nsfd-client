package nsfd.desktop.api.deserializer;

import nsfd.desktop.metrics.Metrics;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

public class MetricsResponseCsvDeserializer implements CsvDeserializer<Metrics> {

    @Override
    public List<Metrics> deserialize(String rawData) {
        return Arrays.stream(rawData.split("\\|"))
                .filter(s -> !s.isEmpty())
                .map(s -> s.split(";"))
                .filter(strings -> strings.length == 5)
                .map(strToMetrics)
                .collect(toList());
    }

    private final Function<String[], Metrics> strToMetrics = str -> new Metrics(
            Integer.parseInt(str[0]) > 0,
            Double.parseDouble(str[1]),
            Integer.parseInt(str[2]) > 0,
            Double.parseDouble(str[3]),
            Instant.ofEpochMilli(Long.parseLong(str[4]))
    );
}
