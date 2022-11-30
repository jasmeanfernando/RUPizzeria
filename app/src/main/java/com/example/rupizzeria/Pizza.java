package com.example.rupizzeria;

import java.util.ArrayList;

/**
 * Pizza Class: Holds a pizza abstract superclass that is extended by Deluxe, Meatzza, BBQChicken, and BuildYourOwn.
 * @author JasmeanFernando
 */
public abstract class Pizza implements Customizable
{
    private ArrayList <Topping> toppings = new ArrayList <Topping> ();
    private Crust crust;
    private Size size;
    public abstract double price();

    /**
     * This method sets default toppings.
     * @param String pizzaFlavor
     */
    public void setDefaultToppings(String pizzaFlavor) {
        if (pizzaFlavor.equalsIgnoreCase("Deluxe")) {
            toppings.add(Topping.SAUSAGE);
            toppings.add(Topping.PEPPERONI);
            toppings.add(Topping.GREENPEPPER);
            toppings.add(Topping.ONION);
            toppings.add(Topping.MUSHROOM);
        }
        else if (pizzaFlavor.equalsIgnoreCase("Meatzza")) {
            toppings.add(Topping.SAUSAGE);
            toppings.add(Topping.PEPPERONI);
            toppings.add(Topping.BEEF);
            toppings.add(Topping.HAM);
        }
        else if (pizzaFlavor.equalsIgnoreCase("BBQChicken")) {
            toppings.add(Topping.BBQCHICKEN);
            toppings.add(Topping.GREENPEPPER);
            toppings.add(Topping.PROVOLONE);
            toppings.add(Topping.CHEDDAR);
        }
    }

    /**
     * This method adds to ArrayList <Topping> toppings.
     * @param Topping selectedTopping
     */
    public void addNewTopping(Topping selectedTopping) {
        toppings.add(selectedTopping);
    }

    /**
     * This method removes from ArrayList <Topping> toppings.
     * @param Topping selectedTopping
     */
    public void removeNewTopping(Topping selectedTopping) {
        toppings.remove(selectedTopping);
    }

    /**
     * This method sets default crust.
     * @param String selectedCrust
     */
    public void setCrust(String selectedCrust) { crust = Crust.valueOf(selectedCrust.toUpperCase()); }

    /**
     * This method sets default size.
     * @param String selectedSize
     */
    public void setSize(String selectedSize) {
        size = Size.valueOf(selectedSize.toUpperCase());
    }

    /**
     * This method returns ArrayList <Topping> toppings.
     * @return ArrayList <Topping> toppings
     */
    public ArrayList <Topping> getToppings() { return this.toppings; }

    /**
     * This method returns crust.
     * @return Crust crust
     */
    public Crust getCrust() { return this.crust; }

    /**
     * This method returns size.
     * @return Size size
     */
    public Size getSize() { return this.size; }

    /**
     * This method concatenates pizza into a string.
     * @return String Pizza details
     */
    @Override
    public String toString() {
        Deluxe dlx = new Deluxe();
        Meatzza mtzz = new Meatzza();
        BBQChicken bbqchckn = new BBQChicken();

        if (this.getClass().isInstance(dlx)) {
            return "Deluxe Pizza: " + this.crust + ", " + this.size + ", " + printToppings();
        }
        else if (this.getClass().isInstance(mtzz)) {
            return "Meatzza Pizza: " + this.crust + ", " + this.size + ", " + printToppings();
        }
        else if (this.getClass().isInstance(bbqchckn)) {
            return "BBQChicken Pizza: " + this.crust + ", " + this.size + ", " + printToppings();
        }
        else {
            return "BuildYourOwn Pizza: " + this.crust + ", " + this.size + ", " + printToppings();
        }
    }

    /**
     * This method concatenates all toppings into a string.
     * @return String Pizza topping details
     */
    public String printToppings() {
        String str = "";
        for (Topping topping: this.toppings) {
            str = str + "/" + topping;
        }
        return str;
    }
}