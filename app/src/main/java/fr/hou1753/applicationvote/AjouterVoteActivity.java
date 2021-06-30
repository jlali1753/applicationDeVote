package fr.hou1753.applicationvote;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AjouterVoteActivity extends AppCompatActivity {

    FirebaseDatabase databaseVote;
    DatabaseReference referenceVote;
    public Integer nb=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_vote);
        setTitle("Ajouter Vote");
        //databaseReference = FirebaseDatabase.getInstance().getReference("VoteList").child("123");
        databaseVote = FirebaseDatabase.getInstance(); //.getReference("artists");

        referenceVote = FirebaseDatabase.getInstance().getReference("vote");
        referenceVote.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                nb = (int) snapshot.getChildrenCount();
                //editTextResult.setText(count);
                setTitle("Ajouter Vote " + nb );
//                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                    Vote vote = dataSnapshot.getValue(Vote.class);
//                    assert vote != null;
//                   MainActivity.voteList.add(vote);
//                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void ajouterVote(View v){

        EditText editTextTextAjouterVote = (EditText) findViewById(R.id.editTextTextAjouterVote);
        if(editTextTextAjouterVote.getText().toString().trim().isEmpty()){

        }else{
            MainActivity.voteList.add(new Vote(editTextTextAjouterVote.getText().toString().trim()));
            ajouter(editTextTextAjouterVote.getText().toString().trim());
            finish();
        }
    }


    private void ajouter( String textVote) {
            Vote vote = new Vote( textVote, 0,0);
            referenceVote.child(nb+"").setValue(vote);
            Toast.makeText(this, "vote added", Toast.LENGTH_LONG).show();

    }

}