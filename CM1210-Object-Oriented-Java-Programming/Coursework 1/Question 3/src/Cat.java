// Spyros Lontos
// c1722325

public class Cat extends Pet {

    private boolean HouseCat;

    public Cat(String name, int age, String gender, double weightKgs, String colour) {
        super(name, age, gender, weightKgs,colour);
        this.HouseCat = HouseCat;
    }

    public boolean isHouseCat() {
        return HouseCat;
    }

    public void setHouseCat(boolean houseCat) {
        HouseCat = houseCat;
    }

    public void purr() {
        System.out.println("Purrrrrrrrr");
    }

    public void meow() {
        System.out.println("MEOW      MEOW");
    }

    public void Speak() {
        System.out.print("I am a Cat and my name is "+name+", I weight "+weightKgs+"Kg, I am a "+ age + " year old "+ gender +" and i am "+colour+ " in colour.");
        if (isHouseCat()){
            System.out.println(" I am a House cat.");
        }
        else {
            System.out.println(" I am not a House cat.");

        }
    }
}
