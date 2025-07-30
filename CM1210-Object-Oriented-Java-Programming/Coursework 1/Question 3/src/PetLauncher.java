// Spyros Lontos
// c1722325

public class PetLauncher {
    public static void main(String[] args){

        System.out.println("Pets\n===========");

        Pug Zizou = new Pug("Zizou",7,"male",12, "grey");
        Zizou.setAggressive(true);
        Zizou.Speak();
        Zizou.Bark();
        Zizou.fetch();
        Zizou.breath();
        Zizou.eat();
        Zizou.poop();
        Zizou.sleep();
        Zizou.Snore();

        System.out.println("===========");

        PufferFish Ano = new PufferFish("Ano",1,"female",0.2,"yellow");
        Ano.Speak();
        Ano.puff();
        Ano.sleep();
        Ano.swim();

        System.out.println("===========");

        Snake Rio = new Snake("Rio",3,"male",33,"gold");
        Rio.setVenomous(true);
        Rio.Speak();
        Rio.attack();
        Rio.hide();
        Rio.eat();
        Rio.slither();

        System.out.println("===========");

        Cat Lambis = new Cat("Lambis",2,"female",7.2,"black");
        Lambis.setHouseCat(true);
        Lambis.Speak();
        Lambis.purr();
        Lambis.meow();
        Lambis.poop();

        System.out.println("===========");

        Boxer Mahites = new Boxer("Mahites",3,"male",22.3,"brown");
        Mahites.Speak();
        Mahites.eat();
        Mahites.Bark();
        Mahites.breath();
        Mahites.poop();

    }
}
