package com.example.gameplan;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.gameplan.Post;
import android.content.Intent;
import android.graphics.Bitmap; // Add import statement for Bitmap
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddPost extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 1;

    private EditText editTextPost;
    private ImageView cameraIcon;
    private Button uploadPostButton;
    private ImageView imageView; // Display captured image

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);

        editTextPost = findViewById(R.id.editTextPost);
        cameraIcon = findViewById(R.id.cameraIcon);
        uploadPostButton = findViewById(R.id.uploadPostButton);
        imageView = findViewById(R.id.imageView);

        cameraIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });

        uploadPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadPostToDatabase();
            }
        });
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            if (extras != null) {
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                imageView.setImageBitmap(imageBitmap);
            }
        }
    }

    private void uploadPostToDatabase() {
        String postText = editTextPost.getText().toString().trim();
        if (!postText.isEmpty()) {
            // Save post text and image URI to Firebase Realtime Database
            DatabaseReference postsRef = FirebaseDatabase.getInstance().getReference("posts");
            String postId = postsRef.push().getKey();
            String userId = FirebaseAuth.getInstance().getCurrentUser().getUid(); // Assuming user is logged in
            String imageUrl = ""; // Placeholder for image URL, you need to upload image to storage and get URL
            Post post = new Post(postId, userId, postText, imageUrl);
            postsRef.child(postId).setValue(post);
            Toast.makeText(this, "Post uploaded successfully", Toast.LENGTH_SHORT).show();
            finish(); // Finish the activity after uploading post
        } else {
            Toast.makeText(this, "Please enter a post", Toast.LENGTH_SHORT).show();
        }
    }
}
