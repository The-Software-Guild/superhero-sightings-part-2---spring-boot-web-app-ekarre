package com.sg.superhero_pt1.dao;

import com.sg.superhero_pt1.model.MemberOrg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class MemberOrgDaoImpl implements MemberOrgDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    @Transactional
    public MemberOrg addMemberOrg(MemberOrg memberOrg) {
        final String sql = "INSERT INTO memberOrg(member_id, org_id) "
                                                      + "VALUES(?,?)";
        jdbc.update(sql,
                memberOrg.getMember_id(),
                memberOrg.getOrg_id());

        return memberOrg;
    }

    @Override
    public List<MemberOrg> getAllMemberOrg(){
        final String sql = "SELECT * FROM memberOrg";
        return jdbc.query(sql, new MemberOrgDaoImpl.MemberMapper());

    }

    @Override
    @Transactional
    public void deleteMemberOrg(int org_id, int member_id) {
        final String sql = "DELETE FROM memberOrg WHERE member_id = ? AND org_id = ? ";
        jdbc.update(sql, member_id, org_id);
    }


    public static final class MemberMapper implements RowMapper<MemberOrg> {

        @Override
        public MemberOrg mapRow(ResultSet rs, int index) throws SQLException {
            MemberOrg memberOrg = new MemberOrg();
            memberOrg.setMember_id(rs.getInt("member_id"));
            memberOrg.setOrg_id(rs.getInt("org_id"));
            return memberOrg;
        }
    }
}
