package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;

public class SellerActivity extends AppCompatActivity {
    //Variables for the program
    private TextView mTextViewResult;
    public static final String[] languages ={"Lang","Eng","Fre"};
    private RequestQueue mQueue;
    private boolean getButtonClicked = false;
    private FloatingActionButton  backButton;
    private Button search_button;
    private EditText search_bar;
    private CharSequence input;
    private String  output;
    Button newbutton;

    //Method that is called when activity is created
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller);
        mTextViewResult = findViewById(R.id.sellerTextView);
        newbutton = findViewById(R.id.button_show_sellers);
        mQueue = Volley.newRequestQueue(this);
        newbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonParse();
                getButtonClicked = true;
            }
        });

        //This assigns the ID of the back button to the variable
        backButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity();
            }
        });

        //This assigns the ID of the search button to the variable
        search_button = (Button) findViewById(R.id.search_button2);
        search_bar = (EditText) findViewById(R.id.search_bar2);
        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input = search_bar.getText();
                jsonSearchParse(input);
            }
        });

        ////This assigns the ID of the spinner to the spinner variable
        Spinner spinner = (Spinner) findViewById(R.id.spinner2);
        //This method allows me to set controls for when an item in the spinner is selected
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, languages);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(0);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedLang = adapterView.getItemAtPosition(i).toString();
                if (selectedLang.equals("Eng")) {
                    setLocal(SellerActivity.this, "en");
                    newbutton.setText("Show Sellers");
                    search_button.setText("Search");
                    search_bar.setHint("Search by Brand");
                } else if (selectedLang.equals("Fre")) {
                    setLocal(SellerActivity.this, "fr");
                    newbutton.setText("TEST");
                    newbutton.setText("exposition électronique");
                    search_button.setText("recherche");
                    search_bar.setHint("recherche par marque");
                } else {
                    Toast.makeText(SellerActivity.this, "Please select a Language", Toast.LENGTH_SHORT).show();

                }

            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    public  void setLocal(Activity activity, String langCode){
        Locale locale = new Locale(langCode);
        locale.setDefault(locale);

        Resources resources = activity.getResources();
        Configuration config = resources.getConfiguration();
        config.setLocale(locale);
        resources.updateConfiguration(config,resources.getDisplayMetrics());
    }

    public void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    //This method grabs the json as an array and converts and prints it out as an object
    private void jsonSearchParse(CharSequence in) {
        mTextViewResult.setText("");
        String url;
        final String[] searchWord = new String[1];
        searchWord[0] = String.valueOf(in);

        if(in != null){
            url = "https://ead2ca2api20220425024150.azurewebsites.net/api/Sellers/";
            JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {


                            try {
                                output="";


                                //Loop the Array
                                JSONObject seller;
                                if(searchWord[0].toLowerCase().contains("currys")) {


                                    for (int i = 0; i < 1; i++) {
                                        seller = response.getJSONObject(0);
                                        int ID = seller.getInt("id");
                                        String Name = seller.getString("name");
                                        String Location = seller.getString("location");
                                        int Rating = seller.getInt("rating");
                                        int ElectronicSold = seller.getInt("electronicSold");
                                        output=String.valueOf(ID) + ", " + Name + ", " + String.valueOf(Location) + ", " +
                                                String.valueOf(Rating)  + ", "  + String.valueOf(ElectronicSold) +"\n\n";
                                    }
                                }
                                else if(searchWord[0].toLowerCase().contains("D.I.D")) {

                                    for (int i = 0; i < 1; i++) {
                                        seller = response.getJSONObject(1);
                                        int ID = seller.getInt("id");
                                        String Name = seller.getString("name");
                                        String Location = seller.getString("location");
                                        int Rating = seller.getInt("rating");
                                        int ElectronicSold = seller.getInt("electronicSold");
                                        output=String.valueOf(ID) + ", " + Name + ", " + String.valueOf(Location) + ", " +
                                                String.valueOf(Rating)  + ", "  + String.valueOf(ElectronicSold) +"\n\n";
                                    }
                                }

                                else if(searchWord[0].toLowerCase().contains("harvey normans")) {

                                    for (int i = 0; i < 1; i++) {
                                        seller = response.getJSONObject(2);
                                        int ID = seller.getInt("id");
                                        String Name = seller.getString("name");
                                        String Location = seller.getString("location");
                                        int Rating = seller.getInt("rating");
                                        int ElectronicSold = seller.getInt("electronicSold");
                                        output=String.valueOf(ID) + ", " + Name + ", " + String.valueOf(Location) + ", " +
                                                String.valueOf(Rating)  + ", "  + String.valueOf(ElectronicSold) +"\n\n";
                                    }
                                }

                                else{
                                    mTextViewResult.setText("");
                                    output = "Sorry, search again : " + searchWord[0];
                                }
                                mTextViewResult.append(output);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                }
            });
            mQueue.add(request);
        }
        else{
            url = "https://ead2ca2api20220425024150.azurewebsites.net/api/Sellers/";
            JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {

                            try {
                                //Loop the Array
                                JSONObject seller;
                                for (int i = 0; i < response.length(); i++) {
                                    seller = response.getJSONObject(i);
                                    int ID = seller.getInt("id");
                                    String Name = seller.getString("name");
                                    String Location = seller.getString("location");
                                    int Rating = seller.getInt("rating");
                                    int ElectronicSold = seller.getInt("electronicSold");
                                    output=String.valueOf(ID) + ", " + Name + ", " + String.valueOf(Location) + ", " +
                                            String.valueOf(Rating)  + ", "  + String.valueOf(ElectronicSold) +"\n\n";

                                    mTextViewResult.append(output);

                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                }
            });
            mQueue.add(request);
        }


    }


    private void jsonParse() {
        mTextViewResult.setText("");
        String url = "https://ead2ca2api20220425024150.azurewebsites.net/api/Sellers";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {

                            //Loop the Array
                            JSONObject seller;
                            for (int i = 0; i < response.length(); i++) {
                                seller = response.getJSONObject(i);
                                int ID = seller.getInt("id");
                                String Name = seller.getString("name");
                                String Location = seller.getString("location");
                                int Rating = seller.getInt("rating");
                                int ElectronicSold = seller.getInt("electronicSold");

                                if(getButtonClicked=true){
                                    mTextViewResult.append(String.valueOf(ID) + ", " + Name + ", " + String.valueOf(Location) + ", " +
                                            String.valueOf(Rating)  + ", "  + String.valueOf(ElectronicSold) +"\n\n");
                                }

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(request);
    }
}
