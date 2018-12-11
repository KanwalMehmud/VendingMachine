/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.controller;

import com.sg.vendingmachinespringmvc.dao.VendingMachinePersistenceException;
import com.sg.vendingmachinespringmvc.model.VendingMachine;
import com.sg.vendingmachinespringmvc.servicelayer.InsufficientFundsException;
import com.sg.vendingmachinespringmvc.servicelayer.InvalidSelectionException;
import com.sg.vendingmachinespringmvc.servicelayer.NoItemInventoryException;
import com.sg.vendingmachinespringmvc.servicelayer.VendingMachineServiceLayer;
import java.math.BigDecimal;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Kanwal
 */
@Controller
@RequestMapping(value = "vendingMachineController")
public class VendingMachineController {

    VendingMachineServiceLayer service;

    @Inject
    public VendingMachineController(VendingMachineServiceLayer service) {
        this.service = service;
    }

    @RequestMapping(value = "/displayVendingItems", method = RequestMethod.GET)
    public String displayVendingItems(Model model) throws VendingMachinePersistenceException {
        List<VendingMachine> items = service.getMenuItems();
        model.addAttribute("items", items);
        return "vendingItems";
    }

    @RequestMapping(value = "/selectedItem", method = RequestMethod.GET)
    public String selectedItem(HttpServletRequest request, Model model) throws VendingMachinePersistenceException {
        List<VendingMachine> items = service.getMenuItems();
        String vendingIdParameter = request.getParameter("id");
        int id = Integer.parseInt(vendingIdParameter);
        service.setItemId(id);
        model.addAttribute("totalMoney", service.getTotalMoney());
        model.addAttribute("items", items);
        model.addAttribute("itemId", id);
        return "vendingItems";
    }

    @RequestMapping(value = "/totalMoneyIn", method = RequestMethod.GET)
    public String totalMoneyIn(HttpServletRequest request, Model model) throws VendingMachinePersistenceException {
        int id = service.getItemId();
        List<VendingMachine> items = service.getMenuItems();
        model.addAttribute("items", items);
        model.addAttribute("itemId", id);
        String addedMoney = request.getParameter("amount");
        BigDecimal money = new BigDecimal(addedMoney);
        service.setTotalMoney(money);
        model.addAttribute("totalMoney", service.getTotalMoney());
        return "vendingItems";

    }

    @RequestMapping(value = "/makePurchase", method = RequestMethod.GET)
    public String makePurchase(HttpServletRequest request, Model model) throws VendingMachinePersistenceException, InvalidSelectionException, InsufficientFundsException, NoItemInventoryException {
        List<VendingMachine> items = service.getMenuItems();

        try {
            model.addAttribute("items", items);
            model.addAttribute("change", service.makePurchase());
            model.addAttribute("message", "Thank you!");
        } catch (NoItemInventoryException | InsufficientFundsException | InvalidSelectionException e) {
            model.addAttribute("message", e.getMessage());
            model.addAttribute("totalMoney", service.getTotalMoney());
            model.addAttribute("items", items);
        }
        return "vendingItems";
    }

    @RequestMapping(value = "/returnChange", method = RequestMethod.GET)
    public String returnChange(HttpServletRequest request, Model model) throws VendingMachinePersistenceException, InsufficientFundsException, NoItemInventoryException {
        model.addAttribute("change", service.returnChange());
        model.addAttribute("items", service.getMenuItems());
        return "vendingItems";
    }
}
