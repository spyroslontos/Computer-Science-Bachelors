// Spyros Lontos
// c1722325

public class Guppy extends Fish {

    public Guppy(String name, int age, String gender, double weightKgs, String colour) {
        super(name, age, gender, weightKgs,colour);
    }

    public void Speak() {
        System.out.println("I am a Guppy Fish and my name is "+name+", I weight "+weightKgs+"Kg, I am a "+ age + " year old "+ gender +" and i am "+colour+ " in colour.");
    }

}
