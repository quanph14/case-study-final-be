package com.codegym.webthuenha.controller.email;

import com.codegym.webthuenha.model.EmailDetails;
import com.codegym.webthuenha.service.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.SchemaOutputResolver;

@RestController
@CrossOrigin("*")
public class EmailController {
    @Autowired
    private EmailService emailService;

    // Sending a simple Email
    @PostMapping("/sendMail")
    public ResponseEntity<EmailDetails> sendMail(@RequestBody EmailDetails details, BindingResult bindingResult)
    {
        if (bindingResult.hasFieldErrors()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        String status
                = emailService.sendSimpleMail(details);
        System.out.println(status);
        return new ResponseEntity<>(details, HttpStatus.OK);
    }

    @PostMapping("/sendMailWithAttachment")
    public String sendMailWithAttachment(
            @RequestBody EmailDetails details)
    {
        String status
                = emailService.sendMailWithAttachment(details);

        return status;
    }
}

