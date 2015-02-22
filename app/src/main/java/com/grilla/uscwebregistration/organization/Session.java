package com.grilla.uscwebregistration.organization;

/**
 * Gonna be perfectly honest, don't know how to use this
 * but I'll leave this here so it exists
 * @author Bill Derouin <bill@billderouin.com>
 */
public class Session {
    private int rnrSessionID;
    private String termCode;
    private String rnrSessionCode;
    private String classBeginDate;
    private String lastAddDropDate;
    private String withdrawWithWDate;
    private String finalExamBeginDate;
    private String finalExamEndDate;
    private String classEndDate;
    private String stopDate;

    public Session(int rnrSessionID, String termCode, String rnrSessionCode, String classBeginDate, String lastAddDropDate, String withdrawWithWDate, String finalExamBeginDate, String finalExamEndDate, String classEndDate, String stopDate) {
        this.rnrSessionID = rnrSessionID;
        this.termCode = termCode;
        this.rnrSessionCode = rnrSessionCode;
        this.classBeginDate = classBeginDate;
        this.lastAddDropDate = lastAddDropDate;
        this.withdrawWithWDate = withdrawWithWDate;
        this.finalExamBeginDate = finalExamBeginDate;
        this.finalExamEndDate = finalExamEndDate;
        this.classEndDate = classEndDate;
        this.stopDate = stopDate;
    }

    public int getRnrSessionID() {
        return rnrSessionID;
    }

    public void setRnrSessionID(int rnrSessionID) {
        this.rnrSessionID = rnrSessionID;
    }

    public String getTermCode() {
        return termCode;
    }

    public void setTermCode(String termCode) {
        this.termCode = termCode;
    }

    public String getRnrSessionCode() {
        return rnrSessionCode;
    }

    public void setRnrSessionCode(String rnrSessionCode) {
        this.rnrSessionCode = rnrSessionCode;
    }

    public String getClassBeginDate() {
        return classBeginDate;
    }

    public void setClassBeginDate(String classBeginDate) {
        this.classBeginDate = classBeginDate;
    }

    public String getLastAddDropDate() {
        return lastAddDropDate;
    }

    public void setLastAddDropDate(String lastAddDropDate) {
        this.lastAddDropDate = lastAddDropDate;
    }

    public String getWithdrawWithWDate() {
        return withdrawWithWDate;
    }

    public void setWithdrawWithWDate(String withdrawWithWDate) {
        this.withdrawWithWDate = withdrawWithWDate;
    }

    public String getFinalExamBeginDate() {
        return finalExamBeginDate;
    }

    public void setFinalExamBeginDate(String finalExamBeginDate) {
        this.finalExamBeginDate = finalExamBeginDate;
    }

    public String getFinalExamEndDate() {
        return finalExamEndDate;
    }

    public void setFinalExamEndDate(String finalExamEndDate) {
        this.finalExamEndDate = finalExamEndDate;
    }

    public String getClassEndDate() {
        return classEndDate;
    }

    public void setClassEndDate(String classEndDate) {
        this.classEndDate = classEndDate;
    }

    public String getStopDate() {
        return stopDate;
    }

    public void setStopDate(String stopDate) {
        this.stopDate = stopDate;
    }
}
