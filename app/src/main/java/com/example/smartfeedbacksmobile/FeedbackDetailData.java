package com.example.smartfeedbacksmobile;

import com.google.gson.annotations.SerializedName;

public class FeedbackDetailData {

    @SerializedName("nps")
    private String nps;

    @SerializedName("csat")
    private String csat;

    @SerializedName("cli")
    private String cli;

    @SerializedName("ces")
    private String ces;

    @SerializedName("simple_star")
    private String simpleStar;

    @SerializedName("exit_survey")
    private String exitSurvey;

    @SerializedName("free_question_1")
    private String freeQuestion1;

    @SerializedName("free_question_2")
    private String freeQuestion2;

    public String getNps() { return nps; }
    public String getCsat() { return csat; }
    public String getCli() { return cli; }
    public String getCes() { return ces; }
    public String getSimpleStar() { return simpleStar; }
    public String getExitSurvey() { return exitSurvey; }
    public String getFreeQuestion1() { return freeQuestion1; }
    public String getFreeQuestion2() { return freeQuestion2; }
}
