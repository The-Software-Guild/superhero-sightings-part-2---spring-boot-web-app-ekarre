package com.sg.superhero_pt1.dao;

import java.util.List;

import com.sg.superhero_pt1.model.Sighting;

public interface SightingDao {
	
	
	Sighting getSightingById(int id);
    List<Sighting> getAllSightings();
    Sighting addSighting(Sighting sighting);
    void updateSighting(Sighting sighting);
    void deleteSightingById(int id);
    List<Sighting> getLastTenSightings();

    // List<Sighting> getSightingsForMember(Member member);
    
}
