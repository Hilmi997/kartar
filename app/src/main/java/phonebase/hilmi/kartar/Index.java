package phonebase.hilmi.kartar;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import phonebase.hilmi.kartar.view.FragmentKegiatan;
import phonebase.hilmi.kartar.view.ScreenKegiatan2;

public class Index extends AppCompatActivity {



    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);


        //home pertama
        FragmentManager fm1 = getSupportFragmentManager();
        fm1.beginTransaction().replace(R.id.bn_main, new FragmentKegiatan()).addToBackStack("").commit();


        BottomNavigationView bnv = (BottomNavigationView) findViewById(R.id.bn_main);
        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_home:
                        FragmentManager fm1 = getSupportFragmentManager();
                        fm1.beginTransaction().replace(R.id.fl_container, new FragmentKegiatan()).addToBackStack("").commit();
                        break;

                    case R.id.action_profile:
                        FragmentManager fm2 = getSupportFragmentManager();
                        fm2.beginTransaction().replace(R.id.fl_container, new ScreenKegiatan2()).commit();
                        break;


                }
                return true;
            }
        });
    }
}