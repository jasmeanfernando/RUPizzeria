package com.example.rupizzeria;

/**
 * ChicagoPizza Class: Holds a valid Chicago Style pizza.
 * @author JasmeanFernando
 */
public class ChicagoPizza implements PizzaFactory
{
    /**
     * This method creates a Chicago Deluxe pizza.
     */
    @Override
    public Pizza createDeluxe()
    {
        Pizza deluxe = new Deluxe();
        return deluxe;
    }

    /**
     * This method creates a Chicago Meatzza pizza.
     */
    @Override
    public Pizza createMeatzza()
    {
        Pizza meatzza = new Meatzza();
        return meatzza;
    }

    /**
     * This method creates a Chicago BBQChicken pizza.
     */
    @Override
    public Pizza createBBQChicken()
    {
        Pizza bbqChicken = new BBQChicken();
        return bbqChicken;
    }

    /**
     * This method creates a Chicago BuildYourOwn pizza.
     */
    @Override
    public Pizza createBuildYourOwn()
    {
        Pizza buildyourown = new BuildYourOwn();
        return buildyourown;
    }
}