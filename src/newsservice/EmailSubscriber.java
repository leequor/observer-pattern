package newsservice;

import java.util.Collections;
import java.util.EnumSet;

public class EmailSubscriber implements Subscriber {
    private final String name;
    private final String email;
    private final EnumSet<NewsCategory> preferences;

    public EmailSubscriber(String name, String email, NewsCategory... categories) {
        this.name = name;
        this.email = email;
        this.preferences = EnumSet.noneOf(NewsCategory.class);
        if (categories.length == 0) {
            preferences.addAll(EnumSet.allOf(NewsCategory.class));
        } else {
            Collections.addAll(preferences, categories);
        }
    }

    @Override
    public void update(NewsItem news) {
        if (isSubscribedTo(news.getCategory())) {
            System.out.println("[Email] " + name + " <" + email + "> received: " + news);
        }
    }

    public boolean isSubscribedTo(NewsCategory category) {
        return preferences.contains(category);
    }

    public void setPreferences(NewsCategory... categories) {
        preferences.clear();
        Collections.addAll(preferences, categories);
    }

    public EnumSet<NewsCategory> getPreferences() {
        return EnumSet.copyOf(preferences);
    }
}
