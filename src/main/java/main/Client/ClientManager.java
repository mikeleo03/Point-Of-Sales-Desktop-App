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

    // Return the type of customerID
    public Integer getClientType(Integer customerID) {
        for (Member member : this.listMember) {
            if (member.getCustomerID() == customerID) {
                return 0;
            }
        }
        for (VIP vip : this.listVIP) {
            if (vip.getCustomerID() == customerID) {
                return 1;
            }
        }
        return -1;
    }

    // Return the name of customerID
    public String getClientName(Integer customerID) {
        for (Member member : this.listMember) {
            if (member.getCustomerID() == customerID) {
                return member.getCustomerName();
            }
        }
        for (VIP vip : this.listVIP) {
            if (vip.getCustomerID() == customerID) {
                return vip.getCustomerName();
            }
        }
        return "";
    }

    // Return the phone of customerID
    public String getClientPhone(Integer customerID) {
        for (Member member : this.listMember) {
            if (member.getCustomerID() == customerID) {
                return member.getNoOfPhone();
            }
        }
        for (VIP vip : this.listVIP) {
            if (vip.getCustomerID() == customerID) {
                return vip.getNoOfPhone();
            }
        }
        return "";
    }

    // Return the activity of customerID
    public Boolean getClientActivity(Integer customerID) {
        for (Member member : this.listMember) {
            if (member.getCustomerID() == customerID) {
                return member.getActive();
            }
        }
        for (VIP vip : this.listVIP) {
            if (vip.getCustomerID() == customerID) {
                return vip.getActive();
            }
        }
        return false;
    }

    // Return the name of all clientID
    public String[] getNonCustomerName() {
        String[] listName = new String[this.listMember.size() + this.listVIP.size() + 1];
        
        listName[0] = "";
        for (Integer i = 0; i < this.listMember.size(); i++) {
            listName[1 + i] = this.listMember.get(i).getCustomerName() + " - (" + this.listMember.get(i).getCustomerID() + ")";
        }
        for (Integer i = 0; i < this.listVIP.size(); i++) {
            listName[1 + this.listMember.size() + i] = this.listVIP.get(i).getCustomerName() + " - (" + this.listVIP.get(i).getCustomerID() + ")";
        }

        Arrays.sort(listName);
        return listName;
    }   

    // Return all ID of customer in descending order
    public Integer[] getAllCustomerID() {
        Integer listID[] = new Integer[this.listCustomer.size()];

        for (Integer i = 0; i < this.listCustomer.size(); i++) {
            listID[i] = this.listCustomer.get(i).getCustomerID();
        }
        Arrays.sort(listID, Collections.reverseOrder());

        return listID;
    }

    // Return all ID of member and VIP in descending order
    public Integer[] getAllNonCustomerID() {
        Integer listID[] = new Integer[this.listMember.size() + this.listVIP.size()];

        for (Integer i = 0; i < this.listMember.size(); i++) {
            listID[i] = this.listMember.get(i).getCustomerID();
        }
        for (Integer i = 0; i < this.listVIP.size(); i++) {
            listID[i + this.listMember.size()] = this.listVIP.get(i).getCustomerID();
        }
        Arrays.sort(listID, Collections.reverseOrder());

        return listID;
    }

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
    public void promoteMembership(Integer membership, Integer customerID, String customerName, String noOfPhone) {
        int index = this.findCustomerIndex(customerID);

        if (membership == 1) {
            this.listMember.add(new Member(customerID, customerName, noOfPhone, 0, true));
        }
        else {
            this.listVIP.add(new VIP(customerID, customerName, noOfPhone, 0, true));
        }
        this.listCustomer.remove(index);
    }

    // Change status or attributes of non customer
    public void changeClientStatus(Integer customerID, String name, String noOfPhone, Boolean isActive, Integer membership) {
        Integer clientType = this.getClientType(customerID) + 1; 
        if (clientType == membership) {
            Integer i = 0;
            Boolean found = false;
            if (clientType == 1) {
                while (i < this.listMember.size() && !found) {
                    if (this.listMember.get(i).getCustomerID() == customerID) {
                        this.listMember.get(i).setCustomerName(name);
                        this.listMember.get(i).setNoOfPhone(noOfPhone);
                        this.listMember.get(i).setActive(isActive);
                        found = true;
                    }
                    else {
                        i++;
                    }
                }
            }
            else {
                while (i < this.listVIP.size() && !found) {
                    if (this.listVIP.get(i).getCustomerID() == customerID) {
                        this.listVIP.get(i).setCustomerName(name);
                        this.listVIP.get(i).setNoOfPhone(noOfPhone);
                        this.listVIP.get(i).setActive(isActive);
                        found = true;
                    }
                    else {
                        i++;
                    }
                }
            }
        }
        else {
            if (clientType == 1) {
                Member oldMember = new Member();
                for (Member member : this.listMember) {
                    if (member.getCustomerID() == customerID) {
                        oldMember = member;
                    }
                }
                this.listVIP.add(new VIP(customerID, name, noOfPhone, oldMember.getPoint(), isActive));
                this.listMember.remove(oldMember);
            }
            else {
                VIP oldVIP = new VIP();
                for (VIP vip : this.listVIP) {
                    if (vip.getCustomerID() == customerID) {
                        oldVIP = vip;
                    }
                }
                this.listMember.add(new Member(customerID, name, noOfPhone, oldVIP.getPoint(), isActive));
                this.listVIP.remove(oldVIP);
            }
        }
    }
}
