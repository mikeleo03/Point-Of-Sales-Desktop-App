package main;
import main.Pages.PaymentPage.*;
import main.Pages.DashboardPage.*;
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
        MainPage mp = new MainPage();
        mp.setVisible(true);

        // ArrayList<Barang> listBarang = new ArrayList<>();
        /* Barang nasgor = new Barang("Nasi Goreng", 10, 11000.00, 13000.00, "Makanan", "../");
        Barang mie = new Barang("Mie Goreng", 10, 9000.00, 7000.00, "Makanan", "../");
        Barang eskrim = new Barang("Ice Cream", 12, 5000.00, 7000.00, "Makanan", "../");
        
        Inventory inv = new Inventory();
        inv.addBarang(mie);
        inv.addBarang(nasgor);
        inv.addBarang(eskrim);

        DetailTransaksi detail = new DetailTransaksi();
        detail.editBarang(nasgor, 3, inv);
        detail.editBarang(mie, 5, inv);
        detail.editBarang(nasgor, 5, inv);
        detail.editBarang(mie, 5, inv);
        detail.deleteDetail(eskrim);
        Bill bill = new Bill(2000, detail);

        System.out.println(bill.getTanggal());
        System.out.println(bill.getWaktu());
        System.out.println(bill);
        bill = new Bill(2000, detail);
        for (ElemenDetailTransaksi elemen : bill.getDetailTransaksi().getElement()) {
            System.out.println(elemen.getJumlahBarang() + " " + elemen.getIdBarang() + " " + elemen.getNamaBarang() + " " + elemen.getSubTotal());
        }
        System.out.println(bill.getNominal());

        detail.setQuantity(mie, 2);
        detail.deleteDetail(nasgor);
        detail.setQuantity(eskrim, 5);
        detail.editBarang(eskrim, 7, inv);
        bill = new Bill(2000, detail);
        for (ElemenDetailTransaksi elemen : bill.getDetailTransaksi().getElement()) {
            System.out.println(elemen.getJumlahBarang() + " " + elemen.getIdBarang() + " " + elemen.getNamaBarang() + " " + elemen.getSubTotal());
        }
        System.out.println(bill.getNominal());

        detail.editBarang(eskrim, 7, inv);
        detail.deleteDetail(nasgor);
        bill = new Bill(2000, detail);
        for (ElemenDetailTransaksi elemen : bill.getDetailTransaksi().getElement()) {
            System.out.println(elemen.getJumlahBarang() + " " + elemen.getIdBarang() + " " + elemen.getNamaBarang() + " " + elemen.getSubTotal());
        }
        System.out.println(bill.getNominal()); */

        // PaymentPage payment = new PaymentPage(bill);
    }
}
