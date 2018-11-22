package com.example.hp.learningandroid.views;

import android.content.Intent;
import android.media.Image;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.learningandroid.R;
import com.example.hp.learningandroid.models.Course;
import com.example.hp.learningandroid.models.Review;
import com.example.hp.learningandroid.services.CourseApiAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailCourse extends AppCompatActivity {
    private int course_id;
    ProgressBar mProgressBar;
    ImageView courseImage;
    RatingBar courseRatingBar;
    TextView courseName, courseDescription, courseInfo, courseTeacherName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_course);
        showToolbar("", true);
        courseImage = (ImageView) findViewById(R.id.courseImage);
        courseName = (TextView) findViewById(R.id.courseName);
        courseDescription = (TextView) findViewById(R.id.courseDescription);
        courseInfo = (TextView) findViewById(R.id.coursInfo);
        courseTeacherName = (TextView) findViewById(R.id.courseTechaerName);
        courseRatingBar = (RatingBar) findViewById(R.id.courseRateBar);

        courseRatingBar.setNumStars(5);

        Intent intent = getIntent();
        course_id = intent.getIntExtra("course_id", 1);

        courseInfo.setVisibility(View.INVISIBLE);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mProgressBar.setMax(100);
        mProgressBar.setVisibility(View.VISIBLE);

        Call<Course> call = CourseApiAdapter.getApiService().getCourse(course_id);
        call.enqueue(new Callback<Course>() {
            @Override
            public void onResponse(Call<Course> call, Response<Course> response) {
                mProgressBar.setVisibility(View.GONE);
                courseInfo.setVisibility(View.VISIBLE);
                Course course = response.body();
                List<Review> reviews = course.getReviews();
                for (Review r : reviews) {
                    courseRatingBar.setRating(r.getRating());
                }

                courseTeacherName.setText("Profesor: " + course.getTeacher().getUser().getName());

                courseName.setText(course.getName());
                courseDescription.setText(course.getDescription());
                Picasso.get().load("http://192.168.1.107:8000/storage/courses/" + course.getPicture())
                        .into(courseImage);

            }

            @Override
            public void onFailure(Call<Course> call, Throwable t) {
                mProgressBar.setVisibility(View.GONE);
                call.cancel();
                Toast.makeText(DetailCourse.this,
                        "Por favor checa tu conexiona internet :(", Toast.LENGTH_LONG).show();
            }
        });
    }

        public void showToolbar (String title, boolean upBotton) {
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle(title);
            getSupportActionBar().setDisplayHomeAsUpEnabled(upBotton);
            CollapsingToolbarLayout collapsingToolbarLayout =
                    (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbar);
        }
}
