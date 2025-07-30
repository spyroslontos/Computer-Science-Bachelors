// Spyros Lontos
// c1722325

public class Snake extends Pet {

    private boolean venomous;

    public Snake(String name, int age, String gender, double weightKgs, String colour) {
        super(name, age, gender, weightKgs,colour);
        this.venomous = venomous;
    }

    public boolean isVenomous() {
        return venomous;
    }

    public void setVenomous(boolean venomous) {
        this.venomous = venomous;
    }

    public void slither() {
        System.out.println("Slithering ~~~~~~~~");
    }

    public void hide() {
        System.out.println("No one will find me...");
    }

    public void attack() {
        System.out.println("WATCH OUT IM COMING");
    }

    public void Speak() {
        System.out.print("I am a Snake and my name is "+name+", I weight "+weightKgs+"Kg, I am a "+ age + " year old "+ gender +" and i am "+colour+ " in colour.");
        if (isVenomous()){
            System.out.println(" I am venomous.");
        }
        else {
            System.out.println(" I am not venomous.");
        }
    }
}
