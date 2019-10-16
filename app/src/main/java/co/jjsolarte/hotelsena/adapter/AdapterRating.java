package co.jjsolarte.hotelsena.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

import co.jjsolarte.hotelsena.R;

public class AdapterRating extends RecyclerView.Adapter<AdapterRating.AdapterView>{

    private List<String> listado;
    private int resource;
    private Activity context;

    private OnClickItem myListener;

    public interface OnClickItem{
        void getItemPosition(int position, float stars);
    }

    public void setMyListener(OnClickItem myListener) {
        this.myListener = myListener;
    }

    public AdapterRating(List<String> listado, int resource, Activity context) {
        this.listado = listado;
        this.resource = resource;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(resource, viewGroup,false);
        return new AdapterView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterView adapterView, int i) {
        String preguntas = listado.get(i);
        adapterView.txtPregunta.setText(preguntas);
    }

    @Override
    public int getItemCount() {
        return listado.size();
    }

    public class AdapterView extends RecyclerView.ViewHolder{

        TextView txtPregunta;
        RatingBar ratingBar;

        public AdapterView(@NonNull View itemView) {
            super(itemView);
            txtPregunta = itemView.findViewById(R.id.itemPregunta);
            ratingBar = itemView.findViewById(R.id.itemRating);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (myListener!=null){
                        int positon = getLayoutPosition();
                        if (positon != RecyclerView.NO_POSITION){
                            myListener.getItemPosition(positon, ratingBar.getRating());
                        }
                    }
                }
            });
        }
    }
}
