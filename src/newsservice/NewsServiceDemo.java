package newsservice;

import java.time.LocalDateTime;

public class NewsServiceDemo {
    public static void main(String[] args) {
        NewsAgency agency = new NewsAgency();

        EmailSubscriber alex = new EmailSubscriber("Alex", "alex@example.com", NewsCategory.TECHNOLOGY);
        SmsSubscriber bianca = new SmsSubscriber("Bianca", "+63 917 000 0000");
        PushSubscriber caleb = new PushSubscriber("Caleb", "device-001");

        agency.subscribe(alex);
        agency.subscribe(bianca);
        agency.subscribe(caleb);
        System.out.println("Subscribers after sign-up: " + agency.getSubscriberCount());

        agency.publish(new NewsItem(
                "New AI model released",
                "A local lab released an open-source language model.",
                NewsCategory.TECHNOLOGY,
                LocalDateTime.now()));

        agency.unsubscribe(bianca);
        System.out.println("Subscribers after Bianca unsubscribes: " + agency.getSubscriberCount());

        alex.setPreferences(NewsCategory.BUSINESS, NewsCategory.TECHNOLOGY);
        agency.publish(new NewsItem(
                "Central bank holds rates",
                "The central bank kept interest rates unchanged.",
                NewsCategory.BUSINESS,
                LocalDateTime.now()));

        agency.publish(new NewsItem(
                "National team wins",
                "The national football team won the regional final.",
                NewsCategory.SPORTS,
                LocalDateTime.now()));
    }
}
