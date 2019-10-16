package co.jjsolarte.hotelsena.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import co.jjsolarte.hotelsena.R;
import co.jjsolarte.hotelsena.adapter.AdapterRating;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistorialFragment extends Fragment {

    View view;
    RecyclerView recyclerView;
    AdapterRating adapter;

    List<String> preguntas = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_historial, container, false);

        cargarPreguntas();
        recyclerView = view.findViewById(R.id.hitRecycler);
        adapter = new AdapterRating(preguntas,R.layout.item_rating, (Activity) view.getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        adapter.setMyListener(new AdapterRating.OnClickItem() {
            @Override
            public void getItemPosition(int position, float stars) {
                if (preguntas!=null){
                    Toast.makeText(view.getContext()
                            , ""+position+" >--< "+stars
                            , Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    private void cargarPreguntas() {
        preguntas.add("Preg 1");
        preguntas.add("Preg 2");
        preguntas.add("Preg 3");
        preguntas.add("Preg 4");
    }


}
