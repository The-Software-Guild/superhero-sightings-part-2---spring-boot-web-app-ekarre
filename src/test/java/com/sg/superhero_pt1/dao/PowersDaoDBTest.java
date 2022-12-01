package com.sg.superhero_pt1.dao;

import com.sg.superhero_pt1.model.Member;
import com.sg.superhero_pt1.model.Powers;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PowersDaoDBTest {
    @Autowired
    PowersDao powersDao;

    public PowersDaoDBTest(){
    }

    @BeforeAll
    public static void setUpClass(){
    }

    @AfterAll
    public static void tearDownClass(){
    }

    @BeforeEach
    public void setUp() {
        List<Powers> powers = powersDao.getAllPowers();
        for(Powers power : powers){
            powersDao.deletePowersById(power.getPowers_id());
        }
        //do for organization and sightings and addresses
    }

    @AfterEach
    public void tearDown(){
    }

    @Test
    public void testAddAndGetPowers(){
        Powers power = new Powers();
        power.setPowers_name("Test name");
        power = powersDao.addPowers(power);
        Powers fromDao = powersDao.getPowersById(power.getPowers_id());
        assertEquals(power, fromDao);
    }

    @Test
    public void testGetAllPowers(){
        Powers power = new Powers();
        power.setPowers_name("Test name");
        power = powersDao.addPowers(power);

        Powers power2 = new Powers();
        power2.setPowers_name("Test name2");
        power2 = powersDao.addPowers(power2);

        List<Powers> powers = powersDao.getAllPowers();
        assertEquals(2, powers.size());
        assertTrue(powers.contains(power));
        assertTrue(powers.contains(power2));
    }

    @Test
    public void TestEditPowers(){
        Powers power = new Powers();
        power.setPowers_name("Test name");
        power = powersDao.addPowers(power);

        Powers fromDao = powersDao.getPowersById(power.getPowers_id());
        assertEquals(power, fromDao);
        power.setPowers_name("New Test name");
        powersDao.editPowers(power);
        assertNotEquals(power, fromDao);
        fromDao = powersDao.getPowersById(power.getPowers_id());
        assertEquals(power, fromDao);
    }

    @Test
    public void TestDeletePowersById(){
        Powers power = new Powers();
        power.setPowers_name("Test name");
        power = powersDao.addPowers(power);

        Powers fromDao = powersDao.getPowersById(power.getPowers_id());
        assertEquals(power, fromDao);
        powersDao.deletePowersById(power.getPowers_id());
        fromDao = powersDao.getPowersById(power.getPowers_id());
        assertNull(fromDao);
    }
}
