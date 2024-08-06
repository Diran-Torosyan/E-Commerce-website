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
    private String firstName;
    private String lastName;
    private String email;
    private int phoneNumber;
    private String address;
    private String city;
    private String state;
    private int zipcode;

    public User() {}
    

    public void customerInfo() {

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("customer.txt"));
            Scanner scan = new Scanner (System.in);
            String customerInfo = (firstName + lastName + email + phoneNumber + address + city + state + zipcode);
                bw.newLine();
                
                System.out.println("First Name:");
                firstName = scan.nextLine();
                bw.write(firstName + " ");

                System.out.println("Last Name: ");
                lastName = scan.nextLine();
                bw.write(lastName + " ");

                System.out.println("Email: ");
                email = scan.nextLine();
                bw.write(email + " ");

                try {
                System.out.println("Phone Number: ");
                phoneNumber = scan.nextInt();
                scan.nextLine();
                bw.write(Integer.toString(phoneNumber) + " ");
                } // end try
                catch (InputMismatchException e){
                    scan.nextLine();
                } // end catch

                System.out.println("Address: ");
                address = scan.nextLine();
                bw.write(address + " ");

                System.out.println("City: ");
                city = scan.nextLine();
                bw.write(city + " ");

                System.out.println("State: ");
                state = scan.nextLine();
                bw.write(state + " ");

                System.out.println("Zipcode: ");
                zipcode = scan.nextInt();
                bw.write(String.valueOf(zipcode));

                System.out.println(firstName + lastName + email + phoneNumber + address + city + state + zipcode);

                scan.close();
                bw.close();

        } // end try
        catch(IOException exc) {System.out.println("Error");} // end catch
        return;
    } // end customerInfo


    
    // admin page
    public void admin() throws FileNotFoundException{
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter username:");
        String username = sc.nextLine();
        System.out.println("Enter password:");
        String password = sc.nextLine();

        try{
            File login = new File("admin.txt"); //reads admin file
            BufferedReader br = new BufferedReader(new FileReader(login));
            String name = br.readLine();
            String pass = br.readLine();

            if((username.equals(name)) && (password.equals(pass))) {
                System.out.println("");
            }
            else {
                System.out.println("Incorrect login info provided.");
            }
            br.close();
            sc.close();
        } catch(IOException e) {
            System.out.println("File not found.");
        } // end try/catch
    } // end admin
    
}
