/*
 * File : Customer.java
 * Contains the class Customer, the basic client of the app
 * Responsibility is to buy products from the app 1 times    
 */

package main.Client;

import java.io.Serializable;
import javax.xml.bind.annotation.*;

@XmlRootElement
public class Customer implements Serializable{
    /* ===================================== ATTRIBUTES =====================================*/

    private Integer customerID;

    /* ====================================== METHODS ====================================== */

    /* ------------------------------------- CONSTRUCTOR ------------------------------------*/ 

    // Default constructor, customerID is 0
    public Customer() {
        this.customerID = 0;
    }

    // User-defined constructor, customerID is set to the parameter ID given
    public Customer(Integer customerID) {
        this.customerID = customerID;
    }

    /* ----------------------------------- GETTER-SETTER ------------------------------------*/

    // Getter-setter for customerID attribute
    @XmlAttribute
    public Integer getCustomerID() {
        return this.customerID;
    }

    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
    }
}    