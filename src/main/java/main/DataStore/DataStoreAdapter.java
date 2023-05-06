/*
 * File : DataStoreAdapter.java
 * Contains the interface DataStoreAdapter
 * Declare contracts to many datastore file format (JSON, XML, and OBJ);    
 */

package main.DataStore;

import main.Client.*;
import main.Barang.*;
import main.Bill.*;

interface DataStoreAdapter {
    /* ------------------------------------ READER-WRITER -----------------------------------*/

    public ClientManager readClientManager();
    public void writeClientManager(ClientManager clientManager);
    public Inventory readInventory();
    public void writeInventory(Inventory inventory);
    public BillManager readBillManager();
    public void writeBillManager(BillManager billManager);

    /* --------------------------------------- DELETER -------------------------------------*/ 

    public void deleteFiles();
}