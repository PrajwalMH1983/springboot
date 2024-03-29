package com.springsecurity.springsecurityclient.event;

import com.springsecurity.springsecurityclient.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class RegistrationCompleteEvent extends ApplicationEvent {

    private User user;
    //A url that is sent to the user via email to verify
    private String applicationUrl;
    public RegistrationCompleteEvent(User user , String applicationUrl) {
        super(user);
        this.user = user;
        this.applicationUrl = applicationUrl;
    }
}
