package com.jonathan.proyectofinal.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.fragments.games.Memorama;
import com.jonathan.proyectofinal.fragments.patient.HomeChildFragment;
import com.jonathan.proyectofinal.fragments.patient.HomePFragment;
import com.jonathan.proyectofinal.fragments.patient.MemorizamePFragment;
import com.jonathan.proyectofinal.fragments.patient.NotificationsPFragment;
import com.jonathan.proyectofinal.interfaces.IComunicateFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainPatient extends AppCompatActivity implements IComunicateFragment {

    private ViewPager viewPager;
    private BottomNavigationView navigation;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.home_patient:
                    viewPager.setCurrentItem(0);
                    return true;
                case R.id.memorizame_patient:
                    viewPager.setCurrentItem(1);
                    return true;
                case R.id.notifications_patient:
                    viewPager.setCurrentItem(2);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_patient);
        /*
        ButterKnife.bind(this);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.containerFramePatient, new HomePFragment()).commit();
        */
        viewPager = findViewById(R.id.view_pager);
        PatientFragmentPageAdapter adapter = new PatientFragmentPageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(adapter.getCount() - 1);
        navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    // METODO PROVISIONAL CON INTERFAZ PARA REDIRECCIONAR AL JUEGO
    @Override
    public void inicarJuego() {
        //Toast.makeText(this, "Iniciar juego desde actividad", Toast.LENGTH_SHORT).show();
        viewPager.setCurrentItem(3);
    }

    private static class PatientFragmentPageAdapter extends FragmentPagerAdapter {

        public PatientFragmentPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return HomePFragment.newInstance();
                case 1:
                    return MemorizamePFragment.newInstance();
                case 2:
                    return NotificationsPFragment.newInstance();
                    // OPCION PROVISIONAL PARA REDIRECCIONAR AL JUEGO
                case 3:
                    return Memorama.newInstance();
            }
            return null;
        }

        // CANTIDAD PROVISIONAL PARA REDIRECCIONAR AL JUEGO
        @Override
        public int getCount() {
            return 4;
        }
    }

    /*
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()){
                        case R.id.home_patient:
                            selectedFragment = new HomePFragment();
                            break;
                        case R.id.memorizame_patient:
                            selectedFragment = new MemorizamePFragment();
                            break;
                        case R.id.notifications_patient:
                            selectedFragment = new NotificationsPFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.containerFramePatient, selectedFragment).commit();
                    return true;
                }
            };
     */
}