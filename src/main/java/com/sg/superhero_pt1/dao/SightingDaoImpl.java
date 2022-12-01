package com.sg.superhero_pt1.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sg.superhero_pt1.model.Sighting;

@Repository
public class SightingDaoImpl implements SightingDao{
	
	
	 @Autowired
	   JdbcTemplate jdbc;


	@Override
	public Sighting getSightingById(int id) {
		  try {
	            final String SELECT_SIGHTING_BY_ID = "SELECT * FROM sightings WHERE sighting_id = ?";
	            Sighting sighting = jdbc.queryForObject(SELECT_SIGHTING_BY_ID, new SightingMapper(), id);
	            return sighting;
	        } catch(DataAccessException ex) {
	            return null;
	        }
	}

	
	@Override
	public List<Sighting> getAllSightings() {
		final String GET_ALL_SIGHTINGS = "SELECT * FROM sightings";
        return jdbc.query(GET_ALL_SIGHTINGS, new SightingMapper());
	}

	@Override
	public List<Sighting> getLastTenSightings() {
		final String GET_ALL_SIGHTINGS = "SELECT * FROM sightings ORDER BY sighting_id DESC LIMIT 10";
		return jdbc.query(GET_ALL_SIGHTINGS, new SightingMapper());
	}

	
	
	@Override
	@Transactional 
	public Sighting addSighting(Sighting sighting) {
		 final String INSERT_SIGHTING = "INSERT INTO sightings(sighting_name, description, latitude, longitude, add_id) "
	                + "VALUES(?,?,?,?,?)";
	        jdbc.update(INSERT_SIGHTING,
	                sighting.getName(),
	                sighting.getDescription(),
	                sighting.getLatitude(),
	        		sighting.getLongitude(),
	        		sighting.getAdd_id());
	                
	        
	        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
	        sighting.setId(newId);
	        return sighting;
	}

	
	
	@Override
	public void updateSighting(Sighting sighting) {
		final String UPDATE_SIGHTING = "UPDATE sightings SET sighting_name = ?, description = ?, latitude = ?, longitude = ?, add_id =? "
                + "WHERE sighting_id = ?";
        jdbc.update(UPDATE_SIGHTING,
                sighting.getName(),
                sighting.getDescription(),
                sighting.getLatitude(),
        		sighting.getLongitude(),
        		sighting.getAdd_id(),
				sighting.getId());
		
	}

	
	
	@Override
	@Transactional
	public void deleteSightingById(int id) {
		 final String DELETE_MEMBERSIGHTING_SIGHTINGS = "DELETE FROM memberSighting WHERE sighting_id = ?";
	        jdbc.update(DELETE_MEMBERSIGHTING_SIGHTINGS, id);
	        
	        final String DELETE_SIGHTING = "DELETE FROM sightings WHERE sighting_id = ?";
	        jdbc.update(DELETE_SIGHTING, id);
		
	}
	
	
	 public static final class SightingMapper implements RowMapper <Sighting> {

	        @Override
	        public Sighting mapRow(ResultSet rs, int index) throws SQLException {
	            Sighting sighting = new Sighting();
	            sighting.setId(rs.getInt("sighting_id"));
	            sighting.setName(rs.getString("sighting_name"));
	            sighting.setDescription(rs.getString("description"));
	            sighting.setLatitude(rs.getDouble("latitude"));
	            sighting.setLongitude(rs.getDouble("longitude"));
	            sighting.setAdd_id(rs.getInt("add_id"));
	            return sighting;
	        }
	    }
}
