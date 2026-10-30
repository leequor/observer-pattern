package newsservice;

public class PushSubscriber implements Subscriber {
    private final String name;
    private final String deviceId;

    public PushSubscriber(String name, String deviceId) {
        this.name = name;
        this.deviceId = deviceId;
    }

    @Override
    public void update(NewsItem news) {
        System.out.println("[Push] " + name + " (" + deviceId + ") received: " + news);
    }
}
