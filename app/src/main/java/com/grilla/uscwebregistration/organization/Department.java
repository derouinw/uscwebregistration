package com.grilla.uscwebregistration.organization;

/**
 * @author Bill Derouin <bill@billderouin.com>
 */
public class Department {
    private String socDepartmentCode;
    private String socDepartmentDescription;
    private String socSchoolCode;

    public Department(String socDepartmentCode, String socDepartmentDescription, String socSchoolCode) {
        this.socDepartmentCode = socDepartmentCode;
        this.socDepartmentDescription = socDepartmentDescription;
        this.socSchoolCode = socSchoolCode;
    }

    public String getSocDepartmentCode() {
        return socDepartmentCode;
    }

    public void setSocDepartmentCode(String socDepartmentCode) {
        this.socDepartmentCode = socDepartmentCode;
    }

    public String getSocDepartmentDescription() {
        return socDepartmentDescription;
    }

    public void setSocDepartmentDescription(String socDepartmentDescription) {
        this.socDepartmentDescription = socDepartmentDescription;
    }

    public String getSocSchoolCode() {
        return socSchoolCode;
    }

    public void setSocSchoolCode(String socSchoolCode) {
        this.socSchoolCode = socSchoolCode;
    }
}
