package com.brillicaservices.gurjas.firebasemoviessample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.sql.Array;
import java.util.Collections;

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

    Spinner category, movieThumbnail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_item);

        movieThumbnail = findViewById(R.id.item_image);
        ArrayAdapter<Integer> movieThumbnailAdapter = new ArrayAdapter<Integer>(this,
                android.R.layout.simple_spinner_dropdown_item, imagesArray);
        movieThumbnail.setAdapter(movieThumbnailAdapter);

        movieThumbnail.setPrompt("Select Image");

        movieThumbnail.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
