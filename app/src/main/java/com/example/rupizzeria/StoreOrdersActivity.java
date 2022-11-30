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
 * StoreOrdersActivity: Activity that controls activity_store_orders.xml.
 * @author JasmeanFernando
 */
public class StoreOrdersActivity extends AppCompatActivity {
    //.xml variables
    private ListView allOrders;
    private EditText ordersRemaining, ordersTotal;

    //activity variables
    private StoreOrders currentStoreOrder = new StoreOrders ();
    private String ordersLeft;
    private String storeOrderPrice;

    /**
     * This method runs StoreOrdersActivity.
     * @param savedInstanceState Used to reload UI state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_orders); //load .xml
        AlertDialog.Builder alert = new AlertDialog.Builder(this); //alerts
        buildAlert(alert);

        //before initialization: check if user added any pizzas to the order.
        if (MainActivity.storeOrder.isEmpty()) {
            //alert dialog
            alert.setMessage("No placed orders in store!");
            AlertDialog error = alert.create();
            error.show();

            //returns to MainActivity
            Intent intent = new Intent(StoreOrdersActivity.this, MainActivity.class);
            startActivity(intent);
        }

        //initialize .xml variables
        allOrders = findViewById(R.id.placedOrdersListView);
        ordersRemaining = findViewById(R.id.ordersRemainingEditText);
        ordersTotal = findViewById(R.id.totalEditText);
        Button cancelOrders = findViewById(R.id.cancelOrdersButton);
        Button backToMain = findViewById(R.id.mainButton);

        //initialize activity variables
        currentStoreOrder.addStoreOrder(MainActivity.storeOrder);

        //initialize allOrders (placedOrdersListView)
        updateOrders();

        //initialize ordersRemaining (ordersRemainingEditText)
        ordersLeft = "" + currentStoreOrder.getStoreOrders().size();
        ordersRemaining.setText(ordersLeft);

        //initialize price
        updateStoreOrderPrice();

        //lambda expression that initializes the cancellation of currentStoreOrder > cancelOrders (cancelOrdersButton)
        cancelOrders.setOnClickListener(view -> {
            //alert dialog
            alert.setMessage("Successfully cancelled all orders!");
            AlertDialog confirmation = alert.create();
            confirmation.show();

            //clear this activity and MainActivity
            clearActivity();
        });

        //lambda expression that initializes the removal of a specific order
        allOrders.setOnItemClickListener((adapterView, view, position, id) -> {
            //alert dialog
            alert.setMessage("Order complete!");
            AlertDialog confirmation = alert.create();
            confirmation.show();

            //get order
            ArrayList <Order> storeOrder = currentStoreOrder.getStoreOrders();
            Order removeOrder = storeOrder.get(position);

            //remove pizza from currentOrder
            currentStoreOrder.remove(removeOrder);
            //remove pizza from MainActivity
            MainActivity.storeOrder.remove(removeOrder);

            //update price
            updateStoreOrderPrice();

            //update Listviews
            updateOrders();

            //update ordersRemaining (ordersRemainingEditText)
            ordersLeft = "" + currentStoreOrder.getStoreOrders().size();
            ordersRemaining.setText(ordersLeft);
        });

        //lambda expression that initializes the return back to MainActivity
        backToMain.setOnClickListener(view -> {
            //returns to MainActivity
            Intent intent = new Intent(StoreOrdersActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }

    /**
     * This method is used to set default view for allOrders (placedOrdersListView).
     */
    private void updateOrders() {
        //clear
        ArrayAdapter <String> allOrdersAdapter;
        allOrders.setAdapter(null);

        //get orders from currentOrder
        ArrayList <Order> storeOrder = currentStoreOrder.getStoreOrders();
        //get order details from pizzas
        ArrayList <String> orderDetails = new ArrayList<>();
        for (Order order: storeOrder) {
            orderDetails.add(order.toString());
        }

        //set allOrders
        allOrdersAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, orderDetails);
        allOrders.setAdapter(allOrdersAdapter);
    }

    /**
     * This method is used to set default view for ordersTotal (totalEditText).
     */
    private void updateStoreOrderPrice() {
        //initialize ordersTotal (totalEditText)
        double total = calculateStoreOrderPrice();
        currentStoreOrder.setPrice(total); //initialize store order price
        storeOrderPrice = priceFormatter(total);
        ordersTotal.setText(storeOrderPrice);
    }

    /**
     * This method calculates running total of store order.
     * @return double cost
     */
    private double calculateStoreOrderPrice() {
        //get orders from currentStoreOrder
        ArrayList <Order> storeOrder = currentStoreOrder.getStoreOrders();
        //calculate cost from orders
        double cost = 0.0;
        for (Order order: storeOrder) {
            cost = cost + order.getPrice();
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
        currentStoreOrder.clearStoreOrders();
        ordersLeft = "0";
        storeOrderPrice = "$0.00";

        //update .xml variables
        allOrders.setAdapter(null);
        ordersRemaining.setText(ordersLeft);
        ordersTotal.setText(storeOrderPrice);

        //update MainActivity
        MainActivity.storeOrder.clearStoreOrders();

        //returns to MainActivity
        Intent intent = new Intent(StoreOrdersActivity.this, MainActivity.class);
        startActivity(intent);
    }
}