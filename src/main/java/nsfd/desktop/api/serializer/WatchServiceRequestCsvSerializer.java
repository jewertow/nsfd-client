package nsfd.desktop.api.serializer;

import nsfd.desktop.api.WatchServiceRequest;

public class WatchServiceRequestCsvSerializer implements CsvSerializer<WatchServiceRequest> {
    @Override
    public String serialize(WatchServiceRequest obj) {
        return String.format("%s;%s;%s", obj.getAction(), obj.getDomain(), obj.getPort());
    }
}
