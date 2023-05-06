package main.Laporan;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import be.quodlibet.boxable.BaseTable;
import be.quodlibet.boxable.Cell;
import be.quodlibet.boxable.Row;
import be.quodlibet.boxable.image.Image;
import be.quodlibet.boxable.utils.PDStreamUtils;
import main.Barang.Barang;

public class LaporanPenjualan extends Laporan{
    private ArrayList<Barang> listPenjualan; 

    public LaporanPenjualan(ArrayList<Barang> listPenjualan, String fileName) {
        super(fileName);
        this.listPenjualan = listPenjualan;
    }

    @Override
    protected void generatePDF() throws IOException {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage(PDRectangle.A4);
        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        
        // Image image = new Image(ImageIO.read(new File("/../../img/icon/logo.png")));
        // float imageWidth = 75;
        // image = image.scaleByWidth(imageWidth);
        // float xPosition = 50;
        // float yPosition = page.getMediaBox().getHeight() - image.getHeight() - 50;
        // image.draw(document, contentStream, xPosition, yPosition);

        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 22);
        contentStream.newLineAtOffset(200, 750);
        contentStream.showText("Laporan Penjualan");
        contentStream.endText();
        contentStream.newLineAtOffset(0, -30);

        float margin = 50;
        float yStartNewPage = page.getMediaBox().getHeight() - (2 * margin);
        float tableWidth = page.getMediaBox().getWidth() - (2 * margin);
        boolean drawContent = true;
        float bottomMargin = 70;
        float yPosition = 680;
        BaseTable table = new BaseTable(yPosition, yStartNewPage, bottomMargin, tableWidth, margin, document, page, true, drawContent);
        Row<PDPage> headerRow = table.createRow(15f);
        Cell<PDPage> cell = headerRow.createCell(10, "No.");
        cell = headerRow.createCell(100, "Nama Barang");
        cell = headerRow.createCell(30, "Stock");
        cell = headerRow.createCell(40, "Harga");
        cell = headerRow.createCell(40, "Harga Beli");
        cell = headerRow.createCell(90, "Kategori");
        table.addHeaderRow(headerRow);
        int count = 1;
        for (Barang penjualan : listPenjualan) {
            Row<PDPage> row = table.createRow(10f);
            cell = row.createCell(10, String.valueOf(count++));
            cell = row.createCell(100, penjualan.getName());
            cell = row.createCell(30, String.valueOf(penjualan.getStock()));
            cell = row.createCell(40, String.valueOf(penjualan.getPrice()));
            cell = row.createCell(40, String.valueOf(penjualan.getBuyPrice()));
            cell = row.createCell(90, penjualan.getCategory());
        }
        table.draw();

        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
        contentStream.newLineAtOffset(50, 50);
        contentStream.showText("Tanggal pembuatan laporan: " + getCurrentDate());
        contentStream.endText();

        contentStream.close();
        document.addPage(page);
        document.save(new File(getFileName()));
        document.close();
    }
}