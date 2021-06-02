package com.example.meetime.Domain;

public class Deal {

    private String title;

    private String value;

    private String currency;

    private User user_id;

    private Person person_id;

    private Organization org_id;

    private Integer stage_id;

    private String status;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public User getUser_id() {
        return user_id;
    }

    public void setUser_id(User user_id) {
        this.user_id = user_id;
    }

    public Person getPerson_id() {
        return person_id;
    }

    public void setPerson_id(Person person_id) {
        this.person_id = person_id;
    }

    public Organization getOrg_id() {
        return org_id;
    }

    public void setOrg_id(Organization org_id) {
        this.org_id = org_id;
    }

    public Integer getStage_id() {
        return stage_id;
    }

    public void setStage_id(Integer stage_id) {
        this.stage_id = stage_id;
    }


}
