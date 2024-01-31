package com.pdf.backend;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.pdf.entidades.DatosPdf;
import com.pdf.entidades.Fonts;
import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Locale;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author ESTEFANIECM
 */
public class CrearPDF {

    private final SimpleDateFormat formatoEntrada = new SimpleDateFormat("yyyy-MM-dd");
    private final DecimalFormat formatoMoneda = new DecimalFormat("#,##0.00");
    private final String direccion = "12 Av. 7-13, Zona 8 de Mixco, Sector A-10, San Cristóbal, Guatemala, C.A.";
    private final String telefonos = "Tels. (502) 2479-6641 / 42 * (502) 2479-6654 * Fax: (502) 2479-6657";

    public String CrearPDF(DatosPdf dp) throws DocumentException, IOException, Exception {
        String base64Pdf = "";
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            Document documento = new Document();
            documento.setMargins(2, 2, 2, 2);
            documento.setPageSize(PageSize.LETTER);
            PdfWriter.getInstance(documento, byteArrayOutputStream);
            documento.open();
            documento.add(new Paragraph(new Chunk("\n")));
            PdfPTable ReciboTabla = new PdfPTable(10);
            ReciboTabla.setHorizontalAlignment(PdfPTable.ALIGN_CENTER);
            ReciboTabla.setWidthPercentage(95);

            if (dp.getIdEmpresa().equals("uuid1")) { //EUROAKRON
                Image logo = getImageFromB64("1");
                logo.setAbsolutePosition(25, 675);
                documento.add(logo);

                ReciboTabla.setWidths(new float[]{8, 9, 10, 10, 10, 10, 1, 8, 9, 10});
                PdfPCell nCelda;
                /**
                 * *******************ENCABEZADO***********************
                 */
                nCelda = Celda("", 0, "12b", 2);
                nCelda.setRowspan(7);
                nCelda.setBorderWidth(0);
                ReciboTabla.addCell(nCelda);

                nCelda = Celda("EURO AKRON", 1, "T", 4);
                nCelda.setRowspan(2);
                nCelda.setMinimumHeight(35f);
                nCelda.setBorderWidth(0);
                nCelda.setVerticalAlignment(Element.ALIGN_CENTER);
                ReciboTabla.addCell(nCelda);

                nCelda = Celda("", 0, "12b", 1);
                nCelda.setRowspan(7);
                nCelda.setBorderWidth(0);
                ReciboTabla.addCell(nCelda);

                Phrase phrase = new Phrase();
                phrase.add(new Chunk("RECIBO DE CAJA  ", Fonts.getFontA11B()));
                phrase.add(new Chunk(dp.getNoRecibo(), Fonts.getfDesc()));
                nCelda = new PdfPCell(phrase);
                nCelda.setRowspan(4);
                nCelda.setColspan(3);
                nCelda.setHorizontalAlignment(Element.ALIGN_CENTER);
                nCelda.setVerticalAlignment(Element.ALIGN_MIDDLE);
                ReciboTabla.addCell(nCelda);

                nCelda = Celda("Euro Akron, Sociedad Anónima", 1, "9b", 4);
                nCelda.setBorderWidth(0);
                nCelda.setVerticalAlignment(Element.ALIGN_MIDDLE);
                ReciboTabla.addCell(nCelda);

                nCelda = Celda(direccion, 1, "8n", 4);
                nCelda.setBorderWidth(0);
                nCelda.setVerticalAlignment(Element.ALIGN_MIDDLE);
                ReciboTabla.addCell(nCelda);

                nCelda = Celda(telefonos, 1, "8n", 4);
                nCelda.setBorderWidth(0);
                nCelda.setVerticalAlignment(Element.ALIGN_MIDDLE);
                ReciboTabla.addCell(nCelda);

                nCelda = Celda("", 1, "12b", 3);
                nCelda.setBorderWidth(0);
                nCelda.setVerticalAlignment(Element.ALIGN_MIDDLE);
                ReciboTabla.addCell(nCelda);

                nCelda = Celda("E-mail: akron@intelnett.com", 1, "8n", 4);
                nCelda.setBorderWidth(0);
                nCelda.setVerticalAlignment(Element.ALIGN_MIDDLE);
                ReciboTabla.addCell(nCelda);

                //Fecha:
                Date fecha = formatoEntrada.parse(dp.getFechaRecibo());
                SimpleDateFormat formatoSalida = new SimpleDateFormat("'Guatemala, 'dd 'de' MMMM 'de' yyyy", new Locale("es", "GT"));
                String fechaFormateada = formatoSalida.format(fecha);
                nCelda = Celda(fechaFormateada, 1, "10n", 3);
                nCelda.setRowspan(2);
                nCelda.setVerticalAlignment(Element.ALIGN_MIDDLE);
                ReciboTabla.addCell(nCelda);

                nCelda = Celda("", 0, "11n", 4);
                nCelda.setBorderWidth(0);
                nCelda.setMinimumHeight(20f);
                ReciboTabla.addCell(nCelda);

                /**
                 * ******************DATOS RECIBO******************************
                 */
                nCelda = Celda(" RECIBIMOS DE:", 0, "10b", 2);
                nCelda.setRowspan(2);
                nCelda.setMinimumHeight(25f);
                nCelda.setBackgroundColor(new BaseColor(224, 224, 224));
                nCelda.setVerticalAlignment(Element.ALIGN_MIDDLE);
                ReciboTabla.addCell(nCelda);

                nCelda = Celda(" " + dp.getRecibimosDe(), 0, "10n", 6);
                nCelda.setRowspan(2);
                nCelda.setMinimumHeight(25f);
                nCelda.setVerticalAlignment(Element.ALIGN_MIDDLE);
                ReciboTabla.addCell(nCelda);

                double montoR = Double.parseDouble(dp.getMontoRecibido());
                nCelda = Celda("Q" + formatoMoneda.format(montoR), 1, "10b", 2);
                nCelda.setRowspan(2);
                nCelda.setMinimumHeight(25f);
                nCelda.setVerticalAlignment(Element.ALIGN_MIDDLE);
                ReciboTabla.addCell(nCelda);

                nCelda = Celda(" POR CONCEPTO DE:", 0, "10b", 2);
                nCelda.setRowspan(2);
                nCelda.setMinimumHeight(30f);
                nCelda.setVerticalAlignment(Element.ALIGN_MIDDLE);
                nCelda.setBackgroundColor(new BaseColor(224, 224, 224));
                ReciboTabla.addCell(nCelda);

                nCelda = Celda(" " + dp.getConcepto(), 0, "10n", 8);
                nCelda.setRowspan(2);
                nCelda.setMinimumHeight(30f);
                nCelda.setVerticalAlignment(Element.ALIGN_MIDDLE);
                ReciboTabla.addCell(nCelda);

                nCelda = Celda("", 0, "11n", 10);
                nCelda.setMinimumHeight(15f);
                nCelda.setBorderWidth(0);
                ReciboTabla.addCell(nCelda);

                /**
                 * ******************FORMA DEPAGO******************************
                 */
                nCelda = Celda("FORMA DE PAGO", 1, "11b", 8);
                nCelda.setRowspan(2);
                nCelda.setMinimumHeight(30f);
                nCelda.setBackgroundColor(new BaseColor(224, 224, 224));
                nCelda.setVerticalAlignment(Element.ALIGN_MIDDLE);
                ReciboTabla.addCell(nCelda);

                nCelda = Celda("", 0, "12b", 2);
                int rn = (dp.getFormasDePago().size())*2;
                nCelda.setRowspan(4 + rn);
                ReciboTabla.addCell(nCelda);

                nCelda = Celda("TIPO DE DOCUMENTO", 1, "10b", 3);
                nCelda.setRowspan(2);
                nCelda.setMinimumHeight(28f);
                nCelda.setBackgroundColor(new BaseColor(224, 224, 224));
                nCelda.setVerticalAlignment(Element.ALIGN_MIDDLE);
                ReciboTabla.addCell(nCelda);

                nCelda = Celda("No. DOCUMENTO", 1, "10b", 2);
                nCelda.setRowspan(2);
                nCelda.setMinimumHeight(28f);
                nCelda.setBackgroundColor(new BaseColor(224, 224, 224));
                nCelda.setVerticalAlignment(Element.ALIGN_MIDDLE);
                ReciboTabla.addCell(nCelda);

                nCelda = Celda("BANCO", 1, "10b", 3);
                nCelda.setRowspan(2);
                nCelda.setMinimumHeight(28f);
                nCelda.setBackgroundColor(new BaseColor(224, 224, 224));
                nCelda.setVerticalAlignment(Element.ALIGN_MIDDLE);
                ReciboTabla.addCell(nCelda);

                //------LINEA DETALLE FORMA DE PAGO------//
                for (int i = 0; i < dp.getFormasDePago().size(); i++) {
                    nCelda = Celda(" " + dp.getFormasDePago().get(i).getTipoPago(), 0, "10b", 2);
                    nCelda.setRowspan(2);
                    nCelda.setMinimumHeight(25f);
                    nCelda.setBackgroundColor(new BaseColor(224, 224, 224));
                    nCelda.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    ReciboTabla.addCell(nCelda);

                    double cantidad = Double.parseDouble(dp.getFormasDePago().get(i).getCantidad());
                    nCelda = Celda("Q" + formatoMoneda.format(cantidad), 1, "10n", 1);
                    nCelda.setRowspan(2);
                    nCelda.setMinimumHeight(30f);
                    nCelda.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    ReciboTabla.addCell(nCelda);

                    nCelda = Celda(dp.getFormasDePago().get(i).getNoDocumento(), 1, "10n", 2);
                    nCelda.setRowspan(2);
                    nCelda.setMinimumHeight(30f);
                    nCelda.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    ReciboTabla.addCell(nCelda);

                    nCelda = Celda(dp.getFormasDePago().get(i).getBanco(), 1, "10n", 3);
                    nCelda.setRowspan(2);
                    nCelda.setMinimumHeight(30f);
                    nCelda.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    ReciboTabla.addCell(nCelda);
                }
            
                //Total
                nCelda = Celda("TOTAL", 1, "10b", 2);
                nCelda.setRowspan(3);
                nCelda.setMinimumHeight(40f);
                nCelda.setBackgroundColor(new BaseColor(224, 224, 224));
                nCelda.setVerticalAlignment(Element.ALIGN_MIDDLE);
                ReciboTabla.addCell(nCelda);

                double total = Double.parseDouble(dp.getTotalRecibo());
                nCelda = Celda("Q"+total, 1, "10b", 1);
                nCelda.setRowspan(3);
                nCelda.setMinimumHeight(40f);
                nCelda.setVerticalAlignment(Element.ALIGN_MIDDLE);
                ReciboTabla.addCell(nCelda);

                nCelda = Celda("SELLO", 1, "9b", 5);
                nCelda.setRowspan(3);
                nCelda.setMinimumHeight(40f);
                nCelda.setVerticalAlignment(Element.ALIGN_BOTTOM);
                ReciboTabla.addCell(nCelda);

                nCelda = Celda("POR EURO AKRON, S.A.", 1, "10b", 2);
                nCelda.setRowspan(3);
                nCelda.setMinimumHeight(40f);
                nCelda.setBackgroundColor(new BaseColor(224, 224, 224));
                nCelda.setVerticalAlignment(Element.ALIGN_MIDDLE);
                ReciboTabla.addCell(nCelda);

            } else if (dp.getIdEmpresa().equals("uuid2")) {
                Image logo = getImageFromB64("2");
                logo.setAbsolutePosition(17, 690);
                documento.add(logo);
                ReciboTabla.setWidths(new float[]{8, 9, 10, 10, 10, 10, 5, 8, 9, 10});

                PdfPCell nCelda;
                /**
                 * *******************ENCABEZADO***********************
                 */
                nCelda = Celda("", 0, "12b", 2);
                nCelda.setRowspan(7);
                nCelda.setBorderWidth(0);
                ReciboTabla.addCell(nCelda);

                nCelda = Celda("PROAGRINCO (PROVEEDORA AGRICOLA INDUSTRIAL Y COMERCIAL)", 1, "8b", 5);
                nCelda.setBorderWidth(0);
                nCelda.setVerticalAlignment(Element.ALIGN_CENTER);
                ReciboTabla.addCell(nCelda);

                Phrase phrase = new Phrase();
                phrase.add(new Chunk("RECIBO DE CAJA  ", Fonts.getFontA10B()));
                phrase.add(new Chunk("No."+dp.getNoRecibo(), Fonts.getfDet()));
                nCelda = new PdfPCell(phrase);
                nCelda.setRowspan(4);
                nCelda.setColspan(3);
                nCelda.setHorizontalAlignment(Element.ALIGN_CENTER);
                nCelda.setVerticalAlignment(Element.ALIGN_MIDDLE);
                ReciboTabla.addCell(nCelda);

                nCelda = Celda(direccion, 1, "8n", 5);
                nCelda.setBorderWidth(0);
                nCelda.setVerticalAlignment(Element.ALIGN_MIDDLE);
                ReciboTabla.addCell(nCelda);

                nCelda = Celda(telefonos, 1, "8n", 5);
                nCelda.setBorderWidth(0);
                nCelda.setVerticalAlignment(Element.ALIGN_MIDDLE);
                ReciboTabla.addCell(nCelda);

                nCelda = Celda("E-mail: proagrinco@intelnet.net.gt", 1, "8n", 5);
                nCelda.setBorderWidth(0);
                nCelda.setVerticalAlignment(Element.ALIGN_MIDDLE);
                ReciboTabla.addCell(nCelda);

                nCelda = Celda("", 1, "12b", 5);
                nCelda.setRowspan(3);
                nCelda.setBorderWidth(0);
                nCelda.setVerticalAlignment(Element.ALIGN_MIDDLE);
                ReciboTabla.addCell(nCelda);

                nCelda = Celda("", 1, "12b", 3);
                nCelda.setBorderWidth(0);
                nCelda.setMinimumHeight(15f);
                nCelda.setVerticalAlignment(Element.ALIGN_MIDDLE);
                ReciboTabla.addCell(nCelda);

                //Fecha:
                Date fecha = formatoEntrada.parse(dp.getFechaRecibo());
                SimpleDateFormat formatoSalida = new SimpleDateFormat("'Guatemala, 'dd 'de' MMMM 'de' yyyy", new Locale("es", "GT"));
                String fechaFormateada = formatoSalida.format(fecha);
                nCelda = Celda(fechaFormateada, 1, "10n", 3);
                nCelda.setRowspan(2);
                nCelda.setMinimumHeight(30f);
                nCelda.setVerticalAlignment(Element.ALIGN_MIDDLE);
                ReciboTabla.addCell(nCelda);

                /**
                 * ******************DATOS RECIBO******************************
                 */
                nCelda = Celda(" RECIBIMOS DE:", 0, "10b", 2);
                nCelda.setRowspan(2);
                nCelda.setMinimumHeight(28f);
                nCelda.setBackgroundColor(new BaseColor(224, 224, 224));
                nCelda.setVerticalAlignment(Element.ALIGN_MIDDLE);
                ReciboTabla.addCell(nCelda);

                nCelda = Celda(" "+dp.getRecibimosDe(), 0, "10n", 6);
                nCelda.setRowspan(2);
                nCelda.setMinimumHeight(30f);
                nCelda.setVerticalAlignment(Element.ALIGN_MIDDLE);
                ReciboTabla.addCell(nCelda);

                double montoR = Double.parseDouble(dp.getMontoRecibido());
                nCelda = Celda("Q" + formatoMoneda.format(montoR), 1, "10b", 2);
                nCelda.setRowspan(2);
                nCelda.setMinimumHeight(30f);
                nCelda.setVerticalAlignment(Element.ALIGN_MIDDLE);
                ReciboTabla.addCell(nCelda);

                nCelda = Celda(" POR CONCEPTO DE:", 0, "10b", 2);
                nCelda.setRowspan(2);
                nCelda.setMinimumHeight(30f);
                nCelda.setVerticalAlignment(Element.ALIGN_MIDDLE);
                nCelda.setBackgroundColor(new BaseColor(224, 224, 224));
                ReciboTabla.addCell(nCelda);

                nCelda = Celda(" "+dp.getConcepto(), 0, "10n", 8);
                nCelda.setRowspan(2);
                nCelda.setMinimumHeight(30f);
                nCelda.setVerticalAlignment(Element.ALIGN_MIDDLE);
                ReciboTabla.addCell(nCelda);

                nCelda = Celda("", 0, "11n", 10);
                nCelda.setMinimumHeight(15f);
                nCelda.setBorderWidth(0);
                ReciboTabla.addCell(nCelda);

                /**
                 * ******************FORMA DE PAGO******************************
                 */
                nCelda = Celda("FORMA DE PAGO", 1, "11b", 8);
                nCelda.setRowspan(2);
                nCelda.setMinimumHeight(30f);
                nCelda.setBackgroundColor(new BaseColor(224, 224, 224));
                nCelda.setVerticalAlignment(Element.ALIGN_MIDDLE);
                ReciboTabla.addCell(nCelda);

                nCelda = Celda("", 0, "12b", 2);
                int rn = (dp.getFormasDePago().size())*2;
                nCelda.setRowspan(4 + rn);
                ReciboTabla.addCell(nCelda);

                nCelda = Celda("TIPO DE DOCUMENTO", 1, "10b", 3);
                nCelda.setRowspan(2);
                nCelda.setMinimumHeight(28f);
                nCelda.setBackgroundColor(new BaseColor(224, 224, 224));
                nCelda.setVerticalAlignment(Element.ALIGN_MIDDLE);
                ReciboTabla.addCell(nCelda);

                nCelda = Celda("No. DOCUMENTO", 1, "10b", 2);
                nCelda.setRowspan(2);
                nCelda.setMinimumHeight(28f);
                nCelda.setBackgroundColor(new BaseColor(224, 224, 224));
                nCelda.setVerticalAlignment(Element.ALIGN_MIDDLE);
                ReciboTabla.addCell(nCelda);

                nCelda = Celda("BANCO", 1, "10b", 3);
                nCelda.setRowspan(2);
                nCelda.setMinimumHeight(28f);
                nCelda.setBackgroundColor(new BaseColor(224, 224, 224));
                nCelda.setVerticalAlignment(Element.ALIGN_MIDDLE);
                ReciboTabla.addCell(nCelda);

                //------LINEA DETALLE FORMA DE PAGO------//
                for (int i = 0; i < dp.getFormasDePago().size(); i++) {
                    nCelda = Celda(" " + dp.getFormasDePago().get(i).getTipoPago(), 0, "10b", 2);
                    nCelda.setRowspan(2);
                    nCelda.setMinimumHeight(25f);
                    nCelda.setBackgroundColor(new BaseColor(224, 224, 224));
                    nCelda.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    ReciboTabla.addCell(nCelda);

                    double cantidad = Double.parseDouble(dp.getFormasDePago().get(i).getCantidad());
                    nCelda = Celda("Q" + formatoMoneda.format(cantidad), 1, "10n", 1);
                    nCelda.setRowspan(2);
                    nCelda.setMinimumHeight(30f);
                    nCelda.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    ReciboTabla.addCell(nCelda);

                    nCelda = Celda(dp.getFormasDePago().get(i).getNoDocumento(), 1, "10n", 2);
                    nCelda.setRowspan(2);
                    nCelda.setMinimumHeight(30f);
                    nCelda.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    ReciboTabla.addCell(nCelda);

                    nCelda = Celda(dp.getFormasDePago().get(i).getBanco(), 1, "10n", 3);
                    nCelda.setRowspan(2);
                    nCelda.setMinimumHeight(30f);
                    nCelda.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    ReciboTabla.addCell(nCelda);
                }
            
                //Total
                nCelda = Celda("TOTAL", 1, "10b", 2);
                nCelda.setRowspan(3);
                nCelda.setMinimumHeight(40f);
                nCelda.setBackgroundColor(new BaseColor(224, 224, 224));
                nCelda.setVerticalAlignment(Element.ALIGN_MIDDLE);
                ReciboTabla.addCell(nCelda);

                double total = Double.parseDouble(dp.getTotalRecibo());
                nCelda = Celda("Q"+total, 1, "10b", 1);
                nCelda.setRowspan(3);
                nCelda.setMinimumHeight(40f);
                nCelda.setVerticalAlignment(Element.ALIGN_MIDDLE);
                ReciboTabla.addCell(nCelda);

                nCelda = Celda("SELLO", 1, "9b", 5);
                nCelda.setRowspan(3);
                nCelda.setMinimumHeight(40f);
                nCelda.setVerticalAlignment(Element.ALIGN_BOTTOM);
                ReciboTabla.addCell(nCelda);

                nCelda = Celda("POR PROAGRINCO", 1, "10b", 2);
                nCelda.setRowspan(3);
                nCelda.setMinimumHeight(40f);
                nCelda.setBackgroundColor(new BaseColor(224, 224, 224));
                nCelda.setVerticalAlignment(Element.ALIGN_MIDDLE);
                ReciboTabla.addCell(nCelda);

            }
            documento.add(ReciboTabla);
            documento.close();

            // Convierte los bytes del PDF a una cadena Base64
            byte[] pdfBytes = byteArrayOutputStream.toByteArray();
            base64Pdf = Base64.getEncoder().encodeToString(pdfBytes);
        } catch (DocumentException de) {
            System.out.println("Se generó un error al crear el documento. \n" + de.getLocalizedMessage());
            throw de;
        }
        return base64Pdf;
    }

    private Paragraph getTexto(String texto, String font) {
        Paragraph paragraph = new Paragraph();
        Chunk chunk = new Chunk();
        switch (font) {
            case "T":
                paragraph.setFont(Fonts.getF());
                break;
            case "r":
                paragraph.setFont(Fonts.getFr());
                break;
            case "az":
                paragraph.setFont(Fonts.getfDesc());
                break;
            case "gr":
                paragraph.setFont(Fonts.getfDet());
                break;
            case "11b":
                paragraph.setFont(Fonts.getFontA11B());
                break;
            case "11n":
                paragraph.setFont(Fonts.getFontA11N());
                break;
            case "13n":
                paragraph.setFont(Fonts.getFontA13N());
                break;
            case "13b":
                paragraph.setFont(Fonts.getFontA13B());
                break;
            case "12b":
                paragraph.setFont(Fonts.getFontA12B());
                break;
            case "10n":
                paragraph.setFont(Fonts.getFontA10N());
                break;
            case "10b":
                paragraph.setFont(Fonts.getFontA10B());
                break;
            case "9n":
                paragraph.setFont(Fonts.getFontA9N());
                break;
            case "9b":
                paragraph.setFont(Fonts.getFontA9B());
                break;
            case "8n":
                paragraph.setFont(Fonts.getFontA8N());
                break;
            case "8b":
                paragraph.setFont(Fonts.getFontA8B());
                break;
            default:
                break;
        }
        paragraph.setLeading(20);
        chunk.append(texto);
        paragraph.add(chunk);
        return paragraph;
    }

    //Método para crear nueva celda
    private PdfPCell Celda(String texto, int align, String font, int colspan) {
        Paragraph textCelda = getTexto(texto, font);
        PdfPCell cell = new PdfPCell(textCelda);
        cell.setColspan(colspan);
        //Centrar texto horizontalmente
        switch (align) {
            case 0:
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                break;
            case 1:
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                break;
            case 2:
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                break;
            default:
                break;
        }
        return cell;
    }

    public byte[] B64toIMAGEFile(final String base64String) throws Exception {
        final String[] strings = base64String.split(",");
        String extension = "";
        final File file = null;
        final String s = strings[0];
        switch (s) {
            case "data:image/jpeg;base64": {
                extension = ".jpeg";
                break;
            }
            case "data:image/png;base64": {
                extension = ".png";
                break;
            }
            default: {
                extension = ".jpg";
                break;
            }
        }
        final byte[] data = DatatypeConverter.parseBase64Binary(strings[1]);
        return data;
    }

    public Image getImageFromB64(String emp) throws BadElementException, IOException, Exception {
        byte[] data = null;
        Image newImage = null;
        if (emp.equals("1")) {
            data = B64toIMAGEFile("data:image/png;base64,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");
            newImage = Image.getInstance(data);
            newImage.scalePercent(50);
        } else if (emp.equals("2")) {
            data = B64toIMAGEFile("data:image/jpeg;base64,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");
            newImage = Image.getInstance(data);
            newImage.scalePercent(35);
        }

        return newImage;
    }

}
