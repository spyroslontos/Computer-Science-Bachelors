// Spyros Lontos
// c1722325

public class Dog extends Pet {

    private boolean aggressive;

    public Dog(String name, int age, String gender, double weightKgs, String colour) {
        super(name, age, gender, weightKgs,colour);
        this.aggressive = aggressive;
    }

    public boolean isAggressive() {
        return aggressive;
    }

    public void setAggressive(boolean aggressive) {
        this.aggressive = aggressive;
    }

    public void Bark() {
        System.out.println("BARK BARK");
    }

    public void fetch() {
        System.out.println("IM GOING TO GET IT");
    }

    public void Speak() {
        System.out.print("I am a Dog and my name is "+name+", I weight "+weightKgs+"Kg, I am a "+ age + " year old "+ gender +" and i am "+colour+ " in colour.");
        if (isAggressive()){
            System.out.println(" I am aggressive.");
        }
        else {
            System.out.println(" I am not aggressive.");
        }
    }
}
