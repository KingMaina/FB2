package com.example.user.fb2;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements ValueEventListener{
    private TextView headingtext;
    private EditText headinginput;
    private FirebaseDatabase
            firebaseDatabase=FirebaseDatabase.getInstance();
    private DatabaseReference mRootReference=firebaseDatabase.getReference();
    private DatabaseReference mHead = mRootReference.child("heading");
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        find items based on their id
        headingtext = findViewById(R.id.textView);
        headinginput = findViewById(R.id.editText);
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading now...");
    }
    public void submitHeading(View view){
        String heading=headinginput.getText().toString();
        mHead.setValue(heading);
        headinginput.setText("");
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        if (dataSnapshot.getValue(String.class) !=null){
            String key=dataSnapshot.getKey();
            if (key.equals("heading")){
                String heading=dataSnapshot.getValue(String.class);
                dialog.show();
                headingtext.setText(heading);
                dialog.dismiss();
            }
        }
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }
}
