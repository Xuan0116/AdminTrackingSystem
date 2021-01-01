import java.awt.*;
import java.util.Scanner;

public class InputValid {

    public static int checkRange(int min, int max) {
        Scanner input = new Scanner(System.in);

        while(true){
            String choice = input.next();
            if(Integer.parseInt(choice) < min || Integer.parseInt(choice) > max)
                System.out.println("Invalid choice.\nPlease only enter between " + min + " and " + max);
            else
                return Integer.parseInt(choice);
        }
    }

    public static String checkValidChar(){
        Scanner input = new Scanner(System.in);

        while (true){
            String ch = input.next();
            if(!(ch.equals("Y") || ch.equals("N")))
                System.out.println("Invalid input.\nPlease only input Y / N.");
            else
                return ch;

        }
    }
}
