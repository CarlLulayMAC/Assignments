package com.example.admin.myapplication.JungleProblem.Animals;

import com.example.admin.myapplication.JungleProblem.Food;

public class Tiger extends Animal{
    int knownOtherAnimals;
    static int sleepBenefit = 5;
    String sound = "Roar";
    public Tiger() {
        super();
    }

    @Override
    public void eat(Food food) {
        if (food.type == Food.GRAINS) {
            System.out.println("Tiger cannot eat grains. Tiger Vomits");
        }
        else {
            super.eat(food);
        }
    }
}
