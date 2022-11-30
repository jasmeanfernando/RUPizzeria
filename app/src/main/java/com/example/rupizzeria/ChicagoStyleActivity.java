package com.example.rupizzeria;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * ChicagoStyleActivity: Activity that controls activity_chicago_style.xml.
 * @author JasmeanFernando
 */
public class ChicagoStyleActivity extends AppCompatActivity {
    private ImageView pizzaImage;
    private ListView allToppings, currentToppings;
    private EditText currentPrice;

    //activity variables
    private final ChicagoPizza styledPizza = new ChicagoPizza();
    private Pizza flavoredPizza;
    private String pizzaFlavor, pizzaSize, pizzaPrice;

    /**
     * This method runs ChicagoStyleActivity.
     * @param savedInstanceState Used to reload UI state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chicago_style); //load .xml
        AlertDialog.Builder alert = new AlertDialog.Builder(this); //alerts
        buildAlert(alert);

        //initialize .xml variables
        Spinner allFlavors = findViewById(R.id.flavorSpinner);
        Spinner allSizes = findViewById(R.id.sizeSpinner);
        pizzaImage = findViewById(R.id.imageImageView);

        allToppings = findViewById(R.id.allToppingsListView);
        allToppings.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);

        currentToppings = findViewById(R.id.currentToppingsListView);
        currentToppings.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);

        currentPrice = findViewById(R.id.priceEditText);

        Button placePizza = findViewById(R.id.placePizzaButton);
        Button backToMain = findViewById(R.id.mainButton);

        //initialize activity variables
        defaultPizza();

        //inner class that initializes allFlavors (flavorSpinner)
        ArrayAdapter <CharSequence> flavorAdapter = ArrayAdapter.createFromResource(this, R.array.flavors, android.R.layout.simple_spinner_item);
        flavorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        allFlavors.setAdapter(flavorAdapter);
        allFlavors.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pizzaFlavor = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), pizzaFlavor, Toast.LENGTH_SHORT).show();

                //initialize pizzaImage (imageImageView)
                //set flavor and set crust
                if (pizzaFlavor.equalsIgnoreCase("Deluxe")) {
                    pizzaImage.setImageResource(R.drawable.deluxe);

                    flavoredPizza = styledPizza.createDeluxe();
                    flavoredPizza.setCrust("DeepDish");
                }
                else if (pizzaFlavor.equalsIgnoreCase("Meatzza")) {
                    pizzaImage.setImageResource(R.drawable.meatzza);

                    flavoredPizza = styledPizza.createMeatzza();
                    flavoredPizza.setCrust("Stuffed");
                }
                else if (pizzaFlavor.equalsIgnoreCase("BBQChicken")) {
                    pizzaImage.setImageResource(R.drawable.bbqchicken);

                    flavoredPizza = styledPizza.createBBQChicken();
                    flavoredPizza.setCrust("Pan");
                }
                else {
                    pizzaImage.setImageResource(R.drawable.buildyourown);

                    flavoredPizza = styledPizza.createBuildYourOwn();
                    flavoredPizza.setCrust("Pan");
                }

                //set default toppings
                flavoredPizza.setDefaultToppings(pizzaFlavor);
                //initialize allToppings (allToppingsListView) and currentToppings (currentToppingsListView)
                updateToppings();

                //set default size
                pizzaSize = allSizes.getSelectedItem().toString();
                flavoredPizza.setSize(pizzaSize);

                //initialize default currentPrice (priceEditText)
                pizzaPrice = priceFormatter(flavoredPizza.price());
                currentPrice.setText(pizzaPrice);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //inner class that initializes allSizes (sizeSpinner)
        ArrayAdapter <CharSequence> sizeAdapter = ArrayAdapter.createFromResource(this, R.array.sizes, android.R.layout.simple_spinner_item);
        sizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        allSizes.setAdapter(sizeAdapter);
        allSizes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pizzaSize = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), pizzaSize, Toast.LENGTH_SHORT).show();

                //set size
                flavoredPizza.setSize(pizzaSize);

                //initialize currentPrice (priceEditText)
                pizzaPrice = priceFormatter(flavoredPizza.price());
                currentPrice.setText(pizzaPrice);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //lambda expression that initializes the addition of toppings
        allToppings.setOnItemClickListener((adapterView, view, position, id) -> {
            Topping item = (Topping) allToppings.getItemAtPosition(position);

            //update flavoredPizza
            flavoredPizza.add(item);

            //update price
            pizzaPrice = priceFormatter(flavoredPizza.price());
            currentPrice.setText(pizzaPrice);

            //update toppings
            updateToppings();
        });

        //lambda expression that initializes the removal of toppings
        currentToppings.setOnItemClickListener((adapterView, view, position, id) -> {
            //get topping
            Topping item = (Topping) currentToppings.getItemAtPosition(position);

            //remove topping from flavoredPizza
            boolean isRemoved = flavoredPizza.remove(item);
            if (!isRemoved) {
                //alert dialog
                alert.setMessage("Must have at least 1 topping!");
                AlertDialog error = alert.create();
                error.show();
            }

            //update price
            pizzaPrice = priceFormatter(flavoredPizza.price());
            currentPrice.setText(pizzaPrice);

            //update toppings
            updateToppings();
        });

        //lambda expression that initializes the making of a Chicago pizza > placePizza (placePizzaButton)
        placePizza.setOnClickListener(view -> {
            //alert dialog
            alert.setMessage("Successfully added to order!");
            AlertDialog confirmation = alert.create();
            confirmation.show();

            //pass flavoredPizza to MainActivity
            MainActivity.orderOfChicagoPizzas.add(flavoredPizza);

            //clear activity
            clearActivity();
        });

        //lambda expression that initializes the return back to MainActivity
        backToMain.setOnClickListener(view -> {
            //returns to MainActivity
            Intent intent = new Intent(ChicagoStyleActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }

    /**
     * This method creates a default Deluxe pizza.
     */
    private void defaultPizza() {
        flavoredPizza = styledPizza.createDeluxe();
        flavoredPizza.setDefaultToppings("Deluxe");
        flavoredPizza.setCrust("DeepDish");
        flavoredPizza.setSize("Small");
        pizzaPrice = priceFormatter(flavoredPizza.price());
    }

    /**
     * This method is used to set default view for allToppings (allToppingsListView) and
     * currentToppings (currentToppingsListView).
     */
    private void updateToppings() {
        //clear
        ArrayAdapter <Topping> allToppingsAdapter;
        ArrayAdapter <Topping> currentToppingsAdapter;
        allToppings.setAdapter(null);
        currentToppings.setAdapter(null);

        //get default toppings and all available toppings
        ArrayList <Topping> usedToppings = flavoredPizza.getToppings();
        ArrayList <Topping> availableToppings = new ArrayList<>(Arrays.asList(Topping.values()));

        //set currentToppings
        currentToppingsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, usedToppings);
        currentToppings.setAdapter(currentToppingsAdapter);

        //set allToppings
        availableToppings.removeAll(usedToppings);
        allToppingsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, availableToppings);
        allToppings.setAdapter(allToppingsAdapter);
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
     * @param alert
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
        flavoredPizza = null;
        pizzaFlavor = "";
        pizzaSize = "";
        pizzaPrice = "$0.00";

        //update .xml variables
        allToppings.setAdapter(null);
        currentToppings.setAdapter(null);
        currentPrice.setText(pizzaPrice);

        //returns to ChicagoStyleActivity
        Intent intent = new Intent(ChicagoStyleActivity.this, ChicagoStyleActivity.class);
        startActivity(intent);
    }
}