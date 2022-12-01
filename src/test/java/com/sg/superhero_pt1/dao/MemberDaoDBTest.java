package com.sg.superhero_pt1.dao;

import com.sg.superhero_pt1.TestApplicationConfiguration;
import com.sg.superhero_pt1.model.Member;
import junit.framework.TestCase;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.Test;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Before;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class MemberDaoDBTest extends TestCase {
    /*@Autowired
    MemberDao memberDao;


    public MemberDaoDBTest(){}

    @Before
    public void setUp() {
        List<Member> members = memberDao.getAllMembers();
        for(Member member : members){
            memberDao.deleteMemberById(member.getMember_id());
        }
        //do for organization and sightings and addresses
    }

    @Test
    public void testAddAndGetMember(){
        Member member = new Member();
        member.setMember_name("Test name");
        member.setDescription("Test description");
        member.setPowers("Test powers");
        member = memberDao.addMember(member);
        Member fromDao = memberDao.getMemberById(member.getMember_id());
        assertEquals(member, fromDao);
    }

    @Test
    public void getAllMembers(){
        Member member = new Member();
        member.setMember_name("Test name");
        member.setDescription("Test description");
        member.setPowers("Test powers");
        member = memberDao.addMember(member);

        Member member2 = new Member();
        member2.setMember_name("Test name2");
        member2.setDescription("Test description2");
        member2.setPowers("Test powers2");
        member2 = memberDao.addMember(member2);

        List<Member> members = memberDao.getAllMembers();
        assertEquals(2, members.size());
        assertTrue(members.contains(member));
        assertTrue(members.contains(member2));
    }

    @Test
    public void testUpdateMember() {
        Member member = new Member();
        member.setMember_name("Test name");
        member.setDescription("Test description");
        member.setPowers("Test powers");
        member = memberDao.addMember(member);

        Member fromDao = memberDao.getMemberById(member.getMember_id());
        assertEquals(member, fromDao);
        member.setMember_name("New Test name");
        memberDao.updateMember(member);
        assertNotEquals(member, fromDao);
        fromDao = memberDao.getMemberById(member.getMember_id());
        assertEquals(member, fromDao);
    }

    @Test
    public void testDeleteMemberById(){
        Member member = new Member();
        member.setMember_name("Test name");
        member.setDescription("Test description");
        member.setPowers("Test powers");
        member = memberDao.addMember(member);

        Member fromDao = memberDao.getMemberById(member.getMember_id());
        assertEquals(member, fromDao);
        memberDao.deleteMemberById(member.getMember_id());
        fromDao = memberDao.getMemberById(member.getMember_id());
        assertNull(fromDao);
    }

        //    @Test
//    public void testGetAllMembersAtSighting(){
//        Member member = new Member();
//        member.setMember_name("Test name");
//        member.setDescription("Test description");
//        member.setPowers("Test powers");
//        member = memberDao.addMember(member);
//
//        Member member2 = new Member();
//        member2.setMember_name("Test name2");
//        member2.setDescription("Test description2");
//        member2.setPowers("Test powers2");
//    }*/

}
