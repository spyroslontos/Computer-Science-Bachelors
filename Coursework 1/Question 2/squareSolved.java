import java.util.Collections;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class squareSolved {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter an odd integer:");

        int n = input.nextInt();
        if (n % 2 == 1) {
            int correctSum = (n * (n * n + 1)) / 2;
            int[][] grid = new int[n][n];

            List<Integer> myList = new ArrayList<Integer>();

            //create a list of numbers by using the input from user
            for (int i = 0; i < n * n; i++) {
//            System.out.println(i+1);
                myList.add(i + 1);

            }
            int row = 0;
            int col = 0;
            Collections.shuffle(myList);// shuffle the list created

            //put the shuffled list into a magic box
            for (int i = 0; i < myList.size(); i++) {

                if (grid[(row + 1) % n][(col + 1) % n] == 0) {
                    row = (row + 1) % n;
                    col = (col + 1) % n;
                } else {
                    row = (row - 1 + n) % n;
                }
                grid[row][col] = myList.get(i);
            }


            for (int x = 0; x < n; x++) {
                for (int y = 0; y < n; y++)
                    System.out.print("| " + grid[x][y] + "|\t");
                System.out.println();
            }
            int temp = 0;

            boolean valid = true;


//      while loop to enter the moves to the game
            while (valid) {

                // row input
                Scanner rowInput = new Scanner(System.in);
                System.out.println("Enter row of number to change: ");
                int rInput = rowInput.nextInt();

                //column input
                Scanner colInput = new Scanner(System.in);
                System.out.println("Enter the column of the number to change: ");
                int cInput = colInput.nextInt();

                //direction input
                Scanner directionInput = new Scanner(System.in);
                System.out.println("Enter the direction to move number "+ grid[rInput - 1][cInput - 1] );
                String dirInput = directionInput.next();


                //move number down
                temp = grid[rInput - 1][cInput - 1];
                if (dirInput.equals("d") && rInput < n) {

                    System.out.println(temp);
                    grid[rInput - 1][cInput - 1] = grid[rInput][cInput - 1];
                    grid[rInput][cInput - 1] = temp;
                } else if (dirInput.equals("d") && rInput == n) {
                    System.out.println(temp);
                    grid[rInput - 1][cInput - 1] = grid[0][cInput - 1];
                    grid[0][cInput - 1] = temp;
                }

                //move number up
                temp = grid[rInput - 1][cInput - 1];
                if (dirInput.equals("u") && rInput > 1) {

                    System.out.println(temp);
                    grid[rInput - 1][cInput - 1] = grid[rInput - 2][cInput - 1];
                    grid[rInput - 2][cInput - 1] = temp;
                } else if (dirInput.equals("u") && rInput == 1) {
                    System.out.println(temp);
                    grid[rInput - 1][cInput - 1] = grid[n - 1][cInput - 1];
                    grid[n - 1][cInput - 1] = temp;
                }

                //move number right
                temp = grid[rInput - 1][cInput - 1];
                if (dirInput.equals("r") && cInput < n) {

                    System.out.println(temp);
                    grid[rInput - 1][cInput - 1] = grid[rInput - 1][cInput];
                    grid[rInput - 1][cInput] = temp;
                } else if (dirInput.equals("r") && cInput == n) {
                    System.out.println(temp);

                    grid[rInput - 1][cInput - 1] = grid[rInput - 1][0];
                    grid[rInput - 1][0] = temp;
                }

                //move number left
                temp = grid[rInput - 1][cInput - 1];
                if (dirInput.equals("l") && cInput > 1) {

                    System.out.println(temp);
                    grid[rInput - 1][cInput - 1] = grid[rInput - 1][cInput - 2];
                    grid[rInput - 1][cInput - 2] = temp;
                } else if (dirInput.equals("l") && cInput == 1) {
                    System.out.println(temp);

                    grid[rInput - 1][cInput - 1] = grid[rInput - 1][n - 1];
                    grid[rInput - 1][n - 1] = temp;
                }

                // check the sums of each line vertical,horizontal and diagonal
                int sumOfRow = 0, sumOfColoumns = 0, sumOfPrimaryDiagonal = 0, sumOfSecondaryDiagonal = 0;
                for (int row2 = 0; row2 < n; row2++) {
                    sumOfRow = 0;
                    sumOfColoumns = 0;
                    for (int col2 = 0; col2 < n; col2++) {
                        sumOfRow += grid[row2][col2];
//                    System.out.println(sumOfRow);
                        sumOfColoumns += grid[col2][row2];

                    }
                    sumOfPrimaryDiagonal += grid[row2][row2];
                    sumOfSecondaryDiagonal += grid[row2][n - row2 - 1];
                }


                if (sumOfColoumns == correctSum && sumOfRow == correctSum && sumOfPrimaryDiagonal == correctSum && sumOfSecondaryDiagonal == correctSum) {
                    System.out.println("You win!");
                    break;
                }


                System.out.println();
                for (int x = 0; x < n; x++) {
                    for (int y = 0; y < n; y++)
                        System.out.print("| " + grid[x][y] + "|\t");
                    System.out.println();
                }

            }

        }


    }
}