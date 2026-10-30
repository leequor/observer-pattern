package newsservice;

public class SmsSubscriber implements Subscriber {
    private final String name;
    private final String phoneNumber;

    public SmsSubscriber(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void update(NewsItem news) {
        System.out.println("[SMS] " + name + " (" + phoneNumber + ") received: " + news);
    }
}
