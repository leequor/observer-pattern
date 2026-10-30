package newsservice;

import java.util.concurrent.CopyOnWriteArrayList;

public class NewsAgency {
    // Copy-on-write keeps broadcasts safe while subscribers join or leave.
    private final CopyOnWriteArrayList<Subscriber> subscribers = new CopyOnWriteArrayList<>();

    public void subscribe(Subscriber subscriber) {
        if (subscriber != null) {
            subscribers.addIfAbsent(subscriber);
        }
    }

    public boolean unsubscribe(Subscriber subscriber) {
        return subscribers.remove(subscriber);
    }

    public int getSubscriberCount() {
        return subscribers.size();
    }

    public void publish(NewsItem news) {
        System.out.println("Breaking news for " + subscribers.size() + " subscriber(s): " + news);
        for (Subscriber subscriber : subscribers) {
            subscriber.update(news);
        }
    }
}
