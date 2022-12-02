package com.sg.superhero_pt1.dao;

import com.sg.superhero_pt1.model.MemberOrg;

import java.util.List;

public interface MemberOrgDao {

    MemberOrg addMemberOrg(MemberOrg memberOrg);

    List<MemberOrg> getAllMemberOrg();

    void deleteMemberOrg( int org_id, int member_id);
}
