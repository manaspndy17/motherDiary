package com.motherdiary.motherdiary.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class mailCont {

    @Autowired
    private JavaMailSender mailSender;

    @PostMapping("/sendEnquiry")
    public String sendEnquiry(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String enquiry) {

        String to = "manaspndy1712@gmail.com"; // Your host email

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("New Enquiry from Homepage");
        message.setText(
                "Name: " + name + "\n" +
                "Email: " + email + "\n" +
                "Message: " + enquiry
        );

        mailSender.send(message);

        return "redirect:/?success=true"; // redirect with success message
    }
}
