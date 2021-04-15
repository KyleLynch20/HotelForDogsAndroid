package com.example.hotelfordogsandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.rengwuxian.materialedittext.MaterialEditText;
import java.util.ArrayList;
import java.util.List;

public class ForumActivity extends AppCompatActivity {
    private FirebaseFirestore db;
    private List<Posts> posts;
    private RecyclerView recyclerView;
    private MaterialEditText cityText;
    private MaterialEditText stateText;
    private Button needSitterButton;
    private Button sitterButton;
    private String typeOfPost;
    private TextView noDataText;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);
        db = FirebaseFirestore.getInstance();
        email = getIntent().getStringExtra("email");
        recyclerView = findViewById(R.id.recycler_view);
        cityText = findViewById(R.id.city);
        stateText = findViewById(R.id.state);
        needSitterButton = findViewById(R.id.btn_need_sitter);
        sitterButton = findViewById(R.id.btn_sitter);
        noDataText = findViewById(R.id.no_data_txt);
        typeOfPost = null;
        posts = new ArrayList<>();
    }

    public void searchButton(View view) {
        if (typeOfPost != null && !TextUtils.isEmpty(stateText.getText().toString()) && !TextUtils.isEmpty(cityText.getText().toString())) {
            db.collection(typeOfPost)
                    .whereEqualTo("city", cityText.getText().toString().toLowerCase().trim())
                    .whereEqualTo("state", stateText.getText().toString().toLowerCase().trim())
                    .get()
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            posts = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if (typeOfPost.equals("needPosts")) {
                                    NeedPost needPost = new NeedPost(
                                            "Away time: " + document.getData().get("amountOfTime").toString(),
                                            "Amount Per Hour: " + document.getData().get("amountPerHour").toString(),
                                            "Is The Dog Animal Friendly: " + document.getData().get("animalFriendly").toString(),
                                            "City: " + document.getData().get("city").toString(),
                                            "State: " + document.getData().get("state").toString(),
                                            "Date: " + document.getData().get("date").toString(),
                                            "Dog Breed: " + document.getData().get("dogBreed").toString(),
                                            "Dog Name: " + document.getData().get("dogName").toString(),
                                            "Dog Needs / Bio: " + document.getData().get("dogNeeds").toString(),
                                            "Email: " + document.getData().get("email").toString(),
                                            "Full Name: " + document.getData().get("fullName").toString(),
                                            "Phone: " + document.getData().get("phone").toString(),
                                            "Is The Dog Potty Trained: " + document.getData().get("pottyTrained").toString(),
                                             document.getData().get("title").toString());
                                    posts.add(new Posts(1, needPost));
                                } else {
                                    SitterPost sitterPost = new SitterPost(
                                            "Amount Per Hour: " + document.getData().get("amountPerHour").toString(),
                                            "Bio: " + document.getData().get("bio").toString(),
                                            "Breed Size Willing To Watch: " + document.getData().get("breedSize").toString(),
                                            "City: " + document.getData().get("city").toString(),
                                            "State: " + document.getData().get("state").toString(),
                                            "Date: " + document.getData().get("date").toString(),
                                            "Email: " + document.getData().get("email").toString(),
                                            "Fenced In Back Yard: " + document.getData().get("fencedBackYard").toString(),
                                            "Full Name: " + document.getData().get("fullName").toString(),
                                            "Other Animals: " + document.getData().get("otherAnimals").toString(),
                                            "Phone: " + document.getData().get("phone").toString(),
                                             document.getData().get("title").toString());
                                    posts.add(new Posts(0, sitterPost));
                                }
                            }
                            if (!posts.isEmpty()) {
                                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                                recyclerView.setAdapter(new CustomAdapter(posts));
                                noDataText.setVisibility(View.INVISIBLE);
                            } else {
                                recyclerView.setLayoutManager(null);
                                recyclerView.setAdapter(null);
                                noDataText.setVisibility(View.VISIBLE);
                            }
                        } else {
                            Log.d("firestore", "Error getting documents: ", task.getException());
                        }
                    });
        }
    }

    public void needSitterButton(View view) {
        sitterButton.setBackground(ContextCompat.getDrawable(this, R.drawable.roundedbutton));
        needSitterButton.setBackground(ContextCompat.getDrawable(this, R.drawable.rounded_button_black));
        typeOfPost = "sitterPosts";
    }

    public void sitterButton(View view) {
        sitterButton.setBackground(ContextCompat.getDrawable(this, R.drawable.rounded_button_black));
        needSitterButton.setBackground(ContextCompat.getDrawable(this,  R.drawable.roundedbutton));
        typeOfPost = "needPosts";
    }

    public void postButton(View view) {
        if (typeOfPost != null && !TextUtils.isEmpty(stateText.getText().toString()) && !TextUtils.isEmpty(cityText.getText().toString())) {
            if (typeOfPost.equals("sitterPosts")) {
                Intent gotoNeedForumActivity = new Intent(this, NeedForumActivity.class);
                gotoNeedForumActivity.putExtra("email", email);
                gotoNeedForumActivity.putExtra("city", cityText.getText().toString());
                gotoNeedForumActivity.putExtra("state", stateText.getText().toString());
                this.startActivity(gotoNeedForumActivity);
            } else {
                Intent gotoSitterForumActivity = new Intent(this, SitterForumActivity.class);
                gotoSitterForumActivity.putExtra("email", email);
                gotoSitterForumActivity.putExtra("city", cityText.getText().toString());
                gotoSitterForumActivity.putExtra("state", stateText.getText().toString());
                this.startActivity(gotoSitterForumActivity);
            }
        }
    }
}