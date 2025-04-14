package Utilidades;

import DTOs.LaboratorioTablaDTO;
import DTOs.ReporteTablaDTO;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 *
 * @author Knocmare
 */
public class GeneradorReportePDF {

    public static void generar(List<ReporteTablaDTO> laboratorios, String ruta) throws Exception {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(ruta));
        document.open();

        document.add(new Paragraph("Reporte de Laboratorios"));
        document.add(new Paragraph(" "));

        PdfPTable table = new PdfPTable(5);
        table.addCell("Laboratorio");
        table.addCell("Fecha");
        table.addCell("Tiempo de Servicio");
        table.addCell("Tiempo de Uso");
        table.addCell("Tiempo Sin Uso");

        // Formato de hora (24 horas)
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        for (ReporteTablaDTO lab : laboratorios) {
            table.addCell(String.valueOf(lab.getNombreLaboratorio()));
            table.addCell(sdf.format(lab.getFecha().getTime()));
            table.addCell(String.valueOf(lab.getTiempoServicio()));
            table.addCell(String.valueOf(lab.getTiempoUso()));
            table.addCell(String.valueOf(lab.getTiempoSinUso()));
        }

        document.add(table);
        document.close();
    }
}
