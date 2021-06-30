package fr.hou1753.applicationvote;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;
import java.util.List;

public class WordListAdapter extends
        RecyclerView.Adapter<WordListAdapter.WordViewHolder> {

    //Préparer la structure qui contiendra les éléments de notre liste
    private final List<Vote> voteList;
    private final Context context;
    //private final LayoutInflater mInflater;
public static  int mPosition;

    class WordViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        public final TextView textViewTextVote;
        public final TextView textViewPosition;
        public final ProgressBar progressBarOui;
        public final ProgressBar progressBarNon;
        public final TextView textViewValOui;
        public final TextView textViewValNon;
        final WordListAdapter mAdapter;


        public WordViewHolder(View itemView, WordListAdapter adapter) {
            super(itemView);
            textViewTextVote = itemView.findViewById(R.id.textViewTextVote);
            textViewPosition = itemView.findViewById(R.id.textViewPosition);
            progressBarOui=itemView.findViewById(R.id.progressBarOui);
            progressBarNon=itemView.findViewById(R.id.progressBarNon);
            textViewValOui = itemView.findViewById(R.id.textViewValOui);
            textViewValNon = itemView.findViewById(R.id.textViewValNon);
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int mPosition = getLayoutPosition();
            Intent intent = new Intent(context, ReponseVoteActivity.class);
            intent.putExtra("votePosition", mPosition);
            context.startActivity(intent);

        }
    }

    public WordListAdapter(Context context, List<Vote> voteList) {
        this.context=context;
        this.voteList = voteList;
        //mInflater  = LayoutInflater.from(context);

    }

    //Appelée au moment de la création du ViewHolder qui affichera
    //les éléments chargés à partir de l'Adapter
    @Override
    public WordListAdapter.WordViewHolder onCreateViewHolder(ViewGroup parent,
                                                             int viewType) {
        // Inflater un view avec le layout déjà créé
        View mItemView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_vote, parent, false);
        return new WordViewHolder(mItemView, this);
    }

    //Elle est appelé à chaque fois qu'une vue doit être chargé.
    //On récupére alors la position du nouvel élément à afficher
    //et on le charge dans le TextView
    @Override
    public void onBindViewHolder(WordListAdapter.WordViewHolder holder,
                                 int position) {
        holder.textViewTextVote.setText(voteList.get(position).getText());
        holder.textViewPosition.setText(position + 1 + "");
        int valOui = voteList.get(position).getNombreOui();
        int valNon = voteList.get(position).getNombreNon();
        int somme = valOui + valNon;
        holder.progressBarOui.setMax(somme);
        holder.progressBarNon.setMax(somme);
        holder.progressBarOui.setProgress(valOui);
        holder.progressBarNon.setProgress(valNon);
        holder.textViewValOui.setText(valOui+"  ");
        holder.textViewValNon.setText(valNon+"  ");


    }

    //Retourne le nombre d'éléments de notre liste
    @Override
    public int getItemCount() {
        return voteList.size();
    }
}