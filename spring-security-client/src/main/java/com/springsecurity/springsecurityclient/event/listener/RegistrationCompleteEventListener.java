package com.springsecurity.springsecurityclient.event.listener;

import com.springsecurity.springsecurityclient.entity.User;
import com.springsecurity.springsecurityclient.event.RegistrationCompleteEvent;
import com.springsecurity.springsecurityclient.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {

    @Autowired
    private UserService userService;
    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        //Create the verification token for the user with link
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        userService.saveVerificationTokenForUser(user , token);

        //Send mail to user
        String url = event.getApplicationUrl()
                + "verifyRegistration?token="
                + token;

        // sendverificationEmail()
        log.info("Click the link to verify your account : {}" , url);
    }
}
