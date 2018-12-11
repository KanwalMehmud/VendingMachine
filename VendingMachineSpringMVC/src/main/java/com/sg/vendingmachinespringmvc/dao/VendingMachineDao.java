/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.dao;

import com.sg.vendingmachinespringmvc.model.VendingMachine;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Kanwal
 */
public interface VendingMachineDao {

    public void loadVendingMachine() throws VendingMachinePersistenceException;

    public void writeVendingMachine() throws VendingMachinePersistenceException;

    public VendingMachine getItem(int itemId);

    List<VendingMachine> getAllItems() throws VendingMachinePersistenceException;

    public VendingMachine removeItem(int itemId);

    public VendingMachine getItemById(int itemId);

}
