/*
 * File : DataStoreAdapter.java
 * Contains the interface DataStoreAdapter
 * Declare contracts to many datastore file format (JSON, XML, and OBJ);    
 */

package main.DataStore;

import main.Barang.*;
import main.Client.*;

interface DataStoreAdapter {
    /* ------------------------------------ READER-WRITER -----------------------------------*/

    public ClientManager readClientManager();
    public void writeClientManager(ClientManager clientManager);
    public Inventory readInventory();
    public void writeInventory(Inventory inventory);

    /* --------------------------------------- DELETER -------------------------------------*/ 

    public void deleteFiles();
}