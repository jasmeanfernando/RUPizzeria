package com.example.rupizzeria;

/**
 * BuildYourOwn Class: Holds a valid BuildYourOwn specialty pizza.
 * @author JasmeanFernando
 */
public class BuildYourOwn extends Pizza
{
    private int numOfToppings = 0;
    private int additionalToppings = 0;
    final static int MIN_TOPPINGS = 1;
    final static int MAX_TOPPINGS = 13;
    final static double EXTRA_TOPPINGS_COST = 1.59;

    /** This method adds a topping to BuildYourOwn pizza.
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

        //first 7 toppings are free
        if (numOfToppings < 7) {
            this.addNewTopping(selectedTopping);
            return true;
        }

        //over 7 toppings cost extra
        additionalToppings++;
        this.addNewTopping(selectedTopping);
        return true;
    }

    /** This method removes a topping from BuildYourOwn pizza.
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

        //first 7 toppings are free
        if (numOfToppings <= 7) {
            this.removeNewTopping(selectedTopping);
            return true;
        }

        //less than 7 toppings doesn't cost extra
        additionalToppings--;
        this.removeNewTopping(selectedTopping);
        return true;
    }

    /**
     * This method calculate the price of BuildYourOwn pizza.
     * @return double cost of BuildYourOwn pizza
     */
    @Override
    public double price() {
        double cost = 0;

        //set price based on size
        Size sizeOfPizza = getSize();
        switch(sizeOfPizza) {
            case SMALL:
                cost = 8.99;
                break;
            case MEDIUM:
                cost = 10.99;
                break;
            case LARGE:
                cost = 12.99;
                break;
        }

        //set price based on extra toppings
        if (additionalToppings > 0) {
            cost = cost + (additionalToppings * EXTRA_TOPPINGS_COST);
        }

        return cost;
    }
}