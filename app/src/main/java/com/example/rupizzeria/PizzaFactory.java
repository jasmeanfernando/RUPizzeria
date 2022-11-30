package com.example.rupizzeria;

/**
 * PizzaFactory Interface: Holds a valid interface that is implemented by:
 * ChicagoPizza, NYPizza
 * @author JasmeanFernando
 */
public interface PizzaFactory
{
    Pizza createDeluxe();
    Pizza createMeatzza();
    Pizza createBBQChicken();
    Pizza createBuildYourOwn();
}