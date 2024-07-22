import javax.swing.*;
import java.awt.*;

public class GUIDesign {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CreatePages createPages = new CreatePages();
            createPages.homeDisplay();
        });
    }
}
