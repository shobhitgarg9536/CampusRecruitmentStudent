package com.shobhit.campusrecruitmentstudent;

public class ApplyJobCompanyDetailModel {

    String name;

    public String getName() {
        return name;
    }

    public ApplyJobCompanyDetailModel(){}

    public ApplyJobCompanyDetailModel(String name, String contact, String city, String address, String about) {
        this.name = name;
        this.contact = contact;
        this.city = city;
        this.address = address;
        this.about = about;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    String contact;
    String city;
    String address;
    String about;
}
