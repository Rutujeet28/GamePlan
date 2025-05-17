package com.example.gameplan;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;

public class CommunityForum extends AppCompatActivity {

    private TextView timeLabel;
    private FirebaseHelper firebaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_forum);

        // Initialize TextView
        timeLabel = findViewById(R.id.timeLabel);

        // Call function to update time
        updateTime("New Time");

        // Find the Home button by its ID
        Button homeButton = findViewById(R.id.home_button);

        // Set OnClickListener to the Home button
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start the main activity
                Intent intent = new Intent(CommunityForum.this, MainActivity.class);
                // Clear the activity stack so that the main activity becomes the new root
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                // Start the main activity
                startActivity(intent);
                // Finish the current activity
                finish();
            }
        });

        // Initialize FirebaseHelper
        firebaseHelper = new FirebaseHelper();

        // Retrieve posts from Firebase
        firebaseHelper.retrievePosts(new FirebaseHelper.OnPostsRetrievedListener() {
            @Override
            public void onPostsRetrieved(List<Post> posts) {
                // Handle retrieved posts
                for (Post post : posts) {
                    // Do something with each post
                    // For example, display post data in a RecyclerView
                }
            }

            @Override
            public void onCancelled(String errorMessage) {
                // Handle database error
                Toast.makeText(CommunityForum.this, errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Function to update the time in the TextView
    private void updateTime(String newTime) {
        timeLabel.setText(newTime);
    }
}
