package com.example.smartfeedbacksmobile;

public class Feedback {
    private String formName;
    private String user;
    private String date;
    private String url;
    private String hash;

    public Feedback(String formName, String user, String date, String hash) {
        this.formName = formName;
        this.user = user;
        this.date = date;
        this.hash = hash;
        this.url = "https://smartfeedbacks.com/" + hash;
    }

    public String getFormName() { return formName; }
    public String getUser() { return user; }
    public String getDate() { return date; }
    public String getUrl() { return url; }
    public String getHash() { return hash; }
}
