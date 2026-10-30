package newsservice;

import java.time.LocalDateTime;

public class NewsItem {
    private final String title;
    private final String content;
    private final NewsCategory category;
    private final LocalDateTime publishedAt;

    public NewsItem(String title, String content, NewsCategory category, LocalDateTime publishedAt) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.publishedAt = publishedAt;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public NewsCategory getCategory() {
        return category;
    }

    public LocalDateTime getPublishedAt() {
        return publishedAt;
    }

    @Override
    public String toString() {
        return "[" + category + "] " + title + " - " + content;
    }
}
