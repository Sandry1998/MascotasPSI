package com.ceatformacion.mascotaspsi.services;

import com.ceatformacion.mascotaspsi.model.Mascota;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;


@Service
public class PdfService {

//1. crear un metodo que genera un pdf con la información de una lista de mascotas

public ByteArrayInputStream exportarMascotas(List<Mascota> mascotas) {

    //2. creamos el documento pdf con formato A4 horizontal

    Document documento = new Document(PageSize.A4.rotate());
    //Indicamos donde temporalmente guardara el flujo en memoria de la tabla generada
    ByteArrayOutputStream salida = new ByteArrayOutputStream();
    try{
        PdfWriter.getInstance(documento, salida);
        //Abra el documento para comenzar a escribir
        documento.open();
    //Definir la fuente que deseamos
        Font tituloFont= FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE, 18);
        //Crear el titulo al documento
        Paragraph titulo = new Paragraph("Listado de Mascota Clientes", tituloFont);
        //Centre el título en el documento
        titulo.setAlignment(Element.ALIGN_CENTER);
        //Añada el titulo al documento
        documento.add(titulo);
        //Añada una línea en blanco debajo del título
        documento.add(Chunk.NEWLINE);


        //CREAR UNA TABLA CON LOS CAMPOS QUE VAMOS A MOSTRAR
        PdfPTable table = new PdfPTable(8);
        table.setWidthPercentage(100);
        //define los anchos de cada columna de la tabla
        table.setWidths(new float[]{1,2,2,5,2,2,2,2});

        //definimos la fuente del titulo de la tabla
        Font encabezadoFont = FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE, 18);
        table.setHeaderRows(1);
        Color color=new Color(201,201,201,1);
        PdfPCell headerCell = new PdfPCell();
        headerCell.setBackgroundColor(color);
        headerCell.setBorderColor(color);

        //Añadir los encabezados a la tabla
        table.addCell(new PdfPCell(new Phrase("Id",encabezadoFont)){{setBackgroundColor(color);}});
        table.addCell(new PdfPCell(new Phrase("Nombre",encabezadoFont)){{setBackgroundColor(color);}});
        table.addCell(new PdfPCell(new Phrase("Raza",encabezadoFont)){{setBackgroundColor(color);}});
        table.addCell(new PdfPCell(new Phrase("Descripción",encabezadoFont)){{setBackgroundColor(color);}});
        table.addCell(new PdfPCell(new Phrase("Edad",encabezadoFont)){{setBackgroundColor(color);}});
        table.addCell(new PdfPCell(new Phrase("Peso",encabezadoFont)){{setBackgroundColor(color);}});
        table.addCell(new PdfPCell(new Phrase("Nombre Dueño",encabezadoFont)){{setBackgroundColor(color);}});
        table.addCell(new PdfPCell(new Phrase("DNI Dueño",encabezadoFont)){{setBackgroundColor(color);}});

        //Añadir el contenido recorriendo un array
        //addCell solo puede enviar datos tipo String...int edad....String.valueOf
        for(Mascota m : mascotas){
            table.addCell(String.valueOf(m.getId()));
            table.addCell(String.valueOf(m.getNombre()));
            table.addCell(String.valueOf(m.getRaza()));
            table.addCell(String.valueOf(m.getDescripcion()));
            table.addCell(String.valueOf(m.getEdad()));
            table.addCell(String.valueOf(m.getPeso()));
            table.addCell(String.valueOf(m.getNombreAmo()));
            table.addCell(String.valueOf(m.getDniAmo()));
        }


        //añadir estas celdas al documento
        documento.add(table);
        //cerrar los documentos después de generarlo
        documento.close();

    }catch(DocumentException e){
        e.printStackTrace();

    }
    return new ByteArrayInputStream(salida.toByteArray());

}



}
