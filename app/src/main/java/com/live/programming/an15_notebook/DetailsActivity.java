package com.live.programming.an15_notebook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.live.programming.an15_notebook.databinding.ActivityDetailsBinding;

public class DetailsActivity extends AppCompatActivity {

    String title, id, desc, d_t;
    ActivityDetailsBinding binding;
    Bundle obj;
    TextView t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

         t = binding.txtDateTime;

        /* get all the data that had been passed from previous screen */
        obj = getIntent().getBundleExtra("details");
        title = obj.getString("title");
        id = obj.getString("id");
        desc = obj.getString("desc");
        d_t = obj.getString("date_time");

        t.setText(d_t);
        binding.txtDesc.setText(desc);
        binding.txtNoteTitle.setText(title);


        // code to use a network image i.e. image url and put it in the image view using glide library

        Glide.with(this)
                .load("https://www.ardentcollaborations.com/assessment_public/production/ardent_logo.png")
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_background)
                .into(binding.circleImage);

    }
}