package com.example.saloninventorymanager.util;

public class AlertResponse {
    final String responseType;
    final String responseTitle;
    final String responseText;


    public AlertResponse(String responseType, String responseTitle, String responseText) {
        this.responseType = responseType;
        this.responseTitle = responseTitle;
        this.responseText = responseText;
    }

    public String getResponseType() {
        return responseType;
    }

    public String getResponseTitle() {
        return responseTitle;
    }

    public String getResponseText() {
        return responseText;
    }
}
