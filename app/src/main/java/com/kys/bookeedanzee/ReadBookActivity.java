package com.kys.bookeedanzee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import com.kys.bookeedanzee.databinding.ActivityReadBookBinding;

public class ReadBookActivity extends AppCompatActivity {

    ActivityReadBookBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityReadBookBinding.inflate((getLayoutInflater()));
        setContentView(binding.getRoot());
        String url = getIntent().getStringExtra("url");

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.parse(url), "application/pdf");
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        Intent newIntent = Intent.createChooser(intent, "Open File");
        try {
            startActivity(newIntent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "Error in opening file Download a pdf Viewer in ur device", Toast.LENGTH_SHORT).show();
        }
        startActivity(intent);

    }
}