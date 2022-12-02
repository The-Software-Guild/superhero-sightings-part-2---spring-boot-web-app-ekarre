package com.sg.superhero_pt1.model;

public class MemberViewDetail {

    int member_id;
    String member_name;
    String member_description;
    String org_name;
    int org_id;
    String powers_name;

    public int getMember_id() {
        return member_id;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }

    public String getMember_name() {
        return member_name;
    }

    public void setMember_name(String member_name) {
        this.member_name = member_name;
    }

    public String getMember_description() {
        return member_description;
    }

    public void setMember_description(String member_description) {
        this.member_description = member_description;
    }

    public String getOrg_name() {
        return org_name;
    }

    public void setOrg_name(String org_name) {
        this.org_name = org_name;
    }

    public int getOrg_id() {
        return org_id;
    }

    public void setOrg_id(int org_id) {
        this.org_id = org_id;
    }

    public String getPowers_name() {
        return powers_name;
    }

    public void setPowers_name(String powers_name) {
        this.powers_name = powers_name;
    }

}
