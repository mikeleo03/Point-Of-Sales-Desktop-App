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

public class XMLDataStoreAdapter implements DataStoreAdapter {
    /* ===================================== ATTRIBUTES =====================================*/

    private String inventoryFilePath;
    private String clientsFilePath;
    
    /* ====================================== METHODS ====================================== */

    /* ------------------------------------- CONSTRUCTOR ------------------------------------*/ 

    // Default constructor, set files path to the default datastore location
    public XMLDataStoreAdapter() {
        this.inventoryFilePath = "../data/inventory.xml";
        this.clientsFilePath = "../data/clients.xml";
    }

    // User-defined constructor, set files path to the chosen datastore location
    public XMLDataStoreAdapter(String folderPath) {
        this.inventoryFilePath = folderPath + "/inventory.xml";
        this.clientsFilePath = folderPath + "/clients.xml";
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

    /* --------------------------------------- DELETER -------------------------------------*/ 
    
    // Delete all datastore stored in the location
    public void deleteFiles() {
        new File(inventoryFilePath).delete();
        new File(clientsFilePath).delete();
    }
}