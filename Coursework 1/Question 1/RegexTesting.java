import java.util.Scanner;

public class RegexTesting {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        System.out.print("Enter text: ");
        String text = in.nextLine();

        if (!text.matches("C\\d{6}")) {
            System.out.println("Doesn't pass");
        }
        else {
            System.out.println(text + " passes");

        }

    }
}
