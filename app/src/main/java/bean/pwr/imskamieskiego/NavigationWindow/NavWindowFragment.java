package bean.pwr.imskamieskiego.NavigationWindow;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;


import bean.pwr.imskamieskiego.R;


public class NavWindowFragment extends Fragment{

    private TextView textViewStart, textViewCel;
    private CheckBox checkStairs;
    private Boolean goDownTheStairs;
    private Button startButton;
    private Fragment searchFragment;
    private NavWindowListener navWindowListener;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.nav_window, container, false);

        textViewStart = (TextView) view.findViewById(R.id.textViewStart);
        textViewCel = (TextView) view.findViewById(R.id.textViewCel);
        checkStairs = (CheckBox) view.findViewById(R.id.checkStairs);
        startButton = (Button) view.findViewById(R.id.startButton);

        typeNav();
        startNavigation();

        return view;
    }


    @Override
    public void onPause() {
        super.onPause();
        navWindowListener.onBack();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof NavWindowListener) {
            navWindowListener = (NavWindowListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement NavWindowListener");
        }
    }

    public void typeNav(){
        textViewCel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchFragment = new SearchFragment();
                replaceFragment(searchFragment,true);
            }
        });

        textViewStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchFragment = new SearchFragment();
                replaceFragment(searchFragment,false);
            }
        });
    }

    public void startNavigation(){
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO Pass it to parent activity via listener
                //Things doing after click START
            }
        });
    }

    public void replaceFragment(Fragment nextFragment,Boolean logic){

        FragmentManager fragmentManager = getFragmentManager();

        Bundle bundle = new Bundle();
        bundle.putBoolean("Bool",logic);
        nextFragment.setArguments(bundle);

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction

                .replace(R.id.drawer_layout,nextFragment)
                .addToBackStack(null)
                .commit();

    }

    public Boolean getGoDownTheStairs() {
        return checkStairs.isChecked();
    }

    public void setGoDownTheStairs(Boolean goDownTheStairs) {
        checkStairs.setChecked(goDownTheStairs);
    }


}