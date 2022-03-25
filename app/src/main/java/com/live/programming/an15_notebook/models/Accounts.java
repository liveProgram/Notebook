package com.live.programming.an15_notebook.models;

public class Accounts {
    private String accId;
    private String accHName;
    private String accEmail;
    private String accMobile;

    public Accounts(String accId, String accHName, String accEmail, String accMobile) {
        this.accId = accId;
        this.accHName = accHName;
        this.accEmail = accEmail;
        this.accMobile = accMobile;
    }

    public String getAccId() {
        return accId;
    }

    public String getAccHName() {
        return accHName;
    }

    public String getAccEmail() {
        return accEmail;
    }

    public String getAccMobile() {
        return accMobile;
    }
}
