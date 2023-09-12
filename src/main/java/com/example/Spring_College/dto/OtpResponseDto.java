package com.example.Spring_College.dto;

import com.twilio.rest.api.v2010.account.Message;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OtpResponseDto {
	private OtpStatus status;
    private String message;
}
