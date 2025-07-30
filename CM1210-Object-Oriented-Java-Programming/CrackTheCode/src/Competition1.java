import java.util.ArrayList;
import java.util.List;

public class Competition1 {
    public static String ciphertext = "ETEVHTWGSAHGWYVPNKQOEGWYVPNKPDEPHWAOVWPFWNHANEVWXAVOAEAJEUXTAOWBTEVHTWGSAHGWYVPNQAOQVGTYHAVAXETOANFQEOIQPLANTEVHFYNSQVEBEOWSKNCKLOPEVTYJAUFWYNCOTWZESQEPERQSQOPEVYCEVHEGDEHEVHEYOPNQEEHWYFTKTEVHTWGSAHGWYVPNKQOWVAPDEPWVTKFWNHANOTEVHTWGSAHGWYVPNQAOPDANAENAWVTKPIWHWYFTKTEVHTWGSAHGWYVPNQAOQVPDAIWNTHWVAWBPDAUQOYLFASQOPEVIDEPQOPDAWPDANWVA";
    public static String phrase = "NEPALSERBIASWITZERLANDBURKINAFASOKYRGYZSTANLUXEMBOURGSLOVAKIATAJIKISTANUGANDACHADANDAUSTRIA";
    
    public static void main( String[] args ) {
        System.out.println( "Ciphertext: " + ciphertext );
        System.out.println( "Hint phrase: " + phrase );

        long countA = phrase.toUpperCase().chars().filter(e -> e == 'A').count();
        long countB = phrase.toUpperCase().chars().filter(e -> e == 'B').count();
        long countC = phrase.toUpperCase().chars().filter(e -> e == 'C').count();
        long countD = phrase.toUpperCase().chars().filter(e -> e == 'D').count();
        long countE = phrase.toUpperCase().chars().filter(e -> e == 'E').count();
        long countF = phrase.toUpperCase().chars().filter(e -> e == 'F').count();
        long countG = phrase.toUpperCase().chars().filter(e -> e == 'G').count();
        long countH = phrase.toUpperCase().chars().filter(e -> e == 'H').count();
        long countI = phrase.toUpperCase().chars().filter(e -> e == 'I').count();
        long countJ = phrase.toUpperCase().chars().filter(e -> e == 'J').count();
        long countK = phrase.toUpperCase().chars().filter(e -> e == 'K').count();
        long countL = phrase.toUpperCase().chars().filter(e -> e == 'L').count();
        long countM = phrase.toUpperCase().chars().filter(e -> e == 'M').count();
        long countN = phrase.toUpperCase().chars().filter(e -> e == 'N').count();
        long countO = phrase.toUpperCase().chars().filter(e -> e == 'O').count();
        long countP = phrase.toUpperCase().chars().filter(e -> e == 'P').count();
        long countQ = phrase.toUpperCase().chars().filter(e -> e == 'Q').count();
        long countR = phrase.toUpperCase().chars().filter(e -> e == 'R').count();
        long countS = phrase.toUpperCase().chars().filter(e -> e == 'S').count();
        long countT = phrase.toUpperCase().chars().filter(e -> e == 'T').count();
        long countU = phrase.toUpperCase().chars().filter(e -> e == 'U').count();
        long countV = phrase.toUpperCase().chars().filter(e -> e == 'V').count();
        long countW = phrase.toUpperCase().chars().filter(e -> e == 'W').count();
        long countX = phrase.toUpperCase().chars().filter(e -> e == 'X').count();
        long countY = phrase.toUpperCase().chars().filter(e -> e == 'Y').count();
        long countZ = phrase.toUpperCase().chars().filter(e -> e == 'Z').count();

        System.out.println();
        System.out.print("A: " + countA);
        System.out.print(" | B: " + countB);
        System.out.print(" | C: " + countC);
        System.out.print(" | D: " + countD);
        System.out.print(" | E: " + countE);
        System.out.print(" | F: " + countF);
        System.out.print(" | G: " + countG);
        System.out.print(" | H: " + countH);
        System.out.print(" | I: " + countI);
        System.out.print(" | J: " + countJ);
        System.out.print(" | K: " + countK);
        System.out.print(" | L: " + countL);
        System.out.println(" | M: " + countM);
        System.out.print("N:  " + countN);
        System.out.print(" | O: " + countO);
        System.out.print(" | P: " + countP);
        System.out.print(" | Q: " + countQ);
        System.out.print(" | R: " + countR);
        System.out.print(" | S: " + countS);
        System.out.print(" | T: " + countT);
        System.out.print(" | U: " + countU);
        System.out.print(" | V: " + countV);
        System.out.print(" | W: " + countW);
        System.out.print(" | X: " + countX);
        System.out.print(" | Y: " + countY);
        System.out.println(" | Z: " + countZ);

        System.out.println();
        System.out.println("By choosing letter T we find the Ranges between each sighting of the letter in the phrase");
        System.out.println();

        List posT = new ArrayList();
        List rangesT = new ArrayList();

        for (int i = 0; i < phrase.length(); ++i) {
            if (phrase.charAt(i) == 'T') {
                posT.add(i);
            }
        }
        System.out.println("Position of T "+posT);

        for (int i = 0; i < posT.size() - 1; ++i) {
            rangesT.add((int) posT.get(i+1)- (int) posT.get(i));
            }

        System.out.println("Ranges of T   " + rangesT);

        System.out.println("----------------------------------");

        List posCipher = new ArrayList();
        List rangesCipher = new ArrayList();

        for (int i = 0; i < ciphertext.length(); ++i) {
            if (ciphertext.charAt(i) == 'P') {
                posCipher.add(i);
            }
        }
        System.out.println("Position of P " + posCipher);

        for (int i = 0; i < posCipher.size() - 1; ++i) {
            rangesCipher.add((int) posCipher.get(i+1)- (int) posCipher.get(i));
        }

        System.out.println("Ranges of P   " + rangesCipher);
        System.out.println("------------------------------------");
        System.out.println("We see matching ranges of characters");
        System.out.println("Position of 104(letter P) in the cipher text corresponds to");
        System.out.println("the 14th position(letter T) in the phrase");
        System.out.println("By using this information we can pin point the phrase in the cipher text");
        System.out.println("------------------------------------------------------------------------");
        System.out.println("phrase = NEPALSERBIASWITZERLANDBURKINAFASOKYRGYZSTANLUXEMBOURGSLOVAKIATAJIKISTANUGANDACHADANDAUSTRIA");
        System.out.println("cipher = VAXETOANFQEOIQPLANTEVHFYNSQVEBEOWSKNCKLOPEVTYJAUFWYNCOTWZESQEPERQSQOPEVYCEVHEGDEHEVHEYOPNQE");
        System.out.println("Then we correspond each letter to each other and create a string of the cipher text alphabet");
        System.out.println("-----------------------------------------------------------------------------------------------------");
        System.out.println("Then we decrypt the ciphertext using its encrypting alphabet (EFGHABCDQRSTUVWXMNOPYZIJKL), giving us");
        System.out.println("-----------------------------------------------------------------------------------------------------");


        String plaintextABC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String ciphertextABC = "EFGHABCDQRSTUVWXMNOPYZIJKL";


        for (int i = 0; i < ciphertext.length(); ++i) {
            System.out.print(plaintextABC.charAt(ciphertextABC.indexOf(ciphertext.charAt(i))));
        }

        System.out.println();
        System.out.println("-----------------------------------------------------------------------------------------------------");
        System.out.println("And after one wikipedia search we find that the answer to the question is, Liechtenstein.");
        System.out.println("");



    }
}