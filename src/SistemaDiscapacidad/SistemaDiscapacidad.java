package SistemaDiscapacidad;

import com.formdev.flatlaf.IntelliJTheme;
import igu.Principal;
import java.awt.Color;
import javax.swing.UIManager;

public class SistemaDiscapacidad {
    
    public static void main(String[] args) {
        
        try {
            IntelliJTheme.setup(SistemaDiscapacidad.class.getResourceAsStream("/default.theme.json"));
            //FlatLightLaf.setup();
            UIManager.put("Component.accentColor", new Color(255,216,102));
            UIManager.put("Component.foreground", new Color(34,31,34));
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }
        
        
        
        java.awt.EventQueue.invokeLater(() -> {
            Principal principal = new Principal();
            principal.setLocationRelativeTo(null); // Centrar la ventana
            principal.setVisible(true); // Hacer visible la ventana
        });
    }
}

