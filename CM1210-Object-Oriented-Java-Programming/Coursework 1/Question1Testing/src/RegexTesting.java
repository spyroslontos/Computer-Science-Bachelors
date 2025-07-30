import java.util.Scanner;

public class RegexTesting {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in); // Create a scanner object passing std in to the constructor

        System.out.print("Enter text: ");
        String text = in.nextLine();

        if (text.matches("!^[a-zA-Z]+|!^[a-zA-Z]+\\s[a-zA-Z]+")) {
            System.out.println("Doesn't pass");
        }
        else {
            System.out.println(text + " passes");

        }

    }
}
