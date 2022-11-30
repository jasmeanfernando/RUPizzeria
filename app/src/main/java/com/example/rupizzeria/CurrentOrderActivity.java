package com.example.rupizzeria;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * CurrentOrderActivity: Activity that controls activity_current_order.xml.
 * @author JasmeanFernando
 */
public class CurrentOrderActivity extends AppCompatActivity {
    //.xml variables
    private ListView allPizzas;
    private EditText orderNumber, orderSubtotal, orderSalesTax, orderTotal;

    //activity variables
    private Order currentOrder = new Order ();
    private String orderID;
    private String orderPrice;

    /**
     * This method runs CurrentOrderActivity.
     * @param savedInstanceState Used to reload UI state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_order); //load .xml
        AlertDialog.Builder alert = new AlertDialog.Builder(this); //alerts
        buildAlert(alert);

        //before initialization: check if user added any pizzas to the order.
        if (MainActivity.orderOfChicagoPizzas.isEmpty() && MainActivity.orderOfNewYorkPizzas.isEmpty()) {
            //alert dialog
            alert.setMessage("No pizzas in current order!");
            AlertDialog error = alert.create();
            error.show();

            //returns to MainActivity
            Intent intent = new Intent(CurrentOrderActivity.this, MainActivity.class);
            startActivity(intent);
        }

        //initialize .xml variables
        allPizzas = findViewById(R.id.placedPizzasListView);
        orderNumber = findViewById(R.id.orderNumberEditText);
        orderSubtotal = findViewById(R.id.subtotalEditText);
        orderSalesTax = findViewById(R.id.taxEditText);
        orderTotal = findViewById(R.id.totalEditText);
        Button clearOrder = findViewById(R.id.clearOrderButton);
        Button placeOrder = findViewById(R.id.placeOrderButton);
        Button backToMain = findViewById(R.id.mainButton);

        //initialize activity variables
        currentOrder.addOrder(MainActivity.orderOfChicagoPizzas);
        currentOrder.addOrder(MainActivity.orderOfNewYorkPizzas);

        //initialize allPizzas (placedPizzasListView)
        updatePizzas();

        //initialize orderNumber (orderNumberEditText)
        orderID = "" + currentOrder.getOrderNumber();
        orderNumber.setText(orderID);

        //initialize price
        updateOrderPrice();

        //lambda expression that initializes the removal of a pizza
        allPizzas.setOnItemClickListener((adapterView, view, position, id) -> {
            //alert dialog
            alert.setMessage("Successfully removed from order!");
            AlertDialog confirmation = alert.create();
            confirmation.show();

            //get pizza
            ArrayList <Pizza> order = currentOrder.getOrder();
            Pizza removePizza = order.get(position);

            //remove pizza from currentOrder
            currentOrder.remove(removePizza);

            //update price
            updateOrderPrice();

            //update Listviews
            updatePizzas();
        });

        //lambda expression that initializes the clearing of currentOrder > clearOrder (clearOrderButton)
        clearOrder.setOnClickListener(view -> {
            //alert dialog
            alert.setMessage("Successfully cleared order!");
            AlertDialog confirmation = alert.create();
            confirmation.show();

            //clear this activity and MainActivity
            clearActivity();
        });

        //lambda expression that initializes the placing of currentOrder > placeOrder (placeOrderButton)
        placeOrder.setOnClickListener(view -> {
            //alert dialog
            alert.setMessage("Successfully placed order! Thank you for ordering from RUPizzeria!");
            AlertDialog confirmation = alert.create();
            confirmation.show();

            //pass currentOrder to MainActivity
            MainActivity.storeOrder.add(currentOrder);

            //clear this activity and MainActivity
            clearActivity();
        });

        //lambda expression that initializes the return back to MainActivity
        backToMain.setOnClickListener(view -> {
            //returns to MainActivity
            Intent intent = new Intent(CurrentOrderActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }

    /**
     * This method is used to set default view for allPizzas (placedPizzasListView).
     */
    private void updatePizzas() {
        //clear
        ArrayAdapter <String> allPizzasAdapter;
        allPizzas.setAdapter(null);

        //get pizzas from currentOrder
        ArrayList <Pizza> order = currentOrder.getOrder();
        //get pizza details from pizzas
        ArrayList <String> pizzaDetails = new ArrayList<>();
        for (Pizza pizza: order) {
            pizzaDetails.add(pizza.toString());
        }

        //set allPizzas
        allPizzasAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, pizzaDetails);
        allPizzas.setAdapter(allPizzasAdapter);
    }

    /**
     * This method is used to set default view for: orderSubtotal (subtotalEditText),
     * orderSalesTax (taxEditText), orderTotal (totalEditText).
     */
    private void updateOrderPrice() {
        //initialize orderSubtotal (subtotalEditText)
        double totalBeforeTax = calculateOrderPrice();
        orderSubtotal.setText(priceFormatter(totalBeforeTax));

        //initialize orderSalesTax (taxEditText)
        double tax = totalBeforeTax * 0.06625;
        orderSalesTax.setText(priceFormatter(tax));

        //initialize orderTotal (totalEditText)
        double totalAfterTax = totalBeforeTax + tax;
        currentOrder.setPrice(totalAfterTax); //initialize order price
        orderPrice = priceFormatter(totalAfterTax);
        orderTotal.setText(orderPrice);
    }

    /**
     * This method calculates running total of order before tax.
     * @return double cost
     */
    private double calculateOrderPrice() {
        //get pizzas from currentOrder
        ArrayList <Pizza> order = currentOrder.getOrder();
        //calculate cost from pizzas
        double cost = 0.0;
        for (Pizza pizza: order) {
            cost = cost + pizza.price();
        }
        return cost;
    }

    /**
     * This method is used to update price in proper format.
     * @param price as double
     * @return price as String
     */
    private String priceFormatter(double price) {
        DecimalFormat formatter = new DecimalFormat("#,##0.00");
        return String.format("$%s", formatter.format(price));
    }

    /**
     * This method is used to create error dialogs.
     * @param AlertDialog.Builder alert
     */
    private void buildAlert(AlertDialog.Builder alert) {
        alert.setCancelable(true);
        alert.setPositiveButton( "Yes", (dialog, id) -> dialog.cancel());
        alert.setNegativeButton("No", (dialog, id) -> dialog.cancel());
    }

    /**
     * This method clears this activity and refreshes page.
     */
    private void clearActivity() {
        //update activity variables
        currentOrder.clearOrder();
        orderID = "####";
        orderPrice = "$0.00";

        //update .xml variables
        allPizzas.setAdapter(null);
        orderNumber.setText(orderID);
        orderSubtotal.setText(orderPrice);
        orderSalesTax.setText(orderPrice);
        orderTotal.setText(orderPrice);

        //update MainActivity
        MainActivity.orderOfChicagoPizzas.clearOrder();
        MainActivity.orderOfNewYorkPizzas.clearOrder();

        //returns to MainActivity
        Intent intent = new Intent(CurrentOrderActivity.this, MainActivity.class);
        startActivity(intent);
    }
}