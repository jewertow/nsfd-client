package nsfd.desktop.api.serializer;

import nsfd.desktop.api.MetricsRequest;

public class MetricsRequestCsvSerializer implements CsvSerializer<MetricsRequest> {
    @Override
    public String serialize(MetricsRequest obj) {
        return obj.getDomain();
    }
}
