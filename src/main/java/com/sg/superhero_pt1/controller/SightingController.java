package com.sg.superhero_pt1.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import com.sg.superhero_pt1.dao.AddressDao;
import com.sg.superhero_pt1.dao.MemberDao;
import com.sg.superhero_pt1.model.Address;
import com.sg.superhero_pt1.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.sg.superhero_pt1.dao.SightingDao;
import com.sg.superhero_pt1.model.Sighting;

@Controller
public class SightingController {
	
	   @Autowired
	   SightingDao sightingDao;

	   @Autowired
	MemberDao memberDao;

	   @Autowired
	AddressDao addressDao;
	
	   
	   Set<ConstraintViolation<Sighting>> violations = new HashSet<>();


	   @GetMapping("sightings")
	    public String displaySightings(Model model) {
		   model.addAttribute("errors", violations);
	        List<Sighting> sightings = sightingDao.getAllSightings();
	        model.addAttribute("sightings", sightings);
		   List<Member> members = memberDao.getAllMembers();
		   model.addAttribute("members", members);
		   List<Address> addresses = addressDao.getAllAddresses();
		   model.addAttribute("addresses", addresses);
	        return "sightings";
	    }

	@GetMapping("homepage")
	public String getFirstTen(Model model) {
		model.addAttribute("errors", violations);
		List<Sighting> sightings = sightingDao.getLastTenSightings();
		model.addAttribute("sightings", sightings);
		return "homepage";
	}
	   
	   @PostMapping("addSighting")
	    public String addSighting(HttpServletRequest request) {
	        String name = request.getParameter("name");
	        String description = request.getParameter("description");
	        String latitude = request.getParameter("latitude");
	        String longitude = request.getParameter("longitude");
	        int add_id = Integer.parseInt(request.getParameter("add_id"));
			int member_id = Integer.parseInt(request.getParameter("member_id"));
	        
	        Sighting sighting = new Sighting();
	        sighting.setName(name);
	        sighting.setDescription(description);
	        sighting.setLatitude(Double.parseDouble(latitude));
	        sighting.setLongitude(Double.parseDouble(longitude));
	        sighting.setAdd_id(add_id);

	        
	        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
	        violations = validate.validate(sighting);
	        
	        if(violations.isEmpty()){
	            sightingDao.addSighting(sighting);
	        }
	        
	        return "redirect:/sightings";
	    }

	   
	   @GetMapping("sightingDetail")
	    public String sightingDetail(Integer id, Model model) {
	        Sighting sighting = sightingDao.getSightingById(id);
	        model.addAttribute("sighting", sighting);
	        return "sightingDetail";
	    }
	
	   @GetMapping("deleteSighting")
	    public String deleteSighting(Integer id) {
	        sightingDao.deleteSightingById(id);
	        return "redirect:/sightings";
	    }
	    
	   @GetMapping("editSighting")
	    public String editSighting(HttpServletRequest request, Model model) {
	        int id = Integer.parseInt(request.getParameter("id"));
	        Sighting sighting = sightingDao.getSightingById(id);
		   List<Member> members = memberDao.getAllMembers();
		   model.addAttribute("members", members);
		   List<Address> addresses = addressDao.getAllAddresses();
		   model.addAttribute("addresses", addresses);
	        model.addAttribute("sighting", sighting);
	        return "editSighting";
	    }
	    
	    @PostMapping("editSighting")
	    public String performEditSighting(HttpServletRequest request) {
	        int id = Integer.parseInt(request.getParameter("id"));
	        Sighting sighting = sightingDao.getSightingById(id);
	        
	        sighting.setName(request.getParameter("name"));
	        sighting.setDescription(request.getParameter("description"));
	        sighting.setLatitude(Double.parseDouble(request.getParameter("latitude")));
	        sighting.setLongitude(Double.parseDouble(request.getParameter("longitude"))); 
	        sighting.setAdd_id(Integer.parseInt(request.getParameter("add_id")));
			// sighting.setId(Integer.parseInt(request.getParameter("id")));

			sightingDao.updateSighting(sighting);
	        
	        return "redirect:/sightings";
	    }
}
