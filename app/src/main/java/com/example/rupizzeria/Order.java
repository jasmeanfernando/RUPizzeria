package com.example.rupizzeria;

import java.util.ArrayList;

/**
 * Order Class: Holds a valid order (list of pizzas).
 * @author JasmeanFernando
 */
public class Order implements Customizable
{
    private int orderNumber;
    private ArrayList <Pizza> order;
    private double orderPrice;

    /**
     * Constructor: Creates Order object.
     */
    public Order() {
        this.orderNumber = (int)Math.ceil(Math.random()*9999);
        this.order = new ArrayList <Pizza> ();
        this.orderPrice = 0.0;
    }

    /**
     * This method sets orderPrice.
     * @param double price
     */
    public void setPrice(double price) { this.orderPrice = price; }

    /** This method adds a pizza to order.
     * @param obj
     * @return boolean true if pizza is added, false otherwise
     */
    @Override
    public boolean add(Object obj) {
        Pizza newPizza = (Pizza)obj;
        order.add(newPizza);
        return true;
    }

    /** This method adds pizzas from a new order to this order.
     * @param Order newOrder
     */
    public void addOrder(Order newOrder)
    {
        ArrayList <Pizza> newOrderPizzas = newOrder.getOrder();
        for (Pizza newPizza: newOrderPizzas) {
            this.order.add(newPizza);
        }
    }

    /** This method removes a pizza from order.
     * @param obj
     * @return boolean true if pizza is removed, false otherwise
     */
    @Override
    public boolean remove(Object obj)
    {
        Pizza unwantedPizza = (Pizza)obj;
        order.remove(unwantedPizza);
        return true;
    }

    /**
     * This method checks if order is empty.
     * @return boolean true if empty, false otherwise
     */
    public boolean isEmpty() {
        if (order.isEmpty()) {
            return true;
        }
        return false;
    }

    /**
     * This method clears order.
     */
    public void clearOrder() { order.clear(); }

    /**
     * This method returns order number.
     * @return int orderNumber
     */
    public int getOrderNumber() { return this.orderNumber; }

    /**
     * This method returns order.
     * @return ArrayList <Pizza> order
     */
    public ArrayList <Pizza> getOrder() { return this.order; }

    /**
     * This method returns price of order.
     * @return double price
     */
    public double getPrice() { return this.orderPrice; }

    /**
     * This method concatenates order into a string.
     * @return String crust
     */
    @Override
    public String toString() {
        return "Order #: " + this.orderNumber;
    }
}