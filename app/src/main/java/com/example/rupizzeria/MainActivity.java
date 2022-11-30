package com.example.rupizzeria;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.button.MaterialButton;

/**
 * MainActivity: Activity that controls activity_main.xml.
 * This is the main controller for the application.
 * @author JasmeanFernando
 */
public class MainActivity extends AppCompatActivity {
    //initialize activity variables
    public static Order orderOfChicagoPizzas = new Order();
    public static Order orderOfNewYorkPizzas = new Order();
    public static StoreOrders storeOrder = new StoreOrders();

    /**
     * This method runs MainActivity.
     * @param savedInstanceState Used to reload UI state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //load .xml

        //initialize .xml variables
        MaterialButton chicagoButton = findViewById(R.id.chicagoStyle);
        MaterialButton newYorkButton = findViewById(R.id.nyStyle);
        MaterialButton currentOrderButton = findViewById(R.id.currOrder);
        MaterialButton storeOrderButton = findViewById(R.id.stOrder);

        //initialize ChicagoStyleActivity
        chicagoButton.setOnClickListener(view -> {
            Intent makeChicagoPizza = new Intent(MainActivity.this, ChicagoStyleActivity.class);
            startActivity(makeChicagoPizza);
        });

        //initialize NYStyleActivity
        newYorkButton.setOnClickListener(view -> {
            Intent makeChicagoPizza = new Intent(MainActivity.this, NYStyleActivity.class);
            startActivity(makeChicagoPizza);
        });

        //initialize CurrentOrderActivity
        currentOrderButton.setOnClickListener(view -> {
            Intent checkCurrentOrder = new Intent(MainActivity.this, CurrentOrderActivity.class);
            startActivity(checkCurrentOrder);
        });

        //initialize StoreOrdersActivity
        storeOrderButton.setOnClickListener(view -> {
            Intent checkStoreOrders = new Intent(MainActivity.this, StoreOrdersActivity.class);
            startActivity(checkStoreOrders);
        });
    }
}