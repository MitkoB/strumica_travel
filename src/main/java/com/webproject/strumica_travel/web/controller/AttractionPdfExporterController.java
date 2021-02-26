package com.webproject.strumica_travel.web.controller;

import com.lowagie.text.DocumentException;
import com.webproject.strumica_travel.model.TouristAttraction;
import com.webproject.strumica_travel.model.pdfExport.AttractionPdfExporter;
import com.webproject.strumica_travel.service.TouristAttractionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class AttractionPdfExporterController {
    private final TouristAttractionService touristAttractionService;

    public AttractionPdfExporterController(TouristAttractionService touristAttractionService) {
        this.touristAttractionService = touristAttractionService;
    }
    @GetMapping("/attractions/export/pdf")
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=attractions.pdf";
        response.setHeader(headerKey, headerValue);

        List<TouristAttraction> attractions = touristAttractionService.findAll();

        AttractionPdfExporter exporter = new AttractionPdfExporter(attractions);
        exporter.export(response);

    }
}
