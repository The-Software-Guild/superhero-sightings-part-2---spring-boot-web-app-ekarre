package com.sg.superhero_pt1.dao;

import com.sg.superhero_pt1.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.util.List;
import java.util.Objects;

@Repository
public class MemberDaoImpl implements MemberDao {
    @Autowired
    JdbcTemplate jdbc;

    @Override
    @Transactional
    public Member addMember(Member member) {
        final String sql = "INSERT INTO member(member_name, member_description, powers_id) "
                + "VALUES(?,?,?)";
        jdbc.update(sql,
                member.getMember_name(),
                member.getDescription(),
                member.getPowers_id());
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        member.setMember_id(newId);
        return member;
    }

    @Override
    public List<Member> getAllMembers(){
        final String sql = "SELECT * FROM member";
        return jdbc.query(sql, new MemberMapper());

    }


    @Override
    public Member getMemberById(int member_id){
        try{
            final String sql = "SELECT * FROM member WHERE member_id = ?";
            return jdbc.queryForObject(sql, new MemberMapper(), member_id);
        } catch (DataAccessException ex) {
            return null;
        }
    }
    @Override
    public List<Member> getAllMembersAtSighting(int sighting_id) {
//        final String sql = "SELECT m.* FROM MemberSighting WHERE sighting_id = ?";
        final String sql = "SELECT m.* FROM member m " + "JOIN memberSighting ms ON " +
                "ms.member_id = m.member_id WHERE ms.sighting_id = ?";
        return jdbc.query(sql, new MemberMapper(), sighting_id);
    }
    @Override
    public List<Member> getAllMembersInOrganization(int org_id) {
        final String sql = "SELECT m.* FROM member m " + "JOIN memberOrg mo ON " +
                "mo.member_id = m.member_id WHERE mo.org_id = ?";
        return jdbc.query(sql, new MemberMapper(), org_id);
    }

    @Override
    @Transactional
    public void deleteMemberById(int member_id) {
        final String DELETE_MEMBERORG = "DELETE FROM memberOrg WHERE member_id = ?";
        jdbc.update(DELETE_MEMBERORG, member_id);

        final String DELETE_MEMBERSIGHT = "DELETE FROM memberSighting WHERE member_id = ?";
        jdbc.update(DELETE_MEMBERSIGHT, member_id);

        final String DELETE_MEMBER = "DELETE FROM member WHERE member_id = ?";
        jdbc.update(DELETE_MEMBER, member_id);
    }

    @Override
    public void updateMember(Member member){
        final String sql = "UPDATE member SET member_name = ?, member_description = ?, powers_id = ? " +
                "WHERE member_id = ?";
        jdbc.update(sql,
                member.getMember_name(),
                member.getDescription(),
                member.getPowers_id(),
                member.getMember_id());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MemberDaoImpl)) return false;
        MemberDaoImpl memberDao = (MemberDaoImpl) o;
        return Objects.equals(jdbc, memberDao.jdbc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jdbc);
    }

    public static final class MemberMapper implements RowMapper<Member> {

        @Override
        public Member mapRow(ResultSet rs, int index) throws SQLException {
            Member member = new Member();
            member.setMember_id(rs.getInt("member_id"));
            member.setMember_name(rs.getString("member_name"));
            member.setDescription(rs.getString("member_description"));
            member.setPowers_id(rs.getInt("powers_id"));
            return member;
        }
    }
}
