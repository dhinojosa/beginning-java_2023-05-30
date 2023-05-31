package com.salesforce;

public class Runner {
    public static void main(String... args) {
        //System.out.println("Hello" + args[0]);
        int result = factorialOf(5, 1);
        System.out.println(result);
        tellMeYourFavoriteFoods("Eggs", "Naan", "Ice Cream", "Broccoli");
    }

    private static int factorialOf(int i, int acc) {
        if (i == 0) return acc;
        else {
            System.out.format("i=%d, acc=%d%n", i, acc);
            return factorialOf(i - 1, acc * i);
        }
    }

    private static void sayHelloTo(String name) {
        sayHelloTo(name + "!");
    }

    private static void tellMeYourFavoriteFoods(String... foods) {
        System.out.println("Your favorite foods include:");
        for(String food: foods) {
            System.out.println("- " + food);
        }
    }


}
