package com.example.hotelfordogsandroid;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
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
    FirebaseFirestore db;
    List<Posts> posts;
    RecyclerView recyclerView;
    MaterialEditText cityText;
    MaterialEditText stateText;
    Button needSitterButton;
    Button sitterButton;
    String typeOfPost;
    TextView noDataText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);
        db = FirebaseFirestore.getInstance();
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
        typeOfPost = "needPosts";
    }

    public void sitterButton(View view) {
        sitterButton.setBackground(ContextCompat.getDrawable(this, R.drawable.rounded_button_black));
        needSitterButton.setBackground(ContextCompat.getDrawable(this,  R.drawable.roundedbutton));
        typeOfPost = "sitterPosts";
    }

    public void postButton(View view) {
        if (typeOfPost != null && !TextUtils.isEmpty(stateText.getText().toString()) && !TextUtils.isEmpty(cityText.getText().toString())) {
            // goto posts
        }

    }





}