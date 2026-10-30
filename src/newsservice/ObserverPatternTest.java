package newsservice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public final class ObserverPatternTest {
    private ObserverPatternTest() {
    }

    public static void main(String[] args) {
        NewsAgency agency = new NewsAgency();
        RecordingSubscriber first = new RecordingSubscriber();
        RecordingSubscriber second = new RecordingSubscriber();
        NewsItem worldNews = new NewsItem("World update", "Summary", NewsCategory.WORLD, LocalDateTime.now());

        agency.subscribe(first);
        agency.subscribe(second);
        agency.subscribe(first);
        assertEquals(2, agency.getSubscriberCount(), "duplicate subscriptions ignored");

        agency.publish(worldNews);
        assertEquals(1, first.getReceivedCount(), "first subscriber notified");
        assertEquals(1, second.getReceivedCount(), "second subscriber notified");

        agency.unsubscribe(second);
        agency.publish(worldNews);
        assertEquals(2, first.getReceivedCount(), "remaining subscriber still notified");
        assertEquals(1, second.getReceivedCount(), "unsubscribed subscriber skipped");
        assertEquals(1, agency.getSubscriberCount(), "count after unsubscribe");

        EmailSubscriber email = new EmailSubscriber("Test", "test@example.com", NewsCategory.TECHNOLOGY);
        assertTrue(email.isSubscribedTo(NewsCategory.TECHNOLOGY), "initial preference applied");
        assertTrue(!email.isSubscribedTo(NewsCategory.SPORTS), "other categories filtered out");
        email.setPreferences(NewsCategory.SPORTS);
        assertTrue(email.isSubscribedTo(NewsCategory.SPORTS), "preference update applied");
        assertTrue(!email.isSubscribedTo(NewsCategory.TECHNOLOGY), "old preference removed");

        System.out.println("All ObserverPatternTest checks passed.");
    }

    private static void assertEquals(int expected, int actual, String label) {
        if (expected != actual) {
            throw new AssertionError(label + ": expected " + expected + " but was " + actual);
        }
    }

    private static void assertTrue(boolean condition, String label) {
        if (!condition) {
            throw new AssertionError(label);
        }
    }

    private static final class RecordingSubscriber implements Subscriber {
        private final List<NewsItem> received = new ArrayList<>();

        @Override
        public void update(NewsItem news) {
            received.add(news);
        }

        private int getReceivedCount() {
            return received.size();
        }
    }
}
