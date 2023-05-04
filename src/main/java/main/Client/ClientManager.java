/*
 * File : ClientManager.java
 * Contains the class ClientManager, the class that organize client object
 * Responsibility is to buy products from the app 1 times    
 */

package main.Client;

import java.util.*;
import java.io.Serializable;
import javax.xml.bind.annotation.*;

@XmlRootElement
public class ClientManager implements Serializable {
    /* ===================================== ATTRIBUTES =====================================*/

    private Integer lastClientID;
    private List<Customer> listCustomer;
    private List<Member> listMember;
    private List<VIP> listVIP;

    /* ====================================== METHODS ====================================== */

    /* ------------------------------------- CONSTRUCTOR ------------------------------------*/

    // Default constructor, lastClientID is 0 and all list is empty
    public ClientManager() {
        this.lastClientID = 0;
        this.listCustomer = new ArrayList<>();
        this.listMember = new ArrayList<>();
        this.listVIP = new ArrayList<>();
    }

    /* ----------------------------------- GETTER-SETTER ------------------------------------*/

    // Getter-setter for lastClientID attribute
    @XmlAttribute
    public Integer getLastClientID() {
        return this.lastClientID;
    }

    public void setLastClientID(Integer lastClientID) {
        this.lastClientID = lastClientID;
    }

    // Getter-setter for listCustomer attribute
    @XmlElement
    public List<Customer> getListCustomer() {
        return this.listCustomer;
    }

    public void setListCustomer(List<Customer> listCustomer) {
        this.listCustomer = listCustomer;
    }

    // Getter-setter for listMember attribute
    @XmlElement
    public List<Member> getListMember() {
        return this.listMember;
    }

    public void setListMember(List<Member> listMember) {
        this.listMember = listMember;
    }

    // Getter-set for listVIP attribute
    @XmlElement
    public List<VIP> getListVIP() {
        return this.listVIP;
    }

    public void setListVIP(List<VIP> listVIP) {
        this.listVIP = listVIP;
    }

    /* ----------------------------------- ADDITIONAL METHOD ----------------------------------*/

    // Get the index of the customer in listCustomer with the same ID as customerID
    private Integer findCustomerIndex(Integer customerID) {
        for (Integer i = 0; i < this.listCustomer.size(); i++) {
            if (this.listCustomer.get(i).getCustomerID() == customerID) {
                return i;
            }
        }
        return -1;
    }
    // Generate new customer
    public void generateCustomer() {
        this.lastClientID++;
        this.listCustomer.add(new Customer(this.lastClientID));
    }

    // Promote the last customer into a member or vip
    public void promoteMembership(Integer membership, Integer customerID, String customerName, String noOfPhone) throws Exception {
        int index = this.findCustomerIndex(customerID);
        
        if (index != -1) {
            if (membership == 1) {
                this.listMember.add(new Member(customerID, customerName, noOfPhone, 0, true));
            }
            else {
                this.listVIP.add(new VIP(customerID, customerName, noOfPhone, 0, true));
            }
            this.listCustomer.remove(index);
        }
        else {
            throw new Exception("Can't promote already promoted client");
        }
    }
}
