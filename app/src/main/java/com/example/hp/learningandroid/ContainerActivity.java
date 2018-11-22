package com.example.hp.learningandroid;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;

import com.example.hp.learningandroid.views.fragments.HomeFragment;
import com.example.hp.learningandroid.views.fragments.ProfileFragment;
import com.example.hp.learningandroid.views.fragments.SearchFragment;

public class ContainerActivity extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, HomeFragment.newInstance());
        fragmentTransaction.commit();

        showToolbar("Home", false);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.bottom_home);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment fragment = null;
                    switch (menuItem.getItemId()) {
                        case R.id.bottom_home:
                            fragment = HomeFragment.newInstance();
                            showToolbar("Home", false);
                            break;
                        case R.id.bottom_profile:
                            fragment = ProfileFragment.newInstance();
                            showToolbar("Perfil", false);
                            break;
                        case R.id.bottom_search:
                            fragment = SearchFragment.newInstance();
                            showToolbar("Buscar", false);
                            break;
                    }
                    if (fragment != null) {
                        FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                                .beginTransaction().replace(R.id.frame_layout, fragment);
                        fragmentTransaction.commit();
                    }
                    return true;
                }
            };
    public void showToolbar (String title, boolean upBotton) {
        toolbar = (Toolbar) findViewById(R.id.default_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upBotton);
    }
}
