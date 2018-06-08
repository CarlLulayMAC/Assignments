package com.example.admin.myapplication.JungleProblem.Animals;

public class Monkey extends Animal{
    String sound = "Ooo Ooo Eee Eee";
    int foodBenefit = 2;
    int soundCost = 4;

    public Monkey() {
        super();
    }

    public void play() {
        if(this.energy >= 8) {
            System.out.println("Oooo Oooo Oooo");
            energy -= 8;
        }
        else {
            System.out.println("Monkey is too tired to play");
        }
    }
}
