package nsfd.desktop.di;

import nsfd.desktop.api.serializer.MetricsRequestCsvSerializer;
import nsfd.desktop.api.serializer.WatchServiceRequestCsvSerializer;
import nsfd.desktop.network.MetricsClient;
import nsfd.desktop.network.WatchServiceClient;
import nsfd.desktop.service.MetricsService;
import nsfd.desktop.service.WatchService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ApplicationContext {

    private static ApplicationContext INSTANCE;
    private static final Object MONITOR = new Object();

    private final Map<Class<?>, Object> contextByType;

    public static ApplicationContext getContext() {
        if (INSTANCE == null) {
            synchronized (MONITOR) {
                if (INSTANCE == null) {
                    INSTANCE = new ApplicationContext();
                }
            }
        }
        return INSTANCE;
    }

    public <T> T get(Class<T> type) {
        var obj = contextByType.get(type);
        if (obj == null) {
            throw new NullPointerException(String.format("Could not find object of type %s", type));
        }
        return type.cast(obj);
    }

    private ApplicationContext() {
        this.contextByType = new ConcurrentHashMap<>();
        initializeContext();
    }

    private void initializeContext() {
        var watchServiceSerializer = new WatchServiceRequestCsvSerializer();
        contextByType.put(WatchServiceRequestCsvSerializer.class, watchServiceSerializer);

        var metricsRequestSerializer = new MetricsRequestCsvSerializer();
        contextByType.put(MetricsRequestCsvSerializer.class, metricsRequestSerializer);

        var watchServiceClient = new WatchServiceClient("127.0.0.1", 5000, 1000, watchServiceSerializer);
        contextByType.put(WatchServiceClient.class, watchServiceClient);

        var watchService = new WatchService(watchServiceClient);
        contextByType.put(WatchService.class, watchService);

        var metricsClient = new MetricsClient("127.0.0.1", 5001, 1000, metricsRequestSerializer);
        contextByType.put(MetricsClient.class, metricsClient);

        var metricsService = new MetricsService(metricsClient);
        contextByType.put(MetricsService.class, metricsService);
    }
}
