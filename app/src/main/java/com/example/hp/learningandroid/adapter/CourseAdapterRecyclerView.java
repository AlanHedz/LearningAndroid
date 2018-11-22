package com.example.hp.learningandroid.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.hp.learningandroid.models.Review;
import com.example.hp.learningandroid.views.DetailCourse;
import com.example.hp.learningandroid.R;
import com.example.hp.learningandroid.models.Course;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CourseAdapterRecyclerView extends RecyclerView.Adapter<CourseAdapterRecyclerView.CourseViewHolder>  {

    List<Course> coursesList;
    Context context;

    public CourseAdapterRecyclerView(List<Course> coursesList, Context context) {
        this.coursesList = coursesList;
        this.context = context;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_course, parent, false);

        return new CourseViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        Course course = coursesList.get(position);
        int rating = 0;
        holder.nameCard.setText(course.getName());
        holder.descriptionCard.setText(course.getDescription());
        holder.courseTeacher.setText("Profesor: " + course.getTeacher().getUser().getName());
        holder.studentsCard.setText("Estudiantes inscritos: " + String.valueOf(course.getStudentsCount()));
        holder.ratingCourse.setNumStars(5);
        List<Review> reviews = course.getReviews();

        for (Review r : reviews) {
            rating = course.getReviews().get(position).getRating();
        }

        holder.ratingCourse.setRating(rating);

        Picasso.get().load("http://192.168.1.107:8000/storage/courses/" + course.getPicture())
                .into(holder.pictureCard);
        final int id = course.getId();
        holder.pictureCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailCourse.class);
                intent.putExtra("course_id", id);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return coursesList.size();
    }

    public class CourseViewHolder extends RecyclerView.ViewHolder {
        private ImageView pictureCard;
        private TextView nameCard, descriptionCard, studentsCard, courseTeacher, levelCard;
        private RatingBar ratingCourse;


        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);
            nameCard = (TextView) itemView.findViewById(R.id.nameCourse_card);
            descriptionCard = (TextView) itemView.findViewById(R.id.descriptionCourse_card);
            studentsCard = (TextView) itemView.findViewById(R.id.studentsCourse_card);
            pictureCard = (ImageView) itemView.findViewById(R.id.imageCourse_Card);
            courseTeacher = (TextView) itemView.findViewById(R.id.courseTeacher_card);
            ratingCourse = (RatingBar) itemView.findViewById(R.id.rateCourse_card);
        }
    }
}
