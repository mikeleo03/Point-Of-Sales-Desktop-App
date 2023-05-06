package main;
import main.Pages.PaymentPage.*;
import main.Transaksi.*;
import main.Barang.*;
import main.Plugin.*;

import java.lang.reflect.Array;
import main.Bill.*;

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
        PluginLoader pl = new PluginLoader();
        try {
            pl.loadPlugin("C:/Users/axioo/Documents/ITB - Informatika/Semester 4/IF2210 Pemrograman Berbasis Objek/Tugas Besar 2/Tubes-2-OOP-CPK/src/main/java/main/Plugin/test.jar");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // ArrayList<Barang> listBarang = new ArrayList<>();
        /* Barang nasgor = new Barang("Nasi Goreng", 10, 11000.00, 13000.00, "Makanan", "../");
        Barang mie = new Barang("Mie Goreng", 10, 9000.00, 7000.00, "Makanan", "../");
        Barang eskrim = new Barang("Ice Cream", 12, 5000.00, 7000.00, "Makanan", "../");
        
        Inventory inv = new Inventory();
        inv.addBarang(mie);
        inv.addBarang(nasgor);
        inv.addBarang(eskrim);
        for (Barang barang : inv.getListBarang()) {
            System.out.println(barang.getName() + " " + barang.getID());
        }

        DetailTransaksi detail = new DetailTransaksi();
        detail.editBarang(nasgor, 3, inv);
        detail.editBarang(mie, 5, inv);
        detail.editBarang(nasgor, 5, inv);
        detail.editBarang(mie, 5, inv);
        detail.deleteDetail(eskrim);

        FixedBill fixedbill = new FixedBill(2000, "12:08:23", "22/03/2022", detail);
        System.out.println(fixedbill); */
        /* for (ElemenDetailTransaksi elemen : fixedbill.getDetailTransaksi().getElement()) {
            System.out.println(elemen.getJumlahBarang() + " " + elemen.getIdBarang() + " " + elemen.getNamaBarang() + " " + elemen.getSubTotal());
        }

        detail.editQuantity(mie, 2);
        detail.deleteDetail(nasgor);
        detail.editQuantity(eskrim, 5);
        detail.addBarang(eskrim, 7, inv);
        for (ElemenDetailTransaksi elemen : fixedbill.getDetailTransaksi().getElement()) {
            System.out.println(elemen.getJumlahBarang() + " " + elemen.getIdBarang() + " " + elemen.getNamaBarang() + " " + elemen.getSubTotal());
        }

        detail.addBarang(eskrim, 7, inv);
        detail.deleteDetail(nasgor);
        for (ElemenDetailTransaksi elemen : fixedbill.getDetailTransaksi().getElement()) {
            System.out.println(elemen.getJumlahBarang() + " " + elemen.getIdBarang() + " " + elemen.getNamaBarang() + " " + elemen.getSubTotal());
        } */

        /* PaymentPage payment = new PaymentPage(fixedbill); */
    }
}
