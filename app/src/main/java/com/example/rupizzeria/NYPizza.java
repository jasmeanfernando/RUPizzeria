package com.example.rupizzeria;

/**
 * NYPizza Class: Holds a valid New York Style pizza.
 * @author JasmeanFernando
 */
public class NYPizza implements PizzaFactory
{
    /**
     * This method creates a New York Deluxe pizza.
     */
    @Override
    public Pizza createDeluxe()
    {
        Pizza deluxe = new Deluxe();
        return deluxe;
    }

    /**
     * This method creates a New York Meatzza pizza.
     */
    @Override
    public Pizza createMeatzza()
    {
        Pizza meatzza = new Meatzza();
        return meatzza;
    }

    /**
     * This method creates a New York BBQChicken pizza.
     */
    @Override
    public Pizza createBBQChicken()
    {
        Pizza bbqChicken = new BBQChicken();
        return bbqChicken;
    }

    /**
     * This method creates a New York BuildYourOwn pizza.
     */
    @Override
    public Pizza createBuildYourOwn()
    {
        Pizza buildyourown = new BuildYourOwn();
        return buildyourown;
    }
}