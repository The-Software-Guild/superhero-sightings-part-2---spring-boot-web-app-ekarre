package com.sg.superhero_pt1.dao;


import com.sg.superhero_pt1.TestApplicationConfiguration;
import com.sg.superhero_pt1.model.Organization;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Before;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class OrganizationDaoDBTest extends TestCase {

    @Autowired
    OrganizationDao organizationDao;

    public OrganizationDaoDBTest() {}

    @Before
    public void setUp() {
        //deletes all entries of each table in the test database, so we can test properly
        List<Organization> organizations = organizationDao.getAllOrganizations();
        for(Organization organization : organizations) {
            organizationDao.deleteOrganizationById(organization.getOrg_id());
        }

    }

    @Test
    public void testAddAndGetOrganization() {
        //create a new organization
        Organization organization = new Organization();
        organization.setOrg_name("wiley");
        organization.setOrg_description("Master of superheros");
        organization.setPhone("342-234-2343");
        organization.setAdd_id(1);
        organizationDao.addOrganization(organization);

        Organization fromDao = organizationDao.getOrganizationById(organization.getOrg_id());

        assertEquals(organization.getOrg_id(), fromDao.getOrg_id());

    }

    @Test
    public void testGetAllAddresses() {
        //create 2 organizations
        Organization organization = new Organization();
        organization.setOrg_name("wiley");
        organization.setOrg_description("Master of superheros");
        organization.setPhone("342-234-2343");
        organizationDao.addOrganization(organization);

        Organization organization2 = new Organization();
        organization.setOrg_name("wiley");
        organization.setOrg_description("Master of superheros");
        organization.setPhone("342-234-2343");
        organizationDao.addOrganization(organization);

        List<Organization> organizations = organizationDao.getAllOrganizations();

        assertEquals(2, organizations.size());
        assertTrue(organizations.contains(organization));
        assertTrue(organizations.contains(organization2));

    }

    @Test
    public void testUpdateOrganization() {
        //create a new organization
        Organization organization = new Organization();
        organization.setOrg_name("wiley");
        organization.setOrg_description("Master of superheros");
        organization.setPhone("342-234-2343");
        organizationDao.addOrganization(organization);

        //update one part of it
        organization.setOrg_name("blues");
        organizationDao.updateOrganization(organization);

        //get the new updated version
        Organization updated = organizationDao.getOrganizationById(organization.getOrg_id());

        assertEquals(organization, updated);
    }

    @Test
    public void testDeleteOrganizationById() {
        //create a new organization
        Organization organization = new Organization();
        organization.setOrg_name("wiley");
        organization.setOrg_description("Master of superheros");
        organization.setPhone("342-234-2343");
        organizationDao.addOrganization(organization);

        Organization fromDao = organizationDao.getOrganizationById(organization.getOrg_id());

        assertEquals(organization.getOrg_id(), fromDao.getOrg_id());

        //delete the organization
        organizationDao.deleteOrganizationById(organization.getOrg_id());

        fromDao = organizationDao.getOrganizationById(organization.getOrg_id());
        assertNull(fromDao);

    }

}
