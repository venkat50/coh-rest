/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import com.tangosol.util.processor.NumberMultiplier;

/**
 *
 * @author venkat
 */
public class CustomAgeDoubler extends NumberMultiplier {

    /**
     * Default constructor (necessary for EL and POF serialization).
     */
    public CustomAgeDoubler() {
        super();
    }

    /**
     * Construct a CustomNumberDoubler for the specified property name.
     *
     * @param sName a property name
     */
    public CustomAgeDoubler(String sName) {
        super(sName, Integer.valueOf(2), false);
    }
}
