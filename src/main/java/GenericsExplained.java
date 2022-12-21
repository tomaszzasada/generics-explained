import java.util.ArrayList;
import java.util.List;

/**
 * Look at compiler errors to get to know what is allowed and what is not.
 * Inheritance of classes:
 * null -> Cat\
 *             Mammal -> Animal -> Object
 * null -> Dog/
 */
public class GenericsExplained {

    public static void main(String[] args) {
        Cat cat = new Cat();
        Dog dog = new Dog();
        Mammal mammal = new Mammal();
        Animal animal = new Animal();
        Object object = new Object();

        List<Cat> cats = new ArrayList<>();
        cats.add(null);
        cats.add(cat);
        cats.add(dog);
        cats.add(mammal);
        cats.add(animal);
        cats.add(object);
        cat = cats.get(0);
        dog = cats.get(0);
        mammal = cats.get(0);
        animal = cats.get(0);
        object = cats.get(0);

        List<Dog> dogs = new ArrayList<>();
        dogs.add(null);
        dogs.add(cat);
        dogs.add(dog);
        dogs.add(mammal);
        dogs.add(animal);
        dogs.add(object);
        cat = dogs.get(0);
        dog = dogs.get(0);
        mammal = dogs.get(0);
        animal = dogs.get(0);
        object = dogs.get(0);

        List<Mammal> mammals = new ArrayList<>();
        mammals.add(null);
        mammals.add(cat);
        mammals.add(dog);
        mammals.add(mammal);
        mammals.add(animal);
        mammals.add(object);
        cat = mammals.get(0);
        dog = mammals.get(0);
        mammal = mammals.get(0);
        animal = mammals.get(0);
        object = mammals.get(0);

        List<Animal> animals = new ArrayList<>();
        animals.add(null);
        animals.add(cat);
        animals.add(dog);
        animals.add(mammal);
        animals.add(animal);
        animals.add(object);
        cat = animals.get(0);
        dog = animals.get(0);
        mammal = animals.get(0);
        animal = animals.get(0);
        object = animals.get(0);

        List<Object> objects = new ArrayList<>();
        objects.add(null);
        objects.add(cat);
        objects.add(dog);
        objects.add(mammal);
        objects.add(animal);
        objects.add(object);
        cat = objects.get(0);
        dog = objects.get(0);
        mammal = objects.get(0);
        animal = objects.get(0);
        object = objects.get(0);

        get(cats);
        get(dogs);
        get(mammals);
        get(animals);
        get(objects);

        put(cats);
        put(dogs);
        put(mammals);
        put(animals);
        put(objects);
    }

    // think of <? extends Mammal> as a range: <min;max> = <null;Mammal>
    private static void get(List<? extends Mammal> mammals) {
        // allows to get everything >= the max
        Cat cat = mammals.get(0);
        Dog dog = mammals.get(0);
        Mammal mammal = mammals.get(0);
        Animal animal = mammals.get(0);
        Object object = mammals.get(0);

        // allows to add everything <= the min
        mammals.add(null);
        mammals.add(new Cat());
        mammals.add(new Dog());
        mammals.add(new Mammal());
        mammals.add(new Animal());
        mammals.add(new Object());
    }

    // think of <? super Mammal>  as a range: <min;max> = <Mammal;Object>
    private static void put(List<? super Mammal> mammals) {
        // allows to get everything >= the max
        Cat cat = mammals.get(0);
        Dog dog = mammals.get(0);
        Mammal mammal = mammals.get(0);
        Animal animal = mammals.get(0);
        Object object = mammals.get(0);

        // allows to add everything <= the min
        mammals.add(null);
        mammals.add(new Cat());
        mammals.add(new Dog());
        mammals.add(new Mammal());
        mammals.add(new Animal());
        mammals.add(new Object());
    }
}
