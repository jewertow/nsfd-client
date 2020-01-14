package nsfd.desktop.api.serializer;

public interface CsvSerializer<T> {
    String serialize(T obj);
}
