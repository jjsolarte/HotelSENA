package co.jjsolarte.hotelsena.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import co.jjsolarte.hotelsena.R;
import co.jjsolarte.hotelsena.extras.onSwipeTouch;

/**
 * A simple {@link Fragment} subclass.
 */
public class OfertasFragment extends Fragment {

    EditText edtNombre;

    onTouchInterface onTouchInterface;

    public OfertasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ofertas, container, false);
        inicializer(view);
        return view;
    }

    private void inicializer(final View view) {
        edtNombre = view.findViewById(R.id.frgOfeEdtNombre);
        onTouchInterface = (OfertasFragment.onTouchInterface) view.getContext();

        edtNombre.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                edtNombre.setFocusable(true);
                edtNombre.setClickable(true);
                edtNombre.selectAll();
                edtNombre.requestFocus();
                edtNombre.setSelection(edtNombre.getText().length());
                onTouchInterface.onSwipeEvent(1);
                if(event.getAction() == MotionEvent.ACTION_MOVE){
                    Toast.makeText(view.getContext(), "si si si", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });

    }

    public interface onTouchInterface{
        public void onSwipeEvent(int i);
    }

}
