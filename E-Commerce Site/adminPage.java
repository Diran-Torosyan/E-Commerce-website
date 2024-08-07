import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;


public class adminPage extends JFrame {
    
    public adminPage() {
        adminDisplay();
    }
    void adminDisplay() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setTitle("Admin Page");
        setSize(screenSize);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel bar = new JPanel();
        bar.setPreferredSize(new Dimension(screenSize.width, 200));
        bar.setBackground(Color.WHITE);
        bar.setLayout(new BoxLayout(bar,BoxLayout.Y_AXIS));

        barTextField itemBar = new barTextField("Enter Item Name");
        itemBar.setPreferredSize(new Dimension(300, 100));
        itemBar.setAlignmentX(Component.CENTER_ALIGNMENT);


        barTextField inventoryBar = new barTextField("Enter new Inventory");
        inventoryBar.setPreferredSize(new Dimension(300, 100));
        inventoryBar.setAlignmentX(Component.CENTER_ALIGNMENT);

        barTextField saleBar = new barTextField("enter if item on sale");
        saleBar.setPreferredSize(new Dimension(300, 100));
        saleBar.setAlignmentX(Component.CENTER_ALIGNMENT);

        barTextField priceBar = new barTextField("price of item");
        priceBar.setPreferredSize(new Dimension(300, 100));
        priceBar.setAlignmentX(Component.CENTER_ALIGNMENT);
    
        //if there is sale//
        //price//
        //maybe tracking sales//

        JButton searchButton = new JButton("commit changes");
        searchButton.setPreferredSize(new Dimension(100, 100));
        searchButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        searchButton.addActionListener(e -> change());

        bar.add(itemBar);
        bar.add(inventoryBar);
        bar.add(saleBar);
        bar.add(priceBar);
        bar.add(searchButton);
        add(bar,BorderLayout.CENTER);
    }
    private void change() {
        // Implement search functionality here
        JOptionPane.showMessageDialog(this, "Search functionality to be implemented");

    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            adminPage frame = new adminPage();
            frame.setVisible(true);
        });
    }
}
class barTextField extends JTextField implements FocusListener {
    private String placeHolder;
    private boolean showPlaceHolder;

    public barTextField(String placeHolder) {
        super(placeHolder);
        this.placeHolder = placeHolder;
        this.showPlaceHolder = true;
        addFocusListener(this);
        setForeground(Color.GRAY);
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (this.getText().isEmpty()) {
            setText("");
            setForeground(Color.BLACK);
            showPlaceHolder = false;
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if(this.getText().isEmpty()) {
            setText(placeHolder);
            setForeground(Color.GRAY);
            showPlaceHolder = true;
        }
    }

    @Override
    public String getText() {
        return showPlaceHolder ? "" : super.getText();
    }
}