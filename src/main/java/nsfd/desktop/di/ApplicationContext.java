package nsfd.desktop.di;

import nsfd.desktop.api.serializer.WatchServiceRequestCsvSerializer;
import nsfd.desktop.network.NsfdTcpClient;
import nsfd.desktop.service.NsfdService;

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
        var nsfdTcpClient = new NsfdTcpClient();
        contextByType.put(NsfdTcpClient.class, nsfdTcpClient);

        var watchServiceSerializer = new WatchServiceRequestCsvSerializer();
        contextByType.put(WatchServiceRequestCsvSerializer.class, watchServiceSerializer);

        var nsfdService = new NsfdService(nsfdTcpClient, watchServiceSerializer);
        contextByType.put(NsfdService.class, nsfdService);
    }
}
