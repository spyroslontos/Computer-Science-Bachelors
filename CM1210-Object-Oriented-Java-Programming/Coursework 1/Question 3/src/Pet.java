// Spyros Lontos
// c1722325

public class Pet {

    String name;
    int age;
    String gender;
    double weightKgs;
    String colour;


    public Pet(String name, int age, String gender, double weightKgs, String colour) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.weightKgs = weightKgs;
        this.colour = colour;
    }

    public void eat() {
        System.out.println("I love eating food");
    }

    public void sleep() {
        System.out.println("Snoozing away");
    }

    public void breath() {
        System.out.println("Inhale...Exhale...Repeat");
    }

    public void poop() {
        System.out.println("We all have to go once in a while");
    }

}
