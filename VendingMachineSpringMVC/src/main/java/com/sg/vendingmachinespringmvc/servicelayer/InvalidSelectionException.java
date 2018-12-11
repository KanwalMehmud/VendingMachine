/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.servicelayer;

/**
 *
 * @author Kanwal
 */
public class InvalidSelectionException extends Exception {

    public InvalidSelectionException(String message) {
        super(message);
    }

    public InvalidSelectionException(String message, Throwable cause) {
        super(message, cause);
    }

}
