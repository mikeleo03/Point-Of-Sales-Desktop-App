package main.Laporan;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import be.quodlibet.boxable.BaseTable;
import be.quodlibet.boxable.Cell;
import be.quodlibet.boxable.Row;
import main.Barang.Barang;
import main.Bill.FixedBill;
import main.Transaksi.DetailTransaksi;

public class LaporanPenjualan extends Laporan{
    private List<FixedBill> listPenjualan; 

    public LaporanPenjualan(List<FixedBill> listPenjualan, String fileName) {
        super(fileName);
        this.listPenjualan = listPenjualan;
    }

    @Override
    public void generatePDF() throws IOException {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage(PDRectangle.A4);
        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 22);
        contentStream.newLineAtOffset(200, 750);
        contentStream.showText("Laporan Penjualan");
        contentStream.endText();

        float margin = 40;
        float yStartNewPage = page.getMediaBox().getHeight() - (2 * margin);
        float tableWidth = page.getMediaBox().getWidth() - (2 * margin);
        boolean drawContent = true;
        float bottomMargin = 70;
        float yPosition = 680;
        BaseTable table = new BaseTable(yPosition, yStartNewPage, bottomMargin, tableWidth, margin, document, page, true, drawContent);
        Row<PDPage> headerRow = table.createRow(15f);
        Cell<PDPage> cell = headerRow.createCell(10, "ID Customer");
        cell.setFont(PDType1Font.HELVETICA_BOLD);
        cell = headerRow.createCell(10, "Waktu");
        cell.setFont(PDType1Font.HELVETICA_BOLD);
        cell = headerRow.createCell(15, "Tanggal");
        cell.setFont(PDType1Font.HELVETICA_BOLD);
        cell = headerRow.createCell(10, "idBarang");
        cell.setFont(PDType1Font.HELVETICA_BOLD);
        cell = headerRow.createCell(20, "Nama Barang");
        cell.setFont(PDType1Font.HELVETICA_BOLD);
        cell = headerRow.createCell(20, "Jumlah Barang");
        cell.setFont(PDType1Font.HELVETICA_BOLD);
        cell = headerRow.createCell(10, "Subtotal");
        cell.setFont(PDType1Font.HELVETICA_BOLD);
        table.addHeaderRow(headerRow);
        int count = 1;
        for (FixedBill penjualan : listPenjualan) {
            DetailTransaksi details = penjualan.getDetailTransaksi();
            Row<PDPage> row = table.createRow(10f);
            for (int i = 0; i < details.getElement().size(); i++) {
                if (i==0) {
                    cell = row.createCell(10, String.valueOf(penjualan.getIdCustomer()));
                    cell = row.createCell(10, penjualan.getWaktu());
                    cell = row.createCell(15, String.valueOf(penjualan.getTanggal()));
                }
                cell = row.createCell(10, String.valueOf(details.getElement().get(i).getIdBarang()));
                cell = row.createCell(20, String.valueOf(details.getElement().get(i).getNamaBarang()));
                cell = row.createCell(20, String.valueOf(details.getElement().get(i).getJumlahBarang()));
                cell = row.createCell(10, String.valueOf(details.getElement().get(i).getSubTotal()));
            }
        }
        table.draw();

        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
        contentStream.newLineAtOffset(50, 50);
        contentStream.showText("Tanggal pembuatan laporan: " + getCurrentDate());
        contentStream.endText();

        contentStream.close();
        document.addPage(page);
        document.save(new File("../docs/" + getFileName()+".pdf"));
        document.close();
    }
}