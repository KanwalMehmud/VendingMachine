/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author Kanwal
 */
public class Change {

    private BigDecimal change;
    private BigDecimal changeInQuarters;
    private BigDecimal changeInDimes;
    private BigDecimal changeInNickels;
    private BigDecimal changeInPennies;

    private BigDecimal quarter = new BigDecimal("0.25");
    private BigDecimal dime = new BigDecimal("0.10");
    private BigDecimal nickel = new BigDecimal("0.05");
    private BigDecimal penny = new BigDecimal("0.01");

    public void calculateChange(BigDecimal change) {
        this.changeInQuarters = change.divide(quarter, 0, RoundingMode.DOWN);
        change = change.subtract(changeInQuarters.multiply(quarter));
        this.changeInDimes = change.divide(dime, 0, RoundingMode.DOWN);
        change = change.subtract(changeInDimes.multiply(dime));
        this.changeInNickels = change.divide(nickel, 0, RoundingMode.DOWN);
        change = change.subtract(changeInNickels.multiply(nickel));
        this.changeInPennies = change.movePointRight(2);
    }

    public BigDecimal getChangeInQuarters() {
        return changeInQuarters;
    }

    public BigDecimal getChangeInDimes() {
        return changeInDimes;
    }

    public BigDecimal getChangeInNickels() {
        return changeInNickels;
    }

    public BigDecimal getChangeInPennies() {
        return changeInPennies;
    }

}
