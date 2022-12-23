package com.codegym.webthuenha.service.email;

import com.codegym.webthuenha.model.EmailDetails;
import org.springframework.stereotype.Service;


public interface EmailService {
    // Method
    // To send a simple email
    String sendSimpleMail(EmailDetails details);

    // Method
    // To send an email with attachment
    String sendMailWithAttachment(EmailDetails details);
}
