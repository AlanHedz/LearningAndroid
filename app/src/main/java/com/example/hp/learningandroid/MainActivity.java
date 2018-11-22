package com.example.hp.learningandroid;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.hp.learningandroid.adapter.CourseAdapterRecyclerView;
import com.example.hp.learningandroid.models.Course;
import com.example.hp.learningandroid.services.CourseApiAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    CourseAdapterRecyclerView cAdapter;
    ProgressBar loadProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);


        RecyclerView coursesRecycler = findViewById(R.id.courseRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        coursesRecycler.setLayoutManager(linearLayoutManager);

        /*cAdapter = new CourseAdapterRecyclerView(R.layout.cardview_course, this);
        coursesRecycler.setAdapter(cAdapter);

        loadProgressBar =  findViewById(R.id.progressBar);
        loadProgressBar.setMax(100);
        loadProgressBar.setVisibility(View.VISIBLE);

        Call<ArrayList<Course>> call = CourseApiAdapter.getApiService().getCourses();
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<ArrayList<Course>> call, Response<ArrayList<Course>> response) {
        if (response.isSuccessful()) {
            loadProgressBar.setVisibility(View.GONE);
            ArrayList<Course> courses = response.body();
            cAdapter.setDataSet(courses);
            Log.d("onResponseCourses", "Tamano del arreglo de los cursos" + courses.size());
        }
    }

    @Override
    public void onFailure(Call<ArrayList<Course>> call, Throwable t) {
        loadProgressBar.setVisibility(View.GONE);
        call.cancel();
        Toast.makeText(MainActivity.this,
                "Por favor checa tu conexiona internet :(", Toast.LENGTH_LONG).show();
    }*/
    }
}
