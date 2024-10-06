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

                // Escribir filas de datos
                for (int f = 0; f < tabla.getRowCount(); f++) {
                    // Obtener los datos de la fila sin el ID
                    StringBuilder linea = new StringBuilder();
                    for (int c = 1; c < tabla.getColumnCount(); c++) { // Comenzar desde 1 para omitir el ID
                        linea.append(String.valueOf(tabla.getValueAt(f, c)));  // Los datos
                        if (c < tabla.getColumnCount() - 1) { // Evitar el último ;
                            linea.append(";");
                        }
                    }
                    bw.write(linea.toString());  // Escribir la línea completa
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
