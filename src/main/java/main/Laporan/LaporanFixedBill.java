package main.Laporan;

import java.io.Console;
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
import be.quodlibet.boxable.utils.PageContentStreamOptimized;
import main.Barang.Barang;

public class LaporanFixedBill extends Laporan{
    private ArrayList<Barang> listFixedBill; 

    public LaporanFixedBill(ArrayList<Barang> listFixedBill, String fileName) {
        super(fileName);
        this.listFixedBill = listFixedBill;
    }

    @Override
    public void generatePDF() throws IOException {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage(PDRectangle.A4);
        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        // PageContentStreamOptimized streamOptimized = new PageContentStreamOptimized(contentStream);
        
        // Image image = new Image(ImageIO.read(new File(System.getProperty("user.dir") + "\\img\\icon\\images\\logo.png")));
        // float imageWidth = 75;
        // image = image.scaleByWidth(imageWidth);
        // float xPos = 50;
        // float yPos = page.getMediaBox().getHeight() - image.getHeight() - 50;
        // image.draw(document,streamOptimized, xPos, yPos);

        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 22);
        contentStream.newLineAtOffset(200, 750);
        contentStream.showText("Laporan Fixed Bill");
        contentStream.endText();

        float margin = 40;
        float yStartNewPage = page.getMediaBox().getHeight() - (2 * margin);
        float tableWidth = page.getMediaBox().getWidth() - (2 * margin);
        boolean drawContent = true;
        float bottomMargin = 70;
        float yPosition = 680;
        BaseTable table = new BaseTable(yPosition, yStartNewPage, bottomMargin, tableWidth, margin, document, page, true, drawContent);
        Row<PDPage> headerRow = table.createRow(15f);
        Cell<PDPage> cell = headerRow.createCell(5, "No.");
        cell.setFont(PDType1Font.HELVETICA_BOLD);
        cell = headerRow.createCell(30, "Nama Barang");
        cell.setFont(PDType1Font.HELVETICA_BOLD);
        cell = headerRow.createCell(10, "Stock");
        cell.setFont(PDType1Font.HELVETICA_BOLD);
        cell = headerRow.createCell(15, "Harga");
        cell.setFont(PDType1Font.HELVETICA_BOLD);
        cell = headerRow.createCell(15, "Harga Beli");
        cell.setFont(PDType1Font.HELVETICA_BOLD);
        cell = headerRow.createCell(20, "Kategori");
        cell.setFont(PDType1Font.HELVETICA_BOLD);
        table.addHeaderRow(headerRow);
        int count = 1;
        for (Barang penjualanFix : listFixedBill) {
            Row<PDPage> row = table.createRow(10f);
            cell = row.createCell(5, String.valueOf(count++));
            cell = row.createCell(30, penjualanFix.getName());
            cell = row.createCell(10, String.valueOf(penjualanFix.getStock()));
            cell = row.createCell(15, String.valueOf(penjualanFix.getPrice()));
            cell = row.createCell(15, String.valueOf(penjualanFix.getBuyPrice()));
            cell = row.createCell(20, penjualanFix.getCategory());
        }
        table.draw();

        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
        contentStream.newLineAtOffset(50, 50);
        contentStream.showText("Tanggal pembuatan laporan: " + getCurrentDate());
        contentStream.endText();

        contentStream.close();
        document.addPage(page);
        document.save(new File(System.getProperty("user.dir") + "/docs/" + getFileName()));
        document.close();
    }
}