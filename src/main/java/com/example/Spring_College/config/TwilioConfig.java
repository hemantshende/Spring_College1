package com.example.Spring_College.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import lombok.Data;

@Configuration
@Component
@ConfigurationProperties(prefix = "twilio")
@Data
public class TwilioConfig {
    private String accountSid;
    private String authToken;
    private String serviceSid;
    private String phoneNumber;
}
