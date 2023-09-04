package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class Dashboard extends AppCompatActivity {

    FirebaseAuth mAuth;
    final DatabaseReference userRef = FirebaseDatabase.getInstance().getReference();
    TextView issued, Memberofmonthname, activeValue, solvedValue, MoMdescription,noBtnTV,yesBtnTV,questionTV;
    ImageView userImage;
    EditText reasonText;
    CardView transc, memberDirectoryCard, grievanceCard, contactUs, refer, baasCard, eventsCard, helpImg, postJob;
    Dialog exploreIeaContactDialog,reviewDialog,reasonDialog;
    DatabaseReference  solvedReference, unResolvedReference, rejectedReference;
    AppCompatButton memberNotificationIcon,sendBtn;
    long allsolvedValue, allProblemValue, allRejectedValue;
    RelativeLayout activeBar, activeVal;
    String userEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);


        mAuth = FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser()!=null){
            userEmail = Objects.requireNonNull(mAuth.getCurrentUser()).getEmail();
        }
        issued = findViewById(R.id.issued_value);
        transc = findViewById(R.id.transaction);

        transc.setOnClickListener(v-> startActivity(new Intent(this, Transaction.class)));

        assert userEmail != null;
        String userEmailConverted = userEmail.replaceAll("\\.", "%7");

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("user/" + userEmailConverted).child("books");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String no = Objects.requireNonNull(snapshot.child("no").getValue().toString());
                issued.setText(no);
                Log.d("testing", "onDataChange: "+no);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}