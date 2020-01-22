package nsfd.desktop.api.deserializer;

import java.util.List;

public interface CsvDeserializer<T> {
    List<T> deserialize(String rawData);
}
