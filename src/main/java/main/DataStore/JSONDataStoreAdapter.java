/*
 * File : JSONDataStoreAdapter.java
 * Contains the class JSONDataStoreAdapter, the adapter to process the JSON format datastore
 * Responsibility is to process datastore in JSON format into an object that can be understood by system
 */

package main.DataStore;

import java.io.File;
import com.fasterxml.jackson.databind.ObjectMapper;

import main.Barang.*;
import main.Client.*;

public class JSONDataStoreAdapter implements DataStoreAdapter {
    /* ===================================== ATTRIBUTES =====================================*/
    
    private final ObjectMapper mapper = new ObjectMapper();
    private String inventoryFilePath;
    private String clientsFilePath;
    
    /* ====================================== METHODS ====================================== */

    /* ------------------------------------- CONSTRUCTOR ------------------------------------*/ 

    // Default constructor, set files path to the default datastore location
    public JSONDataStoreAdapter() {
        this.inventoryFilePath = "../data/inventory.json";
        this.clientsFilePath = "../data/clients.json";
    }

    // User-defined constructor, set files path to the chosen datastore location
    public JSONDataStoreAdapter(String folderPath) {
        this.inventoryFilePath = folderPath + "/inventory.json";
        this.clientsFilePath = folderPath + "/clients.json";
    } 

    /* ------------------------------------ READER-WRITER -----------------------------------*/

    // Read and return the inventory object defined in the JSON inventory datastore file
    public Inventory readInventory() {
        try {
            Inventory inventory = this.mapper.readValue(new File(inventoryFilePath), Inventory.class);
            return inventory;
        }
        catch (Exception e) {e.printStackTrace();return null;}
    }

    // Write the inventory object into the JSON inventory datastore file
    public void writeInventory(Inventory inventory) {
        try {
            this.mapper.writerWithDefaultPrettyPrinter().writeValue(new File(inventoryFilePath), inventory);
        }
        catch (Exception e) {}
    }

    // Read and return the client manager object defined in the JSON client datastore file
    public ClientManager readClientManager() {
        try {
            ClientManager clientManager = this.mapper.readValue(new File(clientsFilePath), ClientManager.class);
            return clientManager;
        }
        catch (Exception e) {e.printStackTrace();return null;}
    }

    // Write the client manager object into the JSON client datastore file
    public void writeClientManager(ClientManager clientManager) {
        try {
            this.mapper.writerWithDefaultPrettyPrinter().writeValue(new File(clientsFilePath), clientManager);
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
