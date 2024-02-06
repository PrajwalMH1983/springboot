package com.springsecurity.springsecurityclient.controller;

import com.springsecurity.springsecurityclient.entity.User;
import com.springsecurity.springsecurityclient.entity.VerificationToken;
import com.springsecurity.springsecurityclient.event.RegistrationCompleteEvent;
import com.springsecurity.springsecurityclient.model.PasswordModel;
import com.springsecurity.springsecurityclient.model.UserModel;
import com.springsecurity.springsecurityclient.service.UserService;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@Slf4j
public class RegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @PostMapping("/register")
    public String registerUser(@RequestBody UserModel userModel , final HttpServletRequest request){
        User user = userService.registerUser(userModel);
        publisher.publishEvent(new RegistrationCompleteEvent(
                user,
                applicationUrl(request)
        ));
        return "Successfully Registered !";
    }

    @GetMapping("/verifyRegistration")
    public String verifyRegistration(@RequestParam("token") String token){
        String result = userService.validateVerificationToken(token);
        if(result.equalsIgnoreCase("valid"))
            return "User verified Successfully!";
        return result;
    }

    @GetMapping("/resendVerifyToken")
    public String resendVerificationToken(@RequestParam("token") String oldToken , HttpServletRequest request){
        VerificationToken verificationToken =
                userService.generateNewVerificationToken(oldToken);
        User user = verificationToken.getUser();
        resendVerificationTokenEmail(user , applicationUrl(request) , verificationToken);
        return "Verification Link sent successfully!";
    }

    @PostMapping("/resetPassword")
    public String resetPassword(@RequestBody PasswordModel passwordModel , HttpServletRequest request){
        User user = userService.findUserByEmail(passwordModel.getEmail());
        String url = "";
        System.out.println("USER : " + user);
        if(user != null){
            //Create a new token
            String token = UUID.randomUUID().toString();
            userService.createPasswordResetTokenForUser(user , token);

            url = passwordResetTokenMail(user , applicationUrl(request) , token);
            System.out.println("URL : " + url);
        }
        return url;
    }

    @PostMapping("/savePassword")
    public String savePassword(@RequestParam("token") String token , @RequestBody PasswordModel passwordModel){
        String result = userService.validatePasswordResetToken(token);
        if(!result.equalsIgnoreCase("valid"))
            return "Invalid Token!";

        Optional<User> user = userService.getUserByPasswordResetToken(token);
        if(user.isPresent()){
            //Change the password
            userService.changePassword(user.get() , passwordModel.getNewPassword());
            return "Password reset successful";
        } else {
            //invalid token
            return "Invalid Token!";
        }
    }

    @PostMapping("/changePassword")
    public String changePassword(@RequestBody PasswordModel passwordModel){
        User user = userService.findUserByEmail(passwordModel.getEmail());
        if(!userService.checkIfValidOldPassword(user , passwordModel.getOldPassword())){
            return "Invalid Old Password!";
        }
        //Save new password functionality
        userService.changePassword(user , passwordModel.getNewPassword());
        return "Password Changed Successfully!";
    }

    private String passwordResetTokenMail(User user, String applicationUrl, String token) {
        String url = applicationUrl
                + "savePassword?token="
                + token;

        // sendverificationEmail()
        log.info("Click the link below to reset your password : {}" , url);
        return url;
    }

    private void resendVerificationTokenEmail(User user, String applicationUrl , VerificationToken verificationToken) {
        String url = applicationUrl
                + "/verifyRegistration?token="
                + verificationToken.getToken();

        // sendverificationEmail()
        log.info("Click the link to verify your account : {}" , url);
    }

    private String applicationUrl(HttpServletRequest request) {
        return "http://"
                + request.getServerName()
                + ":"
                + request.getServerPort()
                + "/"
                + request.getContextPath();
    }
}
