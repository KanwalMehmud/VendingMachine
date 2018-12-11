/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.servicelayer;

import com.sg.vendingmachinespringmvc.dao.VendingMachineDao;
import com.sg.vendingmachinespringmvc.dao.VendingMachinePersistenceException;
import com.sg.vendingmachinespringmvc.model.Change;
import com.sg.vendingmachinespringmvc.model.VendingMachine;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Kanwal
 */
public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer {

    private Change change;
    private int itemId = 0;
    private BigDecimal totalMoney = new BigDecimal("0");
    private VendingMachineDao dao;

    public VendingMachineServiceLayerImpl(VendingMachineDao dao, Change change) {
        this.dao = dao;
        this.change = change;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        if (this.totalMoney == null) {
            this.totalMoney = totalMoney;

        } else {
            this.totalMoney = this.totalMoney.add(totalMoney);
        }
    }

    @Override
    public BigDecimal calculatedChange(int itemId, BigDecimal amount) throws InsufficientFundsException {

        if (amount.compareTo(dao.getItem(itemId).getItemCost()) < 0) {
            throw new InsufficientFundsException("Please insert: $" + (dao.getItem(itemId).getItemCost().subtract(this.totalMoney)));

        }
        return amount.subtract(dao.getItemById(itemId).getItemCost());

    }

    @Override
    public void UpdateAndConfirmInventoryItems(int itemId) throws NoItemInventoryException {
        if (dao.getItem(itemId).getInventoryCount() == 0) {
            throw new NoItemInventoryException(
                    "Sold out!");
        }
        int inventory = getItem(itemId).getInventoryCount() - 1;
        getItem(itemId).setInventoryCount(inventory);
    }

    @Override
    public List<VendingMachine> getMenuItems() throws VendingMachinePersistenceException {
        return dao.getAllItems();
    }

    @Override
    public VendingMachine getItem(int itemId) {
        return dao.getItem(itemId);
    }

    @Override
    public VendingMachine getItemById(int itemId) {
        return dao.getItemById(itemId);
    }

    @Override
    public Change makePurchase() throws InvalidSelectionException, InsufficientFundsException, NoItemInventoryException, VendingMachinePersistenceException {

        if (getItemId() == 0) {
            throw new InvalidSelectionException("Please add money and/or select an item");
        }
        BigDecimal c = calculatedChange(this.itemId, this.totalMoney);
        UpdateAndConfirmInventoryItems(itemId);
        change.calculateChange(c);

        this.totalMoney = new BigDecimal("0");
        setItemId(0);
        dao.writeVendingMachine();
        return change;
    }

    @Override
    public Change returnChange() throws InsufficientFundsException, NoItemInventoryException {
        change.calculateChange(this.totalMoney);
        this.totalMoney = new BigDecimal("0");
        setItemId(0);
        return change;
    }

    @Override
    public void loadVendingMachine() throws VendingMachinePersistenceException {
        dao.loadVendingMachine();
    }

}
