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
import main.Bill.*;
import main.Plugin.Currency.*;

public class JSONDataStoreAdapter implements DataStoreAdapter {
    /* ===================================== ATTRIBUTES =====================================*/
    
    private final ObjectMapper mapper = new ObjectMapper();
    private String inventoryFilePath;
    private String clientsFilePath;
    private String billsFilePath;
    private String fixedBillsFilePath;
    private String currenciesFilePath;
    
    /* ====================================== METHODS ====================================== */

    /* ------------------------------------- CONSTRUCTOR ------------------------------------*/ 

    // Default constructor, set files path to the default datastore location
    public JSONDataStoreAdapter() {
        this.inventoryFilePath = "../data/inventory.json";
        this.clientsFilePath = "../data/clients.json";
        this.billsFilePath = "../data/bills.json";
        this.fixedBillsFilePath = "../data/fixedBills.json";
        this.currenciesFilePath = "../data/currencies.json";
    }

    // User-defined constructor, set files path to the chosen datastore location
    public JSONDataStoreAdapter(String folderPath) {
        this.inventoryFilePath = folderPath + "/inventory.json";
        this.clientsFilePath = folderPath + "/clients.json";
        this.billsFilePath = folderPath + "/bills.json";
        this.fixedBillsFilePath = folderPath + "/fixedBills.json";
        this.currenciesFilePath = folderPath + "/currencies.json";
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

    // Read and return the list of bill defined in the JSON datastore file
    public BillManager readBillManager() {
        try {
            BillManager billManager = this.mapper.readValue(new File(billsFilePath), BillManager.class);
            return billManager;
        }
        catch (Exception e) {e.printStackTrace();return null;}
    }

    // Write the list of bill into the JSON datastore file
    public void writeBillManager(BillManager billManager) {
        try {
            this.mapper.writerWithDefaultPrettyPrinter().writeValue(new File(billsFilePath), billManager);
        }
        catch (Exception e) {}
    }

    // Read and return the list of fixed bill defined in the JSON datastore file
    public FixedBillManager readFixedBillManager() {
        try {
            FixedBillManager fixedBillManager = this.mapper.readValue(new File(fixedBillsFilePath), FixedBillManager.class);
            return fixedBillManager;
        }
        catch (Exception e) {e.printStackTrace();return null;}
    }

    // Write the list of fixed bill into the JSON datastore file
    public void writeFixedBillManager(FixedBillManager fixedBillManager) {
        try {
            this.mapper.writerWithDefaultPrettyPrinter().writeValue(new File(fixedBillsFilePath), fixedBillManager);
        }
        catch (Exception e) {}
    }

    // Read and return the list of currency defined in the JSON datastore file
    public ExchangeRate readExchangeRate() {
        try {
            ExchangeRate exhangeRate = this.mapper.readValue(new File(currenciesFilePath), ExchangeRate.class);
            return exhangeRate;
        }
        catch (Exception e) {e.printStackTrace();return null;}
    }

    // Write the list of currency into the JSON datastore file
    public void writeExchangeRate(ExchangeRate exhangeRate) {
        try {
            this.mapper.writerWithDefaultPrettyPrinter().writeValue(new File(currenciesFilePath), exhangeRate);
        }
        catch (Exception e) {}
    }
    
    /* --------------------------------------- DELETER -------------------------------------*/ 
    
    // Delete all datastore stored in the location
    public void deleteFiles() {
        new File(inventoryFilePath).delete();
        new File(clientsFilePath).delete();
        new File(billsFilePath).delete();
        new File(fixedBillsFilePath).delete();
        new File(currenciesFilePath).delete();
    }
}
