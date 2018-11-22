package com.example.hp.learningandroid.views.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.hp.learningandroid.ContainerActivity;
import com.example.hp.learningandroid.MainActivity;
import com.example.hp.learningandroid.R;
import com.example.hp.learningandroid.adapter.CourseAdapterRecyclerView;
import com.example.hp.learningandroid.models.Course;
import com.example.hp.learningandroid.services.CourseApiAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment{
    CourseAdapterRecyclerView cAdapter;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    List<Course> courseList = new ArrayList<Course>();
    private OnFragmentInteractionListener listener;

    public  HomeFragment () {
        // Required empty public constructor
    }

    public static HomeFragment newInstance () {
        return new HomeFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.courseRecyclerView);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);


        cAdapter = new CourseAdapterRecyclerView(courseList, getActivity());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));

        recyclerView.setAdapter(cAdapter);
        progressBar.setMax(100);
        progressBar.setVisibility(View.VISIBLE);
        getCourseList();
        setHasOptionsMenu(true);
        return view;
    }



    public void getCourseList () {
        Call<List<Course>> call = CourseApiAdapter.getApiService().getCourses();
        call.enqueue(new Callback<List<Course>>() {
            @Override
            public void onResponse(Call<List<Course>> call, Response<List<Course>> response) {
                progressBar.setVisibility(View.GONE);
                if (response == null) {
                    showToast("Ha sucedido un error, intentelo de nuevo.");
                } else {
                    for (Course course : response.body()) {
                        courseList.add(course);
                    }
                    Log.i("RESPONSE: ", ""+response.toString());
                }
                cAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Course>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                call.cancel();
                showToast("Por favor cehca tu conexion a internet");
            }
        });
    }

    public void showToast (String msg) {
        if (HomeFragment.this.isVisible() && msg != null & getActivity() != null) {
            Toast.makeText(getActivity(),
                    msg, Toast.LENGTH_LONG).show();
        }
    }

    public interface OnFragmentInteractionListener {
    }
}
