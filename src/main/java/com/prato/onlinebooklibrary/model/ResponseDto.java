package com.prato.onlinebooklibrary.model;

public class ResponseDto {
    private long id;
    private String userId;
    private String email;
    private String AccessToken;
    public ResponseDto(){

    }

    public ResponseDto(long id, String userId, String email, String accessToken) {
        this.id = id;
        this.userId = userId;
        this.email = email;
        AccessToken = accessToken;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccessToken() {
        return AccessToken;
    }

    public void setAccessToken(String accessToken) {
        AccessToken = accessToken;
    }
}
