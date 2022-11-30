package com.example.rupizzeria;

/**
 * Customizable Interface: Holds a valid interface that is implemented by:
 * Pizza, Deluxe, Meatzza, BBQChicken, BuildYourOwn
 * Order, StoreOrders
 * @author JasmeanFernando
 */
public interface Customizable <E>
{
    boolean add(Object obj);
    boolean remove(Object obj);
}