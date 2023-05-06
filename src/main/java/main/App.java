package main;
import main.Pages.PaymentPage.*;
import main.Transaksi.*;
import main.Barang.*;

import java.lang.reflect.Array;
import main.Bill.*;
import java.util.ArrayList;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        // ArrayList<Barang> listBarang = new ArrayList<>();
        Barang nasgor = new Barang("Nasi Goreng", 10, 11000.00, 13000.00, "Makanan", "../");
        Barang mie = new Barang("Mie Goreng", 10, 9000.00, 7000.00, "Makanan", "../");
        /* listBarang.add(nasgor);
        listBarang.add(mie);
        for (Barang barang : listBarang) {
            System.out.println(barang.getName() + " " + barang.getID());
        } */
        Inventory inv = new Inventory();
        inv.addBarang(mie);
        inv.addBarang(nasgor);
        for (Barang barang : inv.getListBarang()) {
            System.out.println(barang.getName() + " " + barang.getID());
        }

        DetailTransaksi detail = new DetailTransaksi();
        detail.addBarang(0, 3, inv);
        detail.addBarang(1, 5, inv);
        detail.addBarang(0, 5, inv);

        Bill bill = new Bill(2000, "12:08:23", "22/03/2022", detail, 200000.00);
        System.out.println(bill);
        // PaymentPage payment = new PaymentPage(bill);
    }
}
