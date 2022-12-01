package com.sg.superhero_pt1.controller;

import com.sg.superhero_pt1.dao.MemberDao;
import com.sg.superhero_pt1.dao.OrganizationDao;
import com.sg.superhero_pt1.dao.PowersDao;
import com.sg.superhero_pt1.model.Member;
import com.sg.superhero_pt1.model.Organization;
import com.sg.superhero_pt1.model.Powers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
public class MemberController {
    @Autowired
    MemberDao memberDao;

    @Autowired
    OrganizationDao organizationDao;

    @Autowired
    PowersDao powersDao;

    @GetMapping("members")
    public String displayMembers(Model model){
        List<Member> members = memberDao.getAllMembers();
        model.addAttribute("members", members);
        List<Organization> organizations = organizationDao.getAllOrganizations();
        model.addAttribute("organizations", organizations);
        List<Powers> powers = powersDao.getAllPowers();
        model.addAttribute("powers", powers);
        return "members";
    }

    @PostMapping("addMember")
    public String addMember(HttpServletRequest request){
        String member_name = request.getParameter("member_name");
        String member_description = request.getParameter("member_description");
        int powers = Integer.parseInt(request.getParameter("powers_id"));
        int org_id = Integer.parseInt(request.getParameter("org_id"));

        Member member = new Member();
        member.setMember_name(member_name);
        member.setDescription(member_description);
        member.setPowers_id(powers);
        memberDao.addMember(member);
        return "redirect:/members";
    }


    @GetMapping("member/{member_id}")
    public Member getMember(@PathVariable int member_id){
        Member member = memberDao.getMemberById(member_id);
        return member;
    }

    @GetMapping("sightingMembers/{sighting_id}")
    public List<Member> getMembersAtSighting(@PathVariable int sighting_id){
        List<Member> members = memberDao.getAllMembersAtSighting(sighting_id);
        return members;
    }

    @GetMapping("organizationMembers/{org_id}")
    public List<Member> getMembersAtOrganization(@PathVariable int org_id){
        List<Member> members = memberDao.getAllMembersInOrganization(org_id);
        return members;
    }

    @GetMapping("deleteMember")
    public String deleteMember(Integer member_id){
        memberDao.deleteMemberById(member_id);
        return "redirect:/members";
    }

    @GetMapping("updateMember")
    public String editMember(HttpServletRequest request, Model model) {
        int member_id = Integer.parseInt(request.getParameter("member_id"));
        Member member = memberDao.getMemberById(member_id);
        //below: adding in these list elements allow us to update these fields on the update page
        List<Organization> organizations = organizationDao.getAllOrganizations();
        model.addAttribute("organizations", organizations);
        List<Powers> powers = powersDao.getAllPowers();
        model.addAttribute("powers", powers);
        model.addAttribute("member", member);
        return "updateMember";
    }

    @PostMapping("updateMember")
    public String performUpdateMember(HttpServletRequest request) {
        int member_id = Integer.parseInt(request.getParameter("member_id"));
        Member member = memberDao.getMemberById(member_id);
        member.setMember_name(request.getParameter("member_name"));
        member.setDescription(request.getParameter("description"));
        member.setPowers_id(Integer.parseInt(request.getParameter("powers_id")));

        memberDao.updateMember(member);
        return "redirect:/members";
    }

}
