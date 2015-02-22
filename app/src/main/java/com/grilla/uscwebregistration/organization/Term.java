package com.grilla.uscwebregistration.organization;

/**
 * @author Bill Derouin <bill@billderouin>
 */
public class Term {
    private String termCode;
    private String description;
    private String preRegStartDate;
    private String preRegEndDate;
    private String earlyRegStartDate;
    private String normalRegEndDate;
    private String commencementDate;

    public Term(String termCode, String description, String preRegStartDate, String preRegEndDate, String earlyRegStartDate, String normalRegEndDate, String commencementDate) {
        this.termCode = termCode;
        this.description = description;
        this.preRegStartDate = preRegStartDate;
        this.preRegEndDate = preRegEndDate;
        this.earlyRegStartDate = earlyRegStartDate;
        this.normalRegEndDate = normalRegEndDate;
        this.commencementDate = commencementDate;
    }

    public String getTermCode() {
        return termCode;
    }

    public void setTermCode(String termCode) {
        this.termCode = termCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPreRegStartDate() {
        return preRegStartDate;
    }

    public void setPreRegStartDate(String preRegStartDate) {
        this.preRegStartDate = preRegStartDate;
    }

    public String getPreRegEndDate() {
        return preRegEndDate;
    }

    public void setPreRegEndDate(String preRegEndDate) {
        this.preRegEndDate = preRegEndDate;
    }

    public String getEarlyRegStartDate() {
        return earlyRegStartDate;
    }

    public void setEarlyRegStartDate(String earlyRegStartDate) {
        this.earlyRegStartDate = earlyRegStartDate;
    }

    public String getNormalRegEndDate() {
        return normalRegEndDate;
    }

    public void setNormalRegEndDate(String normalRegEndDate) {
        this.normalRegEndDate = normalRegEndDate;
    }

    public String getCommencementDate() {
        return commencementDate;
    }

    public void setCommencementDate(String commencementDate) {
        this.commencementDate = commencementDate;
    }
}
