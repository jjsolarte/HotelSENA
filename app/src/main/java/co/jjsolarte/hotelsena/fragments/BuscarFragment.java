package co.jjsolarte.hotelsena.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import co.jjsolarte.hotelsena.R;
import co.jjsolarte.hotelsena.interfaz.PostInterface;
import co.jjsolarte.hotelsena.model.Mensaje;
import co.jjsolarte.hotelsena.model.Post;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class BuscarFragment extends Fragment {

    List<String> listaPost;
    ListView listView;
    ArrayAdapter adapter;

    public BuscarFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_buscar, container, false);

        listaPost = new ArrayList<>();
        listView = view.findViewById(R.id.fgBuscarList);
        adapter = new ArrayAdapter(getContext()
                ,R.layout.support_simple_spinner_dropdown_item,listaPost);
        listView.setAdapter(adapter);
        //getPost(view);
        getMensajes();

        return view;
    }

    private void getMensajes() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.75.236.121:88/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PostInterface postInterface = retrofit.create(PostInterface.class);

        Call<Mensaje> call = postInterface.listarMensajes();

        call.enqueue(new Callback<Mensaje>() {
            @Override
            public void onResponse(Call<Mensaje> call, Response<Mensaje> response) {

            }

            @Override
            public void onFailure(Call<Mensaje> call, Throwable t) {

            }
        });

    }

    private void getPost(View v) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //GitHubService service = retrofit.create(GitHubService.class);
        PostInterface postInterface = retrofit.create(PostInterface.class);

        Call<List<Post>> call = postInterface.listarPost();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                for (Post post : response.body()){
                    listaPost.add(post.getTitle());
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Toast.makeText(getContext(), "Error de Conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
