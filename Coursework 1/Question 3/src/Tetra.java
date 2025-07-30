// Spyros Lontos
// c1722325

public class Tetra extends Fish {

    public Tetra(String name, int age, String gender, double weightKgs, String colour) {
        super(name, age, gender, weightKgs,colour);
    }

    public void Speak() {
        System.out.println("I am a Tetra Fish and my name is "+name+", I weight "+weightKgs+"Kg, I am a "+ age + " year old "+ gender +" and i am "+colour+ " in colour.");
    }

}
