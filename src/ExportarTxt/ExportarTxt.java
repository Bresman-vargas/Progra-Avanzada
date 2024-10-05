/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExportarTxt;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Exportar datos de JTable a un archivo de texto (.txt)
 */
public class ExportarTxt {

    public void exportarTxt(JTable tabla) throws IOException {
        // Configurar el selector de archivos
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de texto", "txt");
        chooser.setFileFilter(filter);
        chooser.setDialogTitle("Guardar archivo");
        chooser.setAcceptAllFileFilterUsed(false);
        
        // Mostrar el diálogo de guardar
        if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            String ruta = chooser.getSelectedFile().toString();
            
            // Asegurar que el archivo tenga la extensión correcta
            if (!ruta.endsWith(".txt")) {
                ruta = ruta.concat(".txt");
            }
            
            // Crear archivo de texto
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File(ruta)))) {
                
                // Escribir encabezados (nombres de las columnas)
                for (int c = 0; c < tabla.getColumnCount(); c++) {
                    bw.write(tabla.getColumnName(c) + "\t");  // Los nombres de las columnas separados por tabulador
                }
                bw.newLine();  // Nueva línea después de los encabezados

                // Escribir filas de datos
                for (int f = 0; f < tabla.getRowCount(); f++) {
                    for (int c = 0; c < tabla.getColumnCount(); c++) {
                        bw.write(String.valueOf(tabla.getValueAt(f, c)) + "\t");  // Los datos separados por tabulador
                    }
                    bw.newLine();  // Nueva línea después de cada fila
                }

                bw.flush();  // Asegurarse de que todo se ha escrito correctamente
                System.out.println("Archivo guardado exitosamente: " + ruta);
            } catch (IOException e) {
                throw new IOException("Error al guardar el archivo: " + e.getMessage(), e);
            }
        }
    }
}

