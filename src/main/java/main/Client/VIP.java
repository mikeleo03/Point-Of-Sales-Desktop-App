
/*
 * File : VIP.java
 * Contains the class VIP, the client of the app with highest privileges
 * Responsibility is to buy discounted products from the app and get point from it   
 */

package main.Client;

import javax.xml.bind.annotation.*;

@XmlRootElement
public class VIP extends Member {
    /* ===================================== ATTRIBUTES =====================================*/

    private final Double discountRate = 0.1;

    /* ====================================== METHODS ====================================== */

    /* ------------------------------------- CONSTRUCTOR ------------------------------------*/ 

    // Default constructor, initialize all attributes with some undefined value
    public VIP() {
        super();
    }

    // User-defined constructor, initialize all attributes with its corresponding parameter
    public VIP(Integer customerID, String customerName, String noOfPhone, Integer point, Boolean isActive) {
        super(customerID, customerName, noOfPhone, point, isActive);
    }    
}  
