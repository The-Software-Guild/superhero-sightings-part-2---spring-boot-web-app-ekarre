package com.sg.superhero_pt1.controller;


import com.sg.superhero_pt1.dao.MemberDao;
import com.sg.superhero_pt1.dao.MemberSightingDao;
import com.sg.superhero_pt1.dao.SightingDao;
import com.sg.superhero_pt1.model.Member;
import com.sg.superhero_pt1.model.MemberSighting;
import com.sg.superhero_pt1.model.MemberViewDetail;
import com.sg.superhero_pt1.model.Sighting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class MemberSightingController {

    @Autowired
    SightingDao sightingDao;

    @Autowired
    MemberSightingDao memberSightingDao;

    @Autowired
    MemberDao memberDao;


    Set<ConstraintViolation<MemberSighting>> violations = new HashSet<>();

    @GetMapping("MostRecentSightings")
    public String displayRecentSightings(Model model) {
        List<MemberSighting> memberSightings = memberSightingDao.getMostRecent();
        model.addAttribute("MostRecentSightings", memberSightings);
        return "MostRecentSightings";

    }

    @GetMapping("memberSightings")
    public String displayMemberSightings(Model model) {
        List<MemberSighting> memberSightings = memberSightingDao.getAllMemberSightings();
        List<MemberViewDetail> members = memberDao.getAllMembers();
        List<Sighting> sightings = sightingDao.getAllSightings();
        model.addAttribute("memberSightings", memberSightings);
        model.addAttribute("sightings", sightings);
        model.addAttribute("members", members);

        return "memberSightings";
    }


    @PostMapping("addMemberSighting")
    public String addMemberSighting(HttpServletRequest request) throws Exception {
        String sighting_id = request.getParameter("sighting_id");
        String member_id = request.getParameter("member_id");
        String date = request.getParameter("date");

        MemberSighting membersighting = new MemberSighting();
        membersighting.setSighting_id(Integer.parseInt(sighting_id));
        membersighting.setMember_id(Integer.parseInt(member_id));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        membersighting.setDate(formatter.parse(date));

        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(membersighting);

        if (violations.isEmpty()) {
            memberSightingDao.addMemberSighting(membersighting);
        }

        return "redirect:/memberSightings";
    }

    @GetMapping("deleteMemberSighting")
    public String deleteMemberSighting(Integer sighting_id, Integer member_id) {
        memberSightingDao.deleteMemberSightings(sighting_id, member_id);
        return "redirect:/memberSightings";
    }

    @GetMapping("editMemberSighting")
    public String editMemberSighting(HttpServletRequest request, Model model) {
        int sighting_id = Integer.parseInt(request.getParameter("sighting_id"));
        int member_id = Integer.parseInt(request.getParameter("member_id"));
        MemberSighting memberSighting = memberSightingDao.getMemberSightingByIds(sighting_id, member_id);
        List<MemberViewDetail> members = memberDao.getAllMembers();
        List<Sighting> sightings = sightingDao.getAllSightings();
        model.addAttribute("sightings", sightings);
        model.addAttribute("members", members);
        model.addAttribute("memberSighting", memberSighting);
        return "editMemberSighting";
    }

    @PostMapping("editMemberSighting")
    public String performEditMemberSighting(HttpServletRequest request) throws Exception {
        int sighting_id = Integer.parseInt(request.getParameter("sighting_id"));
        int member_id = Integer.parseInt(request.getParameter("member_id"));
        MemberSighting memberSighting = memberSightingDao.getMemberSightingByIds(sighting_id, member_id);

        memberSighting.setMember_id(Integer.parseInt(request.getParameter("member_id")));
        memberSighting.setSighting_id(Integer.parseInt(request.getParameter("sighting_id")));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        memberSighting.setDate(formatter.parse((request.getParameter("date"))));

        memberSightingDao.updateMemberSighting(memberSighting);

        return "redirect:/memberSightings";
    }

    /// WORK in progress *****
    @GetMapping("memberSightingsDetails/{sighting_id}/{member_id}")
    public String displaySightingDetails(@PathVariable int sighting_id, @PathVariable int member_id, Model model) {
        MemberSighting memberSighting = memberSightingDao.getMemberSightingByIds(
                sighting_id, member_id);
        memberSighting.setMembers(memberSighting.getMembers());
        memberSighting.setSighting(memberSighting.getSighting());
        model.addAttribute("memberSighting", memberSighting);
        Sighting sighting = sightingDao.getSightingById(sighting_id);
        Member member = memberDao.getMemberById(member_id);
        model.addAttribute("sighting", sighting);
        model.addAttribute("member", member);
        return "memberSightingsDetails";

    }
  }
