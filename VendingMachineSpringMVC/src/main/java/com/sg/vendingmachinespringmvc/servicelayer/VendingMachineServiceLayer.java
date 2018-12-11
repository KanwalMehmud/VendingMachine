/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.servicelayer;

import com.sg.vendingmachinespringmvc.dao.VendingMachinePersistenceException;
import com.sg.vendingmachinespringmvc.model.Change;
import com.sg.vendingmachinespringmvc.model.VendingMachine;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Kanwal
 */
public interface VendingMachineServiceLayer {

    public int getItemId();

    public void setItemId(int itemId);

    public BigDecimal getTotalMoney();

    public void setTotalMoney(BigDecimal totalMoney);

    public BigDecimal calculatedChange(int itemId, BigDecimal amount) throws InsufficientFundsException;

    public void UpdateAndConfirmInventoryItems(int itemId) throws NoItemInventoryException;

    public void loadVendingMachine() throws VendingMachinePersistenceException;

    public List<VendingMachine> getMenuItems() throws VendingMachinePersistenceException;

    public VendingMachine getItem(int itemId);

    public VendingMachine getItemById(int itemId);

    public Change makePurchase() throws InvalidSelectionException, InsufficientFundsException, NoItemInventoryException, VendingMachinePersistenceException;

    public Change returnChange() throws InsufficientFundsException, NoItemInventoryException;
}
