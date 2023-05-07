/*
 * File : Member.java
 * Contains the class Member, the client of the app with some privileges
 * Responsibility is to buy products from the app and get point from it   
 */

package main.Client;

import javax.xml.bind.annotation.*;

@XmlRootElement
public class Member extends Customer {
    /* ===================================== ATTRIBUTES =====================================*/

    private String customerName;
    private String noOfPhone;
    private Double point;
    private Boolean active ;
    private final Double pointConversionRate = 0.01;

    /* ====================================== METHODS ====================================== */

    /* ------------------------------------- CONSTRUCTOR ------------------------------------*/ 

    // Default constructor, initialize all attributes with some undefined value
    public Member() {
        super();
        this.customerName = "aaaaaaaaaaaaaaaa";
        this.noOfPhone = "";
        this.point = 0.0;
        this.active = false;
    }

    // User-defined constructor, initialize all attributes with its corresponding parameter
    public Member(Integer customerID, String customerName, String noOfPhone, Double point, Boolean active) {
        super(customerID);
        this.customerName = customerName;
        this.noOfPhone = noOfPhone;
        this.point = point;
        this.active = active;
    }

    /* ----------------------------------- GETTER-SETTER ------------------------------------*/

    // Getter-setter for customerName attribute
    @XmlElement
    public String getCustomerName() {
        return this.customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    // Getter-setter for noOfPhone attribute
    @XmlElement
    public String getNoOfPhone() {
        return this.noOfPhone;
    }

    public void setNoOfPhone(String noOfPhone) {
        this.noOfPhone = noOfPhone;
    }

    // Getter-setter for customerID attribute
    @XmlElement
    public Double getPoint() {
        return this.point;
    }

    public void setPoint(Double point) {
        this.point = point;
    }

    // Getter-setter for customerID attribute
    @XmlElement
    public Boolean getActive() {
        return this.active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}  