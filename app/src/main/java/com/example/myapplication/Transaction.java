package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Transaction extends AppCompatActivity {

    AutoCompleteTextView memberSearchTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        memberSearchTextView =   findViewById(R.id.member_search_autocomplete);

        DatabaseReference database;
        database = FirebaseDatabase.getInstance().getReference().child("Books");

        final ArrayAdapter<String> autoComplete = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1);

        database.child("AutoCompleteOptions").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot suggestionSnapshot : dataSnapshot.getChildren()){

                      String suggestion = suggestionSnapshot.child("name").getValue(String.class);

                    autoComplete.add(suggestion);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        AutoCompleteTextView ACTV= (AutoCompleteTextView)findViewById(R.id.member_search_autocomplete);
        ACTV.setAdapter(autoComplete);
    }


}

