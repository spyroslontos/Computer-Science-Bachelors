// Spyros Lontos
// c1722325

public class PufferFish extends Fish{

    public PufferFish(String name, int age, String gender, double weightKgs, String colour) {
        super(name, age, gender, weightKgs,colour);
    }

    public void puff() {
        System.out.println("Puffing up...");
    }

    public void Speak() {
        System.out.println("I am a Puffer Fish and my name is "+name+", I weight "+weightKgs+"Kg, I am a "+ age + " year old "+ gender +" and i am "+colour+ " in colour.");
    }

}
