/*
 * File : XMLDataStoreAdapter.java
 * Contains the class XMLDataStoreAdapter, the adapter to process the XML format datastore
 * Responsibility is to process datastore in XML format into an object that can be understood by system
 */

package main.DataStore;

import java.io.File;
import javax.xml.bind.*;

import main.Barang.*;
import main.Client.*;
import main.Bill.*;

public class XMLDataStoreAdapter implements DataStoreAdapter {
    /* ===================================== ATTRIBUTES =====================================*/

    private String inventoryFilePath;
    private String clientsFilePath;
    private String billsFilePath;
    
    /* ====================================== METHODS ====================================== */

    /* ------------------------------------- CONSTRUCTOR ------------------------------------*/ 

    // Default constructor, set files path to the default datastore location
    public XMLDataStoreAdapter() {
        this.inventoryFilePath = "../data/inventory.xml";
        this.clientsFilePath = "../data/clients.xml";
        this.billsFilePath = "../data/bills.xml";
    }

    // User-defined constructor, set files path to the chosen datastore location
    public XMLDataStoreAdapter(String folderPath) {
        this.inventoryFilePath = folderPath + "/inventory.xml";
        this.clientsFilePath = folderPath + "/clients.xml";
        this.billsFilePath = folderPath + "/bills.xml";
    } 

    /* ------------------------------------ READER-WRITER -----------------------------------*/

    // Read and return the inventory object defined in the XML inventory datastore file
    public Inventory readInventory() {
        try {
            Inventory inventory = JAXB.unmarshal(new File(inventoryFilePath), Inventory.class);
            return inventory;
        }
        catch (Exception e) {e.printStackTrace();return null;}
    }

    // Write the inventory object into the XML inventory datastore file
    public void writeInventory(Inventory inventory) {
        try {
            JAXB.marshal(inventory, new File(inventoryFilePath));
        }
        catch (Exception e) {}
    }

    // Read and return the client manager object defined in the XML client  datastore file
    public ClientManager readClientManager() {
        try {
            ClientManager clientManager = JAXB.unmarshal(new File(clientsFilePath), ClientManager.class);
            return clientManager;
        }
        catch (Exception e) {e.printStackTrace();return null;}
    }

    // Write the client manager object into the XML client datastore file
    public void writeClientManager(ClientManager clientManager) {
        try {
            JAXB.marshal(clientManager, new File(clientsFilePath));
        }
        catch (Exception e) {}
    }

    // Read and return the list of bill defined in the XML datastore file
    public BillManager readBillManager() {
        try {
            BillManager billManager = JAXB.unmarshal(new File(billsFilePath), BillManager.class);
            return billManager;
        }
        catch (Exception e) {e.printStackTrace();return null;}
    }

    // Write the list of bill into the XML datastore file
    public void writeBillManager(BillManager billManager) {
        try {
            JAXB.marshal(billManager, new File(billsFilePath));
        }
        catch (Exception e) {e.printStackTrace();}
    }

    /* --------------------------------------- DELETER -------------------------------------*/ 
    
    // Delete all datastore stored in the location
    public void deleteFiles() {
        new File(inventoryFilePath).delete();
        new File(clientsFilePath).delete();
        new File(billsFilePath).delete();
    }
}