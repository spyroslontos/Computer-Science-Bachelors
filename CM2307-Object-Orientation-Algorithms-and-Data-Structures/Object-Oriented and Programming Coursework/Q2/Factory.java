public class Factory {

    public GameInterface Game(String ans) {

        if (ans.equals("c")) {
            return new CardGame();
        } else if (ans.equals("d")) {
            return new DieGame();
        } else {
            System.out.println("Input not understood");
            System.exit(1);
            return null;
        }
    }
}
