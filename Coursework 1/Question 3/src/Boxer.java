// Spyros Lontos
// c1722325

public class Boxer extends Dog {

    public Boxer(String name, int age, String gender, double weightKgs, String colour) {
        super(name, age, gender, weightKgs,colour);
    }

    public void Speak() {
        System.out.print("I am a Boxer and my name is "+name+", I weight "+weightKgs+"Kg, I am a "+ age + " year old "+ gender +" and i am "+colour+ " in colour.");
        if (isAggressive()){
            System.out.println(" I am aggressive.");
        }
        else {
            System.out.println(" I am not aggressive.");
        }
    }

}
