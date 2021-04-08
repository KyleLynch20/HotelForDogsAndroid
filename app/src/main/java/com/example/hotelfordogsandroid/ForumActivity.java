package com.example.hotelfordogsandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class ForumActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        List<Posts> posts = new ArrayList<>();

        NeedPost needPost1 = new NeedPost("50 hours", "50 dollars","yes","CHI TOWN","IL","4/7/2021","german shepard",
                "forest","Very good boy does not need much","Kylesportsfan@comcast.net","Kyle Lynch","708-724-8946","yes","Ima G");
        posts.add(new Posts(1, needPost1));

        SitterPost sitterPost1 = new SitterPost("50 dollars","I am the best mother fucking sitter boi","german shepard","CHI TOWN",
                "IL","4/7/2021","kylesportsfan@comcast.net","yes","Kyle Lynch","nah B","7087248946","I AM THE GREATEST SITTTTTER BOIII");
        posts.add(new Posts(0,sitterPost1));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new CustomAdapter(posts));
    }
}