package com.shobhit.campusrecruitmentstudent;

public class JobPostingModel {

    public JobPostingModel(){}

    public JobPostingModel(String jobId, String companyId, String companyName, String vacancyName, String noOfVacancy, String dateOfInterview) {
       this.jobId = jobId;
         this.companyId = companyId;
        this.companyName = companyName;
        this.vacancyName = vacancyName;
        NoOfVacancy = noOfVacancy;
        DateOfInterview = dateOfInterview;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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

    private String jobId, companyId, companyName, vacancyName, NoOfVacancy, DateOfInterview;
}
