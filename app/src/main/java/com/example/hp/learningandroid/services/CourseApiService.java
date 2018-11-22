package com.example.hp.learningandroid.services;

import com.example.hp.learningandroid.models.Course;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CourseApiService {

    @GET("courses")
    Call<List<Course>> getCourses();

    @GET("courses/{id}")
    Call<Course> getCourse(@Path("id") int course_id);

}
