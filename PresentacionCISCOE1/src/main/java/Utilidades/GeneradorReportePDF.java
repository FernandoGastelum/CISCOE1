package Utilidades;

import DTOs.LaboratorioTablaDTO;
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

    public static void generar(List<LaboratorioTablaDTO> laboratorios, String ruta) throws Exception {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(ruta));
        document.open();

        document.add(new Paragraph("Reporte de Laboratorios"));
        document.add(new Paragraph(" "));

        PdfPTable table = new PdfPTable(4);
        table.addCell("ID");
        table.addCell("Nombre");
        table.addCell("Hora Apertura");
        table.addCell("Hora Cierre");

        // Formato de hora (24 horas)
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

        for (LaboratorioTablaDTO lab : laboratorios) {
            table.addCell(String.valueOf(lab.getIdLaboratorio()));
            table.addCell(lab.getNombre());

            String horaApertura = sdf.format(lab.getHoraApertura().getTime());
            String horaCierre = sdf.format(lab.getHoraCierre().getTime());

            table.addCell(horaApertura);
            table.addCell(horaCierre);
        }

        document.add(table);
        document.close();
    }
}
