import javax.swing.*;
import java.util.Scanner;
import java.awt.*;
import java.awt.event.*;

//will use this file for GUI Design//
// such as rendering the page, creating and storing pictures and buttons etc. //

public class GUIDesign {
        public static void main(String[]args) {
            new CreatePages();
            
        }
        
}

//this class is where we create the pages that will be used in the project//
// there are four pages as of right now//
//1. the home page//
//2. the account page//
//3. the page where you view the item//
//4. the check out page//
class CreatePages implements ActionListener {
    //where all the frames and buttons will go//

    JFrame homePageFrame = new JFrame();
    JFrame checkOutFrame = new JFrame();
    JButton checkOutButton = new JButton("Checkout");



    CreatePages(){
        GUI();
        Buttons();
    }
    public void GUI(){
        // this is everything you need to create a functioning webpage//
        //it is a set size that we can adjust later//
        //sets title of website at top left//
        homePageFrame.setTitle("E-Commerce Website");
        //sets the background color of site//
        homePageFrame.setBackground(Color.DARK_GRAY);
        //not sure what this does but it makes it work//
        homePageFrame.getContentPane().setLayout(null);
        //makes the pane visible, all others will be set to invisible//
        //unless the button to that page is clicked//
        homePageFrame.setVisible(true);
        //sets static size, can also be dynamic with "homePageFrame.setBounds()"//
        homePageFrame.setSize(800,800);
        //when X on top right is clicked it closes the app//
        homePageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //frame setup for checkout//
        //sets title of website at top left//
        checkOutFrame.setTitle("Checkout");
        //sets the background color of site//
        checkOutFrame.setBackground(Color.DARK_GRAY);
        //not sure what this does but it makes it work//
        checkOutFrame.getContentPane().setLayout(null);
        //makes the pane visible, all others will be set to invisible//
        //unless the button to that page is clicked//
        checkOutFrame.setVisible(false);
        //sets static size, can also be dynamic with "homePageFrame.setBounds()"//
        checkOutFrame.setSize(800,800);
        //when X on top right is clicked it closes the app//
        checkOutFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    //this is everything that we will need to create the buttons on the pages//
    //we do need to put them in static positions//
    public void Buttons(){
        //sets the x and y for the button and the size of the button//
        //will need to fine tune these values eventually//
        checkOutButton.setBounds(375, 200, 100, 40);
        //adds the button to the respective page//
        homePageFrame.add(checkOutButton);
        //makes button do something on click//
        checkOutButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //should change frame to checkout frame//
        homePageFrame.setVisible(false);
        checkOutFrame.setVisible(true);
    }
 }


    