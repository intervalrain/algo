package src.Topic.StackAndQueues;

import java.util.LinkedList;

/**
 * Animal Shelter: 
 * An animal shelter, which holds only dogs ans cats,
 * operates on a strictly "first in, first out" basis.
 * People must adopt either the "oldest" (based on arrival time) of all animals at the shelter,
 * or they can select whether they prefer a dog or a cat (and will receive the oldest animal of that type).
 * They cannot select which specific animal they would like.
 * Create the data structures to maintain this system and implement operations such as enqueue, dequeueAny, dequeueDog, and dequeueCat.
 * You may use the built-in LinkedList data structure.
 */
abstract class Animal{
    private int order;
    protected String name;
    public Animal(String n) { this.name = n; }
    public void setOrder(int ord) { this.order = ord; }
    public int getOrder() { return this.order; }
}
class Dog extends Animal{
    public Dog(String n){super(n);}
}
class Cat extends Animal{
    public Cat(String n){super(n);}
}
public class AnimalShelter {
    LinkedList<Dog> dogs = new LinkedList<>();
    LinkedList<Cat> cats = new LinkedList<>();

    private int order = 0;

    public void enqueue(Animal a){
        a.setOrder(order++);
        if (a instanceof Dog) dogs.addLast((Dog)a);
        else if (a instanceof Cat) cats.addLast((Cat)a);
    }
    public Animal dequeueAny(){
        if (dogs.size() == 0){
            return dequeueCat();
        } else if (cats.size() == 0)
            return  dequeueDog();
        Dog d = dogs.peek();
        Cat c = cats.peek();
        if (d.getOrder() > c.getOrder())
            return dequeueDog();
        else
            return dequeueCat();
    }
    public Animal dequeueDog(){
        return dogs.poll();
    }
    public Animal dequeueCat(){
        return cats.poll();
    }
}
