package com.example.demo.login;

import lombok.Data;


@Data
public class LoginResponse {
    private String accessToken;
    private String tokenType = "Bearer";

    public LoginResponse(String accessToken) {
        this.accessToken = accessToken;
    }

	@Override
	public String toString() {
		return "LoginResponse [accessToken=" + accessToken + ", tokenType=" + tokenType + "]";
	}
    
}
