package com.example.user_profile;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Declare your TextViews
    private TextView nameTextView, emailTextView, phoneTextView, addressTextView;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile); // Replace with your layout file

        // Initialize your TextViews
        nameTextView = findViewById(R.id.textView); // Replace with actual IDs
        emailTextView = findViewById(R.id.profileEmail);
        phoneTextView = findViewById(R.id.profilePhone);
        addressTextView = findViewById(R.id.textView5);

        // Initialize Firebase Database reference
        databaseReference = FirebaseDatabase.getInstance().getReference("user");

        // Fetch data from Firebase
        fetchUserData();
    }


    private void fetchUserData() {
        // Read data from Firebase
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Get data from snapshot

                String name = dataSnapshot.child("name").getValue(String.class);
                String email = dataSnapshot.child("email").getValue(String.class);
                String phone = dataSnapshot.child("phone").getValue(String.class);
                String address = dataSnapshot.child("address").getValue(String.class);

                // Set data to TextViews
                nameTextView.setText(name);
                emailTextView.setText(email);
                phoneTextView.setText(phone);
                addressTextView.setText(address);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
                System.out.println("Failed to read data.");
            }

        });
    }
}
