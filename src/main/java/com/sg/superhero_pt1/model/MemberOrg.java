package com.sg.superhero_pt1.model;

import java.util.List;
import java.util.Objects;

public class MemberOrg {

    private int member_id;

    private int org_id;

    private List<Member> members;

    private List<Organization> organizations;

    public int getMember_id() {
        return member_id;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }

    public int getOrg_id() {
        return org_id;
    }

    public void setOrg_id(int org_id) {
        this.org_id = org_id;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public List<Organization> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(List<Organization> organizations) {
        this.organizations = organizations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MemberOrg)) return false;
        MemberOrg memberOrg = (MemberOrg) o;
        return getMember_id() == memberOrg.getMember_id() && getOrg_id() == memberOrg.getOrg_id() && Objects.equals(getMembers(), memberOrg.getMembers()) && Objects.equals(getOrganizations(), memberOrg.getOrganizations());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMember_id(), getOrg_id(), getMembers(), getOrganizations());
    }
}
