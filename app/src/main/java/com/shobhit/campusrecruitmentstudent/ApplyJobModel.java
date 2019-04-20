package com.shobhit.campusrecruitmentstudent;

public class ApplyJobModel {

    public ApplyJobModel(){}

    public ApplyJobModel(String contactPerson, String jobId, String companyId, String postName, String vacancyName, String noOfVacancy, String dateOfInterview) {
        this.jobId = jobId;
        this.contactPerson = contactPerson;
        this.companyId = companyId;
        this.postName = postName;
        this.vacancyName = vacancyName;
        NoOfVacancy = noOfVacancy;
        DateOfInterview = dateOfInterview;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getVacancyName() {
        return vacancyName;
    }

    public void setVacancyName(String vacancyName) {
        this.vacancyName = vacancyName;
    }

    public String getNoOfVacancy() {
        return NoOfVacancy;
    }

    public void setNoOfVacancy(String noOfVacancy) {
        NoOfVacancy = noOfVacancy;
    }

    public String getDateOfInterview() {
        return DateOfInterview;
    }

    public void setDateOfInterview(String dateOfInterview) {
        DateOfInterview = dateOfInterview;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    private String jobId;
    private String companyId;
    private String postName;
    private String vacancyName;
    private String NoOfVacancy;
    private String DateOfInterview;

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    private String contactPerson;
}
