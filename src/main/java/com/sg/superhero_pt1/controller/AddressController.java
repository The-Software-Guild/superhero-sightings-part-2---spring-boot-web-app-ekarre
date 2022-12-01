package com.sg.superhero_pt1.controller;

import com.sg.superhero_pt1.dao.AddressDao;
import com.sg.superhero_pt1.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class AddressController {

    @Autowired
    AddressDao addressDao;

    @GetMapping("addresses")
    public String getAll(Model model) {
        List<Address> addresses = addressDao.getAllAddresses();
        model.addAttribute("addresses", addresses);
        return "addresses";
    }


    @PostMapping("addAddress")
    public String addAddress(String street, String city, String state, String zipcode) {
        Address address = new Address();
        address.setStreet(street);
        address.setCity(city);
        address.setState(state);
        address.setZipcode(Integer.parseInt(zipcode));
        addressDao.add(address);
        return "redirect:/addresses";
    }

    @GetMapping("addresses/{add_id}")
    public Address getById(@PathVariable int add_id) {
        return addressDao.getAddressById(add_id);
    }

    @GetMapping("deleteAddress")
    public String deleteAddress(Integer add_id) {
        addressDao.deleteAddressById(add_id);
        return "redirect:/addresses";
    }

    @GetMapping("editAddress")
    public String editAddress(HttpServletRequest request, Model model) {
        int add_id = Integer.parseInt(request.getParameter("add_id"));
        Address address = addressDao.getAddressById(add_id);
        model.addAttribute("address", address);
        return "editAddress";
    }

    @PostMapping("editAddress")
    public String performEditAddress(HttpServletRequest request) {
        int add_id = Integer.parseInt(request.getParameter("add_id"));
        Address address = addressDao.getAddressById(add_id);
        address.setStreet(request.getParameter("street"));
        address.setCity(request.getParameter("city"));
        address.setState(request.getParameter("state"));
        address.setZipcode(Integer.parseInt(request.getParameter("zipcode")));
        addressDao.updateAddress(address);
        return "redirect:/addresses";
    }
}
