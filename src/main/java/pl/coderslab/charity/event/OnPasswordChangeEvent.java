package pl.coderslab.charity.event;

import org.springframework.context.ApplicationEvent;
import pl.coderslab.charity.entity.User;

public class OnPasswordChangeEvent extends ApplicationEvent {

    private User user;
    private String appUrl;

    public OnPasswordChangeEvent(Object source, User user, String appUrl) {
        super(source);
        this.user = user;
        this.appUrl = appUrl;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }
}
