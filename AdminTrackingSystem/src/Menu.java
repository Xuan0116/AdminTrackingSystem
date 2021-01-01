import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    public static void signIn() {
        Scanner input = new Scanner(System.in);
        Boolean flag = true;

        do {
            System.out.print("Username: ");
            String username = input.next();
            System.out.print("Password: ");
            String password = input.next();

            for (int i = 0; i < Admin.adminArrayList.size(); i++) {
                if (username.equals(Admin.adminArrayList.get(i).getUsername())) {
                    if (password.equals(Admin.adminArrayList.get(i).getPassword()))
                        flag = false;
                    else
                        System.out.println("Incorrect Password / Username.\nPlease try again.");
                }
            }
        } while (flag) ;

        AdminInterface();
    }




    public static void signUp() {
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("Enter your username: ");
            String username = input.next();
            System.out.println("Enter your password(max 16 characters): ");
            String password = input.next();
            if (password.length() > 16) {
                System.out.println("Exceed max character.\nPlease enter only 16 characters.");
                password = input.nextLine();
            } else {
                new Admin(username, password);
                break;
            }
        }
    }

    public static void AdminInterface () {
        Boolean flag = true;

        while(true){
            System.out.println("Please select below operation.");
            System.out.println("1. View all customers status");
            System.out.println("2. View all shop status");
            System.out.println("3. Custom Flag Status (Case /Closed)");
            System.out.println("4. Custom Flag Normal");
            System.out.println("5. Exit");


            Scanner input = new Scanner(System.in);

            int choice = InputValid.checkRange(1, 5);

            switch (choice){
                case 1: viewCustStatus(); break;
                case 2: viewShopStatus(); break;
                case 3: customFlagStatus(); break;
                case 4: customFlagNormal(); break;
                case 5: return;
            }
        }
    }

    public static void viewCustStatus(){
        CustReport custReport = new CustReport();
        custReport.display();
        System.out.println("Export into csv file? Y/N");
        String ch = InputValid.checkValidChar();
        if (ch.equals("Y"))
            custReport.exportCSV();

    }

    public static void viewShopStatus(){
        ShopReport shopReport = new ShopReport();
        shopReport.display();
        System.out.println("Export into csv file? Y/N");
        String ch = InputValid.checkValidChar();
        if (ch.equals("Y"))
            shopReport.exportCSV();

    }

    public static void customFlagStatus(){
        while(true) {
            int mode = getCaseClosed();
            if (mode == 3)
                break;

            FlagSystem.customFlag(mode);

            viewCustStatus();
            System.out.println("\nPress 1 to continue");
            int glitch = InputValid.checkRange(1,1);


            viewShopStatus();
            System.out.println("\nPress 1 to continue");
            glitch = InputValid.checkRange(1,1);
        }
    }

    public static void customFlagNormal(){
        while (true){
            System.out.println("1. Customer");
            System.out.println("2. Shop");
            System.out.println("3. back");

            int role = InputValid.checkRange(1,3);
            if(role == 3)
                break;

            System.out.println("Flag mode: ");
            System.out.println("1. Case to Normal");
            System.out.println("2. Closed to Normal");

            int mode = InputValid.checkRange(1,2);

            FlagSystem.customFlagNormal(role,mode);

            viewCustStatus();
            System.out.println("\nPress 1 to continue");
            int glitch = InputValid.checkRange(1,1);


            viewShopStatus();
            System.out.println("\nPress 1 to continue");
            glitch = InputValid.checkRange(1,1);
        }
    }

    public static int getCaseClosed(){
        System.out.println("Flag Mode :");
        System.out.println("1. Case");
        System.out.println("2. Closed");
        System.out.println("3. back");

        return InputValid.checkRange(1,3);
    }
}
