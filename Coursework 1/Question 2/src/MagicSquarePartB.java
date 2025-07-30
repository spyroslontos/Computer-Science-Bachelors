import java.util.*;

// Spyros Lontos
// c1722325

public class MagicSquarePartB {
    public static void main ( String [] args ) {

        Scanner in = new Scanner(System.in);
        int numMoves = 0;

        // Creates a 2 dimensional list
        System.out.print("Enter and odd number for array size: ");

        int n = in.nextInt();
        if (n%2 != 1) throw new RuntimeException("number entered must be odd");

        int magicSquare [][] = new int[n][n];

        // Finds the correct sum for each row, column and diagonal
        int CorrectSum = n*((n*n)+1)/2;

        List<Integer> numList = new ArrayList<>();
        for (int i=1; i <= (n*n); i++) {
            numList.add(i);
        }

        // Shuffles the list of 1 up to n integers n^2 times to randomize it
        for (int i = 0; i < (n*n); i++){
            Collections.shuffle(numList);
        }

        int row = 0;
        int column = 0;
        for (int i = 0; i < numList.size(); i++) {
            if (magicSquare[(row+1)%n][(column+1)% n] == 0) {
                row = (row+1) % n;
                column = (column+1)% n; }
            else { row =(row-1+n) % n;
            }
            magicSquare[row][column] = numList.get(i);
        }

        // Fills in the contents of the 2 dimensional list with the shuffled integers
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                System.out.print("|" + magicSquare[i][j] + "| ");
            System.out.println();
        }

        while (true) {

            // Prompts to the user information to swap number places

            System.out.print("Change number in Row: ");
            int rowChange = in.nextInt();

            System.out.print("Change number in Column: ");
            int columnChange = in.nextInt();

            // Stores the number chosen to move
            int chosenNum = magicSquare[rowChange-1][columnChange-1];

            System.out.print("In which direction (U/D/L/R) to move: ");
            String direction = in.next().toUpperCase();
            numMoves++;

            // Move number according to user inputs

            // Moving Right
            if (direction.equals("R") && columnChange != n) {
                magicSquare[rowChange-1][columnChange-1] = magicSquare[rowChange-1][columnChange];
                magicSquare[rowChange-1][columnChange] = chosenNum;
            }
            else if (direction.equals("R") && columnChange == n){
                magicSquare[rowChange-1][columnChange-1] = magicSquare[rowChange-1][0];
                magicSquare[rowChange-1][0] = chosenNum;
            }

            // Moving Left
            if (direction.equals("L") && columnChange > 1 ) {
                magicSquare[rowChange-1][columnChange-1] = magicSquare[rowChange-1][columnChange-2];
                magicSquare[rowChange-1][columnChange-2] = chosenNum;
            }
            else if (direction.equals("L") && columnChange == 1){
                magicSquare[rowChange-1][columnChange-1] = magicSquare[rowChange-1][n-1];
                magicSquare[rowChange-1][n-1] = chosenNum;
            }

            // Moving up
            if  (direction.equals("U") && rowChange > 1) {
                magicSquare[rowChange-1][columnChange-1] = magicSquare[rowChange-2][columnChange-1];
                magicSquare[rowChange-2][columnChange-1] = chosenNum;
            }
            else if (direction.equals("U") && rowChange == 1){
                magicSquare[rowChange-1][columnChange-1] = magicSquare[n-1][columnChange-1];
                magicSquare[n-1][columnChange-1] = chosenNum;
            }

            // Moving down
            if  (direction.equals("D") && rowChange != n) {
                magicSquare[rowChange-1][columnChange-1] = magicSquare[rowChange][columnChange-1];
                magicSquare[rowChange][columnChange-1] = chosenNum;
            }
            else if (direction.equals("D") && rowChange == n){
                magicSquare[rowChange-1][columnChange-1] = magicSquare[0][columnChange-1];
                magicSquare[0][columnChange-1] = chosenNum;
            }

            // Initializing Rows/Columns/Diagonals
            int rightDiagonal = 0;
            int leftDiagonal = 0;
            int RowSum;
            int ColumnSum;
            List<Integer> checkList = new ArrayList<>();

            // Adds all the Sum of rows/columns and diagonals in a list
            for (int i=0; i<n; i++){
                RowSum = 0;
                ColumnSum = 0;
                for (int j=0; j<n ; j++){
                    RowSum += magicSquare[i][j];
                    ColumnSum += magicSquare[j][i];
                }
                checkList.add(RowSum);
                checkList.add(ColumnSum);
                rightDiagonal += magicSquare[i][i];
                leftDiagonal += magicSquare[n-(i+1)][i];
            }
            checkList.add(rightDiagonal);
            checkList.add(leftDiagonal);

            // Checks the number of occurrences in the list with the correct sum
            int occurrences =  Collections.frequency(checkList, CorrectSum);

            // If everything is equal to the correct sum the game ends
            // The user is presented with the final magic square and the number of moves made
            if (occurrences == checkList.size() ){
                System.out.println("============");
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++)
                        System.out.print("|" + magicSquare[i][j] + "| ");
                    System.out.println();
                }
                System.out.println("============");
                System.out.println("Congratulations you Won!");
                System.out.println("You made "+numMoves+" moves.");
                System.exit(1);
            }

            // Creates a new magicSquare after the changes
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++)
                    System.out.print("|" + magicSquare[i][j] + "| ");
                System.out.println();
            }
            checkList.clear();
        }
    }
}
