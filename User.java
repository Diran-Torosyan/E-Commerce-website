import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class User {

    // instance variables
    int custNum = 0;
    private String firstName;
    private String lastName;
    private String email;
    private int phoneNumber;
    private String address;
    private String city;
    private String state;
    private int zipcode;
    String custUser;
    String custPass;

    public User() {}
    

    public void customerInfo() {

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("customer.txt", true));
            BufferedReader br = new BufferedReader(new FileReader("customer.txt"));
            Scanner scan = new Scanner (System.in);
            String customerInfo = (firstName + lastName + email + phoneNumber + address + city + state + zipcode);

                bw.newLine();
                
                custNum++;
                bw.write("\n" + custNum + "\n");

                System.out.println("First Name:");
                firstName = scan.nextLine();
                bw.write(firstName + " ");

                System.out.println("Last Name: ");
                lastName = scan.nextLine();
                bw.write(lastName + "\n");

                System.out.println("Email: ");
                email = scan.nextLine();
                bw.write(email + "\n");

                try {
                System.out.println("Phone Number: ");
                phoneNumber = scan.nextInt();
                scan.nextLine();
                bw.write(Integer.toString(phoneNumber) + "\n");
                } // end try
                catch (InputMismatchException e){
                    scan.nextLine();
                } // end catch

                System.out.println("Address: ");
                address = scan.nextLine();
                bw.write(address + "\n");

                System.out.println("City: ");
                city = scan.nextLine();
                bw.write(city + "\n");

                System.out.println("State: ");
                state = scan.nextLine();
                bw.write(state + "\n");

                System.out.println("Zipcode: ");
                zipcode = scan.nextInt();
                bw.write(String.valueOf(zipcode));

                System.out.println(customerInfo);

                scan.close();
                bw.close();

        } // end try
        catch(IOException exc) {System.out.println("Error");} // end catch
        return;
    } // end customerInfo


    public void customerLogin() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("login.txt", true));
            Scanner sc = new Scanner(System.in);

            bw.newLine();
            System.out.println("Enter username:");
            custUser = sc.nextLine();
            bw.write(custUser + "\n");
            System.out.println("Enter password:");
            custPass = sc.nextLine();
            bw.write(custPass);

            sc.close();
            bw.close();
        } // end try
        catch(IOException exc) {System.out.println("Error");} // end catch
    }

    public void loginCheck() {
        try{
            Scanner cu = new Scanner(new File("login.txt"));
            while(cu.hasNext()) {
                String name = cu.nextLine();
                String pass = cu.nextLine();

                if((name.equals(custUser)) && (pass.equals(custPass))) {
                    System.out.println("");
                }
                else {
                    System.out.println("Incorrect login info provided.");
                }
                cu.close();
            }
        } catch(IOException e) {
            System.out.println("File not found.");
        } // end try/catch
    }

    public void paymentInfo() {
        int creditNum;
        int debtNum;
        int CVC;

    }

    
    // admin page
    public void admin() throws FileNotFoundException{
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter username:");
        String username = sc.nextLine();
        System.out.println("Enter password:");
        String password = sc.nextLine();

        try{
            Scanner ad = new Scanner(new File("admin.txt"));
            while(ad.hasNext()) {
                String name = ad.nextLine();
                String pass = ad.nextLine();

                if((username.equals(name)) && (password.equals(pass))) {
                    System.out.println("Correct");
                }
                else {
                    System.out.println("Incorrect login info provided.");
                }
                sc.close();
                ad.close();
            }
        } catch(IOException e) {
            System.out.println("File not found.");
        } // end try/catch
    } // end admin
    
}
