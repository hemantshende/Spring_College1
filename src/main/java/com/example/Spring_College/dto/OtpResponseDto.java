package com.example.Spring_College.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OtpResponseDto {
	private OtpStatus status;
    private String message;
}
