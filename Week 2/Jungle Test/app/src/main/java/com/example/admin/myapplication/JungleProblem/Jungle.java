package com.example.admin.myapplication.JungleProblem;

import com.example.admin.myapplication.JungleProblem.Animals.Monkey;
import com.example.admin.myapplication.JungleProblem.Animals.Snake;
import com.example.admin.myapplication.JungleProblem.Animals.Tiger;

import java.util.ArrayList;

public class Jungle {
    ArrayList<Monkey> monkeys;
    ArrayList<Snake> snakes;
    ArrayList<Tiger> tigers;

    public Jungle() {
        this.monkeys = new ArrayList<Monkey>();
        this.snakes = new ArrayList<Snake>();
        this.tigers = new ArrayList<Tiger>();

        initMonkeys();
        initTigers();
        initSnakes();
    }

    public void soundOff() {
        for (Monkey monkey : monkeys) {
            monkey.makeSound();
            System.out.println("energy: " + monkey.getEnergy());
        }
        for (Snake snake : snakes) {
            snake.makeSound();
            System.out.println("energy: " + snake.getEnergy());
        }
        for (Tiger tiger : tigers) {
            tiger.makeSound();
            System.out.println("energy: " + tiger.getEnergy());
        }
    }

    private void initMonkeys() {
        Monkey monkey1 = new Monkey();
        Monkey monkey2 = new Monkey();
        Monkey monkey3 = new Monkey();
        Monkey monkey4 = new Monkey();

        monkeys.add(monkey1);
        monkeys.add(monkey2);
        monkeys.add(monkey3);
        monkeys.add(monkey4);
        
        for (Monkey monkey : monkeys) {
            monkey.setKnownOtherAnimals(monkeys.size());
        }
    }

    private void initSnakes() {
        Snake snake1 = new Snake();
        Snake snake2 = new Snake();
        Snake snake3 = new Snake();
        Snake snake4 = new Snake();

        snakes.add(snake1);
        snakes.add(snake2);
        snakes.add(snake3);
        snakes.add(snake4);

        for (Snake snake : snakes) {
            snake.setKnownOtherAnimals(snakes.size());
        }
    }

    private void initTigers() {
        Tiger tiger1 = new Tiger();
        Tiger tiger2 = new Tiger();
        Tiger tiger3 = new Tiger();
        Tiger tiger4 = new Tiger();

        tigers.add(tiger1);
        tigers.add(tiger2);
        tigers.add(tiger3);
        tigers.add(tiger4);

        for (Tiger tiger : tigers) {
            tiger.setKnownOtherAnimals(tigers.size());
        }
    }
}
