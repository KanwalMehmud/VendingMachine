/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.controller;

import com.sg.vendingmachinespringmvc.dao.VendingMachinePersistenceException;
import com.sg.vendingmachinespringmvc.model.VendingMachine;
import com.sg.vendingmachinespringmvc.servicelayer.VendingMachineServiceLayer;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Kanwal
 */
@CrossOrigin//used to accept cross origin requests
@Controller
public class RESTController {

    private VendingMachineServiceLayer service;

    @Inject
    public RESTController(VendingMachineServiceLayer service) {
        this.service = service;
    }

    @RequestMapping(value = "/items", method = RequestMethod.GET)
    @ResponseBody 
    public List<VendingMachine> getAllItems() throws VendingMachinePersistenceException {
        return service.getMenuItems();
    }
}
