package com.example.gameplan;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView; // Import ImageView
import android.content.Intent;

public class ManageForum extends AppCompatActivity {
    ImageView logoutButton; // Change the type to ImageView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the logout button
        logoutButton = findViewById(R.id.logout);

        // Set OnClickListener for the logout button
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call logout function
                logout();
            }
        });
    }

    // Logout function
    private void logout() {
        Intent intent = new Intent(ManageForum.this, Login.class);
        startActivity(intent);
        finish(); // close the current activity
    }
}
