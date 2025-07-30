//C1722325
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class QuestionB {

    static int BubbleMoves = 0;
    static int BubbleSwaps = 0;
    static ArrayList<Long> BubbleAverageTimes = new ArrayList<>();
    static ArrayList<Integer> BubbleAverageMoves = new ArrayList<>();
    static ArrayList<Integer> BubbleAverageSwaps = new ArrayList<>();
    static int MergeSwaps=0;
    static int MergeMoves=0;
    static ArrayList<Long> MergeAverageTimes = new ArrayList<>();
    static ArrayList<Integer> MergeAverageMoves = new ArrayList<>();
    static ArrayList<Integer> MergeAverageSwaps = new ArrayList<>();

    static long averageTime = 0;

    public static void main(String[] args) throws Exception {

        ArrayList<String> filteredList = parseData("data.txt");

        System.out.println("");
        System.out.println("--- Bubble Sort ---");

        for (int i=1; i < 11; i++){
            for(int j=0; j<10;j++){
                long start = System.nanoTime();
                bubbleSort(new ArrayList<String>(filteredList.subList(0,i*100)));
                long end = System.nanoTime();
                averageTime += (end - start);
            }
            BubbleAverageTimes.add(averageTime/10);
            BubbleAverageMoves.add(BubbleMoves/10);
            BubbleAverageSwaps.add(BubbleSwaps/10);

            averageTime = 0;
            BubbleMoves = 0;
            BubbleSwaps = 0;
        }

        System.out.println("Avg Times: " + BubbleAverageTimes);
        System.out.println("Avg Moves: " + BubbleAverageMoves);
        System.out.println("Avg Swaps: " + BubbleAverageSwaps);

        BubbleMoves = 0; BubbleSwaps = 0;
        System.out.println(bubbleSort(new ArrayList<>(filteredList)));
        System.out.println("Swaps(1000): " + BubbleSwaps);
        System.out.println("Moves(1000): " + BubbleMoves);

        System.out.println("");
        System.out.println("--- Merge Sort ---");

        for (int i=1; i < 11; i++){
            for (int j=0;j<10;j++){
                long start = System.nanoTime();
                mergeSort(new ArrayList<String>(filteredList.subList(0,i*100)));
                long end = System.nanoTime();
                averageTime += (end - start);
            }
            MergeAverageTimes.add(averageTime/10);
            MergeAverageMoves.add(MergeMoves/10);
            MergeAverageSwaps.add(MergeSwaps/10);

            averageTime = 0;
            MergeMoves = 0;
            MergeSwaps = 0;
        }

        System.out.println("Avg Times: " + MergeAverageTimes);
        System.out.println("Avg Moves: " + MergeAverageMoves);
        System.out.println("Avg Swaps: " + MergeAverageSwaps);

        MergeMoves = 0; MergeSwaps = 0;
        System.out.println(mergeSort(new ArrayList<>(filteredList)));
        System.out.println("Swaps(1000): " + MergeSwaps);
        System.out.println("Moves(1000): " + MergeMoves);

    }

    static ArrayList parseData(String data) throws IOException {
        ArrayList<String> fileList= new ArrayList<>();
        ArrayList<String> filteredList= new ArrayList<>();

        File file = new File(data);
        BufferedReader buff = new BufferedReader(new FileReader(file));

        String line;
        try {
            while ((line = buff.readLine()) != null) {
                fileList.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (int i=0; i<fileList.size(); i++){
            for (int j=0; j<Arrays.asList(fileList.get(i).split(" ")).size(); j++) {
                if (Arrays.asList(fileList.get(i).split(" ")).get(j).length() > 3 && Arrays.asList(fileList.get(i).split(" ")).get(j).matches("[a-zA-Z]+")) {
                    filteredList.add(Arrays.asList(fileList.get(i).split(" ")).get(j).toLowerCase());
                }
                else if (filteredList.size() == 1000) {
                    break;
                }
            }
            if (filteredList.size() == 1000) {
                break;
            }
        }
        return filteredList;
    }


    static ArrayList bubbleSort(ArrayList<String> A) throws IOException {

        int n = A.size();
        int limit = n - 2;
        int done = 0;

        while (done == 0) {
            done = 1;
            for (int j = 0; j <= limit; j++) {

                BubbleMoves++;

                if (A.get(j + 1).compareToIgnoreCase(A.get(j)) < 0) {
                    String temp = A.get(j);
                    A.set(j, A.get(j + 1));
                    A.set(j + 1, temp);
                    BubbleSwaps++;
                    done = 0;
                }
            }
        }
        return A;
    }


    static ArrayList<String> mergeSort(ArrayList<String> filteredList) {

        ArrayList<String> sortedList;

        if (filteredList.size() == 1) {
            sortedList = filteredList;
        } else {
            int middle = filteredList.size() /2;

            ArrayList<String> leftSide = new ArrayList< String >();
            ArrayList<String> rightSide = new ArrayList< String >();

            for ( int x = 0; x < middle; x++) {
                leftSide.add(filteredList.get(x));
                MergeMoves++;
            }
            for ( int x = middle; x < filteredList.size(); x++) {
                rightSide.add(filteredList.get(x));
                MergeMoves++;
            }

            leftSide = mergeSort(leftSide);
            rightSide = mergeSort(rightSide);
            sortedList = merge(leftSide, rightSide);
        }
        return sortedList;
    }

    static ArrayList< String > merge(ArrayList<String> leftSide, ArrayList<String> rightSide) {

        ArrayList< String > merged = new ArrayList<>();
        int left = 0;
        int right = 0;

        while (left < leftSide.size() && right < rightSide.size()) {

            if ((leftSide.get(left)).compareTo(rightSide.get(right)) < 0) {
                merged.add(leftSide.get(left));
                left++;
                MergeMoves++;
            } else {
                merged.add(rightSide.get(right));
                right++;
                MergeMoves++;
                MergeSwaps++;
            }
        }

        while (left < leftSide.size()) {
            merged.add(leftSide.get(left));
            left++;
            MergeMoves++;
        }

        while (right < rightSide.size()) {
            merged.add(rightSide.get(right));
            right++;
            MergeMoves++;
            MergeSwaps++;
        }

        return merged;
    }
}
