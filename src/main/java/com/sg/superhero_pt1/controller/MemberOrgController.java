package com.sg.superhero_pt1.controller;


import com.sg.superhero_pt1.dao.MemberDao;
import com.sg.superhero_pt1.dao.MemberOrgDao;
import com.sg.superhero_pt1.dao.OrganizationDao;
import com.sg.superhero_pt1.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class MemberOrgController {

    @Autowired
    MemberDao memberDao;

    @Autowired
    OrganizationDao organizationDao;

    @Autowired
    MemberOrgDao memberOrgDao;


    @GetMapping("memberOrg")
    public String displayMemberOrg(Model model) {
        List<MemberOrg> memberOrg = memberOrgDao.getAllMemberOrg();
        List<MemberViewDetail> members = memberDao.getAllMembers();
        List<Organization> orgs = organizationDao.getAllOrganizations();
        model.addAttribute("memberOrg", memberOrg);
        model.addAttribute("orgs", orgs);
        model.addAttribute("members", members);

        return "memberOrg";
    }


    /*@PostMapping("addMemberOrg")
    public String addMemberOrg(HttpServletRequest request) {
        String org_id = request.getParameter("org_id");
        String member_id = request.getParameter("member_id");

        MemberOrg memberOrg = new MemberOrg();
        memberOrg.setOrg_id(Integer.parseInt(org_id));
        memberOrg.setMember_id(Integer.parseInt(member_id));

        memberOrgDao.addMemberOrg(memberOrg);

        return "redirect:/memberOrg";
    }*/

    @GetMapping("deleteMemberOrg")
    public String deleteMemberOrg(Integer org_id, Integer member_id) {
        memberOrgDao.deleteMemberOrg(org_id, member_id);
        return "redirect:/memberOrg";
    }
}
