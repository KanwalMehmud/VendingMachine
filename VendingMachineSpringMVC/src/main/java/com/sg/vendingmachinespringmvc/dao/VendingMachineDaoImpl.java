/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.dao;

import com.sg.vendingmachinespringmvc.model.VendingMachine;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Kanwal
 */
public class VendingMachineDaoImpl implements VendingMachineDao {

    public static final String VENDING_FILE = "E:\\Kanwal SG BitBucket\\kanwal-mehmud-individual-work\\DDWA\\VendingMachineSpringMVC\\vendingfile.txt";

    public static final String DELIMITER = "::";

    private final Map<Integer, VendingMachine> vendingMachine = new HashMap<>();

    @Override
    public VendingMachine getItem(int itemId) {
        VendingMachine vendingItem = vendingMachine.get(itemId);
        return vendingItem;
    }

    @Override
    public VendingMachine removeItem(int itemId) {
        VendingMachine removedItem = vendingMachine.remove(itemId);
        return removedItem;
    }

    @Override
    public VendingMachine getItemById(int itemId) {
        return vendingMachine.get(itemId);
    }

    @Override
    public List<VendingMachine> getAllItems() throws VendingMachinePersistenceException {

        loadVendingMachine();
        return new ArrayList<VendingMachine>(vendingMachine.values());
    }

    @Override
    public void loadVendingMachine() throws VendingMachinePersistenceException {
        Scanner scanner;

        try {
            //Scanner used for reading the file
            scanner = new Scanner(new BufferedReader(new FileReader(VENDING_FILE)));

        } catch (FileNotFoundException e) {
            throw new VendingMachinePersistenceException(
                    "Could not load Vending data into memory.", e);
        }

        String currentLine;

        String[] currentTokens;

        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            //VendingMachine currentVendingItem = new VendingMachine(currentTokens[0]);
            VendingMachine currentVendingItem = new VendingMachine();
            currentVendingItem.setId(Integer.parseInt(currentTokens[0]));
            currentVendingItem.setItemName(currentTokens[1]);
            currentVendingItem.setItemCost(new BigDecimal(currentTokens[2]));
            currentVendingItem.setInventoryCount(Integer.parseInt(currentTokens[3]));
            vendingMachine.put(currentVendingItem.getId(), currentVendingItem);
        }
        scanner.close();
    }

    public void writeVendingMachine() throws VendingMachinePersistenceException {
        PrintWriter out;

        try {

            out = new PrintWriter(new FileWriter(VENDING_FILE));
        } catch (IOException e) {
            throw new VendingMachinePersistenceException("Could not save Vending data.", e);
        }
        List<VendingMachine> VendingList = this.getAllItems();
        for (VendingMachine currentVending : VendingList) {
            out.println(currentVending.getId() + DELIMITER
                    + currentVending.getItemName() + DELIMITER
                    + currentVending.getItemCost() + DELIMITER
                    + currentVending.getInventoryCount() + DELIMITER
            );

            out.flush();
        }
        out.close();
    }

}
