package com.webproject.strumica_travel.model.pdfExport;

import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.webproject.strumica_travel.model.TouristAttraction;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class AttractionPdfExporter {
    private List<TouristAttraction> touristAttractionList;

    public AttractionPdfExporter(List<TouristAttraction> touristAttractionList) {
        this.touristAttractionList = touristAttractionList;
    }
    private void tableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.GREEN);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setColor(Color.BLACK);

        cell.setPhrase(new Phrase("Attraction name", font));

        table.addCell(cell);

        cell.setPhrase(new Phrase("Location", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Description", font));
        table.addCell(cell);
    }

    private void tableData(PdfPTable table) {
        for (TouristAttraction touristAttraction : touristAttractionList) {
            table.addCell(String.valueOf(touristAttraction.getName()));
            table.addCell(touristAttraction.getLocation());
            table.addCell(touristAttraction.getDescription());
        }
    }
    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.GREEN);
        Paragraph p = new Paragraph("Tourist Attractions", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(p);
        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.5f, 3.5f, 3.0f});
        table.setSpacingBefore(10);
        tableHeader(table);
        tableData(table);
        document.add(table);
        document.close();

    }
}
