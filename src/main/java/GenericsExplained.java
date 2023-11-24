import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Look at compiler errors to get to know what is allowed and what is not.
 * Inheritance of classes:
 *     / Cat \
 * null        Mammal -> Animal -> Object
 *     \ Dog /
 *
 * Rules of thumb:
 * 1. Substitution principle: wherever a value of type T is expected, you can provide instead a value of a subtype of T.
 * 2. ADD --> [min,max] --> GET
 *    Allows to ADD everything below min, ang GET everything above max
 */
public class GenericsExplained {

    public static void main(String[] args) {

        Cat cat = new Cat();
        Dog dog = new Dog();
        Mammal mammal = new Mammal();
        Animal animal = new Animal();
        Object object = new Object();

        // Obvious:
        cat = null;
        dog = cat;
        mammal = cat;
        mammal = dog;
        animal = cat;
        animal = mammal;
        object = animal;

        // Now parameterized types:
        //T<A> -> U<B>
        //when:
        //T -> U
        //A ⊆ B

        List<? extends Mammal> extendMammals;
        List<Cat> cats1 = new ArrayList<>();
        // [null,Mammal] ⊇? [Cat,Cat]
        extendMammals = cats1;

        List<? extends Mammal> extendMammals2;
        List<? super Cat> superCats2 = new ArrayList<>();
        // [null,Mammal] ⊇? [Cat,Object]
        extendMammals2 = superCats2;

        Collection<? super Cat> superCats3;
        List<? super Object> superObjects3 = List.of();
        // [Cat,Object] ⊇? [Object,Object]
        superCats3 = superObjects3;

        Collection<?> everything;
        List<? extends Dog> extendDogs = new ArrayList<>();
        // [null,Object] ⊇? [Dog,Object]
        everything = extendDogs;



        // Now getting and adding elements
        // Think of <Cat> as a range: [min,max] = [Cat,Cat]
        List<Cat> cats = new ArrayList<>();
        // ADD --> [Cat,Cat] --> GET
        // Allows to add everything <= the min
        cats.add(null);
        cats.add(cat);
        cats.add(dog);
        cats.add(mammal);
        cats.add(animal);
        cats.add(object);

        // ADD --> [Cat,Cat] --> GET
        // Allows to get everything >= the max
        cat = cats.get(0);
        dog = cats.get(0);
        mammal = cats.get(0);
        animal = cats.get(0);
        object = cats.get(0);


        // Think of <Dog> as a range: [min,max] = [Dog,Dog]
        List<Dog> dogs = new ArrayList<>();
        // ADD --> [min,max] --> GET
        // Allows to add everything <= the min
        dogs.add(null);
        dogs.add(cat);
        dogs.add(dog);
        dogs.add(mammal);
        dogs.add(animal);
        dogs.add(object);

        // ADD --> [min,max] --> GET
        // Allows to get everything >= the max
        cat = dogs.get(0);
        dog = dogs.get(0);
        mammal = dogs.get(0);
        animal = dogs.get(0);
        object = dogs.get(0);


        // think of <Mammal> as a range: [min,max] = [Mammal,Mammal]
        List<Mammal> mammals = new ArrayList<>();
        // ADD --> [min,max] --> GET
        // Allows to add everything <= the min
        mammals.add(null);
        mammals.add(cat);
        mammals.add(dog);
        mammals.add(mammal);
        mammals.add(animal);
        mammals.add(object);
        // ADD --> [min,max] --> GET
        // Allows to get everything >= the max
        cat = mammals.get(0);
        dog = mammals.get(0);
        mammal = mammals.get(0);
        animal = mammals.get(0);
        object = mammals.get(0);

        // think of <Animal> as a range: [min,max] = [Animal,Animal]
        List<Animal> animals = new ArrayList<>();
        // ADD --> [min,max] --> GET
        // Allows to add everything <= the min
        animals.add(null);
        animals.add(cat);
        animals.add(dog);
        animals.add(mammal);
        animals.add(animal);
        animals.add(object);
        // ADD --> [min,max] --> GET
        // Allows to get everything >= the max
        cat = animals.get(0);
        dog = animals.get(0);
        mammal = animals.get(0);
        animal = animals.get(0);
        object = animals.get(0);

        // think of <Object> as a range: [min,max] = [Object,Object]
        List<Object> objects = new ArrayList<>();
        // ADD --> [min,max] --> GET
        // Allows to add everything <= the min
        objects.add(null);
        objects.add(cat);
        objects.add(dog);
        objects.add(mammal);
        objects.add(animal);
        objects.add(object);

        // ADD --> [min,max] --> GET
        // Allows to get everything >= the max
        cat = objects.get(0);
        dog = objects.get(0);
        mammal = objects.get(0);
        animal = objects.get(0);
        object = objects.get(0);


        extendsExample(cats);
        extendsExample(dogs);
        extendsExample(mammals);
        extendsExample(animals);
        extendsExample(objects);

        superExample(cats);
        superExample(dogs);
        superExample(mammals);
        superExample(animals);
        superExample(objects);

        unboundedExample(cats);
        unboundedExample(dogs);
        unboundedExample(mammals);
        unboundedExample(animals);
        unboundedExample(objects);
    }


    // Think of <? extends Mammal> as a range: [min,max] = [null,Mammal]
    private static void extendsExample(List<? extends Mammal> elements) {
        // ADD --> [min,max] --> GET
        // allows to get everything >= the max
        Cat cat = elements.get(0);
        Dog dog = elements.get(0);
        Mammal mammal = elements.get(0);
        Animal animal = elements.get(0);
        Object object = elements.get(0);

        // ADD --> [min,max] --> GET
        // allows to add everything <= the min
        elements.add(null);
        elements.add(new Cat());
        elements.add(new Dog());
        elements.add(new Mammal());
        elements.add(new Animal());
        elements.add(new Object());
    }


    // Think of <? super Mammal>  as a range: [min,max] = [Mammal,Object]
    private static void superExample(List<? super Mammal> elements) {
        // ADD --> [min,max] --> GET
        // allows to get everything >= the max
        Cat cat = elements.get(0);
        Dog dog = elements.get(0);
        Mammal mammal = elements.get(0);
        Animal animal = elements.get(0);
        Object object = elements.get(0);

        // ADD --> [min,max] --> GET
        // allows to add everything <= the min
        elements.add(null);
        elements.add(new Cat());
        elements.add(new Dog());
        elements.add(new Mammal());
        elements.add(new Animal());
        elements.add(new Object());
    }

    // think of <?>  as a range: [min,max] = [null,Object]
    private static void unboundedExample(List<?> elements) {
        // ADD --> [min,max] --> GET
        // allows to get everything >= the max
        Cat cat = elements.get(0);
        Dog dog = elements.get(0);
        Mammal mammal = elements.get(0);
        Animal animal = elements.get(0);
        Object object = elements.get(0);

        // ADD --> [min,max] --> GET
        // allows to add everything <= the min
        elements.add(null);
        elements.add(new Cat());
        elements.add(new Dog());
        elements.add(new Mammal());
        elements.add(new Animal());
        elements.add(new Object());
    }

    // bounded types are something different from wildcard parameterized types!
    private static <T extends Mammal> void boundedTypeExample(List<T> elements, T element) {
        elements.add(element);
    }
}
