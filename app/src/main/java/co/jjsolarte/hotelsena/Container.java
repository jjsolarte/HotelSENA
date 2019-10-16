package co.jjsolarte.hotelsena;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import co.jjsolarte.hotelsena.extras.onSwipeTouch;
import co.jjsolarte.hotelsena.fragments.BuscarFragment;
import co.jjsolarte.hotelsena.fragments.HistorialFragment;
import co.jjsolarte.hotelsena.fragments.OfertasFragment;

public class Container extends AppCompatActivity implements OfertasFragment.onTouchInterface{

    private FirebaseAuth myAuth;
    private FirebaseAuth.AuthStateListener myAuthListener;

    BottomNavigationView bottomNav;

    BuscarFragment buscarFragment;
    OfertasFragment ofertasFragment;
    HistorialFragment historialFragment;
    
    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        inicializer();
        /*
        myAuth = FirebaseAuth.getInstance();
                myAuth.signOut();
                finish();
                */
        getSupportFragmentManager().beginTransaction().replace(R.id.containerFrame,buscarFragment)
                .setTransition(FragmentTransaction.TRANSIT_ENTER_MASK).commit();
    }

    private void inicializer() {
        bottomNav = findViewById(R.id.containerNav);
        buscarFragment = new BuscarFragment();
        historialFragment = new HistorialFragment();
        ofertasFragment = new OfertasFragment();
        frameLayout = findViewById(R.id.containerFrame);

        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.menuNavBuscar:
                        getSupportFragmentManager().beginTransaction().replace(R.id.containerFrame,buscarFragment)
                                .setTransition(FragmentTransaction.TRANSIT_ENTER_MASK).commit();
                        menuItem.setChecked(true);
                        break;
                    case R.id.menuNavHistorial:
                        getSupportFragmentManager().beginTransaction().replace(R.id.containerFrame,historialFragment)
                                .setTransition(FragmentTransaction.TRANSIT_ENTER_MASK).commit();
                        menuItem.setChecked(true);
                        break;
                    case R.id.menuNavOfertas:
                        getSupportFragmentManager().beginTransaction().replace(R.id.containerFrame,ofertasFragment)
                                .setTransition(FragmentTransaction.TRANSIT_ENTER_MASK).commit();
                        menuItem.setChecked(true);
                        break;
                }
                return false;
            }
        });
        
        frameLayout.setOnTouchListener(new onSwipeTouch(this){
            @Override
            public void onSwipeRight() {
                super.onSwipeRight();
                //Toast.makeText(Container.this, "Derecha", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public void onSwipeEvent(int i) {
        Toast.makeText(this, ""+i, Toast.LENGTH_SHORT).show();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

}
