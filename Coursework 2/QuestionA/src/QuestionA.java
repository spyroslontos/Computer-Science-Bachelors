//C1722325
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class QuestionA {

    static ArrayList<String> filelist= new ArrayList<>();
    static ArrayList<String> filteredList= new ArrayList<>();

    public static void main(String[] args) throws Exception {

        System.out.println(parseData("data.txt"));

    }

    public static ArrayList parseData(String data) throws IOException{
        ArrayList<String> filelist= new ArrayList<>();
        ArrayList<String> filteredList= new ArrayList<>();

        File file = new File(data);
        BufferedReader buff = new BufferedReader(new FileReader(file));

        String line;
        try {
            while ((line = buff.readLine()) != null) {
                filelist.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (int i=0; i<filelist.size(); i++){
            for (int j=0; j<Arrays.asList(filelist.get(i).split(" ")).size(); j++) {
                if (Arrays.asList(filelist.get(i).split(" ")).get(j).length() > 3 && Arrays.asList(filelist.get(i).split(" ")).get(j).matches("[a-zA-Z]+")) {
                    filteredList.add(Arrays.asList(filelist.get(i).split(" ")).get(j));
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
}
