package com.example.hp.learningandroid.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.hp.learningandroid.ContainerActivity;
import com.example.hp.learningandroid.MainActivity;
import com.example.hp.learningandroid.R;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        showToolbar("Crear cuenta", true);
    }

    public void showToolbar (String title, boolean upBotton) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_feo);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upBotton);
    }

    public void toHome (View view) {
        Intent intent = new Intent(this, ContainerActivity.class);
        startActivity(intent);
    }
}
