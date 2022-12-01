package com.sg.superhero_pt1.dao;

import com.sg.superhero_pt1.model.Member;


import java.util.List;

public interface MemberDao {
    Member addMember(Member member);
    List<Member> getAllMembers();
    Member getMemberById(int id);
    List<Member> getAllMembersAtSighting(int sightingId);
    List<Member> getAllMembersInOrganization(int org_id);
    void deleteMemberById(int member_id);
    void updateMember(Member member);

}

