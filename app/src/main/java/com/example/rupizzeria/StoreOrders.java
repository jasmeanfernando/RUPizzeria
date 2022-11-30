package com.example.rupizzeria;

import java.util.ArrayList;

/**
 * StoreOrders Class: Holds a valid store order (list of orders).
 * @author JasmeanFernando
 */
public class StoreOrders implements Customizable
{
    private ArrayList <Order> storeOrders;
    private double orderPrice;

    /**
     * Constructor: Creates StoreOrders object.
     */
    public StoreOrders() {
        this.storeOrders = new ArrayList <Order> ();
        this.orderPrice = 0.0;
    }

    /**
     * This method sets orderPrice.
     * @param double price
     */
    public void setPrice(double price) { this.orderPrice = price; }

    /** This method adds an order to storeOrder.
     * @param obj
     * @return boolean true if order is added, false otherwise
     */
    @Override
    public boolean add(Object obj)
    {
        Order newOrder = (Order)obj;
        storeOrders.add(newOrder);
        return true;
    }

    /** This method adds orders from a new store order to this store order.
     * @param Order newOrder
     */
    public void addStoreOrder(StoreOrders newStoreOrder)
    {
        ArrayList <Order> newStoreOrderOrders = newStoreOrder.getStoreOrders();
        for (Order newOrder: newStoreOrderOrders) {
            this.storeOrders.add(newOrder);
        }
    }

    /** This method removes an order from storeOrder.
     * @param obj
     * @return boolean true if order is removed, false otherwise
     */
    @Override
    public boolean remove(Object obj)
    {
        Order unwantedOrder = (Order)obj;
        storeOrders.remove(unwantedOrder);
        return true;
    }

    /**
     * This method checks if storeOrders is empty.
     * @return boolean true if empty, false otherwise
     */
    public boolean isEmpty() {
        if (storeOrders.isEmpty()) {
            return true;
        }
        return false;
    }

    /**
     * This method clears storeOrders.
     */
    public void clearStoreOrders() { storeOrders.clear(); }

    /**
     * This method returns storeOrders.
     * @return ArrayList <Order> storeOrders
     */
    public ArrayList <Order> getStoreOrders() { return this.storeOrders; }

    /**
     * This method returns price of storeOrders.
     * @return int size
     */
    public double getPrice() { return this.orderPrice; }
}