package bean.pwr.imskamieskiego;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import bean.pwr.imskamieskiego.NavigationWindow.NavWindowFragment;


public class MapActivity extends AppCompatActivity {

    Button button;
    Fragment navWindowFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        button = (Button) findViewById(R.id.button);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                navWindowFragment = fragmentManager.findFragmentById(R.id.fragment_container);




                if (navWindowFragment == null){
                    navWindowFragment = new NavWindowFragment();

                    fragmentTransaction
                            .setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right)
                            .add(R.id.fragment_container, navWindowFragment)
                            .addToBackStack(null)
                            .commit();
                }
            }
        });

    }






}