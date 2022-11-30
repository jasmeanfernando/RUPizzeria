package com.example.rupizzeria;

/**
 * BBQChicken Class: Holds a valid BBQChicken specialty pizza.
 * @author JasmeanFernando
 */
public class BBQChicken extends Pizza
{
    private int numOfToppings = 0;
    private int additionalToppings = 0;
    final static int MIN_TOPPINGS = 1;
    final static int MAX_TOPPINGS = 13;
    final static double EXTRA_TOPPINGS_COST = 1.59;

    /** This method adds a topping to BBQChicken pizza.
     * @param obj
     * @return boolean true if topping is added, false otherwise
     */
    @Override
    public boolean add(Object obj) {
        numOfToppings = getToppings().size();
        Topping selectedTopping = (Topping)obj;

        //if maximum # of toppings is reached
        if (numOfToppings == MAX_TOPPINGS) {
            return false;
        }

        //if topping is a default topping
        if (selectedTopping == Topping.BBQCHICKEN || selectedTopping == Topping.GREENPEPPER ||
                selectedTopping == Topping.PROVOLONE || selectedTopping == Topping.CHEDDAR) {
            this.addNewTopping(selectedTopping);
            return true;
        }

        //if topping is an additional topping
        additionalToppings++;
        this.addNewTopping(selectedTopping);
        return true;
    }

    /** This method removes a topping from BBQChicken pizza.
     * @param obj
     * @return boolean true if topping is removed, false otherwise
     */
    @Override
    public boolean remove(Object obj) {
        numOfToppings = getToppings().size();
        Topping selectedTopping = (Topping)obj;

        //if minimum # of toppings is reached
        if (numOfToppings == MIN_TOPPINGS) {
            return false;
        }

        //if topping is a default topping
        if (selectedTopping == Topping.BBQCHICKEN || selectedTopping == Topping.GREENPEPPER ||
                selectedTopping == Topping.PROVOLONE || selectedTopping == Topping.CHEDDAR) {
            this.removeNewTopping(selectedTopping);
            return true;
        }

        //if topping is an additional topping
        additionalToppings--;
        this.removeNewTopping(selectedTopping);
        return true;
    }

    /**
     * This method calculate the price of BBQChicken pizza.
     * @return double cost of BBQChicken pizza
     */
    @Override
    public double price() {
        double cost = 0;

        //set price based on size
        Size sizeOfPizza = getSize();
        switch(sizeOfPizza) {
            case SMALL:
                cost = 13.99;
                break;
            case MEDIUM:
                cost = 15.99;
                break;
            case LARGE:
                cost = 17.99;
                break;
        }

        //set price based on extra toppings
        if (additionalToppings > 0) {
            cost = cost + (additionalToppings * EXTRA_TOPPINGS_COST);
        }

        return cost;
    }
}