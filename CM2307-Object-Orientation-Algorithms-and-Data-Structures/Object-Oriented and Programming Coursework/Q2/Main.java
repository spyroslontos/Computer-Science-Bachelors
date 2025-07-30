import java.io.*;

public class Main {

    public static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws Exception {
        Factory Factory = new Factory();

        System.out.print("Card (c) or Die (d) game? ");
        String ans=br.readLine();

        GameInterface Game = Factory.Game(ans);

        Game.initialiseGame();
        Game.mainGame();
        Game.declareGameWinner();

    }
}
