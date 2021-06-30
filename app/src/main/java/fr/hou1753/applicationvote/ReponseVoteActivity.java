package fr.hou1753.applicationvote;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ReponseVoteActivity extends AppCompatActivity {

    public  int position;
    FirebaseDatabase databaseVote;
    DatabaseReference referenceVote;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reponse_vote);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_action_name);
        actionBar.setDisplayHomeAsUpEnabled(true);
        databaseVote = FirebaseDatabase.getInstance(); //.getReference("artists");
        referenceVote = FirebaseDatabase.getInstance().getReference("vote");

        Intent intent = getIntent();
        position = intent.getIntExtra("votePosition",0);

        TextView textViewTextVote = (TextView) findViewById(R.id.textViewTextVote);
        textViewTextVote.setText(MainActivity.voteList.get(position).getText());
    }

    public void onClickOui(View v){
        MainActivity.voteList.get(position).nombreOui+=1;
        referenceVote.child(position+"").child("nombreOui").setValue( MainActivity.voteList.get(position).nombreNon);

        MainActivity.adapter.notifyDataSetChanged();

        finish();
    }
    public void onClickNon(View v){
        MainActivity.voteList.get(position).nombreNon+=1;
        referenceVote.child(position+"").child("nombreNon").setValue( MainActivity.voteList.get(position).nombreNon);

        MainActivity.adapter.notifyDataSetChanged();

        finish();
    }
    @Override
    public boolean onOptionsItemSelected( MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
}