import java.util.*;

// Spyros Lontos
// c1722325

public class MagicSquarePartA {
    public static void main ( String [] args ) {

        Scanner in = new Scanner(System.in);

        // Creates a 2 dimensional list
        System.out.print("Enter and odd number for array size: ");
        int n = in.nextInt();
        if (n%2 != 1) throw new RuntimeException("number entered must be odd");

        int magicSquare [][] = new int[n][n];

        // Prints the magic square with all "0"
        for (int i = 0; i < magicSquare.length ; i++)
        {
            for (int j = 0; j < magicSquare[i].length ; j++)
            {
                System.out.print(magicSquare[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();

        // Implementing Pseudocode Given

        int x = 1;
        int y = (n+1)/2;
        magicSquare[x][y] = 1;

        boolean wrappedx = false;
        boolean wrappedy = false;

        for (int i = 2; i <= (n*n); i++)  {

            if (x==0){ x = 2; wrappedx = true;}
            if (y==0){ y = 2; wrappedy = true;}

            if (magicSquare[x-1][y-1] == 0 && wrappedx) {
                x = 0;
                x = x+1;
                y = y-1;
            }
            else if (magicSquare[x-1][y-1] == 0 && wrappedy) {
                x = x+1;
                y = y-1;
            }
            else if (magicSquare[x-1][y-1] == 0 && wrappedx && wrappedy) {
                x = 0;
                x = x+1;
                y = y-1;
            }
            else if (magicSquare[x-1][y-1] == 0) {
                if (x==2){ x = -1; }
                x = x+1;
                y = y-1;
            }
            else {
                if (x==2){ x = -1; }
                x = x+1;
                y = y;
            }
            magicSquare[x][y] = i;
        }

        // Displaying the complete magic square
        for (int i = 0; i < magicSquare.length ; i++) {
            for (int j = 0; j < magicSquare[i].length; j++) {
                System.out.print(magicSquare[i][j] + " ");
            }
            System.out.println();

        }
    }
}
