/*
 * File : OBJDataStoreAdapter.java
 * Contains the class OBJDataStoreAdapter, the adapter to process the OBJ format datastore
 * Responsibility is to process datastore in OBJ format into an object that can be understood by system
 */

package main.DataStore;

import java.io.*;

import main.Barang.*;
import main.Client.*;

public class OBJDataStoreAdapter implements DataStoreAdapter {
    /* ===================================== ATTRIBUTES =====================================*/
    
    private ObjectInputStream objectInput;
    private ObjectOutputStream objectOutput;
    private String inventoryFilePath;
    private String clientsFilePath;
    
    /* ====================================== METHODS ====================================== */

    /* ------------------------------------- CONSTRUCTOR ------------------------------------*/ 

    // Default constructor, set files path to the default datastore location
    public OBJDataStoreAdapter() {
        this.inventoryFilePath = "../data/inventory.obj";
        this.clientsFilePath = "../data/clients.obj";
    }

    // User-defined constructor, set files path to the chosen datastore location
    public OBJDataStoreAdapter(String folderPath) {
        this.inventoryFilePath = folderPath + "/inventory.obj";
        this.clientsFilePath = folderPath + "/clients.obj";
    } 

    /* ------------------------------------ READER-WRITER -----------------------------------*/

    // Read and return the inventory object defined in the OBJ inventory datastore file
    public Inventory readInventory() {
        try {
            this.objectInput = new ObjectInputStream(new FileInputStream(inventoryFilePath));
            Inventory inventory = (Inventory) this.objectInput.readObject();
            this.objectInput.close();
            return inventory;
        }
        catch (Exception e) {e.printStackTrace();return null;}
    }

    // Write the inventory object into the OBJ inventory datastore file
    public void writeInventory(Inventory inventory) {
        try {
            this.objectOutput = new ObjectOutputStream(new FileOutputStream(inventoryFilePath));
            this.objectOutput.writeObject(inventory);
            this.objectOutput.flush();
            this.objectOutput.close();
        }
        catch (Exception e) {}
    }

    // Read and return the client manager object defined in the OBJ client datastore file
    public ClientManager readClientManager() {
        try {
            this.objectInput = new ObjectInputStream(new FileInputStream(clientsFilePath));
            ClientManager clientManager = (ClientManager) this.objectInput.readObject();
            this.objectInput.close();
            return clientManager;
        }
        catch (Exception e) {e.printStackTrace();return null;}
    }

    // Write the client manager object into the OBJ client datastore file
    public void writeClientManager(ClientManager clientManager) {
        try {
            this.objectOutput = new ObjectOutputStream(new FileOutputStream(clientsFilePath));
            this.objectOutput.writeObject(clientManager);
            this.objectOutput.flush();
            this.objectOutput.close();
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
