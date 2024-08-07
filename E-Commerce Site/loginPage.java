import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;


public class loginPage extends JFrame {
   
    public loginPage() {
        loginDisplay();
    }
    void loginDisplay() {
        setTitle("Login Page");
        setSize(400,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel loginBar = new JPanel();
        loginBar.setPreferredSize(new Dimension(200, 200));
        loginBar.setBackground(Color.WHITE);
        loginBar.setLayout(new BoxLayout(loginBar,BoxLayout.Y_AXIS));

        barTextField usernameBar = new barTextField("Enter Username");
        usernameBar.setPreferredSize(new Dimension(50, 20));
        usernameBar.setAlignmentX(Component.CENTER_ALIGNMENT);

        barTextField passwordBar = new barTextField("Enter Password");
        passwordBar.setPreferredSize(new Dimension(50, 20));
        passwordBar.setAlignmentX(Component.CENTER_ALIGNMENT);


        loginBar.add(usernameBar);
        loginBar.add(passwordBar);
        add(loginBar,BorderLayout.CENTER);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            loginPage frame = new loginPage();
            frame.setVisible(true);
        });
    }
}
