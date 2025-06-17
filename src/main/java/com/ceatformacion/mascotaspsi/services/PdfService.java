package com.ceatformacion.mascotaspsi.services;

import com.ceatformacion.mascotaspsi.model.Mascotas;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;


@Service
public class PdfService {

//1. crear un metodo que genera un pdf con la información de una lista de mascotas

public ByteArrayInputStream exportarMascotas(List<Mascotas> mascotas) {

    //2. creamos el documento pdf con formato A4 horizontal

    Document documento = new Document(PageSize.A4.rotate());
    //Indicamos donde temporalmente guardara el flujo en memoria de la tabla generada
    ByteArrayOutputStream salida = new ByteArrayOutputStream();
    try{
        PdfWriter.getInstance(documento, salida);
        //Abra el documento para comenzar a escribir
        documento.open();
    //Definir la fuente que deseamos
        Font tituloFont= FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE, 20);
        //Crear el titulo al documento
        Paragraph titulo = new Paragraph("Listado de Mascotas Clientes", tituloFont);
        //Centre el título en el documento
        titulo.setAlignment(Element.ALIGN_CENTER);
        //Añada el titulo al documento
        documento.add(titulo);
        //Añada una linea en blanco debajo del título
        documento.add(Chunk.NEWLINE);


        //CREAR UNA TABLA CON LOS CAMPOS QUE VAMOS A MOSTRAR
        PdfPTable table = new PdfPTable(7);
        table.setWidthPercentage(100);
        //define los anchos de cada columna de la tabla
        table.setWidths(new float[]{3,3,6,2,2,3,5});

        //definimos la fuente del titulo de la tabla
        Font encabezadoFont = FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE, 18);

        //Añadir los encabezados a la tabla
        table.addCell(new PdfPCell(new Phrase("Nombre",encabezadoFont)));
        table.addCell(new PdfPCell(new Phrase("Raza",encabezadoFont)));
        table.addCell(new PdfPCell(new Phrase("Descripción",encabezadoFont)));
        table.addCell(new PdfPCell(new Phrase("Edad",encabezadoFont)));
        table.addCell(new PdfPCell(new Phrase("Peso",encabezadoFont)));
        table.addCell(new PdfPCell(new Phrase("Nombre Dueño",encabezadoFont)));
        table.addCell(new PdfPCell(new Phrase("DNI Dueño",encabezadoFont)));

        //Añadir el contenido recorriendo un array


        //añadir estas celdas al documento
        documento.add(table);





    }catch (Exception e){

    return new ByteArrayOutputStream(salida.toByteArray());
    }

}



}
