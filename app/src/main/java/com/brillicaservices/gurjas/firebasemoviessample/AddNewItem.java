package com.brillicaservices.gurjas.firebasemoviessample;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.brillicaservices.gurjas.firebasemoviessample.database.DatabaseHelper;
import com.brillicaservices.gurjas.firebasemoviessample.movies.MoviesModelView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class AddNewItem extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Integer imagesArray[] = new Integer[]{R.drawable.avengers_infinity_war,
            R.drawable.avengers_infinity_war_imax_poster,
            R.drawable.dark_knight,
            R.drawable.deadpool,
            R.drawable.fast_furious_7,
            R.drawable.fight_club,
            R.drawable.godfather,
            R.drawable.hancock,
            R.drawable.harry_potter,
            R.drawable.inception,
            R.drawable.into_the_wild,
            R.drawable.iron_man_3,
            R.drawable.pursuit_of_happiness,
            R.drawable.john_wick,
            R.drawable.lion_king,
            R.drawable.rocky,
            R.drawable.shawshank_redemption,
            R.drawable.wanted};
    String categories = "";
    EditText title, description, year;
    RatingBar rating;
    private DatabaseReference database;
    int selectImage =0 ;
    Spinner category, movieThumbnail;
    TextView save;
    DatabaseHelper databaseHelper;
    ArrayList<MoviesModelView> moviesModelViewArrayList = new ArrayList<>();
    String items[] = {"Select ", "MOVIE", "SERIES"};

    //    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_item);

        movieThumbnail = findViewById(R.id.item_image);
        category = findViewById(R.id.item_category);
        category.setOnItemSelectedListener(this);
        database = FirebaseDatabase.getInstance().getReference();
        title = (EditText) findViewById(R.id.item_title);
        description = findViewById(R.id.item_description);
        rating = findViewById(R.id.item_rating);
        year = findViewById(R.id.release_year);

        /*
        * Creating an arrayAdapter object and passing 3 different arguments
        * i.e. context, layout, array*/
        ArrayAdapter arrayAdapter1 = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items);

        /*
        * using the spinner's setAdapter method to update it's adapter*/
        category.setAdapter(arrayAdapter1);

        /*
        * setPrompt is select on spinner to just give the refernce that the
        * first object of array is only a label.*/
        category.setPrompt(items[0]);
        ArrayAdapter<Integer> movieThumbnailAdapter = new ArrayAdapter<Integer>(this,
                android.R.layout.simple_spinner_dropdown_item, imagesArray);
        movieThumbnail.setAdapter(movieThumbnailAdapter);

        movieThumbnail.setPrompt("Select Image");

        movieThumbnail.setOnItemSelectedListener(this);
        save = findViewById(R.id.save_item);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//

                if (category.getSelectedItem().toString().equals("MOVIE")) {
                    databaseHelper = new DatabaseHelper(getApplicationContext());
                    String name = title.getText().toString().trim();
                    String des = description.getText().toString().trim();
                    double ra = (double) rating.getRating();
                    int yea = Integer.parseInt(year.getText().toString().trim());
//                    moviesModelViewArrayList.addAll(databaseHelper.allStudentsDetails());
                    databaseHelper.addNew(new MoviesModelView(name,
                            des, yea, ra, selectImage));

                    Toast.makeText(getApplicationContext(),
                            "Student data saved successfully",
                            Toast.LENGTH_LONG).show();
                } else {


                    String name = title.getText().toString().trim();
                    String des = description.getText().toString().trim();
                    double ra = (double) rating.getRating();
                    int yea = Integer.parseInt(year.getText().toString().trim());
                    HashMap<String, Object> hashMap = new HashMap<String, Object>();
                    hashMap.put("title", name);
                    hashMap.put("description", des);
                    hashMap.put("releaseyear", yea);
                    hashMap.put("rating", ra);
                    Toast.makeText(AddNewItem.this, "stored", Toast.LENGTH_LONG);
                    database.push().setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(AddNewItem.this, "stored", Toast.LENGTH_LONG);
                            } else {
                                Toast.makeText(AddNewItem.this, "not stored", Toast.LENGTH_LONG);
                            }
                        }
                    });
                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        categories = items[position];
        selectImage = imagesArray[position];
//        String selectedClass = parent.getItemAtPosition(position).toString();
//        switch (selectedClass)
//        {
//            case "SERIES":
//                String name = title.getText().toString().trim();
//                String des = description.getText().toString().trim();
////                int ra = Integer.parseInt(rating.getText().toString().trim());
//                int yea = Integer.parseInt(year.getText().toString().trim());
//                HashMap<String, Object> hashMap = new HashMap<String, Object>();
//                hashMap.put("title", name);
//                hashMap.put("description", des);
//                hashMap.put("releaseyear", yea);
//                break;
//
//            case "Class 2":
//
//                break;
//
//
//        }
//
//        //set divSpinner Visibility to Visible
//        divSpinner.setVisibility(View.VISIBLE);
//    }
//
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
