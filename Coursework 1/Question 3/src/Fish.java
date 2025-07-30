// Spyros Lontos
// c1722325

public class Fish extends Pet {

    public Fish(String name, int age, String gender, double weightKgs, String colour) {
        super(name, age, gender, weightKgs,colour);
    }

    public void swim() {
        System.out.println("Under the sea.... swimming");
    }

    public void Speak() {
        System.out.println("I am a Fish and my name is "+name+", I weight "+weightKgs+"Kg, I am a "+ age + " year old "+ gender +" and i am "+colour+ " in colour.");
    }

}
