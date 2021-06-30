package fr.hou1753.applicationvote;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase databaseVote;
    DatabaseReference referenceVote;
    public Integer nb=0;

   public static List<Vote> voteList=new ArrayList<>();
    public static WordListAdapter   adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseVote = FirebaseDatabase.getInstance(); //.getReference("artists");


        RecyclerView recyclerView = findViewById(R.id.RV_listVote);
        adapter = new WordListAdapter(this, voteList);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager l = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(l);
        adapter.notifyDataSetChanged();
        referenceVote = FirebaseDatabase.getInstance().getReference("vote");
        referenceVote.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                nb = (int) snapshot.getChildrenCount();
                //editTextResult.setText(count);
                MainActivity.voteList.clear();

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Vote vote = new Vote(dataSnapshot.child("text").getValue().toString(),dataSnapshot.child("nombreOui").getValue().toString(),dataSnapshot.child("nombreNon").getValue().toString());
                    MainActivity.voteList.add(vote);
                    adapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        return true;
    }
    @Override
    public boolean onOptionsItemSelected( MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_addVote:
                Intent intent = new Intent(MainActivity.this, AjouterVoteActivity.class);
               // AffichagePage.putExtra("idSession", idSession);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}