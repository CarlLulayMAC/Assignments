package com.example.admin.myapplication.JungleProblem.Animals;

import com.example.admin.myapplication.JungleProblem.Food;

public abstract class Animal {
    int energy;

    int knownOtherAnimals;
    static int soundCost = 3;
    static int sleepBenefit = 5;
    static int foodBenefit = 5;
    String sound = "";

    public int getEnergy() {
        return energy;
    }

    public void setKnownOtherAnimals(int knownOtherAnimals) {
        this.knownOtherAnimals = knownOtherAnimals;
    }

    public Animal() {
        this.energy = 25;
    }

    public void sleep() {
        energy += sleepBenefit;
    }

    public void makeSound() {
        if (energy >= soundCost) {
            energy -= soundCost;
            System.out.println(sound);
        }
    }

    public void eat(Food food) {
        energy += foodBenefit;
    }


}
