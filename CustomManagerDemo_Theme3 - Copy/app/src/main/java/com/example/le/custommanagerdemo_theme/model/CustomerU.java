package com.example.le.custommanagerdemo_theme.model;

/**
 * Created by Henry on 05-Aug-16.
 */
public class CustomerU {


    private String accountID = "";
    private String name = "";
    private String phoneNumber = "";
    private String address = "";
    private String company = "";
    private String project = "";
    private String email = "";
    private String firstContact = "";
    private String nextContact = "";
    private String rating = "";
    private String customerType = "";
    private String demand = "";
    private String agency = "";
    private String note = "";
    private String other = "";


    public CustomerU() {

    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstContact() {
        return firstContact;
    }

    public void setFirstContact(String firstContact) {
        this.firstContact = firstContact;
    }

    public String getNextContact() {
        return nextContact;
    }

    public void setNextContact(String nextContact) {
        this.nextContact = nextContact;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getDemand() {
        return demand;
    }

    public void setDemand(String demand) {
        this.demand = demand;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public void setString(int i, String value) {
        switch (i) {
            case 1:
                name += value + " ";
                break;
            case 3:
                phoneNumber += value + " ";
                break;
            case 2:
                address += value + " ";
                break;
            case 4:
                company += value + " ";
                break;
            case 5:
                project += value + " ";
                break;
            case 6:
                email += value + " ";
                break;
            case 7:
                firstContact += value + " ";
                break;
            case 8:
                nextContact += value + " ";
                break;
            case 9:
                rating += value + " ";
                break;
            case 10:
                customerType += value + " ";
                break;
            case 11:
                demand += value + " ";
                break;
            case 12:
                agency += value + " ";
                break;
            case 13:
                note += value + " ";
                break;
            case 14:
                other += value + " ";
                break;
        }
    }
}
