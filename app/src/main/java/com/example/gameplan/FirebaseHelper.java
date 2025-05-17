package com.example.gameplan;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class FirebaseHelper {
    private DatabaseReference postsRef;
    private ValueEventListener postsListener;

    public FirebaseHelper() {
        postsRef = FirebaseDatabase.getInstance().getReference("posts");
    }

    public void retrievePosts(final OnPostsRetrievedListener listener) {
        postsListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Post> posts = new ArrayList<>();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Post post = postSnapshot.getValue(Post.class);
                    posts.add(post);
                }
                listener.onPostsRetrieved(posts);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                listener.onCancelled(databaseError.getMessage());
            }
        };

        postsRef.addValueEventListener(postsListener);
    }

    public void removePostsListener() {
        if (postsListener != null) {
            postsRef.removeEventListener(postsListener);
        }
    }

    public interface OnPostsRetrievedListener {
        void onPostsRetrieved(List<Post> posts);

        void onCancelled(String errorMessage);
    }
}
